package com.yahoo.dracarys.data;

import com.parse.ParseUser;
import com.yahoo.dracarys.R;
import com.yahoo.dracarys.models.DrawerLineItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jue on 1/31/15.
 */
public class DrawerData {
    private static List<DrawerLineItem> data;

    public static List<DrawerLineItem> getDrawerItems() {
        if (data == null || data.size() == 0) {
            data = new ArrayList();

            DrawerLineItem lineItem = new DrawerLineItem();
            lineItem.setTitle("¯\\_(ツ)_/¯   Hi "+ ParseUser.getCurrentUser().getUsername());
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_search);
            lineItem.setTitle("Search");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_stack);
            lineItem.setTitle("Public Stream");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_single);
            lineItem.setTitle("Private Stream");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_bookmark);
            lineItem.setTitle("My Favorites");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_profile);
            lineItem.setTitle("My Profile");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_text_add);
            lineItem.setTitle("Enter ISBN");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_image_scanner);
            lineItem.setTitle("Scan ISBN");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_profile);
            lineItem.setTitle("Logout");
            data.add(lineItem);

        }
        return data;
    }
}
