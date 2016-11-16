package com.erikmejia.onamet.ui;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erikmejia.onamet.R;
import com.erikmejia.onamet.util.Utils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.tomergoldst.tooltips.ToolTip;
import com.tomergoldst.tooltips.ToolTipsManager;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class ForecastDetails extends AppCompatActivity {
    private static final String TAG = ForecastDetails.class.getSimpleName();
    ToolTipsManager toolTipsManager;
    RelativeLayout frame;

    NativeExpressAdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_details);

        adView = (NativeExpressAdView) findViewById(R.id.forecast_details_ad_content);
        frame = (RelativeLayout) findViewById(R.id.today_forecast_frame);

        adView.loadAd(
                new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // All emulators
                        .addTestDevice("E0451870C934704914ACFF7D2E7F7F7F")
                        .build()
        ); // Load ad into the view

        changeTextFonts();

        toolTipsManager = new ToolTipsManager();

        dynamicBackground();

    }

    public void changeTextFonts() {

        Bundle extras = getIntent().getExtras();
        LinearLayout iconWrapper = (LinearLayout) findViewById(R.id.today_forecast_icon);

        TextView date;
        TextView maxTemperature;
        TextView minTemperature;
        TextView weatherDescription;
        TextView windSpeed;
        TextView windDirection;
        TextView humidity;
        TextView rainPercent;

        date = (TextView) findViewById(R.id.forecast_details_date);
        maxTemperature = (TextView) findViewById(R.id.forecast_details_max_text);
        minTemperature = (TextView) findViewById(R.id.forecast_details_min_text);
        weatherDescription = (TextView) findViewById(R.id.forecast_details_description);
        windSpeed = (TextView) findViewById(R.id.forecast_details_wind_text);
        windDirection = (TextView) findViewById(R.id.forecast_details_degrees_text);
        humidity = (TextView) findViewById(R.id.forecast_details_humidity_text);
//        rainPercent = (TextView) findViewById(R.id.forecast_details_rain_percent_text);

        Typeface regTypeface = Typeface.createFromAsset(getAssets(), "fonts/Brandon_reg.otf");
        Typeface boldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Brandon_bld.otf");

        date.setText(extras.getString(Utils.ForecastConstants.FORECAST_DATE));
        maxTemperature.setText(extras.getString(Utils.ForecastConstants.MAX_TEMPERATURE));
        minTemperature.setText(extras.getString(Utils.ForecastConstants.MIN_TEMPERATURE));
        weatherDescription.setText(extras.getString(Utils.ForecastConstants.DESCRIPTION));

        humidity.setText(extras.getString(Utils.ForecastConstants.HUMIDITY));
        windSpeed.setText(extras.getString(Utils.ForecastConstants.WIND_SPEED));
        windDirection.setText(extras.getString(Utils.ForecastConstants.WIND_DIRECTION));

        Utils.setAnimatedIcon(
                iconWrapper,
                extras.getInt(Utils.ForecastConstants.ICON_ID),
                this);

        date.setTypeface(boldTypeface);
        maxTemperature.setTypeface(regTypeface);
        minTemperature.setTypeface(regTypeface);
        weatherDescription.setTypeface(regTypeface);


        windDirection.setTypeface(regTypeface);
        windSpeed.setTypeface(regTypeface);
        humidity.setTypeface(regTypeface);
//        rainPercent.setTypeface(regTypeface);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void showTip(View view) {
        String msg;

        switch (view.getId()) {
            case R.id.forecast_details_max_text:
                msg = "Temperatura máxima";
                break;
            case R.id.forecast_details_min_text:
                msg = "Temperatura mínima";
                break;
            case R.id.wind_pressure_wrapper:
                msg = "Presión del viento";
                break;
            case R.id.wind_direction_wrapper:
                msg = "Dirección del viento en grados";
                break;
            default:
                msg = "Nivel de humedad";
        }

        ToolTip.Builder builder = new ToolTip.Builder(
                this,
                view,
                frame,
                msg,
                ToolTip.POSITION_ABOVE
        );

        builder.setBackgroundColor(R.color.colorPrimaryDark);

        toolTipsManager.show(builder.build());
    }

    public void dynamicBackground() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        Log.d(TAG, "dynamicBackground: " + timeOfDay);

        if (timeOfDay >= 7 && timeOfDay <=18)
            frame.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.rt5, null));
        else
            frame.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.rt1, null));
    }
}
