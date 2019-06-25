package com.wadedwyane.www.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.wadedwyane.www.R;

/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: Administrator
 * Time: 2019/6/20 17:21
 * Description: 刘海屏适配的案例
 * -----------------------------------------------------------
 */
public class DisplayCutoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1.设置全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //2.判断手机厂商

        //3.判断是否有刘海
        boolean hasDisplayCutout = hasDisplayCutout(window);

        if (hasDisplayCutout) {
            //4.设置是否让内容区域延伸进刘海区
            WindowManager.LayoutParams params = window.getAttributes();
            /**
             * LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT 全屏模式，内容下移，非全屏不受影响
             * LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES 允许内容去延伸进刘海区
             * LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER 不允许内容延伸进刘海区
             */
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(params);

            //5.设置成沉浸式
            int flags = View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

            int visibility = window.getDecorView().getSystemUiVisibility();
            //追加flags到visibility中
            visibility |= flags;
            window.getDecorView().setSystemUiVisibility(visibility);
        }

        setContentView(R.layout.activity_display_cutout);
        Button btn = findViewById(R.id.btn);
        RelativeLayout container = findViewById(R.id.container);

        //6.是否避开刘海区域
        if (hasDisplayCutout) {
            //方式一
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) btn.getLayoutParams();
            params.topMargin = (int) getSystemStatusHeight();
            btn.setLayoutParams(params);

            //方式二
            container.setPadding(0, (int) getSystemStatusHeight(), 0, 0);
        }
    }

    /**
     * 判断是否有刘海
     *
     * @param window
     * @return
     */
    private boolean hasDisplayCutout(Window window) {
        View decorView = window.getDecorView();
        WindowInsets insets = decorView.getRootWindowInsets();
        //判断sdk版本号
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && null != insets) {
            DisplayCutout displayCutout = insets.getDisplayCutout();
            if (null != displayCutout) {
                if (null != displayCutout.getBoundingRects() && displayCutout.getBoundingRects().size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取系统的状态栏高度
     * 如果有刘海屏,通常情况下刘海屏高度和系统状态栏高度一致
     *
     * @return
     */
    private float getSystemStatusHeight() {
        int resID = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resID > 0) {
            return getResources().getDimensionPixelSize(resID);
        }
        return 96;
    }
}
