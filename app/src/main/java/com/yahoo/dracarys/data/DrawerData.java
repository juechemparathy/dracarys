package com.yahoo.dracarys.data;

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
            lineItem.setTitle("Hi uSer!");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_search);
            lineItem.setTitle("Search");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_public_channel);
            lineItem.setTitle("Public Stream");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_private_channel);
            lineItem.setTitle("Private Stream");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.book_fav_bar);
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
            lineItem.setIconId(R.drawable.book_logout);
            lineItem.setTitle("Logout");
            data.add(lineItem);

        }
        return data;
    }
}
