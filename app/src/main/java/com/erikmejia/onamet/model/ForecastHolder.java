package com.erikmejia.onamet.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.util.Utils;

/**
 * Created by erik on 11/5/16.
 */

public class ForecastHolder extends RecyclerView.ViewHolder {

    private TextView max_temperature;
    private TextView min_temperature;
    private TextView date;
    private TextView forecast_description;
    public ImageView icon;



    public ForecastHolder(View itemView) {
        super(itemView);

        this.max_temperature = (TextView) itemView.findViewById(R.id.forecast_max_temperature);
        this.min_temperature = (TextView) itemView.findViewById(R.id.future_forecast_min_temperature);
        this.date = (TextView) itemView.findViewById(R.id.forecast_date_title);
        this.forecast_description = (TextView) itemView.findViewById(R.id.forecast_description);
        this.icon = (ImageView) itemView.findViewById(R.id.future_weather_icon);
//            icon_wrapper = (LinearLayout) itemView.findViewById(R.id.forecast_item_icon_wrapper);

//        itemView.setClickable(true);
    }


    public void setMaxTemperature(String temperature) {
        max_temperature.setText(temperature);
    }

    public void setMinTemperature(String temperature) {
        min_temperature.setText(temperature);
    }

    public void setDate(String newDate) {
        date.setText(newDate);
    }

    public void setDescription(String summary) {
        forecast_description.setText(summary);
    }

    public void setIcon(int iconId) {
        icon.setImageResource(Utils.bulletinIcon(iconId));
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
