package com.wadedwyane.www.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * 网易云音乐实现屏幕适配的工具类
 */
public class UIUtils {

    public Context mContext;
    private static UIUtils instance;

    public static final float STANDARD_WIDTH = 1080f;
    public static final float STANDARD_HEIGHT = 1920f;
    public static float displayMetricsWidth;
    public static float displayMetricsHeight;
    public float systemStatusBarHeight;

    private UIUtils(Context context) {
        //获取到设备的真实信息
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetricsWidth == 0.0f || displayMetricsHeight == 0.0f) {
            //得到真实设备的信息
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //得到状态栏高度
            systemStatusBarHeight = getSystemStatusBarHeight(context);
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                //横屏
                this.displayMetricsWidth = displayMetrics.heightPixels;
                this.displayMetricsHeight = displayMetrics.widthPixels - systemStatusBarHeight;
            } else {
                //竖屏
                this.displayMetricsWidth = displayMetrics.widthPixels;
                this.displayMetricsHeight = displayMetrics.heightPixels - systemStatusBarHeight;
            }
        }
    }

    /**
     * 获取水平方向的缩放系数
     *
     * @return
     */
    public float getHorizontalScaleValue() {
        return displayMetricsWidth / STANDARD_WIDTH;
    }

    /**
     * 获取垂直方向的缩放系数
     *
     * @return
     */
    public float getVerticalScaleValue() {
        return displayMetricsHeight / (STANDARD_HEIGHT - systemStatusBarHeight);
    }

    /**
     * 根据缩放系数,获取宽度
     *
     * @param width
     * @return
     */
    public float getWidth(float width) {
        return Math.round(width * getHorizontalScaleValue());
    }

    /**
     * 根据缩放系数,获取高度
     *
     * @param height
     * @return
     */
    public float getHeight(float height) {
        return Math.round(height * getVerticalScaleValue());
    }

    public static UIUtils getInstance(Context context) {
        if (null == instance) {
            instance = new UIUtils(context);
        }
        return instance;
    }

    public static UIUtils getInstance() {
        if (null == instance) {
            throw new RuntimeException("UIUtils应该先初始化");
        }
        return instance;
    }

    /**
     * 获取手机状态栏高度
     *
     * @param context
     * @return
     */
    public float getSystemStatusBarHeight(Context context) {
        return getValue(context, "com.android.internal.R$dimen", "system_bar_height", 48);
    }

    /**
     * 通过反射获取手机的状态栏高度
     *
     * @param context
     * @param dimeClass
     * @param system_bar_height
     * @param defaultValue
     * @return
     */
    private float getValue(Context context, String dimeClass, String system_bar_height, int defaultValue) {
        try {
            Class<?> clz = Class.forName(dimeClass);
            Object object = clz.newInstance();
            Field field = clz.getField(system_bar_height);
            int id = Integer.parseInt(field.get(object).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    //TODO:补充另一种获取状态栏的方法
}
