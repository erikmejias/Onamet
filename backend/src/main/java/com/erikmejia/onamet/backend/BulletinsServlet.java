package com.erikmejia.onamet.backend;

import com.erikmejia.onamet.backend.model.Bulletin;
import com.erikmejia.onamet.backend.model.City;
import com.erikmejia.onamet.backend.model.Country;
import com.erikmejia.onamet.backend.model.Forecast;
import com.erikmejia.onamet.backend.util.Utils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by erik on 11/13/16.
 */

public class BulletinsServlet extends HttpServlet {
    String responseData = null;
    static Logger Log = Logger.getLogger("com.erikmejia.onamet.backend.ForecastServlet");

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

        buildDemoData();

        // As an admin, the app has access to read and write all data, regardless of Security Rules
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("bulletins");


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
    public void pushDemoData (Bulletin bulletin) {
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("bulletins");

        ref.push().setValue(bulletin);
    }

    public void buildDemoData() {
        DateFormat df = new SimpleDateFormat("d MMM - h:mm aaa", new Locale("es", "DO"));
        String description = "Lorem ipsum dolor sit amet. Ip dolor ipsum lot of trouble sitting in the dinning table of Frank Abagnale Jr. " +
                "The Guy that almost breakall of it.\n\n" +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it.\n" +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it." +
                " The Guy that almost breakall of it.";

        for (int i = 0; i < 7; i++) {
            Bulletin bulletin = new Bulletin(
                    "Título de Prueba #" + i,
                    description,
                    df.format(new Date().getTime())
            );

            pushDemoData(bulletin);
        }
    }


}