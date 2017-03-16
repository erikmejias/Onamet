package com.erikmejia.onamet.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.erikmejia.onamet.R;
import com.thbs.skycons.library.CloudHvRainView;
import com.thbs.skycons.library.CloudMoonView;
import com.thbs.skycons.library.CloudRainView;
import com.thbs.skycons.library.CloudSunView;
import com.thbs.skycons.library.CloudThunderView;
import com.thbs.skycons.library.CloudView;
import com.thbs.skycons.library.MoonView;
import com.thbs.skycons.library.SunView;
import com.thbs.skycons.library.WindView;

import java.util.Calendar;

/**
 * Created by erik on 10/18/16.
 */

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    public static int setIcon(int codeId) {
        switch (codeId){
            case WeatherCodes.CLEAR_SKY:
                if (isByDay())
                    return R.drawable.clear_day;
                else
                    return R.drawable.clear_night;
            case WeatherCodes.THUNDERSTORM:
                if (isByDay())
                    return R.drawable.thunder_day;
                else
                    return R.drawable.thunder_night;
            case WeatherCodes.THUNDERSTORM_RAIN:
                if (isByDay())
                    return R.drawable.thunder_day;
                else
                    return R.drawable.thunder_night;
            case WeatherCodes.THUNDERSTORM_HEAVY_RAIN:
                if (isByDay())
                    return R.drawable.storm_weather_day;
                else
                    return R.drawable.storm_weather_night;
            case WeatherCodes.DRIZZLE:
                if (isByDay())
                    return R.drawable.rainy_day;
                else
                    return R.drawable.rainy_night;
            case WeatherCodes.DRIZZLE_HEAVY:
                if (isByDay())
                    return R.drawable.rainy_day;
                else
                    return R.drawable.rainy_night;
            case WeatherCodes.RAIN_LIGHT:
                if (isByDay())
                    return R.drawable.rainy_day;
                else
                    return R.drawable.rainy_night;
            case WeatherCodes.RAIN_MODERATE:
                if (isByDay())
                    return R.drawable.rainy_day;
                else
                    return R.drawable.rainy_night;
            case WeatherCodes.RAIN_HEAVY:
                if (isByDay())
                    return R.drawable.rainy_day;
                else
                    return R.drawable.rainy_night;
            case WeatherCodes.CLOUDS_FEW:
                if (isByDay())
                    return R.drawable.partly_cloudy;
                else
                    return R.drawable.partly_cloudy_night;
            case WeatherCodes.CLOUDS_SCATTERED:
                if (isByDay())
                    return R.drawable.mostly_cloudy;
                else
                    return R.drawable.mostly_cloudy_night;
            case WeatherCodes.CLOUDS_BROKEN:
                if (isByDay())
                    return R.drawable.mostly_cloudy;
                else
                    return R.drawable.mostly_cloudy_night;
            case WeatherCodes.TROPICAL_STORM:
                if (isByDay())
                    return R.drawable.storm_weather_day;
                else
                    return R.drawable.storm_weather_night;
            case WeatherCodes.HURRICANE:
                if (isByDay())
                    return R.drawable.storm_weather_day;
                else
                    return R.drawable.storm_weather_night;
            case WeatherCodes.WINDY:
                if (isByDay())
                    return R.drawable.windy_day;
                else
                    return R.drawable.windy_night;
            default:
                return R.drawable.unknown;
        }
    }

    public static void setAnimatedIcon(LinearLayout layout, int iconId, Context context) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

