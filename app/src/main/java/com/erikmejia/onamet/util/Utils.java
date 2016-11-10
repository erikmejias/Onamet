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
            case 0:
                SunView sunView = new SunView(context);
                sunView.setLayoutParams(params);
                layout.addView(sunView);
                break;
            case 1:
                CloudFogView cloud = new CloudFogView(context);
                cloud.setLayoutParams(params);
                layout.addView(cloud);
                break;
            case 2:
                CloudHvRainView cloudHvRainView = new CloudHvRainView(context);
                cloudHvRainView.setLayoutParams(params);
                layout.addView(cloudHvRainView);
                break;
            case 3:
                CloudMoonView cloudMoonView = new CloudMoonView(context);
                cloudMoonView.setLayoutParams(params);
                layout.addView(cloudMoonView);
                break;
            case 4:
                CloudRainView rainView = new CloudRainView(context);
                rainView.setLayoutParams(params);
                layout.addView(rainView);
                break;
            case 5:
                CloudSunView cloudSunView = new CloudSunView(context);
                cloudSunView.setLayoutParams(params);
                layout.addView(cloudSunView);
                break;
            case 6:
                CloudThunderView cloudThunderView = new CloudThunderView(context);
                cloudThunderView.setLayoutParams(params);
                layout.addView(cloudThunderView);
                break;
            case 7:
                CloudView cloudView = new CloudView(context);
                cloudView.setLayoutParams(params);
                layout.addView(cloudView);
                break;
            case 8:
                MoonView moonView = new MoonView(context);
                moonView.setLayoutParams(params);
                layout.addView(moonView);
                break;
            case 9:
                WindView windView = new WindView(context);
                windView.setLayoutParams(params);
                layout.addView(windView);
                break;
        }
    }
}
