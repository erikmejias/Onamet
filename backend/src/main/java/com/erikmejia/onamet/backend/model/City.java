package com.erikmejia.onamet.backend.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erik on 11/3/16.
 *
 * This class defines a City, which contains a forecast list for the next 14 days.
 */

public class City {

    private String name;
    private String population;
    private String lat;         // Latitude
    private String lon;         // Longitude
    private List<Forecast> forecasts;

//    Method to add forecast to current city
    public void addForecast(Forecast forecast) {
        forecasts.add(forecast);
    }

    public City(String name, String population, String lat, String lon) {
        this.name = name;
        this.population = population;
        this.lat = lat;
        this.lon = lon;
        forecasts = new ArrayList<>();
    }

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

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
