package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wadedwyane.www.view.GradientLayout;


/**
 * @author kui.liu
 * @time 2019/6/5 10:50
 * 主要介绍Paint的重要API/着色渲染器
 */
public class GradientPaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GradientLayout(this));
    }
}
