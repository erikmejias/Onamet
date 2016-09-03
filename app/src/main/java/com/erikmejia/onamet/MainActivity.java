package com.erikmejia.onamet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.erikmejia.onamet.model.Demo;
import com.erikmejia.onamet.model.ForecastAdapter;
import com.erikmejia.onamet.ui.SettingsActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
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

        String[] demoData = {"Ysanny", "Erik", "Marcell", "Cindy", "Fausto"};

//        Cache data to local disk ( OFFLINE SUPPORT ).
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        azuaReference= FirebaseDatabase.getInstance().getReference("/demo");
        RecyclerView forecastList = (RecyclerView)
                findViewById(R.id.future_forecast_recycler_list);
        forecastList.setHasFixedSize(true);
        forecastList.setLayoutManager(new LinearLayoutManager(this));

        ForecastAdapter forecastsAdapter = new ForecastAdapter(demoData);
        forecastList.setAdapter(forecastsAdapter);

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
//            Open settings ui panel.
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

}
