package com.wadedwyane.www.utils;

import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CalculateUtils {

    /**
     * 设置相对布局的参数
     *
     * @param view
     * @param width
     * @param height
     * @param leftMargin
     * @param rightMargin
     * @param topMargin
     * @param bottomMargin
     */
    public static void setViewRelativeLayoutParams(View view, float width, float height,
                                                   float topMargin, float bottomMargin,
                                                   float leftMargin, float rightMargin) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (null != params) {
            if (width != RelativeLayout.LayoutParams.MATCH_PARENT ||
                    width != RelativeLayout.LayoutParams.WRAP_CONTENT ||
                    width != RelativeLayout.LayoutParams.FILL_PARENT) {
                params.width = (int) UIUtils.getInstance().getWidth(width);
            } else {
                params.width = (int) width;
            }

            if (height != RelativeLayout.LayoutParams.MATCH_PARENT ||
                    height != RelativeLayout.LayoutParams.WRAP_CONTENT ||
                    height != RelativeLayout.LayoutParams.FILL_PARENT) {
                params.height = (int) UIUtils.getInstance().getHeight(height);
            } else {
                params.height = (int) height;
            }

            params.leftMargin = (int) UIUtils.getInstance().getWidth(leftMargin);
            params.rightMargin = (int) UIUtils.getInstance().getWidth(rightMargin);
            params.topMargin = (int) UIUtils.getInstance().getHeight(topMargin);
            params.bottomMargin = (int) UIUtils.getInstance().getHeight(bottomMargin);
            view.setLayoutParams(params);
        } else {

        }
    }

    /**
     * 设置线性布局的布局参数
     *
     * @param view
     * @param width
     * @param height
     * @param leftMargin
     * @param rightMargin
     * @param topMargin
     * @param bottomMargin
     */
    public static void setViewLinearLayoutParams(View view, float width, float height,
                                                 float topMargin, float bottomMargin,
                                                 float leftMargin, float rightMargin) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (null != params) {
            if (width != LinearLayout.LayoutParams.MATCH_PARENT ||
                    width != LinearLayout.LayoutParams.WRAP_CONTENT ||
                    width != LinearLayout.LayoutParams.FILL_PARENT) {
                params.width = (int) UIUtils.getInstance().getWidth(width);
            } else {
                params.width = (int) width;
            }

            if (height != LinearLayout.LayoutParams.MATCH_PARENT ||
                    height != LinearLayout.LayoutParams.WRAP_CONTENT ||
                    height != LinearLayout.LayoutParams.FILL_PARENT) {
                params.height = (int) UIUtils.getInstance().getHeight(height);
            } else {
                params.height = (int) height;
            }

            params.leftMargin = (int) UIUtils.getInstance().getWidth(leftMargin);
            params.rightMargin = (int) UIUtils.getInstance().getWidth(rightMargin);
            params.topMargin = (int) UIUtils.getInstance().getHeight(topMargin);
            params.bottomMargin = (int) UIUtils.getInstance().getHeight(bottomMargin);
            view.setLayoutParams(params);
        } else {

        }
    }

    /**
     * 设置线性布局的参数
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setViewLinearLayoutParams(View view, float width, float height) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (null != params) {
            if (width != LinearLayout.LayoutParams.MATCH_PARENT ||
                    width != LinearLayout.LayoutParams.WRAP_CONTENT ||
                    width != LinearLayout.LayoutParams.FILL_PARENT) {
                params.width = (int) UIUtils.getInstance().getWidth(width);
            } else {
                params.width = (int) width;
            }

            if (height != LinearLayout.LayoutParams.MATCH_PARENT ||
                    height != LinearLayout.LayoutParams.WRAP_CONTENT ||
                    height != LinearLayout.LayoutParams.FILL_PARENT) {
                params.height = (int) UIUtils.getInstance().getHeight(height);
            } else {
                params.height = (int) height;
            }
            view.setLayoutParams(params);
        }
    }

    /**
     * 设置字体大小
     *
     * @param view
     * @param size
     */
    public static void setTextSize(TextView view, int size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, UIUtils.getInstance().getHeight(size));
    }

}
