package com.yahoo.dracarys.models;

import com.parse.ParseObject;

/**
 * Created by jue on 1/31/15.
 */
public class BookLineItem {
    private int iconId;
    private String title;
    private int position;
    private String imageUrl;
    private String author;
    private String username;
    private String age;
    private int star;
    private String ean;
    private ParseObject parseBookObject;
    private int state;
    private int displaystate;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDisplaystate() {
        return displaystate;
    }

    public void setDisplaystate(int displaystate) {
        this.displaystate = displaystate;
    }

    public ParseObject getParseBookObject() {
        return parseBookObject;
    }

    public void setParseBookObject(ParseObject parseBookObject) {
        this.parseBookObject = parseBookObject;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
