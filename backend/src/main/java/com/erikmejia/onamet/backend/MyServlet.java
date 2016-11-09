/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.erikmejia.onamet.backend;

import com.eclipsesource.json.JsonObject;
import com.erikmejia.onamet.backend.model.City;
import com.erikmejia.onamet.backend.model.Country;
import com.erikmejia.onamet.backend.model.Forecast;
import com.erikmejia.onamet.backend.model.NewsItem;
import com.github.dvdme.ForecastIOLib.FIOCurrently;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import com.erikmejia.onamet.backend.util.Utils.Cities;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.xml.ws.Response;

public class MyServlet extends HttpServlet {
    String responseData = null;
    static Logger Log = Logger.getLogger("com.erikmejia.onamet.backend.MyServlet");

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

        // This fires when the servlet first runs, returning all the existing values
        // only runs once, until the servlet starts up again.
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
//                Log.info("new value: "+ document); // TODO - checking data FROM Firebase.

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
    public void pushDemoData (Country country) {
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
    public void buildForecastData(){

        Country country = new Country();

        for (int index = 0; index < Cities.cityTest.length; index++) {
            int columnA = 0;
            int columnB = 1;
            int columnC = 2;

            City city = new City(
                    Cities.cityTest[index][columnA],    // Name of the city
                    "234,235",                          // Population of the city
                    Cities.cityTest[index][columnB],    // Latitude
                    Cities.cityTest[index][columnC]);   // Longitude
            for (int i = 0; i < 13; i++) {
                /*
                * Getting a Calendar object to put current date and then increment one for the
                * next forecasts
                * */
                DateFormat df = new SimpleDateFormat("MMM d");
                Date dateobj = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateobj);
                calendar.add(Calendar.DATE, i);
                Forecast forecast = new Forecast(
                        "12.3",
                        "9.23",
                        "34",
                        "456",
                        "6:37",
                        "8:01",
                        "lluvias ligeras",
                        "34",
                        df.format(calendar.getTime()),
                        3
                );
                city.addForecast(forecast);
            }
//            Send new formed city to firebase
            country.addCity(city);
        }
        pushDemoData(country);
    }

    /*
    * Method to retrieve forecast data from OWM &
    * */

    public void requestForecasts() throws IOException {
//        TODO - Implement Data Fetching
        ForecastIO forecastIO = new ForecastIO("a681f2f1c2d2a75d3f576801b14c131f");

        forecastIO.setUnits(ForecastIO.UNITS_SI);
        forecastIO.setExcludeURL("minutely");
        forecastIO.setLang("es");
        forecastIO.setUnits(ForecastIO.UNITS_SI);

        URL url = new URL(forecastIO.getUrl("19.209111", "-70.447677"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuffer json = new StringBuffer();
        String line;

        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();

        forecastIO.getForecast(json.toString());

        FIOCurrently currently = new FIOCurrently(forecastIO);
        System.out.println(currently.get().temperature());

        System.out.println("response data " + json);






//        FIOCurrently currently = new FIOCurrently(forecastIO);
//
//        System.out.println(currently.get().summary());
    }

}
