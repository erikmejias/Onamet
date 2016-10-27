package com.erikmejia.onamet.ui;

import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.erikmejia.onamet.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class ForecastDetails extends AppCompatActivity {

    NativeExpressAdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_details);


        adView = (NativeExpressAdView) findViewById(R.id.forecast_details_ad_content);

        changeTextFonts();
    }

    public void changeTextFonts() {
        TextView cityName;
        TextView maxTemperature;
        TextView minTemperature;
        TextView weatherDescription;
        TextView sixAM;
        TextView eightAM;
        TextView tenAM;
        TextView twuelvePM;
        TextView twoPM;
        TextView fourPM;
        TextView sixPM;
        TextView eightPM;
        TextView tenPM;
        TextView twuelveAM;
        TextView windSpeed;
        TextView windDirection;
        TextView sunrise;
        TextView sunset;

        cityName = (TextView) findViewById(R.id.forecast_details_city_name);
        maxTemperature = (TextView) findViewById(R.id.forecast_details_max_text);
        minTemperature = (TextView) findViewById(R.id.forecast_details_min_text);
        weatherDescription = (TextView) findViewById(R.id.forecast_details_description);
        sixAM = (TextView) findViewById(R.id.forecast_details_six_am);
        eightAM = (TextView) findViewById(R.id.forecast_details_eight_am);
        tenAM = (TextView) findViewById(R.id.forecast_details_ten_am);
        twuelvePM = (TextView) findViewById(R.id.forecast_details_twuelve_pm);
        twoPM = (TextView) findViewById(R.id.forecast_details_two_pm);
        fourPM = (TextView) findViewById(R.id.forecast_details_four_pm);
        sixPM = (TextView) findViewById(R.id.forecast_details_six_pm);
        eightPM = (TextView) findViewById(R.id.forecast_details_eight_pm);
        tenPM = (TextView) findViewById(R.id.forecast_details_ten_pm);
        twuelveAM = (TextView) findViewById(R.id.forecast_details_twuelve_am);
        windDirection = (TextView) findViewById(R.id.forecast_details_wind_text);
        windSpeed = (TextView) findViewById(R.id.forecast_details_degrees_text);
        sunrise = (TextView) findViewById(R.id.forecast_details_sunrise_text);
        sunset = (TextView) findViewById(R.id.forecast_details_sunset_text);

        Typeface regTypeface = Typeface.createFromAsset(getAssets(), "fonts/Brandon_reg.otf");
        Typeface thinTypeface = Typeface.createFromAsset(getAssets(), "fonts/Brandon_thin.otf");

        cityName.setTypeface(regTypeface);
        maxTemperature.setTypeface(regTypeface);
        minTemperature.setTypeface(regTypeface);
        weatherDescription.setTypeface(regTypeface);

        sixAM.setTypeface(thinTypeface);
        eightAM.setTypeface(thinTypeface);
        tenAM.setTypeface(thinTypeface);
        twuelvePM.setTypeface(thinTypeface);
        twoPM.setTypeface(thinTypeface);
        fourPM.setTypeface(thinTypeface);
        sixPM.setTypeface(thinTypeface);
        eightPM.setTypeface(thinTypeface);
        tenPM.setTypeface(thinTypeface);
        twuelveAM.setTypeface(thinTypeface);
        windDirection.setTypeface(regTypeface);
        windSpeed.setTypeface(regTypeface);
        sunrise.setTypeface(regTypeface);
        sunset.setTypeface(regTypeface);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adView.loadAd(
                new AdRequest.Builder()
                .addTestDevice("E0451870C934704914ACFF7D2E7F7F7F")
                .build()
        ); // Load ad into the view
    }
}
