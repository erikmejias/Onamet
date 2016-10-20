package com.erikmejia.onamet.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.util.Utils;
import com.thbs.skycons.library.Cloud;
import com.thbs.skycons.library.CloudFogView;
import com.thbs.skycons.library.CloudHvRainView;
import com.thbs.skycons.library.CloudMoonView;
import com.thbs.skycons.library.CloudRainView;
import com.thbs.skycons.library.CloudSunView;
import com.thbs.skycons.library.CloudThunderView;
import com.thbs.skycons.library.CloudView;
import com.thbs.skycons.library.MoonView;
import com.thbs.skycons.library.SkyconView;
import com.thbs.skycons.library.SunView;
import com.thbs.skycons.library.WindView;

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
    private Context context;
    private OnItemClickListener listener;

    private LinearLayout.LayoutParams params;

    public ForecastAdapter(List<Forecast> receivedData, OnItemClickListener listener){
        this.dataset = receivedData;
        this.listener = listener;
        Log.d(TAG, "ForecastAdapter: " + dataset.size());

//        Removes today forecast from future list
        dataset.remove(0);
    }

    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,
                parent, false);


        context = parent.getContext();
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        font_thin = Typeface.createFromAsset(parent.getContext().getAssets(),
                "fonts/Brandon_thin.otf");
        font_reg = Typeface.createFromAsset(parent.getContext().getAssets(),
                "fonts/Brandon_reg.otf");
        font_bold = Typeface.createFromAsset(parent.getContext().getAssets(),
                "fonts/Brandon_bld.otf");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        Setting up received forecast data to it's appropiate holder view.
        holder.max_temperature.setText(dataset.get(position).getMax());
        holder.min_temperature.setText(dataset.get(position).getMin());
        holder.date.setText(dataset.get(position).getDate());
        holder.forecast_description.setText(dataset.get(position).getDescription());
        holder.icon.setImageResource(Utils.bulletinIcon(dataset.get(position).getIconId()));


//        holder.icon_wrapper.setLayoutParams(params);
//        setIcon(holder.icon_wrapper, dataset.get(position).getIconId());

        holder.date.setTypeface(font_reg);
        holder.max_temperature.setTypeface(font_bold);
        holder.min_temperature.setTypeface(font_thin);
        holder.forecast_description.setTypeface(font_reg);

//        Calling the method that handles click selections.
        holder.bind(dataset.get(position), listener);

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
        public ImageView icon;
//        public LinearLayout icon_wrapper;

        public ViewHolder(View itemView) {
            super(itemView);
            max_temperature = (TextView) itemView.findViewById(R.id.forecast_max_temperature);
            min_temperature = (TextView) itemView.findViewById(R.id.future_forecast_min_temperature);
            date = (TextView) itemView.findViewById(R.id.forecast_date_title);
            forecast_description = (TextView) itemView.findViewById(R.id.forecast_description);
            icon = (ImageView) itemView.findViewById(R.id.future_weather_icon);
//            icon_wrapper = (LinearLayout) itemView.findViewById(R.id.forecast_item_icon_wrapper);

            itemView.setClickable(true);
        }

        public void bind(final Forecast forecastItem, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(forecastItem);
                }
            });
        }
    }
}
