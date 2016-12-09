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
    public void pushDemoData (Bulletin bulletin) {
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("bulletins");
        System.out.println("Enviado " + bulletin.toString());

        ref.push().setValue(bulletin);
    }

    public void buildDemoData() {
        DateFormat df = new SimpleDateFormat("d MMM - h:mm aaa", new Locale("es", "DO"));
        String description = "Los boletines emitidos por la ONAMET los podrás ver acá. La aplicación está en constante " +
                "actualización sin consumir batería, si encuentras algún problema con la app te animamos a escribirnos con el " +
                "asunto para darle solución lo mas pronto posible.\n" +
                "\n" +
                "La información del clima se actualiza de manera frecuente, por lo que te animamos a revisarla " +
                "constantemente para que estés enterado de como sera el día. \n" +
                "\n" +
                "Nuestro trabajo es informarte, gracias por permitírnoslo.";

        Bulletin bulletin = new Bulletin(
                "Bienvenido a Onamet",
                description,
                df.format(new Date().getTime())
        );

        pushDemoData(bulletin);
    }


}