package com.erikmejia.onamet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    //    Firebase Database Object
    /* Firebase References to objects of the Realtime database. */
    private DatabaseReference azuaReference;

    public TextView cityName;
    public TextView minTemp;
    public TextView maxTemp;
    public TextView night_temp;
    public TextView speed;
    public TextView pressure;
    public TextView humidity;
    public TextView clouds;
    public TextView rain;
    public TextView degrees;
    public TextView weather_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cityName = (TextView) findViewById(R.id.city_name_textview);
        minTemp = (TextView) findViewById(R.id.min_temp_textview);
        maxTemp = (TextView) findViewById(R.id.max_temp_textview);
        night_temp = (TextView) findViewById(R.id.night_temp_textview);
        speed = (TextView) findViewById(R.id.speed);
        pressure = (TextView) findViewById(R.id.pressure);
        humidity = (TextView) findViewById(R.id.humidity);
        clouds = (TextView) findViewById(R.id.cloud_textview);
        rain = (TextView) findViewById(R.id.rain_textview);
        degrees = (TextView) findViewById(R.id.deg_textview);
        weather_description = (TextView) findViewById(R.id.weather_descripton_textview);


//        Cache data to local disk ( OFFLINE SUPPORT ).
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        azuaReference= FirebaseDatabase.getInstance().getReference("forecasts/monteplata");

        renderChanges();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: initiated");

        Log.d(TAG, "onStart: finished");

    }

    public void renderChanges() {

        azuaReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String _cityName = dataSnapshot.child("city").child("name").getValue(String.class);
                String _clouds = String.valueOf(dataSnapshot.child("list").child("0").child("clouds").getValue(Long.class));
                String _degree = String.valueOf(dataSnapshot.child("list").child("0").child("deg").getValue(Long.class));
                String _humidity = String.valueOf(dataSnapshot.child("list").child("0").child("humidity").getValue(Long.class));
                String _pressure = String.valueOf(dataSnapshot.child("list").child("0").child("pressure").getValue(Long.class));
                String _rain = String.valueOf(dataSnapshot.child("list").child("0").child("rain").getValue(Long.class));
                String _speed = String.valueOf(dataSnapshot.child("list").child("0").child("speed").getValue(Long.class));

                String _min = String.valueOf(dataSnapshot.child("list").child("0").child("temp").child("min").getValue(Long.class));
                String _max = String.valueOf(dataSnapshot.child("list").child("0").child("temp").child("max").getValue(Long.class));
                String _night = String.valueOf(dataSnapshot.child("list").child("0").child("temp").child("night").getValue(Long.class));

                String _description = dataSnapshot.child("list").child("0").child("weather/0").child("description").getValue(String.class);

                cityName.setText(_cityName);
                clouds.setText(_clouds);
                degrees.setText(_degree);
                humidity.setText(_humidity);
                pressure.setText(_pressure);
                rain.setText(_rain);
                speed.setText(_speed);
                minTemp.setText(_min);
                maxTemp.setText(_max);
                night_temp.setText(_night);
                weather_description.setText(_description);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
