package com.yahoo.dracarys.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.yahoo.dracarys.R;
import com.yahoo.dracarys.adapters.LineItemAdapter;
import com.yahoo.dracarys.models.BookLineItem;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LineItemAdapter lineItemAdapter;
    List<BookLineItem> bookLineItems = new ArrayList<BookLineItem>();
    String searchKey;
    EditText et_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.search_rcview);
        lineItemAdapter = new LineItemAdapter(this, bookLineItems);
        recyclerView.setAdapter(lineItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        et_search = (EditText) findViewById(R.id.et_search);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void searchLibrary(View view) {
        searchKey = et_search.getText().toString();
        if (searchKey != null && searchKey.trim().length() > 0) {
            bookLineItems = new ArrayList<BookLineItem>();
//            ParseQuery<ParseObject> query = ParseQuery.getQuery("Locker");
//            query.include("lockerPointer");
//            query.include("userPointer");
//            query.orderByDescending("updatedAt");
//            query.whereContains("title", searchKey);
//            query.findInBackground(new FindCallback<ParseObject>() {
//                public void done(List<ParseObject> lockerList, ParseException e) {
//                    if (e == null) {
//                        if(lockerList.size()>0) {
//                            Log.d("SEARCH", "Retrieved " + lockerList.size() + " items");
//
//                                for (ParseObject parseObject : lockerList) {
//                                    BookLineItem bookLineItem = new BookLineItem();
//                                    try {
//                                    bookLineItem.setAuthor(parseObject.getString("author"));
//                                    bookLineItem.setImageUrl(parseObject.getString("smallimageurl"));
//                                    bookLineItem.setTitle(parseObject.getString("title"));
//                                    bookLineItem.setUsername(parseObject.getParseUser("userPointer").getUsername());
//                                    bookLineItem.setAge(parseObject.getString("createdAt"));
//                                    bookLineItem.setEan(parseObject.getString("ean"));
//                                    bookLineItem.setParseBookObject(parseObject);
//                                    bookLineItems.add(bookLineItem);
//                                    }catch(Exception ex){
//                                        Log.d("DEBUG",bookLineItem.getEan());
//                                        ex.printStackTrace();
//                                    }
//                                }
//
//                            lineItemAdapter.setBookLineItemList(bookLineItems);
//                            lineItemAdapter.notifyDataSetChanged();
//                        }else{
//                            Toast.makeText(getApplicationContext(),"No books found.",Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Log.d("LOCKER", "Error: " + e.getMessage());
//                    }
//                }
//            });
        }
    }
}
