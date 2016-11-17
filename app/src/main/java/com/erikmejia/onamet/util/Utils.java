package com.erikmejia.onamet.util;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.erikmejia.onamet.R;
import com.thbs.skycons.library.Cloud;
import com.thbs.skycons.library.CloudFogView;
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

    public static int bulletinIcon(int codeId) {
        switch (codeId){
            case WeatherCodes.CLEAR_SKY:
                return R.drawable.clear_day;
            case WeatherCodes.THUNDERSTORM:
                return R.drawable.thunder_weather;
            case WeatherCodes.THUNDERSTORM_RAIN:
                return R.drawable.thunder_day;
            case WeatherCodes.THUNDERSTORM_HEAVY_RAIN:
                return R.drawable.thunder_weather;
            case WeatherCodes.DRIZZLE:
                return R.drawable.rainy_weather;
            case WeatherCodes.DRIZZLE_HEAVY:
                return R.drawable.rainy_day;
            case WeatherCodes.RAIN_LIGHT:
                return R.drawable.rainy_day;
            case WeatherCodes.RAIN_MODERATE:
                return R.drawable.rainy_day;
            case WeatherCodes.RAIN_HEAVY:
                return R.drawable.rainy_weather;
            case WeatherCodes.CLOUDS_FEW:
                return R.drawable.cloudy_weather;
            case WeatherCodes.CLOUDS_SCATTERED:
                return R.drawable.mostly_cloudy;
            case WeatherCodes.CLOUDS_BROKEN:
                return R.drawable.partly_cloudy;
            case WeatherCodes.TROPICAL_STORM:
                return R.drawable.storm_weather;
            case WeatherCodes.HURRICANE:
                return R.drawable.storm_weather;
            case WeatherCodes.WINDY:
                return R.drawable.windy_day;
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
        } else if (density.equalsIgnoreCase("medium")) {
            params.width = 400;
            params.height = 400;
        } else if (density.equalsIgnoreCase("high")) {
            params.width = 600;
            params.height = 600;
        } else if (density.equalsIgnoreCase("xhigh")) {
            params.width = 800;
            params.height = 800;
        } else if (density.equalsIgnoreCase("xxhigh")) {
            params.width = 1000;
            params.height = 1000;
        } else if (density.equalsIgnoreCase("xxxhigh")) {
            params.width = 1200;
            params.height = 1200;
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
        public static final String FORECAST_DATE = "forecast_date";
        public static final String MAX_TEMPERATURE = "max_temperature";
        public static final String MIN_TEMPERATURE = "min_temperature";
        public static final String DESCRIPTION = "description";
        public static final String HUMIDITY = "humidity";
        public static final String WIND_SPEED = "wind_speed";
        public static final String WIND_DIRECTION = "wind_direction";
        public static final String CITY_NAME = "city_name";
        public static final String ICON_ID = "icon_id";
    }

    public interface WeatherCodes {
        public static final int CLEAR_SKY = 0;
        public static final int THUNDERSTORM = 1;
        public static final int THUNDERSTORM_RAIN = 2;
        public static final int THUNDERSTORM_HEAVY_RAIN = 3;
        public static final int DRIZZLE = 4;
        public static final int DRIZZLE_HEAVY = 5;
        public static final int RAIN_LIGHT = 6;
        public static final int RAIN_MODERATE = 7;
        public static final int RAIN_HEAVY = 8;
        public static final int CLOUDS_FEW = 9;
        public static final int CLOUDS_SCATTERED = 10;
        public static final int CLOUDS_BROKEN = 11;
        public static final int TROPICAL_STORM = 12;
        public static final int HURRICANE = 13;
        public static final int WINDY = 14;
        public static final int NO_REGISTRY = 15;
    }

    public static boolean isByDay() {
        boolean isDay;
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

//        Check if its by day.
        isDay = timeOfDay >= 7 && timeOfDay <= 18;

        return isDay;
    }

    private static String whichDensity(Context context) {

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
