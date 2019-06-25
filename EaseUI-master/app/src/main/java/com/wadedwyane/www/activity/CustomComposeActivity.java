package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.wadedwyane.www.R;
import com.wadedwyane.www.view.CarView;
import com.wadedwyane.www.view.CustomToolbar;
/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: Administrator
 * Time: 2019/6/25 14:46
 * Description: 自定义组合控件实现一个toolbar以及绘制一个小车旋转的View
 * -----------------------------------------------------------
 */
public class CustomComposeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customcompose);
        CustomToolbar customToolbar = findViewById(R.id.custom_toolbar);
        FrameLayout container = findViewById(R.id.container);
        customToolbar.setOnLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomComposeActivity.this, "返回", Toast.LENGTH_SHORT).show();
            }
        });
        customToolbar.setOnRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomComposeActivity.this, "back", Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(new CarView(this));
    }
}
