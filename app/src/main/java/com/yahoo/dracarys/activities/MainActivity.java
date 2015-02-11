package com.yahoo.dracarys.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yahoo.dracarys.R;
import com.yahoo.dracarys.adapters.MainPagerAdapter;
import com.yahoo.dracarys.fragments.NavigationDrawerFragment;
import com.yahoo.dracarys.fragments.TimelineFragment;
import com.yahoo.dracarys.helpers.SlidingTabLayout;


public class MainActivity extends ActionBarActivity implements TimelineFragment.OnFragmentInteractionListener {

    private Toolbar toolbar;
    ViewPager viewPager;
    SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        NavigationDrawerFragment navigatorFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        navigatorFragment.setUp(R.id.navigation_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


        viewPager= (ViewPager)findViewById(R.id.pager);

        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if (id == R.id.search) {
            startActivity(new Intent(this, SearchActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
