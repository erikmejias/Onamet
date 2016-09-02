package com.erikmejia.onamet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.erikmejia.onamet.ui.SettingsActivity;
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


//        Cache data to local disk ( OFFLINE SUPPORT ).
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        azuaReference= FirebaseDatabase.getInstance().getReference("forecasts/laromana");

        renderChanges();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: initiated");

        Log.d(TAG, "onStart: finished");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Check which selection is being made.
        switch (item.getItemId()) {
            case R.id.settings:
                showSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSettings() {
//        Start explicit intent
        Intent intent;
        intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }

    public void renderChanges() {

        azuaReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String _cityName = dataSnapshot.child("name").getValue(String.class);
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

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
