package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wadedwyane.www.R;
import com.wadedwyane.www.view.BigImageView;

import java.io.IOException;
import java.io.InputStream;

public class BigImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_imageview);

        BigImageView biv = findViewById(R.id.biv);
        try {
            InputStream inputStream = getAssets().open("big.jpeg");
            biv.setImage(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
