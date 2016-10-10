package com.erikmejia.onamet.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class ForecastFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private static String TAG = ForecastFragment.class.getSimpleName();

    private String[] demoData;
    private List<Forecast> forecastsData;

    public ForecastFragment() {
//        required empty constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        DEMO DATA for future forecastster
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

        Spinner spinner = (Spinner) rootView.findViewById(R.id.provinces_spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> provincesAdapter = ArrayAdapter.createFromResource(
                container.getContext(), R.array.provinces_array, R.layout.spinner_province_item);
        provincesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(provincesAdapter);

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
                    "lluvias dispersas", "3923.454", "354.223", "34 NE", "Hoy"
            );
//            Add new forecast objects to the list.
            forecastsData.add(forecast);
        }
    }

    private void loadTodayData(View rootView) {

        View todayForecast_layout = rootView.findViewById(R.id.today_forecast_layout);
//        todayForecast_layout.getBackground().setAlpha(190);

//        Custom font
        Typeface font_thin = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Brandon_thin.otf");
        Typeface font_reg = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Brandon_reg.otf");
        Typeface font_bold = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Brandon_bld.otf");

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

        maxTemperature.setTypeface(font_thin);
        description.setTypeface(font_reg);
        cityName.setTypeface(font_reg);
        date.setTypeface(font_bold);
        degrees.setTypeface(font_reg);
        windSpeed.setTypeface(font_reg);
        sunrise.setTypeface(font_reg);
        sunset.setTypeface(font_reg);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedProvince = parent.getItemAtPosition(position).toString();
        Log.d(TAG, "onItemSelected: " + selectedProvince);
        Toast.makeText(getContext(), selectedProvince, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
