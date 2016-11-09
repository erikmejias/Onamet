package com.erikmejia.onamet.ui;

import android.content.Intent;
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
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.model.City;
import com.erikmejia.onamet.model.FirebaseAdapter;
import com.erikmejia.onamet.model.Forecast;
import com.erikmejia.onamet.model.ForecastAdapter;
import com.erikmejia.onamet.model.ForecastHolder;
import com.erikmejia.onamet.model.OnForecastItemClickListener;
import com.erikmejia.onamet.util.Utils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by erik on 9/4/16.
 *
 * Fragment responsible of displaying main forecast information.
 */

public class ForecastFragment extends Fragment{
    private static String TAG = ForecastFragment.class.getSimpleName();
    private DatabaseReference databaseReference;
    FirebaseAdapter firebaseAdapter;

    private List<Forecast> forecastsData;
    public List<City> provincesData;
    private FirebaseRecyclerAdapter<Forecast, ForecastHolder> tAdapter;
    private RecyclerView forecastList;
    private LinearLayoutManager linearLayoutManager;

    public ForecastFragment() {
//        required empty constructor.
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        Initializing list of City objects
//        this.provincesData = new ArrayList<>();
//        forecastsData = new ArrayList<>();

//        loadDemoData();

//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        databaseReference = FirebaseDatabase.getInstance().getReference("/forecasts/cities/0/forecasts");
        Query query = databaseReference;

        firebaseAdapter = new FirebaseAdapter(Forecast.class,
                R.layout.forecast_item,
                ForecastHolder.class,
                query);

//        loadDemoData();
//        Log.d(TAG, "onCreate: created " + forecastsData.size() + " forecasts entries");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.forecasts_layout, container, false);

//        TODO - Fix alpha property issue in today's forecast background image


//        Cache data to local disk ( OFFLINE SUPPORT ).
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


//        loadTodayData(rootView);

        forecastList = (RecyclerView)
                rootView.findViewById(R.id.future_forecast_recycler_list);
        forecastList.setHasFixedSize(true);
//        Makes smooth scrolling inside the NestedScrollView
        forecastList.setNestedScrollingEnabled(false);


        /*ForecastAdapter forecastsAdapter = new ForecastAdapter(forecastsData, new OnForecastItemClickListener() {
            @Override
            public void onItemClicked(Forecast forecastItem) {
                Intent intent;
                intent = new Intent(getContext(), ForecastDetails.class);
                startActivity(intent);
            }
        }, getContext());*/

//        Firebase Adapter V2.0
//        DatabaseReference forecastReference =
//                FirebaseDatabase.getInstance().getReference("/forecasts/cities/0/forecasts/");

        Log.d(TAG, "onCreateView: databaseReference " + String.valueOf(databaseReference));



//        Setting adapter to recyclerview
        forecastList.setAdapter(firebaseAdapter);

//        Log.d(TAG, "onCreateView: database reference" + databaseReference.getRef());

        /*
        NativeExpressAdView adView = (NativeExpressAdView) rootView.findViewById(R.id.forecastAd);

        AdRequest request = new AdRequest.Builder()
                .addTestDevice("E0451870C934704914ACFF7D2E7F7F7F")
                .build();
        adView.loadAd(request); // Load ad into the view
        */

        return rootView;
    }

