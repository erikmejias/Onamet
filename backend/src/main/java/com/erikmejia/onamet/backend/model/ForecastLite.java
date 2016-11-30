package com.erikmejia.onamet.backend.model;

/**
 * Created by erik on 11/30/16.
 */

public class ForecastLite {

    private String name;
    private int iconId;

    public ForecastLite() {}

    public ForecastLite(String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
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
