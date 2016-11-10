package com.erikmejia.onamet.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erik on 11/5/16.
 */

public class City {

    private String name;
    private String population;
    private String lat;         // Latitude
    private String lon;         // Longitude
    private long cityCode;
    private List<Forecast> forecasts;

    //    Method to add forecast to current city
    public void addForecast(Forecast forecast) {
        forecasts.add(forecast);
    }

    public City(String name, String population, String lat, String lon, long cityCode) {
        this.name = name;
        this.population = population;
        this.lat = lat;
        this.lon = lon;
        this.cityCode = cityCode;
        forecasts = new ArrayList<>();
    }

//    Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public long getCityCode() {
        return cityCode;
    }

    public void setCityCode(long cityCode) {
        this.cityCode = cityCode;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}