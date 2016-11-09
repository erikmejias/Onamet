package com.erikmejia.onamet.model;

/**
 * Created by erik on 9/2/16.
 */

public class Forecast {
//    Forecast attributes
    private String max;         // Max temperature
    private String min;         // Min temperature
    private String speed;       // Wind Speed
    private String humidity;
    private String sunrise_time;
    private String sunset_time;
    private String deg;         // Wind direction
    private String date;        // Forecast date
    private String description;
    private int iconId;

    public Forecast() {
//        Empty constructor
    }

    public Forecast(String max, String min, String speed, String humidity, String sunrise_time,
                    String sunset_time, String deg,
                    String date, String description, int iconId) {

        this.max = max;
        this.min = min;
        this.speed = speed;
        this.humidity = humidity;
        this.sunrise_time = sunrise_time;
        this.sunset_time = sunset_time;
        this.deg = deg;
        this.date = date;
        this.description = description;
        this.iconId = iconId;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getSunrise_time() {
        return sunrise_time;
    }

    public void setSunrise_time(String sunrise_time) {
        this.sunrise_time = sunrise_time;
    }

    public String getSunset_time() {
        return sunset_time;
    }

    public void setSunset_time(String sunset_time) {
        this.sunset_time = sunset_time;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
