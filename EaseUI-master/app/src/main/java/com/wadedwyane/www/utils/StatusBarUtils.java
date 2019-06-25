package com.wadedwyane.www.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.wadedwyane.www.view.StatusBarView;

public class StatusBarUtils {

    /**
     * 将状态栏设置成透明的颜色
     *
     * @param activity
     */
    public static void setTranslucentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 给Activity设置一个StatusBar
     *
     * @param activity
     */
    public static void setStatusBar(Activity activity, Toolbar toolbar) {
        setTranslucentStatusBar(activity);
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();

        int childCount = decorView.getChildCount();
        //判断是否添加了StatusBar,不要重复添加即可
        // 如果添加了,StatusBar一定位于第一个元素,那么只需要将StatusBar背景色透明度设置为透明即可;
        // 如果没有添加,则绘制一个和状态栏高度一致的矩形即可
        if (childCount > 0 && decorView.getChildAt(childCount - 1) instanceof StatusBarView) {
            decorView.getChildAt(childCount - 1).setBackgroundColor(Color.argb(0, 0, 0, 0));
            return;
        }

        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        (int) UIUtils.getInstance().getSystemStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        decorView.addView(statusBarView);

        if (null != toolbar) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
            marginLayoutParams.setMargins(0, (int) UIUtils.getInstance().getSystemStatusBarHeight(activity), 0, 0);
        }
    }

}
