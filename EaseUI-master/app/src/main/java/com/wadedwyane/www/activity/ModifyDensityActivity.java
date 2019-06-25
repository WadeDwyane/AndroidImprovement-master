package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wadedwyane.www.R;
import com.wadedwyane.www.utils.DensityUtil;

public class ModifyDensityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_density);
        /*这个方法有两种调用方式:
        * 1. 在BaseActivity中调用
        * 2. 在BaseApplication中调用,详情见BaseApplication
        * */
        DensityUtil.setDensity(getApplication(), this);
    }
}
