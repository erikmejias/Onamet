package com.erikmejia.onamet.model;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erikmejia.onamet.R;
import com.thbs.skycons.library.SkyconView;

import java.util.List;

/**
 * Created by erik on 9/2/16.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder>{
    private String TAG = ForecastAdapter.class.getSimpleName();

    private List<Forecast> dataset;
    private Typeface font_thin;
    private Typeface font_reg;
    private Typeface font_bold;

    public ForecastAdapter(List<Forecast> receivedData){
        dataset = receivedData;
        dataset.remove(0);
        Log.d(TAG, "ForecastAdapter: " + dataset.size());
    }

    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,
                parent, false);

        font_thin = Typeface.createFromAsset(parent.getContext().getAssets(),
                "fonts/Brandon_thin.otf");
        font_reg = Typeface.createFromAsset(parent.getContext().getAssets(),
                "fonts/Brandon_reg.otf");
        font_bold = Typeface.createFromAsset(parent.getContext().getAssets(),
                "fonts/Brandon_bld.otf");

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
        holder.icon.setStrokeColor(R.color.colorPrimary);

        holder.date.setTypeface(font_reg);
        holder.max_temperature.setTypeface(font_bold);
        holder.min_temperature.setTypeface(font_thin);
        holder.forecast_description.setTypeface(font_reg);
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
        public SkyconView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            max_temperature = (TextView) itemView.findViewById(R.id.future_forecast_max_temperature);
            min_temperature = (TextView) itemView.findViewById(R.id.future_forecast_min_temperature);
            date = (TextView) itemView.findViewById(R.id.future_forecast_date_title);
            forecast_description = (TextView) itemView.findViewById(R.id.future_forecast_description);
            icon = (SkyconView) itemView.findViewById(R.id.forecast_item_icon);
        }
    }
}
