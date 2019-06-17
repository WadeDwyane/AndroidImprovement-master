package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wadedwyane.www.view.CanvasSaveRestoreView;


/**
 * @author kui.liu
 * @time 2019/6/12 10:15
 */
public class CanvasSaveRestoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasSaveRestoreView(this));
    }
}
