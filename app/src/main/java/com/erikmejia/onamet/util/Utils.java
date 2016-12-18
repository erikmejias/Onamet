package com.erikmejia.onamet.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

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
            params.width = 590;
            params.height = 590;
            Log.d(TAG, "setAnimatedIcon: xhigh density");
        } else if (density.equalsIgnoreCase("xxhigh")) {
            params.width = 800;
            params.height = 800;
            Log.d(TAG, "setAnimatedIcon: xxhigh density");
        } else if (density.equalsIgnoreCase("xxxhigh")) {
            params.width = 900;
            params.height = 900;
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
        String MAX_TEMPERATURE = "city_name";
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

    static boolean isByDay() {
        boolean isDay;
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

//        Check if its by day.
        isDay = timeOfDay >= 6 && timeOfDay <= 18;

        return isDay;
    }

    public static void dynamicBackground(@NonNull Context context, @NonNull View frame) {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 7 && timeOfDay <= 14)
            frame.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.t1, null));
        else if (timeOfDay >= 14 && timeOfDay < 19)
            frame.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.t2, null));
        else
            frame.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.mipmap.backt2, null));

    }

    private static String whichDensity(@NonNull Context context) {

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;


        if (density < DisplayMetrics.DENSITY_MEDIUM) {
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
        }

    }
}
