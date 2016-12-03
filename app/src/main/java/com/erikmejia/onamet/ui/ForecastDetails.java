package com.erikmejia.onamet.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.erikmejia.onamet.R;
import com.erikmejia.onamet.util.Utils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.tomergoldst.tooltips.ToolTip;
import com.tomergoldst.tooltips.ToolTipsManager;

import java.util.ArrayList;

import im.dacer.androidcharts.BarView;
import im.dacer.androidcharts.PieView;


public class ForecastDetails extends AppCompatActivity {
    private static final String TAG = ForecastDetails.class.getSimpleName();
    ToolTipsManager toolTipsManager;
    RelativeLayout frame;

    private TextView date;
    private TextView maxTemperature;
    private TextView minTemperature;
    private TextView weatherDescription;
    private TextView windSpeed;
    private TextView windDirection;
    private TextView humidity;
    private TextView heat_lvls_title;
    private TextView rainPercent;

    private LinearLayout iconWrapper;

    private BarView chart;
    private ArrayList<Integer> dataset;
    private ArrayList<String> texts;

    NativeExpressAdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        iconWrapper = (LinearLayout) findViewById(R.id.today_forecast_icon);

        date = (TextView) findViewById(R.id.forecast_details_date);
        maxTemperature = (TextView) findViewById(R.id.forecast_details_max_text);
        minTemperature = (TextView) findViewById(R.id.forecast_details_min_text);
        weatherDescription = (TextView) findViewById(R.id.forecast_details_description);
        windSpeed = (TextView) findViewById(R.id.forecast_details_wind_text);
        windDirection = (TextView) findViewById(R.id.forecast_details_degrees_text);
        humidity = (TextView) findViewById(R.id.forecast_details_humidity_text);
        heat_lvls_title = (TextView) findViewById(R.id.forecast_details_head_lvls_title);

        adView = (NativeExpressAdView) findViewById(R.id.forecast_details_ad_content);
        frame = (RelativeLayout) findViewById(R.id.today_forecast_frame);

        chart = (BarView) findViewById(R.id.barchart);
        dataset = new ArrayList<>();
        texts = new ArrayList<>();

        texts.add("mañana");
        texts.add("tarde");
        texts.add("noche");

        toolTipsManager = new ToolTipsManager();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadReceivedData() {

        Bundle extras = getIntent().getExtras();

        date.setText(extras.getString(Utils.ForecastConstants.FORECAST_DATE));
        maxTemperature.setText(extras.getString(Utils.ForecastConstants.MAX_TEMPERATURE));
        minTemperature.setText(extras.getString(Utils.ForecastConstants.MIN_TEMPERATURE));
        weatherDescription.setText(extras.getString(Utils.ForecastConstants.DESCRIPTION));

        humidity.setText(extras.getString(Utils.ForecastConstants.HUMIDITY));
        windSpeed.setText(extras.getString(Utils.ForecastConstants.WIND_SPEED));
        windDirection.setText(extras.getString(Utils.ForecastConstants.WIND_DIRECTION));

        dataset.add(Integer.valueOf(extras.getString(Utils.ForecastConstants.MORNING_TEMPERATURE)));
        dataset.add(Integer.valueOf(extras.getString(Utils.ForecastConstants.NOON_TEMPERATURE)));
        dataset.add(Integer.valueOf(extras.getString(Utils.ForecastConstants.NIGHT_TEMPERATURE)));

        chart.setDataList(dataset, 43);
        chart.setBottomTextList(texts);

        Utils.setAnimatedIcon(
                iconWrapper,
                extras.getInt(Utils.ForecastConstants.ICON_ID),
                this);

        Typeface regTypeface = Typeface.createFromAsset(getAssets(), "fonts/Brandon_reg.otf");
        Typeface boldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Brandon_bld.otf");
        Typeface lightTypeface = Typeface.createFromAsset(getAssets(), "fonts/Brandon_light.otf");

        date.setTypeface(boldTypeface);
        maxTemperature.setTypeface(regTypeface);
        minTemperature.setTypeface(regTypeface);
        weatherDescription.setTypeface(regTypeface);
        heat_lvls_title.setTypeface(lightTypeface);


        windDirection.setTypeface(regTypeface);
        windSpeed.setTypeface(regTypeface);
        humidity.setTypeface(regTypeface);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadReceivedData();

        adView.loadAd(
                new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // All emulators
                        .addTestDevice("E0451870C934704914ACFF7D2E7F7F7F")
                        .build()
        );

        Utils.dynamicBackground(this, frame);
    }

    public void showTip(View view) {
        String msg;

        toolTipsManager.clear();
        int POSITION;

        switch (view.getId()) {
            case R.id.forecast_details_max_text:
                msg = "Temperatura máxima";
                POSITION = ToolTip.POSITION_RIGHT_TO;
                break;
            case R.id.forecast_details_min_text:
                msg = "Temperatura mínima";
                POSITION = ToolTip.POSITION_RIGHT_TO;
                break;
            case R.id.wind_pressure_wrapper:
                msg = "Velocidad del viento";
                POSITION = ToolTip.POSITION_ABOVE;
                break;
            case R.id.wind_direction_wrapper:
                msg = "Dirección del viento en grados";
                POSITION = ToolTip.POSITION_ABOVE;
                break;
            default:
                msg = "Nivel de humedad";
                POSITION = ToolTip.POSITION_ABOVE;
        }

        ToolTip.Builder builder = new ToolTip.Builder(
                this,
                view,
                frame,
                msg,
                POSITION
        );

        builder.setBackgroundColor(R.color.white);
//        builder.setTextColor(R.color.white);

        toolTipsManager.show(builder.build());
    }
}
