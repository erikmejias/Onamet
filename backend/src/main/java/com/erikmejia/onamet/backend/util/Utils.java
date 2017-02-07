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

    public interface Constants {
        public int CLEAR_SKY = 0;
        public int THUNDERSTORM = 1;
        public int THUNDERSTORM_RAIN = 2;
        public int THUNDERSTORM_HEAVY_RAIN = 3;
        public int DRIZZLE = 4;
        public int DRIZZLE_HEAVY = 5;
        public int RAIN_LIGHT = 6;
        public int RAIN_MODERATE = 7;
        public int RAIN_HEAVY = 8;
        public int CLOUDS_FEW = 9;
        public int CLOUDS_SCATTERED = 10;
        public int CLOUDS_BROKEN = 11;
        public int TROPICAL_STORM = 12;
        public int HURRICANE = 13;
        public int WINDY = 14;
        public int NO_REGISTRY = 15;

        String[][] CITY_ENTRIES = {

                /*
                * Add new provinces with their associated coordinates and codes here. They'll be filled
                * with weather forecast data and eventually propagated into the Firebase Database.
                *
                * City Name, Latitude, Longitude, City Code
                * */

                {"Azua", "12.345", "-19.234", "3512208"},
                {"Bonao", "12.345", "-19.234", "3511233"},
                {"Bani", "12.345", "-19.234", "3512064"},
                {"Bahoruco", "12.18.481380", "-71.419647", "3495857"},
                {"Barahona", "18.16667", "-71.25", "3512042"},
                {"Dajabón", "12.345", "-19.234", "3508951"},
                {"Duarte", "12.345", "-19.234", "3508718"},
                {"El Seibo", "12.345", "-19.234", "3506189"},
                {"Elías Piña", "18.88", "-71.7", "3509386"},
                {"Higuey", "12.345", "-19.234", "3493240"},
                {"Hato Mayor del Rey", "12.345", "-19.234", "3504765"},
                {"Hermanas Mirabal", "12.345", "-19.234", "3493282"},
                {"Independencia", "12.345", "-19.234", "3504326"},
                {"Jarabacoa", "12.345", "-19.234", "3504158"},
                {"La Romana", "12.345", "-19.234", "3500957"},
                {"La Vega", "12.345", "-19.234", "3509382"},
                {"Mao", "12.345", "-19.234", "3496831"},
                {"Miches", "12.345", "-19.234", "3496396"},
                {"Monte Plata", "12.345", "-19.234", "3496132"},
                {"Moca", "12.345", "-19.234", "3496331"},
                {"Monseñor Nouel", "18.91667", "-70.416672", "3496274"},
                {"Monte Cristi", "12.345", "-19.234", "3493174"},
                {"Nagua", "12.345", "-19.234", "3496021"},
                {"Pedernales", "12.345", "-19.234", "3495137"},
                {"Peravia", "18.33333", "-70.366669", "3495015"},
                {"Punta Cana", "12.345", "-19.234", "3494242"},
                {"Puerto Plata", "12.345", "-19.234", "3493175"},
                {"Santo Domingo", "12.345", "-19.234", "3492908"},
                {"Santiago", "12.345", "-19.234", "3492914"},
                {"San Pedro de Macorís", "12.345", "-19.234", "3493031"},
                {"Sabana de la Mar", "12.345", "-19.234", "3493568"},
                {"Samaná", "12.345", "-19.234", "3492997"},
                {"Sánchez Ramírez", "12.345", "-19.234", "3493198"},
                {"San Cristobal", "12.345", "-19.234", "3511540"},
                {"San José de Ocoa", "12.345", "-19.234", "3493100"},
                {"Sánchez Ramírez", "19.2281", "-69.613701", "3493198"},
                {"San Juan", "12.345", "-19.234", "3493091"},
                {"Santiago Rodríguez", "19.41667", "-71.333328", "3492912"},
                {"San Francisco", "12.345", "-19.234", "3493146"},
                {"Valverde", "19.616671", "-71", "3492112"}
        };
    }

    public static int getWeatherCode(int code) {

        switch (code){
            case 200: // Thunderstorm with light rain
                return Constants.THUNDERSTORM_RAIN;
            case 201: // Thunderstorm with rain
                return Constants.THUNDERSTORM_RAIN;
            case 202: // Thunderstorm with heavy rain
                return Constants.THUNDERSTORM_HEAVY_RAIN;
            case 210: // Light Thunderstorm
                return Constants.THUNDERSTORM_RAIN;
            case 212: // Thunderstorm
                return Constants.THUNDERSTORM;
            case 221: // Ragged thunderstorm
                return Constants.THUNDERSTORM;
            case 230: // Thunderstorm with light drizzle
                return Constants.THUNDERSTORM_RAIN;
            case 231: // Thunderstorm with drizzle
                return Constants.THUNDERSTORM_RAIN;
            case 232: // Thunderstorm with heavy drizzle
                return Constants.THUNDERSTORM_RAIN;
            case 300: // Light intensity drizzle
                return Constants.DRIZZLE;
            case 301: // Drizzle
                return Constants.DRIZZLE;
            case 302: // Heavy intensity drizzle
                return Constants.DRIZZLE_HEAVY;
            case 310: // Light intensity drizzle
                return Constants.DRIZZLE;
            case 311: // Drizzle rain
                return Constants.DRIZZLE;
            case 312: // Heavy intensity drizzle rain
                return Constants.DRIZZLE_HEAVY;
            case 313: // Shower rain and drizzle
                return Constants.DRIZZLE;
            case 314: // Heavy shower rain and drizzle
                return Constants.DRIZZLE_HEAVY;
            case 321: // Shower drizzle
                return Constants.DRIZZLE;
            case 500: // Light rain
                return Constants.RAIN_LIGHT;
            case 501: // Moderate rain
                return Constants.RAIN_MODERATE;
            case 502: // Heavy intensity rain
                return Constants.RAIN_HEAVY;
            case 503: // Very heavy rain
                return Constants.RAIN_HEAVY;
            case 504: // Extreme rain
                return Constants.RAIN_HEAVY;
            case 511: // Freezing rain
                return Constants.RAIN_LIGHT;
            case 520: // Light intensity shower rain
                return 0;
            case 521: // Shower rain
                return Constants.RAIN_MODERATE;
            case 522: // Heavy intensity shower rain
                return Constants.RAIN_HEAVY;
            case 531: // Ragged shower rain
                return Constants.RAIN_MODERATE;
            case 800: // Clear Sky
                return Constants.CLEAR_SKY;
            case 801: // Few clouds
                return Constants.CLOUDS_FEW;
            case 802: // Scattered clouds
                return Constants.CLOUDS_SCATTERED;
            case 803: // Broken clouds
                return Constants.CLOUDS_BROKEN;
            case 804: // Overcast clouds
                return Constants.CLOUDS_FEW;
            case 901: // Tropical storm
                return Constants.TROPICAL_STORM;
            case 902: // Hurricane
                return Constants.HURRICANE;
            case 905: // Windy
                return Constants.WINDY;
            case 962: // Hurricane
                return Constants.HURRICANE;
            default: // No idea
                return Constants.NO_REGISTRY;

        }

    }


}
