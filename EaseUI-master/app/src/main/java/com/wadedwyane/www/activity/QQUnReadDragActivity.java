package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wadedwyane.www.R;

public class QQUnReadDragActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //展示Path的基本API
//        setContentView(new PathView(this));
        setContentView(R.layout.activity_qq_unread_drag);
    }
}
