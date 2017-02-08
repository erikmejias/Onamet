/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.erikmejia.onamet.backend;

import com.erikmejia.onamet.backend.model.City;
import com.erikmejia.onamet.backend.model.Country;
import com.erikmejia.onamet.backend.model.Forecast;
import com.erikmejia.onamet.backend.model.ForecastLite;
import com.erikmejia.onamet.backend.util.Utils;
import com.erikmejia.onamet.backend.util.Utils.Constants;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.servlet.http.*;

public class ForecastServlet extends HttpServlet {
    static Logger Log = Logger.getLogger("com.erikmejia.onamet.backend.ForecastServlet");

//    Request API Builder for new Forecasts
    OpenWeatherMap owm = new OpenWeatherMap(
            OpenWeatherMap.Units.METRIC,
            "ab935127aec33bcab3d7a12509748c88"
    );

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Log.info("Refreshing Firebase Database");

        String outString;
        outString = "<p>Sending message from App Engine to Firebase";

        resp.getWriter().println("outstring" + outString);

        // Note: Ensure that the Onamet-035fa87b100b.json has read
        // permissions set.
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(getServletContext().getResourceAsStream("/WEB-INF/Onamet-035fa87b100b.json"))
                .setDatabaseUrl("https://project-7000350159161293832.firebaseio.com/")
                .build();

        try {
            FirebaseApp.getInstance();
        }
        catch (Exception error){
            Log.info("doesn't exist...");
        }

        try {
            FirebaseApp.initializeApp(options);
        }
        catch(Exception error){
            Log.info("already exists...");
        }

        // As an admin, the app has access to read and write all data, regardless of Security Rules
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("forecasts");

        buildForecastData();
        buildCurrentWeatherList();

        // This fires when the servlet first runs, returning all the existing values
        // only runs once, until the servlet starts up again.
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
//                Log.info("new value: "+ document);

