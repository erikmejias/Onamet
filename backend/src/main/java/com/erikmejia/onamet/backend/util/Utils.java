package com.erikmejia.onamet.backend.util;

/**
 * Created by erik on 11/3/16.
 */

public class Utils {

    private interface CITIES_BACKGROUND {

        String deflt = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/default.jpg?alt=media&token=cfbcaa6d-033a-4e6f-b04b-2d6644ac0630";

        String AZUA = deflt;
        String BONAO = deflt;
        String BANI = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/bani.jpg?alt=media&token=96e85ba3-5c7a-47d2-a127-59faf610ede5";
        String BAHORUCO = deflt;
        String BARAHONA = deflt;
        String DAJABON = deflt;
        String DUARTE = deflt;
        String EL_SEIBO = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/seibo.jpg?alt=media&token=9a74b4a9-87ba-4db4-a1bf-b579abbcda48";
        String ELIAS_PINA = deflt;
        String HIGUEY = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/higuey.jpg?alt=media&token=ea26574d-cdb6-4886-b9e3-c62b70c393e2";
        String HATO_MAYOR = deflt;
        String HERMANAS_MIRABAL = deflt;
        String INDEPENDENCIA = deflt;
        String JARABACOA = deflt;
        String LA_ROMANA = deflt;
        String LA_VEGA = deflt;
        String MAO = deflt;
        String MICHES = deflt;
        String MONTE_PLATA = deflt;
        String MOCA = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/moca.jpg?alt=media&token=571d5172-fe96-4884-a053-a19e5f8f6d67";
        String MONSENOR_NOUEL = deflt;
        String MONTE_CRISTI = deflt;
        String NAGUA = deflt;
        String PEDERNALES = deflt;
        String PERAVIA = deflt;
        String PUNTA_CANA = deflt;
        String PUERTO_PLATA = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/puerto_plata.jpg?alt=media&token=27624d80-50e7-45d4-8e37-a045edb6a837";
        String SANTO_DOMINGO = deflt;
        String SANTIAGO = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/santiago.JPG?alt=media&token=f4d06bfe-9a3a-4faf-b978-724950555e75";
        String SAN_PEDRO_MACORIS = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/san_pedro_1.jpg?alt=media&token=3f6c32f3-1963-4a7a-9b40-9321c5d6b741";
        String SABANA_DE_LA_MAR = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/sabana_dela_mar.jpg?alt=media&token=0d76a718-7174-48f4-8dd8-e38367cbd971";
        String SAMANA = deflt;
        String SANCHEZ_RAMIREZ = deflt;
        String SAN_CRISTOBAL = deflt;
        String SAN_JOSE_OCOA = deflt;
        String SAN_JUAN = deflt;
        String SANTIAGO_RODRIGUEZ = deflt;
        String SAN_FRANCISCO = deflt;
        String VALVERDE = deflt;
    }

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
                * City Name, Latitude, Longitude, City Code, City ID (Must be UNIQUE)
                * */