//        Default animated icon size values
        params.width = 400;
        params.height = 400;

        String density = whichDensity(context);

        if (density.equalsIgnoreCase("low")) {
            params.width = 200;
            params.height = 200;
            Log.d(TAG, "setAnimatedIcon: low density");
        } else if (density.equalsIgnoreCase("medium")) {
            params.width = 280;
            params.height = 280;
            Log.d(TAG, "setAnimatedIcon: medium density");
        } else if (density.equalsIgnoreCase("high")) {
            params.width = 340;
            params.height = 340;
            Log.d(TAG, "setAnimatedIcon: high density");
        } else if (density.equalsIgnoreCase("xhigh")) {
            params.width = 500;
            params.height = 500;
            Log.d(TAG, "setAnimatedIcon: xhigh density");
        } else if (density.equalsIgnoreCase("xxhigh")) {
            params.width = 750;
            params.height = 750;
            Log.d(TAG, "setAnimatedIcon: xxhigh density");
        } else if (density.equalsIgnoreCase("xxxhigh")) {
            params.width = 880;
            params.height = 880;
            Log.d(TAG, "setAnimatedIcon: xxxhigh density");
        }

        layout.setClickable(true);


        switch (iconId){
            case WeatherCodes.CLEAR_SKY:
                if (layout.findViewById(R.id.about_toolbar) == null) {

                    if (isByDay()) {    // Check if day or night
                        SunView sunView = new SunView(context);
                        sunView.setId(R.id.about_toolbar);
                        sunView.setLayoutParams(params);
                        layout.addView(sunView);
                    } else {
                        MoonView moonView = new MoonView(context);
                        moonView.setId(R.id.about_toolbar);
                        moonView.setLayoutParams(params);
                        layout.addView(moonView);
                    }
                }
                break;
            case WeatherCodes.THUNDERSTORM:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudThunderView cloud = new CloudThunderView(context);
                    cloud.setId(R.id.about_toolbar);
                    cloud.setLayoutParams(params);
                    layout.addView(cloud);
                }
                break;
            case WeatherCodes.THUNDERSTORM_RAIN:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudThunderView cloudHvRainView = new CloudThunderView(context);
                    cloudHvRainView.setId(R.id.about_toolbar);
                    cloudHvRainView.setLayoutParams(params);
                    layout.addView(cloudHvRainView);
                }
                break;
            case WeatherCodes.THUNDERSTORM_HEAVY_RAIN:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudThunderView cloudMoonView = new CloudThunderView(context);
                    cloudMoonView.setId(R.id.about_toolbar);
                    cloudMoonView.setLayoutParams(params);
                    layout.addView(cloudMoonView);
                }
                break;
            case WeatherCodes.DRIZZLE:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudRainView rainView = new CloudRainView(context);
                    rainView.setId(R.id.about_toolbar);
                    rainView.setLayoutParams(params);
                    layout.addView(rainView);
                }
                break;
            case WeatherCodes.DRIZZLE_HEAVY:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudHvRainView cloudSunView = new CloudHvRainView(context);
                    cloudSunView.setId(R.id.about_toolbar);
                    cloudSunView.setLayoutParams(params);
                    layout.addView(cloudSunView);
                }
                break;
            case WeatherCodes.RAIN_LIGHT:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudRainView cloudThunderView = new CloudRainView(context);
                    cloudThunderView.setId(R.id.about_toolbar);
                    cloudThunderView.setLayoutParams(params);
                    layout.addView(cloudThunderView);
                }
                break;
            case WeatherCodes.RAIN_MODERATE:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudRainView cloudView = new CloudRainView(context);
                    cloudView.setId(R.id.about_toolbar);
                    cloudView.setLayoutParams(params);
                    layout.addView(cloudView);
                }
                break;
            case WeatherCodes.RAIN_HEAVY:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudHvRainView moonView = new CloudHvRainView(context);
                    moonView.setId(R.id.about_toolbar);
                    moonView.setLayoutParams(params);
                    layout.addView(moonView);
                }
                break;
            case WeatherCodes.CLOUDS_SCATTERED:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudView windView = new CloudView(context);
                    windView.setId(R.id.about_toolbar);
                    windView.setLayoutParams(params);
                    layout.addView(windView);
                }
                break;
            case WeatherCodes.CLOUDS_BROKEN:
                if (layout.findViewById(R.id.about_toolbar) == null) {

                    if (isByDay()) {
                        CloudSunView windView = new CloudSunView(context);
                        windView.setId(R.id.about_toolbar);
                        windView.setLayoutParams(params);
                        layout.addView(windView);
                    } else {
                        CloudMoonView cloudMoonView = new CloudMoonView(context);
                        cloudMoonView.setId(R.id.about_toolbar);
                        cloudMoonView.setLayoutParams(params);
                        layout.addView(cloudMoonView);
                    }
                }
                break;
            case WeatherCodes.TROPICAL_STORM:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudHvRainView windView = new CloudHvRainView(context);
                    windView.setId(R.id.about_toolbar);
                    windView.setLayoutParams(params);
                    layout.addView(windView);
                }
                break;
            case WeatherCodes.HURRICANE:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudHvRainView windView = new CloudHvRainView(context);
                    windView.setId(R.id.about_toolbar);
                    windView.setLayoutParams(params);
                    layout.addView(windView);
                }
                break;
            case WeatherCodes.WINDY:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    WindView windView = new WindView(context);
                    windView.setId(R.id.about_toolbar);
                    windView.setLayoutParams(params);
                    layout.addView(windView);
                }
                break;
            case WeatherCodes.CLOUDS_FEW:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudView windView = new CloudView(context);
                    windView.setId(R.id.about_toolbar);
                    windView.setLayoutParams(params);
                    layout.addView(windView);
                }
                break;
            default:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudView windView = new CloudView(context);
                    windView.setId(R.id.about_toolbar);
                    windView.setLayoutParams(params);
                    layout.addView(windView);
                }
        }
    }

    public interface ForecastConstants {
        String FORECAST_DATE = "forecast_date";
        String MAX_TEMPERATURE = "max_temperature";
        String MIN_TEMPERATURE = "min_temperature";
        String DESCRIPTION = "description";
        String HUMIDITY = "humidity";
        String WIND_SPEED = "wind_speed";
        String WIND_DIRECTION = "wind_direction";
        String CITY_NAME = "city_name";
        String ICON_ID = "icon_id";
        String MORNING_TEMPERATURE = "morning_temp";
        String NOON_TEMPERATURE = "noon_temp";
        String NIGHT_TEMPERATURE = "night_temp";
    }

    interface WeatherCodes {
        int CLEAR_SKY = 0;
        int THUNDERSTORM = 1;
        int THUNDERSTORM_RAIN = 2;
        int THUNDERSTORM_HEAVY_RAIN = 3;
        int DRIZZLE = 4;
        int DRIZZLE_HEAVY = 5;
        int RAIN_LIGHT = 6;
        int RAIN_MODERATE = 7;
        int RAIN_HEAVY = 8;
        int CLOUDS_FEW = 9;
        int CLOUDS_SCATTERED = 10;
        int CLOUDS_BROKEN = 11;
        int TROPICAL_STORM = 12;
        int HURRICANE = 13;
        int WINDY = 14;
        int NO_REGISTRY = 15;
    }

    interface CityBackground {
        String LA_ROMANA = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/la_romana.jpg?alt=media&token=c389ef39-f4e3-485c-840a-40b961f806f6";
        String SANTO_DOMINGO = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/santo_domingo.jpg?alt=media&token=e7274a6e-5441-4095-8f07-f611928ddfeb";
        String SANTIAGO = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/santiago.JPG?alt=media&token=f4d06bfe-9a3a-4faf-b978-724950555e75";
        String HIGUEY = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/higuey.jpg?alt=media&token=ea26574d-cdb6-4886-b9e3-c62b70c393e2";
        String BANI = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/bani.jpg?alt=media&token=96e85ba3-5c7a-47d2-a127-59faf610ede5";
        String MOCA = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/moca.jpg?alt=media&token=571d5172-fe96-4884-a053-a19e5f8f6d67";
        String PUERTO_PLATA = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/puerto_plata.jpg?alt=media&token=27624d80-50e7-45d4-8e37-a045edb6a837";
        String SABANA_DE_LA_MAR = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/sabana_dela_mar.jpg?alt=media&token=0d76a718-7174-48f4-8dd8-e38367cbd971";
        String SAN_PEDRO = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/san_pedro_1.jpg?alt=media&token=3f6c32f3-1963-4a7a-9b40-9321c5d6b741";
        String SEIBO = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/seibo.jpg?alt=media&token=9a74b4a9-87ba-4db4-a1bf-b579abbcda48";
        String PUNTA_CANA = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/backt2.jpeg?alt=media&token=5b5767b7-be10-4b04-a977-254318741e86";


        String DEFAULT = "https://firebasestorage.googleapis.com/v0/b/project-7000350159161293832.appspot.com/o/default.jpg?alt=media&token=cfbcaa6d-033a-4e6f-b04b-2d6644ac0630";
    }

    private static boolean isByDay() {
        boolean isDay;
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

//        Check if its by day.
        isDay = timeOfDay >= 6 && timeOfDay <= 18;

        return isDay;
    }

    public static void dynamicBackground(@NonNull final Context context, @NonNull final View frame,
                                         String imgUrl) {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);


        /*int PROVINCE_ID = position;
        Log.d(TAG, "dynamicBackground: " + PROVINCE_ID);
        String link;

//        if (timeOfDay >= 7 && timeOfDay <= 14)
//            frame.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.backt2, null));
//        else if (timeOfDay >= 14 && timeOfDay < 19)
//            frame.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.t1, null));
//        else
//            frame.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.backt2, null));
        switch (PROVINCE_ID) {
            case 0:
                link = CityBackground.SANTO_DOMINGO;
                break;
            case 1:
                link = CityBackground.SANTIAGO;
                break;
            case 2:
                link = CityBackground.MOCA;
                break;
            case 3:
                link = CityBackground.LA_ROMANA;
                break;
            case 4:
                link = CityBackground.HIGUEY;
                break;
            case 5:
                link = CityBackground.PUNTA_CANA;
                break;
            case 6:
                link = CityBackground.SAN_PEDRO;
                break;
            case 8:
                link = CityBackground.SEIBO;
                break;
            case 9:
                link = CityBackground.SABANA_DE_LA_MAR;
                break;
            case 12:
                link = CityBackground.PUERTO_PLATA;
                break;
            case 21:
                link = CityBackground.BANI;
                break;
            default:
                link = CityBackground.DEFAULT;
                break;
        }*/

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

