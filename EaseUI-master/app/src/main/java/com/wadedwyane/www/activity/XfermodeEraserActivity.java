package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wadedwyane.www.view.XfermodeEraserView;


/**
 * @author kui.liu
 * @time 2019/6/5 11:13
 */
public class XfermodeEraserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new XfermodeEraserView(this));
    }
}
