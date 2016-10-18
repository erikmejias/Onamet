package com.erikmejia.onamet.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erikmejia.onamet.R;
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

    private LinearLayout.LayoutParams params;

    public ForecastAdapter(List<Forecast> receivedData){
        dataset = receivedData;
        Log.d(TAG, "ForecastAdapter: " + dataset.size());
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

        holder.icon_wrapper.setLayoutParams(params);

        setIcon(holder.icon_wrapper, dataset.get(position).getIconId());

        holder.date.setTypeface(font_reg);
        holder.max_temperature.setTypeface(font_bold);
        holder.min_temperature.setTypeface(font_thin);
        holder.forecast_description.setTypeface(font_reg);
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
        public LinearLayout icon_wrapper;

        public ViewHolder(View itemView) {
            super(itemView);
            max_temperature = (TextView) itemView.findViewById(R.id.forecast_max_temperature);
            min_temperature = (TextView) itemView.findViewById(R.id.future_forecast_min_temperature);
            date = (TextView) itemView.findViewById(R.id.forecast_date_title);
            forecast_description = (TextView) itemView.findViewById(R.id.forecast_description);
            icon_wrapper = (LinearLayout) itemView.findViewById(R.id.forecast_item_icon_wrapper);
        }
    }

    public void setIcon(LinearLayout layout, int iconId) {
        switch (iconId){
            case 1:
                SunView sunView = new SunView(context);
                params.width = 220;
                params.height = 220;
                layout.addView(sunView);
                break;
            case 2:
                CloudFogView cloud = new CloudFogView(context);
                params.width = 220;
                params.height = 220;
                layout.addView(cloud);
                break;
            case 3:
                CloudHvRainView cloudHvRainView = new CloudHvRainView(context);
                params.width = 220;
                params.height = 220;
                layout.addView(cloudHvRainView);
                break;
            case 4:
                CloudMoonView cloudMoonView = new CloudMoonView(context);
                params.width = 220;
                params.height = 220;
                layout.addView(cloudMoonView);
                break;
            case 5:
                CloudRainView rainView = new CloudRainView(context);
                params.width = 220;
                params.height = 220;
                layout.addView(rainView);
                break;
            case 6:
                CloudSunView cloudSunView = new CloudSunView(context);
                params.width = 220;
                params.height = 220;
                layout.addView(cloudSunView);
                break;
            case 7:
                CloudThunderView cloudThunderView = new CloudThunderView(context);
                params.width = 220;
                params.height = 220;
                layout.addView(cloudThunderView);
                break;
            case 8:
                CloudView cloudView = new CloudView(context);
                params.width = 220;
                params.height = 220;
                layout.addView(cloudView);
                break;
            case 9:
                MoonView moonView = new MoonView(context);
                params.width = 220;
                params.height = 220;
                layout.addView(moonView);
                break;
            case 10:
                WindView windView = new WindView(context);
                params.width = 220;
                params.height = 220;
                layout.addView(windView);
                break;
        }
    }
}
