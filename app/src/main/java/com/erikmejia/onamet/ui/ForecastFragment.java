package com.erikmejia.onamet.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.model.City;
import com.erikmejia.onamet.model.FirebaseAdapter;
import com.erikmejia.onamet.model.Forecast;
import com.erikmejia.onamet.model.ForecastHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dmax.dialog.SpotsDialog;


/**
 * Created by erik on 9/4/16.
 *
 * Fragment responsible of displaying main forecast information.
 */

public class ForecastFragment extends Fragment {

    private static String TAG = ForecastFragment.class.getSimpleName();
    private DatabaseReference databaseReference;
    private DatabaseReference mainReference;
    FirebaseDatabase database;
    FirebaseAdapter firebaseAdapter;
    private static boolean calledAlready = false;
    private int PROVINCE_ID;

    public ForecastFragment() {
//        required empty constructor.
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            this.PROVINCE_ID = savedInstanceState.getInt("city");
            Toast.makeText(getActivity(), "picked " + PROVINCE_ID, Toast.LENGTH_SHORT).show();
        }


        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        database = FirebaseDatabase.getInstance();
        mainReference = database.getReference("forecasts/cities/");
        databaseReference =
                mainReference.child(PROVINCE_ID + "/forecasts");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.forecasts_layout, container, false);


        firebaseAdapter = new FirebaseAdapter(Forecast.class,
                R.layout.forecast_item,
                ForecastHolder.class,
                databaseReference,
                getActivity());
        final ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.feed_loading);
        progressBar.setVisibility(View.GONE);
        final SpotsDialog progressDialog = new SpotsDialog(getActivity());
        progressDialog.show();
        progressDialog.setMessage("cargando...");


        final RecyclerView forecastList = (RecyclerView)
                rootView.findViewById(R.id.future_forecast_recycler_list);
        forecastList.setHasFixedSize(true);
        forecastList.setLayoutManager(new LinearLayoutManager(getActivity()));

//        Setting adapter to RecyclerView
        forecastList.setAdapter(firebaseAdapter);

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

        return rootView;
    }

    public void runOver() {
        for (int i = 0; i < 23; i++) {
            mainReference = database.getReference("forecasts/cities/" + i + "forecasts");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Stop listening for changes in the Firebase DB.
        firebaseAdapter.cleanup();
    }

}
