/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.erikmejia.onamet.backend;

import com.erikmejia.onamet.backend.model.NewsItem;
import com.google.api.client.googleapis.auth.clientlogin.ClientLogin;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.http.*;
import javax.xml.ws.Response;

public class MyServlet extends HttpServlet {
    static Logger Log = Logger.getLogger("com.erikmejia.onamet.backend.MyServlet");

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Log.info("Refreshing Firebase Database");

        String outString;
        outString = "<p>Sending message from App Engine to Firebase";

        resp.getWriter().println(outString);

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

        pushDemoData();

        // This fires when the servlet first runs, returning all the existing values
        // only runs once, until the servlet starts up again.
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                Log.info("new value: "+ document);

                String todoText = "Don't forget to...\n\n";

                Iterator<DataSnapshot> children = dataSnapshot.getChildren().iterator();

                while(children.hasNext()){
                    DataSnapshot childSnapshot = (DataSnapshot) children.next();
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
    public void pushDemoData () {
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("news");

        Map<String, NewsItem> news = new HashMap<String, NewsItem>();
        news.put("Test 1", new NewsItem("El Titulo de noticia", "Oct 10", "Lorem Ipsum too dolor."));
        news.put("Test 2", new NewsItem("El Titulo de noticia", "Oct 11", "Lorem Ipsum too dolor."));
        news.put("Test 3", new NewsItem("El Titulo de noticia", "Oct 12", "Lorem Ipsum too dolor."));
        news.put("Test 4", new NewsItem("El Titulo de noticia", "Oct 13", "Lorem Ipsum too dolor."));

        ref.setValue(news);
    }

    /*
    * Method to retrieve forecast data from OWM.
    * */

    public void requestForecasts() {

    }
}
