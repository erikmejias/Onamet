package com.erikmejia.onamet.model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erikmejia.onamet.R;

import java.util.List;

/**
 * Created by erik on 9/2/16.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder>{
    private String TAG = ForecastAdapter.class.getSimpleName();

    private List<Forecast> dataset;

    public ForecastAdapter(List<Forecast> receivedData){
        dataset = receivedData;
        dataset.remove(0);
        Log.d(TAG, "ForecastAdapter: " + dataset.size());
    }

    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,
                parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Setting up received forecast data to it's appropiate holder view.
        holder.max_temperature.setText(dataset.get(position).getMax());
        holder.min_temperature.setText(dataset.get(position).getMin());
        holder.date.setText(dataset.get(position).getDate());
        holder.forecast_description.setText(dataset.get(position).getDescription());
        Log.d(TAG, "onBindViewHolder: " + dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView max_temperature;
        public TextView min_temperature;
        public TextView date;
        public TextView forecast_description;

        public ViewHolder(View itemView) {
            super(itemView);
            max_temperature = (TextView) itemView.findViewById(R.id.future_forecast_max_temperature);
            min_temperature = (TextView) itemView.findViewById(R.id.future_forecast_min_temperature);
            date = (TextView) itemView.findViewById(R.id.future_forecast_date_title);
            forecast_description = (TextView) itemView.findViewById(R.id.future_forecast_description);
        }
    }
}
