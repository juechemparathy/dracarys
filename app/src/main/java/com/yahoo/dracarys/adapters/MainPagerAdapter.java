package com.yahoo.dracarys.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yahoo.dracarys.fragments.TimelineFragment;

/**
 * Created by jue on 2/11/15.
 */
  public class MainPagerAdapter extends FragmentPagerAdapter {

    String[] tabTitles = {"Timeline","Notifications","Me"};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        TimelineFragment timelineFragment = TimelineFragment.newInstance(position);
        return timelineFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
