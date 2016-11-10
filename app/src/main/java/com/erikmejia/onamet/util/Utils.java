package com.erikmejia.onamet.util;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

import com.erikmejia.onamet.R;
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

/**
 * Created by erik on 10/18/16.
 */

public class Utils {

    public static int bulletinIcon(int codeId) {
        switch (codeId){
            case 1:
                return R.drawable.clear_day;
            case 2:
                return R.drawable.cloudy_weather;
            case 3:
                return R.drawable.mostly_cloudy;
            case 4:
                return R.drawable.rainy_day;
            case 5:
                return R.drawable.rainy_night;
            case 6:
                return R.drawable.storm_weather;
            case 7:
                return R.drawable.haze_day;
            case 8:
                return R.drawable.partly_cloudy;
            case 9:
                return R.drawable.thunder_day;
            case 10:
                return R.drawable.mostly_cloudy;
            default:
                return R.drawable.clear_day;
        }
    }

    public static void setAnimatedIcon(LinearLayout layout, int iconId, Context context) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.width = 750;
        params.height = 750;


        switch (iconId){
            case 1:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    SunView sunView = new SunView(context);
                    sunView.setId(R.id.about_toolbar);
                    sunView.setLayoutParams(params);
                    layout.addView(sunView);
                }
                break;
            case 0:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudFogView cloud = new CloudFogView(context);
                    cloud.setId(R.id.about_toolbar);
                    cloud.setLayoutParams(params);
                    layout.addView(cloud);
                }
                break;
            case 2:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudHvRainView cloudHvRainView = new CloudHvRainView(context);
                    cloudHvRainView.setId(R.id.about_toolbar);
                    cloudHvRainView.setLayoutParams(params);
                    layout.addView(cloudHvRainView);
                }
                break;
            case 3:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudMoonView cloudMoonView = new CloudMoonView(context);
                    cloudMoonView.setId(R.id.about_toolbar);
                    cloudMoonView.setLayoutParams(params);
                    layout.addView(cloudMoonView);
                }
                break;
            case 4:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudRainView rainView = new CloudRainView(context);
                    rainView.setId(R.id.about_toolbar);
                    rainView.setLayoutParams(params);
                    layout.addView(rainView);
                }
                break;
            case 5:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudSunView cloudSunView = new CloudSunView(context);
                    cloudSunView.setId(R.id.about_toolbar);
                    cloudSunView.setLayoutParams(params);
                    layout.addView(cloudSunView);
                }
                break;
            case 6:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudThunderView cloudThunderView = new CloudThunderView(context);
                    cloudThunderView.setId(R.id.about_toolbar);
                    cloudThunderView.setLayoutParams(params);
                    layout.addView(cloudThunderView);
                }
                break;
            case 7:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    CloudView cloudView = new CloudView(context);
                    cloudView.setId(R.id.about_toolbar);
                    cloudView.setLayoutParams(params);
                    layout.addView(cloudView);
                }
                break;
            case 8:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    MoonView moonView = new MoonView(context);
                    moonView.setId(R.id.about_toolbar);
                    moonView.setLayoutParams(params);
                    layout.addView(moonView);
                }
                break;
            case 9:
                if (layout.findViewById(R.id.about_toolbar) == null) {
                    WindView windView = new WindView(context);
                    windView.setId(R.id.about_toolbar);
                    windView.setLayoutParams(params);
                    layout.addView(windView);
                }
                break;
        }
    }
}
