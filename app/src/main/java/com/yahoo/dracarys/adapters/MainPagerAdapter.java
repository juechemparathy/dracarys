package com.yahoo.dracarys.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.yahoo.dracarys.R;
import com.yahoo.dracarys.fragments.FavoriteFragment;
import com.yahoo.dracarys.fragments.PersonalTimelineFragment;
import com.yahoo.dracarys.fragments.ProfileFragment;
import com.yahoo.dracarys.fragments.TimelineFragment;

/**
 * Created by jue on 2/11/15.
 * FragmentStatePagerAdapter vs FragmentPagerAdapter
 */
public class MainPagerAdapter extends FragmentPagerAdapter {


    Context context;

    String[] tabTitles = {"Private Timeline", "Notifications", "MyProfile","Public Timeline"};
    int icons[] = {R.drawable.book_private_channel, R.drawable.book_fav_bar, R.drawable.book_profile,R.drawable.book_public_channel};

    public MainPagerAdapter(FragmentManager fm,Context c) {
        super(fm);
        this.context=c;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0) {
            PersonalTimelineFragment personalTimelineFragment = PersonalTimelineFragment.newInstance(position);
            return personalTimelineFragment;
        }
        else if(position == 1){
            FavoriteFragment favoriteFragment = new FavoriteFragment();
            return favoriteFragment;
        }else if(position == 2){
            return new ProfileFragment();
        }else{
            TimelineFragment timelineFragment = TimelineFragment.newInstance(position);
            return timelineFragment;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable =this.context.getResources().getDrawable(icons[position]);
        drawable.setBounds(0,0,36,36);
        ImageSpan imageSpan = new ImageSpan(drawable);
        //notice the space in the spannable string below else it wont work.
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