//        Default size values
        params.width = 400;
        params.height = 400;

        String density = whichDensity(context);

        if (density.equalsIgnoreCase("low")) {
            params.width = 200;
            params.height = 200;
            Log.d(TAG, "background image: low density");
        } else if (density.equalsIgnoreCase("medium")) {
            params.width = 280;
            params.height = 280;
            Log.d(TAG, "background image: medium density");
        } else if (density.equalsIgnoreCase("high")) {
            params.width = 340;
            params.height = 340;
            Log.d(TAG, "background image: high density");
        } else if (density.equalsIgnoreCase("xhigh")) {
            params.width = 590;
            params.height = 590;
            Log.d(TAG, "background image: xhigh density");
        } else if (density.equalsIgnoreCase("xxhigh")) {
            params.width = 800;
            params.height = 800;
            Log.d(TAG, "background image: xxhigh density");
        } else if (density.equalsIgnoreCase("xxxhigh")) {
            params.width = 900;
            params.height = 900;
            Log.d(TAG, "background image: xxxhigh density");
        }

        SimpleTarget target = new SimpleTarget<Bitmap>(params.width, params.height) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {


                frame.setBackground(new BitmapDrawable(context.getResources(), resource));
            }
        };

        Glide.with(context.getApplicationContext())
                .load(imgUrl)
                .asBitmap()
                .placeholder(R.color.colorAccent)
                .animate(R.anim.abc_fade_in)
                .centerCrop()
                .into(target);

    }

    private static String whichDensity(@NonNull Context context) {

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;

        String response;

        switch (density){
            case DisplayMetrics.DENSITY_LOW:
                response = "low";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                response = "medium";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                response = "high";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                response = "xhigh";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                response = "xxhigh";
                break;
            default:
                response = "xxxhigh";
                break;

        }

        /*if (density < DisplayMetrics.DENSITY_MEDIUM) {
            return "low";
        } else if (density >= DisplayMetrics.DENSITY_MEDIUM && density <
                DisplayMetrics.DENSITY_HIGH) {
            return "medium";
        } else if (density >= DisplayMetrics.DENSITY_HIGH && density <
                DisplayMetrics.DENSITY_XHIGH) {
            return "high";
        } else if (density >= DisplayMetrics.DENSITY_XHIGH && density <
                DisplayMetrics.DENSITY_XXHIGH) {
            return "xhigh";
        } else if (density >= DisplayMetrics.DENSITY_XXHIGH && density <
                DisplayMetrics.DENSITY_XXXHIGH) {
            return "xxhigh";
        } else {
            return "xxxhigh";
        }*/

        return response;

    }

    public static void applyFontForToolbarTitle(Activity context, int toolbarID){
        Toolbar toolbar = (Toolbar) context.findViewById(toolbarID);
        for(int i = 0; i < toolbar.getChildCount(); i++){
            View view = toolbar.getChildAt(i);
            if(view instanceof TextView){
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.
                        createFromAsset(context.getAssets(), "fonts/Brandon_bld.otf");
                if(tv.getText().equals(toolbar.getTitle())){
                    tv.setTypeface(titleFont);
                    break;
                }
            }
        }
    }
}
