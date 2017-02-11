package com.erikmejia.onamet.ui;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
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

import com.db.chart.animation.Animation;
import com.db.chart.model.Bar;
import com.db.chart.model.BarSet;
import com.db.chart.model.LineSet;
import com.db.chart.model.Point;
import com.db.chart.renderer.AxisRenderer;
import com.db.chart.view.BarChartView;
import com.db.chart.view.LineChartView;
import com.db.chart.view.StackBarChartView;
import com.erikmejia.onamet.R;
import com.erikmejia.onamet.util.Utils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.tomergoldst.tooltips.ToolTip;
import com.tomergoldst.tooltips.ToolTipsManager;

import java.util.ArrayList;



public class ForecastDetails extends AppCompatActivity {
    private static final String TAG = ForecastDetails.class.getSimpleName();
    private ToolTipsManager toolTipsManager;
    private RelativeLayout frame;

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

    private LineChartView lineChartView;
    private LineSet lineSet;

    private Bundle extras;

//    NativeExpressAdView adView;

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

//        adView = (NativeExpressAdView) findViewById(R.id.forecast_details_ad_content);
        frame = (RelativeLayout) findViewById(R.id.today_forecast_frame);

        extras = getIntent().getExtras();

        lineSet = new LineSet();
        lineSet.addPoint(new Point("MAÑANA", Integer.valueOf(extras.getString(Utils.ForecastConstants.MORNING_TEMPERATURE))));
        lineSet.addPoint(new Point("TARDE", Integer.valueOf(extras.getString(Utils.ForecastConstants.NOON_TEMPERATURE))));
        lineSet.addPoint(new Point("NOCHE", Integer.valueOf(extras.getString(Utils.ForecastConstants.NIGHT_TEMPERATURE))));

        lineChartView = (LineChartView) findViewById(R.id.linechart);


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

        date.setText(extras.getString(Utils.ForecastConstants.FORECAST_DATE));
        maxTemperature.setText(extras.getString(Utils.ForecastConstants.MAX_TEMPERATURE));
        minTemperature.setText(extras.getString(Utils.ForecastConstants.MIN_TEMPERATURE));
        weatherDescription.setText(extras.getString(Utils.ForecastConstants.DESCRIPTION));

        humidity.setText(extras.getString(Utils.ForecastConstants.HUMIDITY));
        windSpeed.setText(extras.getString(Utils.ForecastConstants.WIND_SPEED));
        windDirection.setText(extras.getString(Utils.ForecastConstants.WIND_DIRECTION));

        Typeface regTypeface = Typeface.createFromAsset(getAssets(), "fonts/Brandon_reg.otf");
        Typeface boldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Brandon_bld.otf");
        Typeface lightTypeface = Typeface.createFromAsset(getAssets(), "fonts/Brandon_light.otf");


        lineSet.setColor(getResources().getColor(R.color.white));
        lineSet.setDotsColor(getResources().getColor(R.color.white));
        lineSet.setFill(getResources().getColor(R.color.colorAccent));
        lineSet.setThickness(2f);
        lineSet.setSmooth(true);

        lineChartView.setAxisColor(getResources().getColor(R.color.white));
        lineChartView.setLabelsColor(getResources().getColor(R.color.white));
        lineChartView.setYAxis(false);
        lineChartView.setYLabels(AxisRenderer.LabelPosition.NONE);
        lineChartView.setAxisBorderValues(0, 40, 1); // It sets the maximum capacity, allowing for individuality each one.
        lineChartView.setTypeface(regTypeface);
//        Paint paint = new Paint();
//        paint.setColor(getResources().getColor(R.color.white));
//        lineChartView.setGrid(4, 4, paint);

        lineChartView.addData(lineSet);
        lineChartView.show(new Animation(2800));


        Utils.setAnimatedIcon(
                iconWrapper,
                extras.getInt(Utils.ForecastConstants.ICON_ID),
                this);


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

        /*adView.loadAd(
                new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // All emulators
                        .addTestDevice("E0451870C934704914ACFF7D2E7F7F7F")
                        .build()
        );*/

        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        int PROVINCE_ID = getPrefs.getInt("city", 0);

        Utils.dynamicBackground(this, frame, PROVINCE_ID);
    }

    public void showTip(View view) {
        String msg;

        toolTipsManager.clear();
        int POSITION;

        switch (view.getId()) {
            case R.id.forecast_details_max_text:
                msg = "Temperatura máxima";
                POSITION = ToolTip.POSITION_ABOVE;
                break;
            case R.id.forecast_details_min_text:
                msg = "Temperatura mínima";
                POSITION = ToolTip.POSITION_BELOW;
                break;
            case R.id.wind_pressure_wrapper:
                msg = "Velocidad viento";
                POSITION = ToolTip.POSITION_ABOVE;
                break;
            case R.id.wind_direction_wrapper:
                msg = "Dirección viento";
                POSITION = ToolTip.POSITION_ABOVE;
                break;
            default:
                msg = "Humedad";
                POSITION = ToolTip.POSITION_ABOVE;
        }

        ToolTip.Builder builder = new ToolTip.Builder(
                this,
                view,
                frame,
                msg,
                POSITION
        );

        builder.setBackgroundColor(getResources().getColor(R.color.black_alpha_40));
//        builder.setTextColor(R.color.white);

        toolTipsManager.show(builder.build());
    }
}
