package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wadedwyane.www.view.BallSplitView;


/**
 * @author kui.liu
 * @time 2019/6/12 13:40
 */
public class BallShotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new BallSplitView(this));
    }
}
