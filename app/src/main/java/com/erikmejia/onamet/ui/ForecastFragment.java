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

public class ForecastFragment extends Fragment {

    private static String TAG = ForecastFragment.class.getSimpleName();
    private DatabaseReference databaseReference;
    FirebaseAdapter firebaseAdapter;

    private RecyclerView forecastList;

    public ForecastFragment() {
//        required empty constructor.
    }

    @Override
    public void onStart() {
        super.onStart();
        //        Cache data to local disk ( OFFLINE SUPPORT ).
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (databaseReference == null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
            databaseReference =
                    database.getReference("forecasts/cities/1/forecasts");
        }

//        loadDemoData();
//        Log.d(TAG, "onCreate: created " + forecastsData.size() + " forecasts entries");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.forecasts_layout, container, false);

//        TODO - Fix alpha property issue in today's forecast background image



//        loadTodayData(rootView);
        firebaseAdapter = new FirebaseAdapter(Forecast.class,
                R.layout.forecast_item,
                ForecastHolder.class,
                databaseReference,
                getActivity());

        forecastList = (RecyclerView)
                rootView.findViewById(R.id.future_forecast_recycler_list);
        forecastList.setHasFixedSize(true);
        forecastList.setLayoutManager(new LinearLayoutManager(getActivity()));



//        Setting adapter to recyclerview
        forecastList.setAdapter(firebaseAdapter);
        Log.d(TAG, "onCreateView: firebaseAdapterCount " +
                firebaseAdapter.getItemCount());

        /*
        NativeExpressAdView adView = (NativeExpressAdView) rootView.findViewById(R.id.forecastAd);

        AdRequest request = new AdRequest.Builder()
                .addTestDevice("E0451870C934704914ACFF7D2E7F7F7F")
                .build();
        adView.loadAd(request); // Load ad into the view
        */

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Stop listening for changes in the Firebase DB.
        firebaseAdapter.cleanup();
    }

/*    private void loadTodayData(final View rootView) {

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
    }*/

}
