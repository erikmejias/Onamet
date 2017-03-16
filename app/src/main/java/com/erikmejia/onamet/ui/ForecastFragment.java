package com.erikmejia.onamet.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.model.FirebaseAdapter;
import com.erikmejia.onamet.model.Forecast;
import com.erikmejia.onamet.model.ForecastHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dmax.dialog.SpotsDialog;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


/**
 * Created by erik on 9/4/16.
 *
 * Fragment responsible of displaying main forecast information.
 */

public class ForecastFragment extends Fragment {

    private static String TAG = ForecastFragment.class.getSimpleName();
    private Context activity;

    private DatabaseReference databaseReference;
    private DatabaseReference mainReference;

    FirebaseDatabase database;
    FirebaseAdapter firebaseAdapter;

    private ProgressBar progressBar;
    private SpotsDialog progressDialog;

    private RecyclerView forecastList;

    public ForecastFragment() {
//        required empty constructor.
    }

    @Override
    public void onStart() {
        super.onStart();

//        Initializing the custom Firebase adapter
        firebaseAdapter = new FirebaseAdapter(Forecast.class,
                R.layout.forecast_item,
                ForecastHolder.class,
                databaseReference,
                activity
        );

//        Setting up animation properties for the main recycler view using a library
        ScaleInAnimationAdapter animationAdapter =
                new ScaleInAnimationAdapter(firebaseAdapter);
        animationAdapter.setInterpolator(new OvershootInterpolator());
        animationAdapter.setDuration(350);
        animationAdapter.setFirstOnly(false);

//        Setting adapter to RecyclerView and moving to the 1st position
        forecastList.setAdapter(animationAdapter);
        forecastList.scrollToPosition(0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Reading the city property to know which city id to load
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(activity);
        int PROVINCE_ID = getPrefs.getInt("city", 0);

//        Keep an overall reference to Forecast database (that way it keeps loaded in memory)
        database = FirebaseDatabase.getInstance();
        mainReference = database.getReference("forecasts/cities/");
//        Getting a reference for the selected city the user wants
        databaseReference =
                mainReference.child(PROVINCE_ID + "/forecasts");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                progressDialog.dismiss();
                forecastList.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.forecasts_layout, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.feed_loading);
        progressBar.setVisibility(View.GONE);

        progressDialog = new SpotsDialog(getActivity(), R.style.ProgressDialog); // TODO - Modify theme that match UI language.
        progressDialog.show();
        progressDialog.setMessage("cargando...");


        forecastList = (RecyclerView)
                rootView.findViewById(R.id.future_forecast_recycler_list);
        forecastList.setHasFixedSize(true);


        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        forecastList.setLayoutManager(layoutManager);


        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();
        mainReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Hooked up a listener that keeps entire database cached in memory.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.activity = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

//        Stop listening for changes in the Firebase DB.
        firebaseAdapter.cleanup();
    }

}
