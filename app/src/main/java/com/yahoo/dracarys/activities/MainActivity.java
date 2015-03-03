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
import android.view.View;
import android.widget.Toast;

import com.yahoo.dracarys.R;
import com.yahoo.dracarys.adapters.MainPagerAdapter;
import com.yahoo.dracarys.fragments.NavigationDrawerFragment;
import com.yahoo.dracarys.helpers.SlidingTabLayout;
import com.yahoo.dracarys.interfaces.OnFragmentInteractionListener;

//icons  - https://www.iconfinder.com/search/?q=book&style=glyph&price=free
public class MainActivity extends ActionBarActivity implements OnFragmentInteractionListener {

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
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(),this));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.main_tab_view, R.id.tabText);
        mTabs.setBackgroundResource(R.drawable.appbar_wood);
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.accent);
            }
        });
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

        if (id == R.id.search) {
            startActivity(new Intent(this, SearchActivity.class));
        }

        if (id == R.id.add) {
            startActivity(new Intent(this, AddActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onReject(View view) {
        Toast.makeText(this, "Loan Rejcted", Toast.LENGTH_SHORT);
    }

    public void onAccept(View view) {
        Toast.makeText(this,"Loan Accepted",Toast.LENGTH_SHORT);
    }

}
