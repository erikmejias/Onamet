package com.erikmejia.onamet.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.model.Forecast;
import com.erikmejia.onamet.model.ForecastAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erik on 9/4/16.
 *
 * Fragment responsible of displaying main forecast information.
 */

public class ForecastFragment extends Fragment {
    private static String TAG = ForecastFragment.class.getSimpleName();

    private String[] demoData;
    private List<Forecast> forecastsData;

    public ForecastFragment() {
//        required empty constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        DEMO DATA for future forecasts
        demoData = new String[]{"today", "tomorrow", "Marcell", "Cindy",
                "MacBook Pro", "Alvin", "Eduardo", "Brayan", "Jorge", "Joel",
                "Jeissy", "David", "Daniel", "Fausto"};

        loadDemoData();
        Log.d(TAG, "onCreate: created " + forecastsData.size() + " forecasts entries");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.forecasts_layout, container, false);

//        Cache data to local disk ( OFFLINE SUPPORT ).
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

//        azuaReference= FirebaseDatabase.getInstance().getReference("/demo");
        loadTodayData(rootView);
        RecyclerView forecastList = (RecyclerView)
                rootView.findViewById(R.id.future_forecast_recycler_list);
        forecastList.setHasFixedSize(true);
        forecastList.setLayoutManager(new LinearLayoutManager(container.getContext()));

        ForecastAdapter forecastsAdapter = new ForecastAdapter(forecastsData);
        forecastList.setAdapter(forecastsAdapter);

        /*NativeExpressAdView adView = (NativeExpressAdView) rootView.findViewById(R.id.forecastAd);

        AdRequest request = new AdRequest.Builder()
                .addTestDevice("E0451870C934704914ACFF7D2E7F7F7F")
                .build();
        adView.loadAd(request); // Load ad into the view*/

        return rootView;
    }

    public void loadDemoData() {
//        Initialize a forecast objects holder.
        forecastsData = new ArrayList<Forecast>();

        for (int i = 0; i < 5; i++) {
            Forecast forecast = new Forecast(
                    "25º", "23º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Azua", "234,134 habitantes",
                    "a lot of water", "3923.454", "354.223", "34 NE", "Mie " + i
            );
//            Add new forecast objects to the list.
            forecastsData.add(forecast);
        }
    }

    private void loadTodayData(View rootView) {
        Forecast todayForecast = forecastsData.get(0);
//        Get references prior to setting text.

        TextView cityName = (TextView) rootView.findViewById(R.id.city_name_text);
        TextView date = (TextView) rootView.findViewById(R.id.today_date_text);
        TextView population = (TextView) rootView.findViewById(R.id.today_city_population);
        TextView description = (TextView) rootView.findViewById(R.id.today_forecast_description_text);
        TextView windSpeed = (TextView) rootView.findViewById(R.id.today_wind_text);
        TextView degrees = (TextView) rootView.findViewById(R.id.today_degrees_text);
        TextView sunset = (TextView) rootView.findViewById(R.id.today_sunset_text);
        TextView sunrise = (TextView) rootView.findViewById(R.id.today_sunrise_text);
        TextView maxTemperature = (TextView) rootView.findViewById(R.id.today_forecast_max_text);

        cityName.setText(todayForecast.getName());
        date.setText(todayForecast.getDate());
        population.setText(todayForecast.getPopulation());
        description.setText(todayForecast.getDescription());
        windSpeed.setText(todayForecast.getSpeed());
        degrees.setText(todayForecast.getDeg());
        sunrise.setText(todayForecast.getSunrise_time());
        sunset.setText(todayForecast.getSunset_time());
        maxTemperature.setText(todayForecast.getMax());
    }
}
