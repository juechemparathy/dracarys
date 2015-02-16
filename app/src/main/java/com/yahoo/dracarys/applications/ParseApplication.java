package com.yahoo.dracarys.applications;

import android.app.Application;
import com.parse.Parse;

/**
 * Created by jue on 2/15/15.
 */
public class ParseApplication extends Application {

    private static String YOUR_APPLICATION_ID = "npJJBriOwZhe1LfSMuT4yqO0q9A6xj4KX2ep7ygt";
    private static String YOUR_CLIENT_KEY = "BWzT9KQej1Ssh4jKXrQvU0l4U4Ur06Sw7w8xfPfg";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        // Add your initialization code here
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
    }
}
