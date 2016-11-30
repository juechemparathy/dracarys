package com.yahoo.dracarys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.yahoo.dracarys.R;
import com.yahoo.dracarys.helpers.AmazonFetcher;
import com.yahoo.dracarys.helpers.VolleySingleton;

import java.util.Map;

public class AddActivity extends ActionBarActivity{
    private Toolbar toolbar;
    private final int REQUEST_CODE = 20;
    EditText etProductCode;
    ImageView ivScannner;
    Map<String,String> productResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        productResult=null;

        toolbar =  (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etProductCode = (EditText)findViewById(R.id.et_add_isbn);
        ivScannner =(ImageView)findViewById(R.id.iv_add_isbn_scan);
    }

    public void onScannerRequest(View view) {
        //Invoke scanner activity

        Intent i = new Intent(AddActivity.this, ScannerActivity.class);
        //i.putExtra("mode", 2); // pass arbitrary data to launched activity
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String code = data.getExtras().getString("itemcode");
            String format = data.getExtras().getString("format");
            // Toast the name to display temporarily on screen
            Toast.makeText(this,"Scanner: "+ code, Toast.LENGTH_SHORT).show();
            //Get the ISBN result
            createLockerItem(code);
        }
    }

    private void createLockerItem(String code){
        if(code == null  ||  code.length()==0){
            return;
        }
        //Fetch ean details
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        String url = "http://theagiledirector.com/getRest_v3.php?isbn="+code;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("DEBUG", "Calling AmazonFetcher");
                productResult = AmazonFetcher.parseXMLInput(response);
//                ParseObject lockerItem = new ParseObject("Locker");
//                lockerItem.put("title", productResult.get("title"));
//                lockerItem.put("ean", productResult.get("ean"));
//                lockerItem.put("state", 1);
//                lockerItem.put("displaystate", 1);
//                lockerItem.put("smallimageurl", productResult.get("smallimageurl"));
//                lockerItem.put("author", productResult.get("author"));
//                lockerItem.put("userPointer",ParseUser.getCurrentUser());
//
//
//
//                //Set the default flags
//
//                //Honor the user preferences
//
//                //Add to the locker
//                lockerItem.saveInBackground();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("DEBUG",error.getMessage());
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
    }
    public void onManualRequest(View view) {
        //Invoke manual enrty activity
        String code =  etProductCode.getText().toString();
        if(code==null || code.trim().length()==0){
            Toast.makeText(this, "Enter a valid ISBN code"+ code, Toast.LENGTH_SHORT).show();
        }else{
            createLockerItem(code);
            Toast.makeText(this, "Added to your locker"+ code, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
