package com.erikmejia.onamet.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erikmejia.onamet.R;

/**
 * Created by erik on 11/5/16.
 */

public class ForecastHolder extends RecyclerView.ViewHolder {

    public TextView max_temperature;
    public TextView min_temperature;
    public TextView date;
    public TextView forecast_description;
    public ImageView icon;



    public ForecastHolder(View itemView) {
        super(itemView);
        max_temperature = (TextView) itemView.findViewById(R.id.forecast_max_temperature);
        min_temperature = (TextView) itemView.findViewById(R.id.future_forecast_min_temperature);
        date = (TextView) itemView.findViewById(R.id.forecast_date_title);
        forecast_description = (TextView) itemView.findViewById(R.id.forecast_description);
        icon = (ImageView) itemView.findViewById(R.id.future_weather_icon);
//            icon_wrapper = (LinearLayout) itemView.findViewById(R.id.forecast_item_icon_wrapper);

        itemView.setClickable(true);
    }

    public void setMaxTemperature(String temperature) {
//        max_temperature = (TextView) mView.findViewById(R.id.forecast_max_temperature);
        max_temperature.setText(temperature);
    }

    public void setMinTemperature(String temperature) {
//        min_temperature = (TextView) mView.findViewById(R.id.future_forecast_min_temperature);
        min_temperature.setText(temperature);
    }

    public void setDate(String newDate) {
//        date = (TextView) mView.findViewById(R.id.forecast_date_title);
        date.setText(newDate);
    }

    public void setDescription(String summary) {
//        forecast_description = (TextView) mView.findViewById(R.id.forecast_description);
        forecast_description.setText(summary);
    }

    public void setIcon(int iconId) {
//        icon = (ImageView) mView.findViewById(R.id.future_weather_icon);
        icon.setImageResource(iconId);
    }

    public void bind(final Forecast forecastItem, final OnForecastItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(forecastItem);
            }
        });
    }
}
