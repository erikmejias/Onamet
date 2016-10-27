package com.erikmejia.onamet.model;

import android.content.Context;
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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;

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
    private OnForecastItemClickListener listener;
    private static final int AD_TYPE = 0;
    private static final int FORECAST_TYPE = 1;
    private Context receivedContext;


    public ForecastAdapter(List<Forecast> receivedData, OnForecastItemClickListener listener, Context context){
        this.dataset = receivedData;
        this.listener = listener;
        this.receivedContext = context;

        font_thin = Typeface.createFromAsset(receivedContext.getAssets(),
                "fonts/Brandon_thin.otf");
        font_reg = Typeface.createFromAsset(receivedContext.getAssets(),
                "fonts/Brandon_reg.otf");
        font_bold = Typeface.createFromAsset(receivedContext.getAssets(),
                "fonts/Brandon_bld.otf");
        Log.d(TAG, "ForecastAdapter: " + dataset.size());

//        Removes today forecast from future list
        dataset.remove(0);
    }

    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        NativeExpressAdView adView;
        ViewHolder holder;

        if (viewType == AD_TYPE) {
            /*adView = new NativeExpressAdView(receivedContext);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId("ca-app-pub-6005843157698202/3043293572");

            float density = receivedContext.getResources().getDisplayMetrics().density;
            int height = Math.round(AdSize.BANNER.getHeight() * density);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, height);
            adView.setLayoutParams(params);

            AdRequest request = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            adView.loadAd(request);*/

            adView = (NativeExpressAdView) LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item_layout,
                    parent, false);
            AdRequest request = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("E0451870C934704914ACFF7D2E7F7F7F")
                    .build();
            adView.loadAd(request);
            holder = new ViewHolder(adView);

        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,
                    parent, false);
            holder = new ViewHolder(view);

        }


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        Setting up received forecast data to it's appropiate holder view, avoiding when its an AD1
        if (position != 3) {
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

        public void bind(final Forecast forecastItem, final OnForecastItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(forecastItem);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 3)
            return AD_TYPE;
        return FORECAST_TYPE;
    }
}
