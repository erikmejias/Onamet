package com.erikmejia.onamet.backend.model;

/**
 * Created by erik on 10/13/16.
 */

public class Forecast {

    //    Forecast attributes
    private String city_name;
    private String max;         // Max temperature
    private String min;         // Min temperature
    private String morning_temp; // Morning temperature
    private String noon_temp; // Noon temperature
    private String night_temp; // Night temperature
    private String speed;       // Wind Speed
    private String humidity;
    private String sunrise_time;
    private String sunset_time;
    private String description;
    private String deg;         // Wind direction
    private String date;        // Forecast date
    private String sync_time;
    private int iconId;

    public Forecast() {
//        Empty constructor
    }

    public Forecast(String city_name, String max, String min, String morning_temp, String noon_temp,
                    String night_temp, String speed, String humidity, String sunrise_time,
                    String sunset_time, String description, String deg, String date, String sync_time,
                    int iconId) {

        this.city_name = city_name;
        this.max = max;
        this.min = min;
        this.morning_temp = morning_temp;
        this.noon_temp = noon_temp;
        this.night_temp = night_temp;
        this.speed = speed;
        this.humidity = humidity;
        this.sunrise_time = sunrise_time;
        this.sunset_time = sunset_time;
        this.description = description;
        this.deg = deg;
        this.date = date;
        this.sync_time = sync_time;
        this.iconId = iconId;
    }

    @Override
    public String toString() {
        return "Forecast: " + this.getCity_name() + this.getMax() + " " + this.getMin() + " "  + this.getDate() + " "
                + this.getDescription() + " "  + this.getDeg() + " "  + this.getHumidity() + " "
                + this.getSunrise_time() + " "  + this.getSunset_time();
    }

    public String getMorning_temp() {
        return morning_temp;
    }

    public void setMorning_temp(String morning_temp) {
        this.morning_temp = morning_temp;
    }

    public String getNoon_temp() {
        return noon_temp;
    }

    public void setNoon_temp(String noon_temp) {
        this.noon_temp = noon_temp;
    }

    public String getNight_temp() {
        return night_temp;
    }

    public void setNight_temp(String night_temp) {
        this.night_temp = night_temp;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getSync_time() {
        return sync_time;
    }

    public void setSync_time(String sync_time) {
        this.sync_time = sync_time;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
