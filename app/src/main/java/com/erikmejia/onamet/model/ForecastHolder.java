package com.erikmejia.onamet.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.util.Utils;

/**
 * Created by erik on 11/5/16.
 */

public class ForecastHolder extends RecyclerView.ViewHolder {

    private View rootView;

    private TextView max_temperature;
    private TextView min_temperature;
    private TextView date;
    private TextView last_sync;
    private TextView forecast_description;
    public ImageView icon;

    private TextView city_name;
    private LinearLayout icon_wrapper;


    public ForecastHolder(View itemView) {
        super(itemView);
        rootView = itemView;

        if (rootView.findViewById(R.id.city_name_text) != null) {
            //        TODAY FORECAST ONLY
            this.city_name = (TextView) itemView.findViewById(R.id.city_name_text);
            this.icon_wrapper = (LinearLayout) itemView.findViewById(R.id.today_forecast_icon);
            this.max_temperature = (TextView) itemView.findViewById(R.id.forecast_max_temperature);
            this.date = (TextView) itemView.findViewById(R.id.forecast_date_title);
            this.last_sync = (TextView) itemView.findViewById(R.id.forecast_last_sync);
            this.forecast_description = (TextView) itemView.findViewById(R.id.forecast_description);
        } else {

            this.max_temperature = (TextView) itemView.findViewById(R.id.forecast_max_temperature);
            this.min_temperature = (TextView) itemView.findViewById(R.id.future_forecast_min_temperature);
            this.date = (TextView) itemView.findViewById(R.id.forecast_date_title);
            this.forecast_description = (TextView) itemView.findViewById(R.id.forecast_description);
            this.icon = (ImageView) itemView.findViewById(R.id.future_weather_icon);
        }

    }

    public View getRootView() {
        return rootView;
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

    public TextView getMax_temperature() {
        return max_temperature;
    }

    public TextView getMin_temperature() {
        return min_temperature;
    }

    public TextView getDate() {
        return date;
    }

    public TextView getLast_sync() {
        return last_sync;
    }

    public void setLast_sync(String last_sync) {
        this.last_sync.setText(last_sync);
    }

    public TextView getForecast_description() {
        return forecast_description;
    }

    public TextView getCity_name() {
        return city_name;
    }

    void setCityName(String name) {
        this.city_name.setText(name);
    }

    void setAnimatedIcon(int iconId, Context context) {
        Utils.setAnimatedIcon(this.icon_wrapper, iconId, context);
    }
}