    public void loadDemoData() {
//        Initialize a forecast objects holder.
//        forecastsData = new ArrayList<>();
        City city = new City(
                "Santo Domingo",
                "345,245",
                "19.345",
                "-23.545",
                forecastsData
                );

        /*for (int i = 0; i < 5; i++) {
            Forecast forecast = new Forecast(
                    "25º", "23º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Azua", "234,134 habitantes",
                    "lluvias dispersas", "3923.454", "354.223", "34 NE", "Hoy"
            );
//            Add new forecast objects to the list.
            forecastsData.add(forecast);
        }*/

        Forecast forecast = new Forecast(
                "26º",
                "25º",
                "23 m/s",
                "15",
                "6:37",
                "7:01",
                "33 NE",
                "Hoy",
                "lluvias ligeras",
                5
        );
        Forecast forecast2 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 8",
                "cielo claro",
                2
        );
        Forecast forecast3 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 9",
                "nubes dispersas",
                3
        );
        Forecast forecast4 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 10",
                "lluvias ligeras",
                4
        );
        Forecast forecast5 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 11",
                "lluvias ligeras",
                5
        );
        Forecast forecast6 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 12",
                "lluvias ligeras",
                6
        );
        Forecast forecast7 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 13",
                "lluvias ligeras",
                7
        );
        Forecast forecast8 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 14",
                "lluvias ligeras",
                3
        );
        Forecast forecast9 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 15",
                "lluvias ligeras",
                4
        );
        Forecast forecast10 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 16",
                "lluvias ligeras",
                5
        );
        Forecast forecast11 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 17",
                "lluvias ligeras",
                7
        );
        Forecast forecast12 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 18",
                "lluvias ligeras",
                1
        );
        Forecast forecast13 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 19",
                "lluvias ligeras",
                9
        );
        Forecast forecast14 = new Forecast(
                "23º",
                "19º",
                "23",
                "15",
                "6:37",
                "7:01",
                "33",
                "Nov 20",
                "lluvias ligeras",
                11
        );


        forecastsData.add(forecast);
        forecastsData.add(forecast2);
        forecastsData.add(forecast3);
        forecastsData.add(forecast4);
        forecastsData.add(forecast5);
        forecastsData.add(forecast6);
        forecastsData.add(forecast7);
        forecastsData.add(forecast8);
        forecastsData.add(forecast9);
        forecastsData.add(forecast10);
        forecastsData.add(forecast11);
        forecastsData.add(forecast12);
        forecastsData.add(forecast13);
        forecastsData.add(forecast14);
    }

    public void addCity(City city) {
        this.provincesData.add(city);
        Log.d(TAG, "addCity: added " + city.getName());
    }

    private void loadTodayData(final View rootView) {

//        Custom font
        final Typeface font_thin = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Brandon_thin.otf");
        final Typeface font_reg = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Brandon_reg.otf");
        final Typeface font_bold = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Brandon_bld.otf");

        Forecast todayForecast = forecastsData.get(0);
//        Get references prior to setting text.

        LinearLayout icon_wrapper = (LinearLayout) rootView.findViewById(R.id.today_forecast_icon);

        View todayForecastWrapper = rootView.findViewById(R.id.today_forecast_frame);

//        Setting alpha transparent to today forecast background image.
//        rootView.findViewById(R.id.activity_main).getBackground().setAlpha(140);


//        params.width = 550;
//        params.height = 550;
//        SunView cloudView = new SunView(getContext());
//        cloudView.setStrokeColor(Color.WHITE);
//        icon_wrapper.addView(cloudView);

        Utils.setAnimatedIcon(icon_wrapper, todayForecast.getIconId(), getContext());

//        SunView sunView = (SunView) rootView.findViewById(R.id.today_forecast_icon_item);
//        sunView.setStrokeColor(Color.WHITE);



        TextView cityName = (TextView) rootView.findViewById(R.id.city_name_text);
        TextView date = (TextView) rootView.findViewById(R.id.today_date_text);
        TextView description = (TextView) rootView.findViewById(R.id.today_forecast_description_text);
        TextView windSpeed = (TextView) rootView.findViewById(R.id.today_wind_text);
        TextView degrees = (TextView) rootView.findViewById(R.id.today_degrees_text);
        TextView sunset = (TextView) rootView.findViewById(R.id.today_sunset_text);
        TextView sunrise = (TextView) rootView.findViewById(R.id.today_sunrise_text);
        TextView maxTemperature = (TextView) rootView.findViewById(R.id.today_forecast_max_text);

        maxTemperature.setTypeface(font_thin);
        description.setTypeface(font_reg);
        cityName.setTypeface(font_bold);
        date.setTypeface(font_reg);
        degrees.setTypeface(font_bold);
        windSpeed.setTypeface(font_bold);
        sunrise.setTypeface(font_bold);
        sunset.setTypeface(font_bold);

        date.setText(todayForecast.getDate());
        cityName.setText("Santo Domingo");
        description.setText(todayForecast.getDescription());
        windSpeed.setText(todayForecast.getSpeed());
        degrees.setText(todayForecast.getDeg());
        sunrise.setText(todayForecast.getSunrise_time());
        sunset.setText(todayForecast.getSunset_time());
        maxTemperature.setText(todayForecast.getMax());

        todayForecastWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getContext(), ForecastDetails.class);
                startActivity(intent);
            }
        });
    }

}
