package com.erikmejia.onamet.model;

import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by erik on 11/5/16.
 */

public class FirebaseAdapter extends FirebaseRecyclerAdapter<Forecast, ForecastHolder> {
    private static final String TAG = FirebaseAdapter.class.getSimpleName();

    public FirebaseAdapter(Class<Forecast> modelClass, int layout,
                           Class<ForecastHolder> viewHolderClass, Query ref) {

        super(modelClass, layout, viewHolderClass, ref);
        Log.d(TAG, "FirebaseAdapter: CONSTRUCTOR called. DB Query is " + ref.toString());
    }

    @Override
    public void populateViewHolder(ForecastHolder viewHolder, Forecast model, int position) {
        Log.d(TAG, "populateViewHolder EXECUTED!!!");

        viewHolder.setDate(model.getDate());
        viewHolder.setIcon(position);
        viewHolder.setMaxTemperature(model.getMax());
        viewHolder.setMinTemperature(model.getMin());
        viewHolder.setDescription(model.getDescription());

    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