                {"Azua", "12.345", "-19.234", "3512208", CITIES_BACKGROUND.AZUA},
                {"Bonao", "12.345", "-19.234", "3511233", CITIES_BACKGROUND.BONAO},
                {"Bani", "12.345", "-19.234", "3512064", CITIES_BACKGROUND.BANI},
                {"Bahoruco", "12.18.481380", "-71.419647", "3495857", CITIES_BACKGROUND.BAHORUCO},
                {"Barahona", "18.16667", "-71.25", "3512042", CITIES_BACKGROUND.BARAHONA},
                {"Dajabón", "12.345", "-19.234", "3508951", CITIES_BACKGROUND.DAJABON},
                {"Duarte", "12.345", "-19.234", "3508718", CITIES_BACKGROUND.DUARTE},
                {"El Seibo", "12.345", "-19.234", "3492984", CITIES_BACKGROUND.EL_SEIBO},
                {"Elías Piña", "18.88", "-71.7", "3509386", CITIES_BACKGROUND.ELIAS_PINA},
                {"Higuey", "12.345", "-19.234", "3493240", CITIES_BACKGROUND.HIGUEY},
                {"Hato Mayor del Rey", "12.345", "-19.234", "3504765", CITIES_BACKGROUND.HATO_MAYOR},
                {"Hermanas Mirabal", "12.345", "-19.234", "3493282", CITIES_BACKGROUND.HERMANAS_MIRABAL},
                {"Independencia", "12.345", "-19.234", "3504326", CITIES_BACKGROUND.INDEPENDENCIA},
                {"Jarabacoa", "12.345", "-19.234", "3504158", CITIES_BACKGROUND.JARABACOA},
                {"La Romana", "12.345", "-19.234", "3500957", CITIES_BACKGROUND.LA_ROMANA},
                {"La Vega", "12.345", "-19.234", "3509382", CITIES_BACKGROUND.LA_VEGA},
                {"Mao", "12.345", "-19.234", "3496831", CITIES_BACKGROUND.MAO},
                {"Miches", "12.345", "-19.234", "3496396", CITIES_BACKGROUND.MICHES},
                {"Monte Plata", "12.345", "-19.234", "3496132", CITIES_BACKGROUND.MONTE_PLATA},
                {"Moca", "12.345", "-19.234", "3496331", CITIES_BACKGROUND.MOCA},
                {"Monseñor Nouel", "18.91667", "-70.416672", "3496274", CITIES_BACKGROUND.MONSENOR_NOUEL},
                {"Monte Cristi", "12.345", "-19.234", "3493174", CITIES_BACKGROUND.MONTE_CRISTI},
                {"Nagua", "12.345", "-19.234", "3496021", CITIES_BACKGROUND.NAGUA},
                {"Pedernales", "12.345", "-19.234", "3495137", CITIES_BACKGROUND.PEDERNALES},
                {"Peravia", "18.33333", "-70.366669", "3495015", CITIES_BACKGROUND.PERAVIA},
                {"Punta Cana", "12.345", "-19.234", "3494242", CITIES_BACKGROUND.PUNTA_CANA},
                {"Puerto Plata", "12.345", "-19.234", "3493175", CITIES_BACKGROUND.PUERTO_PLATA},
                {"Santo Domingo", "12.345", "-19.234", "3492908", CITIES_BACKGROUND.SANTO_DOMINGO},
                {"Santiago", "12.345", "-19.234", "3492914", CITIES_BACKGROUND.SANTIAGO},
                {"San Pedro de Macorís", "12.345", "-19.234", "3493031", CITIES_BACKGROUND.SAN_PEDRO_MACORIS},
                {"Sabana de la Mar", "12.345", "-19.234", "3493568", CITIES_BACKGROUND.SABANA_DE_LA_MAR},
                {"Samaná", "12.345", "-19.234", "3492997", CITIES_BACKGROUND.SAMANA},
                {"San Cristobal", "12.345", "-19.234", "3511540", CITIES_BACKGROUND.SAN_CRISTOBAL},
                {"San José de Ocoa", "12.345", "-19.234", "3493100", CITIES_BACKGROUND.SAN_JOSE_OCOA},
                {"Sánchez Ramírez", "19.2281", "-69.613701", "3493198", CITIES_BACKGROUND.SANCHEZ_RAMIREZ},
                {"San Juan", "12.345", "-19.234", "3493091", CITIES_BACKGROUND.SAN_JUAN},
                {"Santiago Rodríguez", "19.41667", "-71.333328", "3492912", CITIES_BACKGROUND.SANTIAGO_RODRIGUEZ},
                {"San Francisco", "12.345", "-19.234", "3493146", CITIES_BACKGROUND.SAN_FRANCISCO},
                {"Valverde", "19.616671", "-71", "3492112", CITIES_BACKGROUND.VALVERDE}
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
