package com.yahoo.dracarys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yahoo.dracarys.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class AddActivity extends ActionBarActivity{
    private ZXingScannerView mScannerView;
    private Toolbar toolbar;
    private final int REQUEST_CODE = 20;
    EditText etProductCode;
    ImageView ivScannner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

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
            //Add to the locker
            //Move to Add Activity for more..
        }
    }

    public void onManualRequest(View view) {
        //Invoke manual enrty activity
        String code =  etProductCode.getText().toString();
        Toast.makeText(this, "Manual: "+ code, Toast.LENGTH_SHORT).show();

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
