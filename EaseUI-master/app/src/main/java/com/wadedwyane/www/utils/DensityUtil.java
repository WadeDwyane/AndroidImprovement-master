package com.wadedwyane.www.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

public class DensityUtil {

    public static final int STANDARD_WIDTH = 360;//设计师给的标准,单位是dp,这个值可以根据设计稿来修改

    public static float appDensity;
    public static float appScaleDensity;


    public static void setDensity(final Application application, Activity activity) {
        //先获取屏幕信息
        DisplayMetrics metrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0) {
            appDensity = metrics.density;
            appScaleDensity = metrics.scaledDensity;

            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体发生变化
                    if (null != newConfig && newConfig.fontScale > 0) {
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });

            //获取到了当前设备的信息,根据当前设备信息,修改目标view的density,scaleDensity,densityDpi
            float targetDensity = appDensity / STANDARD_WIDTH;
            float targetScaleDensity = targetDensity * (appScaleDensity / STANDARD_WIDTH);
            int targetDensityDpi = (int) (targetDensity * 160);

            //替换掉acitivty的density,scaleDensity,densityDpi
            DisplayMetrics activityDis = activity.getResources().getDisplayMetrics();
            activityDis.density = targetDensity;
            activityDis.scaledDensity = targetScaleDensity;
            activityDis.densityDpi = targetDensityDpi;
        }
    }

}
