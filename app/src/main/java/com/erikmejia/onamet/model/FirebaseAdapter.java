package com.erikmejia.onamet.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.ui.ForecastDetails;
import com.erikmejia.onamet.util.Utils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.firebase.database.Query;

/**
 * Created by erik on 11/5/16.
 */

public class FirebaseAdapter extends FirebaseRecyclerAdapter<Forecast, ForecastHolder> {
    private static final String TAG = FirebaseAdapter.class.getSimpleName();

    private static final int TODAY = 0;
    private static final int FORECAST_TYPE = 1;
    private static final int AD_TYPE = 2;

    private View futureView;

    private Typeface font_thin;
    private Typeface font_reg;
    private Typeface font_bold;

    private Context context;
    private int layout;

    public FirebaseAdapter(Class<Forecast> modelClass, int layout,
                           Class<ForecastHolder> viewHolderClass, Query ref, Context context) {
        super(modelClass, layout, viewHolderClass, ref);
        this.context = context;
        this.layout = layout;
        font_thin = Typeface.createFromAsset(context.getAssets(),
                "fonts/Brandon_thin.otf");
        font_reg = Typeface.createFromAsset(context.getAssets(),
                "fonts/Brandon_reg.otf");
        font_bold = Typeface.createFromAsset(context.getAssets(),
                "fonts/Brandon_bld.otf");
    }

    @Override
    public void populateViewHolder(final ForecastHolder viewHolder, final Forecast model, final int position) {

        if (getItemViewType(position) == TODAY) {

            viewHolder.setCityName(model.getCity_name());
            viewHolder.setAnimatedIcon(model.getIconId(), context);

            viewHolder.setDate(model.getDate());
            viewHolder.setMaxTemperature(model.getMax());
            viewHolder.setDescription(model.getDescription());

            viewHolder.getDate().setTypeface(font_reg);
            viewHolder.getCity_name().setTypeface(font_bold);
            viewHolder.getMax_temperature().setTypeface(font_bold);
            viewHolder.getForecast_description().setTypeface(font_reg);


        } else if (getItemViewType(position) == FORECAST_TYPE) {
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
                int icon_id = model.getIconId();

//                Attach this data to the new Activity
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

                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public ForecastHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        NativeExpressAdView adView;
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,
                parent, false);

        switch (viewType){
            case TODAY:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_forecast_item,
                        parent, false);
                Log.d(TAG, "onCreateViewHolder: returned TODAY");
                return new ForecastHolder(view);
            case FORECAST_TYPE:
                View future = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,
                        parent, false);
                Log.d(TAG, "onCreateViewHolder: returned FUTURE");
                return new ForecastHolder(future);
            case AD_TYPE:
                float density = parent.getContext().getResources().getDisplayMetrics().density;
                int height = Math.round(AdSize.BANNER.getHeight() * density);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, height);

                adView = (NativeExpressAdView) LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item_layout,
                        parent, false);
                AdRequest request = new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .addTestDevice("E0451870C934704914ACFF7D2E7F7F7F")
                        .build();
//            adView.setAdSize(AdSize.BANNER);
                adView.setLayoutParams(params);

                adView.loadAd(request);

                Log.d(TAG, "onCreateViewHolder: returned AD");
                return new ForecastHolder(adView); // TODO: Add an adViewHolder
            default:
                return new ForecastHolder(root);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return TODAY;
        else
            return FORECAST_TYPE;
    }
}
