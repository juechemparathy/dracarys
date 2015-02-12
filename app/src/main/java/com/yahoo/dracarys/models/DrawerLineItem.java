package com.yahoo.dracarys.models;

/**
 * Created by jue on 1/31/15.
 */
public class DrawerLineItem{
    private int iconId;
    private String title;
    private int position;

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
