package com.wadedwyane.www.aopdemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.i("AOP", "name : " + activity.getComponentName().getClassName() + " Created");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.i("AOP", "name : " + activity.getComponentName().getClassName() + " Started");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.i("AOP", "name : " + activity.getComponentName().getClassName() + " Resumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.i("AOP", "name : " + activity.getComponentName().getClassName() + " Destroyed");
            }
        });
    }
}
