package com.yahoo.dracarys.data;

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
            for (int i = 0; i < 20; i++) {
                DrawerLineItem lineItem = new DrawerLineItem();
                lineItem.setIconId(i);
                lineItem.setTitle("Hello World " + i);
                data.add(lineItem);
            }
        }
        return data;
    }
}
