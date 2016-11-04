package com.erikmejia.onamet.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by erik on 11/3/16.
 */

public class Country {
    private List<City> cities;
    private List<Map<String, City>> citiesMap;

    public Country() {
        cities = new ArrayList<>();
        citiesMap = new ArrayList<>();
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city) {
        cities.add(city);
    }
}
