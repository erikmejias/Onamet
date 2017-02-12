package com.erikmejia.onamet.backend.model;

/**
 * Created by erik on 11/30/16.
 */

public class ForecastLite {

    private int id;
    private String name;
    private int iconId;

    public ForecastLite() {}

    public ForecastLite(int id, String name, int iconId) {
        this.id = id;
        this.name = name;
        this.iconId = iconId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
