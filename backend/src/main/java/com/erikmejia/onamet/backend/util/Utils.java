package com.erikmejia.onamet.backend.util;

/**
 * Created by erik on 11/3/16.
 */

public class Utils {
    String[][] provinces = {
            {"", ""},
            {"", ""},
            {"", ""}
    };

    public interface Cities {

        String[][] cityTest = {

                /*
                * Add new provinces with their associated coordinates and codes here. They'll be filled
                * with weather forecast data and eventually propagated into the Firebase Database.
                *
                * City Name, Latitude, Longitude, City Code
                * */

                {"Moca", "12.345", "-19.234", "3496331"},
                {"La Romana", "12.345", "-19.234", "3500957"},
                {"Higuey", "12.345", "-19.234", "3493240"},
                {"Punta Cana", "12.345", "-19.234", "3494242"},
                {"San Pedro", "12.345", "-19.234", "3493031"},
                {"Hato Mayor del Rey", "12.345", "-19.234", "3504765"},
                {"El Seibo", "12.345", "-19.234", "3506189"},
                {"Sabana de la Mar", "12.345", "-19.234", "3493568"},
                {"Miches", "12.345", "-19.234", "3496396"},
                {"Monte Plata", "12.345", "-19.234", "3496132"},
                {"Puerto Plata", "12.345", "-19.234", "3493175"},
                {"Santiago", "12.345", "-19.234", "3492914"},
                {"La Vega", "12.345", "-19.234", "3509382"},
                {"San Francisco", "12.345", "-19.234", "3493146"},
                {"Santo Domingo", "12.345", "-19.234", "3492908"},
                {"Mao", "12.345", "-19.234", "3496831"},
                {"Jarabacoa", "12.345", "-19.234", "3504158"},
                {"Bonao", "12.345", "-19.234", "3511233"},
                {"Nagua", "12.345", "-19.234", "3496021"},
                {"Samaná", "12.345", "-19.234", "3492997"},
                {"Azua", "12.345", "-19.234", "3512208"},
                {"Bani", "12.345", "-19.234", "3512064"},
        };
    }

}
