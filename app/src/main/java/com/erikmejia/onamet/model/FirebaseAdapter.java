package com.erikmejia.onamet.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.ui.ForecastDetails;
import com.erikmejia.onamet.util.Utils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.firebase.database.Query;

/**
 * Created by erik on 11/5/16.
 * Custom Adapter that handles and displays Forecast Objects from the
 * Firebase Database and logic for displaying ads in Forecasts content list.
 */

public class FirebaseAdapter extends FirebaseRecyclerAdapter<Forecast, ForecastHolder> {

    private static final String TAG = FirebaseAdapter.class.getSimpleName();

    private static final int TODAY = 0;
    private static final int FUTURE = 1;

    private AdRequest adRequest;

    private Typeface font_thin;
    private Typeface font_reg;
    private Typeface font_bold;
    private Typeface font_light;

    private Context context;


    public FirebaseAdapter(Class<Forecast> modelClass, int layout,
                           Class<ForecastHolder> viewHolderClass, Query ref,
                           Context context) {

        super(modelClass, layout, viewHolderClass, ref);
        this.context = context;
        font_thin = Typeface.createFromAsset(context.getAssets(),
                "fonts/Brandon_thin.otf");
        font_reg = Typeface.createFromAsset(context.getAssets(),
                "fonts/Brandon_reg.otf");
        font_bold = Typeface.createFromAsset(context.getAssets(),
                "fonts/Brandon_bld.otf");
        font_light = Typeface.createFromAsset(context.getAssets(),
                "fonts/Brandon_light.otf");

//        adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("E0451870C934704914ACFF7D2E7F7F7F")
//                .build();
    }

    @Override
    public void populateViewHolder(final ForecastHolder viewHolder, final Forecast model, final int position) {

        if (getItemViewType(position) == TODAY) {

            viewHolder.setCityName(model.getCity_name());
            viewHolder.setAnimatedIcon(model.getIconId(), context);

            viewHolder.setDate(model.getDate());
            viewHolder.setMaxTemperature(model.getMax());
            viewHolder.setDescription(model.getDescription());
            viewHolder.setLast_sync(model.getSync_time());

            viewHolder.getDate().setTypeface(font_reg);
            viewHolder.getLast_sync().setTypeface(font_light);
            viewHolder.getCity_name().setTypeface(font_bold);
            viewHolder.getMax_temperature().setTypeface(font_bold);
            viewHolder.getForecast_description().setTypeface(font_reg);


        } else if (getItemViewType(position) == FUTURE) {
            viewHolder.setIcon(model.getIconId());
            viewHolder.setMinTemperature(model.getMin());

            viewHolder.setDate(model.getDate());
            viewHolder.setMaxTemperature(model.getMax());
            viewHolder.setDescription(model.getDescription());

            viewHolder.getDate().setTypeface(font_reg);
            viewHolder.getMax_temperature().setTypeface(font_bold);
            viewHolder.getMin_temperature().setTypeface(font_thin);
            viewHolder.getForecast_description().setTypeface(font_reg);
        }


//        Listener for click touches on each Forecast items (including TODAY)
        viewHolder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ForecastDetails.class);
//                Organizing the data prior sending
                String date = model.getDate();
                String max_temperature = model.getMax();
                String min_temperature = model.getMin();
                String description = model.getDescription();
                String humidity = model.getHumidity();
                String wind_speed = model.getSpeed();
                String wind_direction = model.getDeg();
                String morning_temp = model.getMorning_temp();
                String noon_temp = model.getNoon_temp();
                String night_temp = model.getNight_temp();
                String province_name = model.getCity_name();
                int icon_id = model.getIconId();

//                Attach this forecast data to the new Activity
                intent.putExtra(
                        Utils.ForecastConstants.CITY_NAME,
                        province_name
                );
                intent.putExtra(
                        Utils.ForecastConstants.FORECAST_DATE,
                        date
                );
                intent.putExtra(
                        Utils.ForecastConstants.MAX_TEMPERATURE,
                        max_temperature
                );
                intent.putExtra(
                        Utils.ForecastConstants.MIN_TEMPERATURE,
                        min_temperature
                );
                intent.putExtra(
                        Utils.ForecastConstants.DESCRIPTION,
                        description
                );
                intent.putExtra(
                        Utils.ForecastConstants.HUMIDITY,
                        humidity
                );
                intent.putExtra(
                        Utils.ForecastConstants.WIND_SPEED,
                        wind_speed
                );
                intent.putExtra(
                        Utils.ForecastConstants.WIND_DIRECTION,
                        wind_direction
                );
                intent.putExtra(
                        Utils.ForecastConstants.ICON_ID,
                        icon_id
                );
                intent.putExtra(
                        Utils.ForecastConstants.MORNING_TEMPERATURE,
                        morning_temp
                );
                intent.putExtra(
                        Utils.ForecastConstants.NOON_TEMPERATURE,
                        noon_temp
                );
                intent.putExtra(
                        Utils.ForecastConstants.NIGHT_TEMPERATURE,
                        night_temp
                );

                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public ForecastHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
//            In today load the corresponding layout and set the AD into it.
            case TODAY:

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_forecast_item,
                        parent, false);
                /*NativeExpressAdView adView;

                adView = (NativeExpressAdView) view.findViewById(R.id.today_forecast_ad);

                adView.loadAd(adRequest);*/
                return new ForecastHolder(view);
//            In future load the corresponding layout.
            case FUTURE:

                View future = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,
                        parent, false);

                return new ForecastHolder(future);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {

//        In the first position load the TODAY layout, in others load FUTURE layout
        if (position == 0)
            return TODAY;
        else
            return FUTURE;
    }

}
