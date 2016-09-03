package com.erikmejia.onamet.model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erikmejia.onamet.R;

/**
 * Created by erik on 9/2/16.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder>{
    private String TAG = ForecastAdapter.class.getSimpleName();

    private String[] dataset;

    public ForecastAdapter(String[] receivedData){
        dataset = receivedData;
        Log.d(TAG, "ForecastAdapter: " + dataset.length);
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
        holder.max_temperature.setText("27º");
        holder.min_temperature.setText("24º");
        holder.date.setText("Sep 2");
        holder.icon.setText("SUNNY");
        holder.forecast_description.setText("dia soleado");
        Log.d(TAG, "onBindViewHolder: " + dataset[position]);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView max_temperature;
        public TextView min_temperature;
        public TextView date;
        public TextView icon;
        public TextView forecast_description;

        public ViewHolder(View itemView) {
            super(itemView);
            max_temperature = (TextView) itemView.findViewById(R.id.future_forecast_max_temperature);
            min_temperature = (TextView) itemView.findViewById(R.id.future_forecast_min_temperature);
            date = (TextView) itemView.findViewById(R.id.future_forecast_date_title);
            icon = (TextView) itemView.findViewById(R.id.future_forecast_icon);
            forecast_description = (TextView) itemView.findViewById(R.id.future_forecast_description);
        }
    }
}
