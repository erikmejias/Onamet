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
        this.provincesData = new ArrayList<>();
        forecastsData = new ArrayList<>();

//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference("/forecasts/cities/0/forecasts");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot citySnapshot: dataSnapshot.getChildren()) {
                    Forecast forecast = citySnapshot.getValue(Forecast.class);
                    Log.i(TAG, "loaded: " + forecast.getDescription());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d(TAG, "onCreate: created " + provincesData.size() + " city entries");
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

//        azuaReference= FirebaseDatabase.getInstance().getReference("/demo");


//        loadTodayData(rootView);
        DatabaseReference cityReference =
                FirebaseDatabase.getInstance().getReference("/forecasts/cities/0");
        cityReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot citySnapshot: dataSnapshot.getChildren()) {
                    City city = citySnapshot.getValue(City.class);
                    TextView cityName = (TextView) rootView.findViewById(R.id.city_name_text);
                    Log.d(TAG, "loaded city: " + city.getName());
                    cityName.setText(city.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final RecyclerView forecastList = (RecyclerView)
                rootView.findViewById(R.id.future_forecast_recycler_list);
        forecastList.setHasFixedSize(true);
//        Makes smooth scrolling inside the NestedScrollView
        forecastList.setNestedScrollingEnabled(false);

        if (container != null) {
            forecastList.setLayoutManager(new LinearLayoutManager(container.getContext()));
        }

        ForecastAdapter forecastsAdapter = new ForecastAdapter(forecastsData, new OnForecastItemClickListener() {
            @Override
            public void onItemClicked(Forecast forecastItem) {
                Intent intent;
                intent = new Intent(getContext(), ForecastDetails.class);
                startActivity(intent);
            }
        }, getContext());

//        Firebase Adapter V2.0
        DatabaseReference forecastReference =
                FirebaseDatabase.getInstance().getReference("/forecasts/cities/0/forecasts/");

        final FirebaseRecyclerAdapter<Forecast, ForecastHolder> fAdapter =
                new FirebaseRecyclerAdapter<Forecast, ForecastHolder>(
                        Forecast.class,
                        R.layout.forecast_item,
                        ForecastHolder.class,
                        forecastReference
                ) {
                    @Override
                    protected void populateViewHolder(ForecastHolder viewHolder, Forecast model, int position) {
                        viewHolder.setDate(model.getDate());
                        viewHolder.setIcon(position);
                        viewHolder.setMaxTemperature(model.getMax());
                        viewHolder.setMinTemperature(model.getMin());
                        viewHolder.setDescription(model.getDescription());
                    }
        };

        Log.d(TAG, "onCreateView: databaseReference " + String.valueOf(databaseReference));

        firebaseAdapter = new FirebaseAdapter(Forecast.class,
                R.layout.forecast_item,
                ForecastHolder.class,
                databaseReference);

        firebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                Log.d(TAG, "onCreateView: adapter quantity" + firebaseAdapter.getItemCount());
            }
        });



//        Setting adapter to recyclerview
        forecastList.setAdapter(firebaseAdapter);

        Log.d(TAG, "onCreateView: database reference" + databaseReference.getRef());

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

        /*for (int i = 0; i < 5; i++) {
            Forecast forecast = new Forecast(
                    "25º", "23º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Azua", "234,134 habitantes",
                    "lluvias dispersas", "3923.454", "354.223", "34 NE", "Hoy"
            );
//            Add new forecast objects to the list.
            forecastsData.add(forecast);
        }*/

        /*Forecast forecast = new Forecast(
                "25º", "23º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "La Romana", "234,134 habitantes",
                "cielo nublado", "3923.454", "354.223", "34 NE", "Hoy", 5
        );

        Forecast forecast1 = new Forecast(
                "27º", "24º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Azua", "234,134 habitantes",
                "cielo nublado", "3923.454", "354.223", "34 NE", "Mar 01", 2
        );

        Forecast forecast2 = new Forecast(
                "34º", "32º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Monte Plata", "234,134 habitantes",
                "cielo despejado", "3923.454", "354.223", "34 NE", "Jue 20", 3
        );

        Forecast forecast3 = new Forecast(
                "19º", "16º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Santo Domingo", "234,134 habitantes",
                "tormenta electrica", "3923.454", "354.223", "34 NE", "Vie 21", 4
        );

        Forecast forecast4 = new Forecast(
                "37º", "35º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Sabana de la Mar", "234,134 habitantes",
                "cielo claro", "3923.454", "354.223", "34 NE", "Sab 22", 5
        );

        Forecast forecast5 = new Forecast(
                "19º", "16º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Hato Mayor", "234,134 habitantes",
                "cielo despejado", "3923.454", "354.223", "34 NE", "Dom 23", 6
        );

        Forecast forecast6 = new Forecast(
                "25º", "23º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "San Francisco", "234,134 habitantes",
                "lluvias dispersas", "3923.454", "354.223", "34 NE", "Lun 24", 7
        );Forecast forecast7 = new Forecast(
                "25º", "23º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "La Romana", "234,134 habitantes",
                "cielo despejado", "3923.454", "354.223", "34 NE", "Mar 25", 8
        );

        Forecast forecast8 = new Forecast(
                "27º", "24º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Azua", "234,134 habitantes",
                "cielo nublado", "3923.454", "354.223", "34 NE", "Mie 26", 2
        );

        Forecast forecast9 = new Forecast(
                "34º", "32º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Monte Plata", "234,134 habitantes",
                "cielo despejado", "3923.454", "354.223", "34 NE", "Jue 27", 3
        );

        Forecast forecast10 = new Forecast(
                "19º", "16º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Santo Domingo", "234,134 habitantes",
                "tormenta electrica", "3923.454", "354.223", "34 NE", "Vie 28", 4
        );

        Forecast forecast11 = new Forecast(
                "37º", "35º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Sabana de la Mar", "234,134 habitantes",
                "cielo claro", "3923.454", "354.223", "34 NE", "Sab 29", 5
        );

        Forecast forecast12 = new Forecast(
                "19º", "16º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "Hato Mayor", "234,134 habitantes",
                "cielo despejado", "3923.454", "354.223", "34 NE", "Dom 30", 6
        );

        Forecast forecast13 = new Forecast(
                "25º", "23º", "23 m/s", "33%", "6:35 AM", "7:22 PM", "San Francisco", "234,134 habitantes",
                "lluvias dispersas", "3923.454", "354.223", "34 NE", "Lun 31", 7
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
        forecastsData.add(forecast1);*/
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