                String todoText = "Don't forget to...\n\n";

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    todoText = todoText + " * " + childSnapshot.getValue().toString() + "\n";
                }

            }

            @Override
            public void onCancelled(DatabaseError error){
                System.out.println("Error: "+error);
            }
        });
    }

    /*
            * Method to push data to the Firebase Database.
            * */
    private void pushDemoData(Country country) {
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("forecasts");

        Map<String, City> city_map = new HashMap<>();
//        city_map.put(city.getName(), city);

        ref.setValue(country);
    }

    /*
    * Method that takes all data sources to compile city objects with their respective forecasts
    * assigned.
    * */
    private void buildForecastData(){

        Country country = new Country();

        for (int index = 0; index < Utils.Constants.CITY_ENTRIES.length; index++) {
            int columnA = 0;
            int columnB = 1;
            int columnC = 2;
            int columnD = 3;

//            Create a new city by fetching data from Utils.Constants.CITY_ENTRIES interface.
            City city = new City(
                    Utils.Constants.CITY_ENTRIES[index][columnA],    // Name of the city
                    "234,235",                          // Population of the city
                    Utils.Constants.CITY_ENTRIES[index][columnB],    // Latitude
                    Constants.CITY_ENTRIES[index][columnC],    // Longitude
                    Long.valueOf(
                            Constants.CITY_ENTRIES[index][columnD] // City code
                    ));

            city.setForecasts(searchWeather(city.getName(), city.getCityCode()));

//            Add the completed city to the Country object
            country.addCity(city);
        }
//        Add all cities to Firebase
        pushDemoData(country);
    }

    /*
    * Method to retrieve forecast data from OWM
    * */

    private List<Forecast> searchWeather(String cityName, long cityCode){
        List<Forecast> receivedForecasts = new ArrayList<>();

        byte quantity = 15;
        DateFormat dateFormatToday = new SimpleDateFormat("EEEE d", new Locale("es", "DO"));

        DailyForecast dailyForecast = owm.dailyForecastByCityCode(cityCode, quantity);
//        TODO - Put a checking here (if ?) that verifies the array isn't ZERO/empty

        for (int index = 0; index < 15; index++) {

            String date;
            String thisDate;

            switch (index) {
                case 0:
//                    formatting the date before saving to be like this: Hoy, Miércoles 3
                    thisDate =
                            dateFormatToday.format(dailyForecast.getForecastInstance(index).getDateTime())
                            .substring(0, 1).toUpperCase() +
                                    dateFormatToday.format(dailyForecast.getForecastInstance(index).getDateTime())
                            .substring(1).toLowerCase();
                    date = "Hoy, " + thisDate;
                    break;
                case 1:
                    date = "Mañana";
                    break;
                default:
                    thisDate =
                            dateFormatToday.format(dailyForecast.getForecastInstance(index).getDateTime())
                                    .substring(0, 1).toUpperCase() +
                                    dateFormatToday.format(dailyForecast.getForecastInstance(index).getDateTime())
                                            .substring(1).toLowerCase();
                    date = thisDate;
            }

            String description = dailyForecast.getForecastInstance(index).
                    getWeatherInstance(0).getWeatherDescription();
            if (dailyForecast.getForecastInstance(index).getWeatherInstance(0)
                    .getWeatherDescription().equalsIgnoreCase("lluvia de gran intensidad")) {
                description = "lluvias intensas";
            }

//            Getting current time of the server for last sync message
            DateFormat df = new SimpleDateFormat("h:mm a", new Locale("es", "DO"));
            df.setTimeZone(TimeZone.getTimeZone("GMT-04:00"));
            Calendar calobj = Calendar.getInstance();

            Forecast forecast = new Forecast(
                    cityName,
                    String.valueOf(
                            Math.round(dailyForecast.getForecastInstance(index).getTemperatureInstance().getMaximumTemperature())
                                    + "º"),
                    String.valueOf(
                            Math.round(dailyForecast.getForecastInstance(index).getTemperatureInstance().getMinimumTemperature())
                                    + "º"),
                    String.valueOf(
                            Math.round(dailyForecast.getForecastInstance(index).getTemperatureInstance().getMorningTemperature())
                                    ),
                    String.valueOf(
                            Math.round(dailyForecast.getForecastInstance(index).getTemperatureInstance().getEveningTemperature())
                                    ),
                    String.valueOf(
                            Math.round(dailyForecast.getForecastInstance(index).getTemperatureInstance().getNightTemperature())
                                    ),
                    String.valueOf(
                            Math.round(dailyForecast.getForecastInstance(index).getWindSpeed())
                    + " km/h"),
                    String.valueOf(Math.round(dailyForecast.getForecastInstance(index).getHumidity())
                    + "%"),
                    "6:34",
                    "7:03",
                    description,
                    String.valueOf(Math.round(dailyForecast.getForecastInstance(index).getWindDegree())
                    + "º"),
                    date,
                    "última actualización a las " + df.format(calobj.getTime()),
                    Utils.getWeatherCode(
                            dailyForecast.getForecastInstance(index).getWeatherInstance(0)
                                    .getWeatherCode())
            );
            System.out.println(forecast);

            receivedForecasts.add(forecast);

        }
        return receivedForecasts;

    }

    /*
    * Method to retrieve forecast data from OWM | Used to populate the navigation drawer with a weather icon.
    * */
    private void buildCurrentWeatherList() {

        int columnName = 0; // Columns that contains the name
        int columnId = 3; // Columns that contains the code
        byte count = 1;

//        Array to store all new forecasts
        ArrayList<ForecastLite> forecastList = new ArrayList<>();

        for (int index = 0; index < Utils.Constants.CITY_ENTRIES.length; index++) {
            /*DailyForecast forecast =
                    owm.dailyForecastByCityCode(
                            Long.valueOf(Utils.Constants.CITY_ENTRIES[index][columnId]),
                            count);*/
            DailyForecast weather = owm.dailyForecastByCityCode(
                    Long.valueOf(Utils.Constants.CITY_ENTRIES[index][columnId]),
                    count
            );

            ForecastLite currentForecast = new ForecastLite(
                    Constants.CITY_ENTRIES[index][columnName],
                    Utils.getWeatherCode(weather.getForecastInstance(0).getWeatherInstance(0).getWeatherCode())
            );
//            Save newly found forecast in a single object
            forecastList.add(currentForecast);
        }

//            Add all these forecasts to Firebase
        pushCurrentForecast(forecastList);
    }

    /*
    * Method that pushes current forecasts list to Firebase
    * */
    private void pushCurrentForecast(ArrayList<ForecastLite> currentForecastList) {
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("forecasts_list");

        ref.setValue(currentForecastList);
    }

}
