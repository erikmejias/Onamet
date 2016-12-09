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

import javax.servlet.ServletException;
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
        String name = req.getParameter("name");
        if (name == null) {
            resp.getWriter().println("Please enter a name");
        } else {
            resp.getWriter().println("Added message" + name);
        }

        String outString;
        outString = "<p>Sending message from App Engine to Firebase";

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

//        buildDemoData();

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

        String title = req.getParameter("text_message_title");
        String body = req.getParameter("text_message_body");

        resp.setContentType("text/plain");
        if (title == null && body == null) {
            resp.getWriter().println("Please enter data");
        }
        resp.getWriter().println("Enviado el boletín titulado " + title);
        buildDemoData(title, body);
    }

    /*
         * Method to push data to the Firebase Database.
         * */
    public void pushDemoData (Bulletin bulletin) {
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("bulletins");
        System.out.println("Enviado " + bulletin.toString());

        ref.push().setValue(bulletin);
    }

    public void buildDemoData(String title, String msg) {
        DateFormat df = new SimpleDateFormat("d MMM - h:mm aaa", new Locale("es", "DO"));

        Bulletin bulletin = new Bulletin(
                title,
                msg,
                df.format(new Date().getTime())
        );

        pushDemoData(bulletin);
    }


}