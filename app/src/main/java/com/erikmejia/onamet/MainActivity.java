package com.erikmejia.onamet;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.erikmejia.onamet.model.ForecastAdapter;
import com.erikmejia.onamet.ui.SettingsActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    //    Firebase Database Object
    /* Firebase References to objects of the Realtime database. */
    private DatabaseReference azuaReference;

    private DrawerLayout drawerLayout;
    private ListView drawerList;

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

//        DEMO DATA for future forecasts
        String[] demoData = {"today", "tomorrow", "Marcell", "Cindy", "MacBook Pro"};

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
