package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wadedwyane.www.view.XfermodeView;

public class XfermodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new XfermodeView(this));
    }
}
