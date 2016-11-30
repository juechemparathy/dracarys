package com.yahoo.dracarys.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.yahoo.dracarys.R;

public class StartupActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        //check for upgrade stage
        checkForUpgrade();
        //check for UserPref init page
        checkForUserPrefSettings();

//        ParseUser currentUser = ParseUser.getCurrentUser();
//        if (currentUser != null) {
//            // do stuff with the user
//            Intent i = new Intent(this, MainActivity.class);
//            i.putExtra("startUpMode", "normal");
//            Log.d("DEBUG", "Starting up in normal mode");
//            startActivity(i);
//        } else {
            // show the signup or login screen
            Intent i = new Intent(this, LoginActivity.class);
            i.putExtra("startUpMode", "normal");
            Log.d("DEBUG", "Starting up in normal mode");
            startActivity(i);
//        }
    }


    private void checkForUpgrade() {

    }

    private void checkForUserPrefSettings() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_startup, menu);
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
}
