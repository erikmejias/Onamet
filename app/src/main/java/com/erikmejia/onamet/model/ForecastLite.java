package com.erikmejia.onamet.model;

/**
 * Created by erik on 12/2/16.
 */

public class ForecastLite {

    private int id;
    private String name;
    private int iconId;
    private String backgroundLink;

    public ForecastLite() {}

    public ForecastLite(int id, String name, int iconId, String backgroundLink) {
        this.id = id;
        this.name = name;
        this.iconId = iconId;
        this.backgroundLink = backgroundLink;
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

    public String getBackgroundLink() {
        return backgroundLink;
    }

    public void setBackgroundLink(String backgroundLink) {
        this.backgroundLink = backgroundLink;
    }
}