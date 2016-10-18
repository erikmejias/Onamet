package com.erikmejia.onamet.backend.model;

/**
 * Created by erik on 10/13/16.
 */

public class NewsItem {
    private String title;
    private String date;
    private String body;

    public NewsItem(String title, String date, String body) {
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
