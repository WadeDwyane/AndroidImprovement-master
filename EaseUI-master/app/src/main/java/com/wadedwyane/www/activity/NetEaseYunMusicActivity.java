package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wadedwyane.www.R;
import com.wadedwyane.www.utils.CalculateUtils;
import com.wadedwyane.www.utils.UIUtils;

public class NetEaseYunMusicActivity extends AppCompatActivity {

    public TextView mTextView3;
    public TextView mTextView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yunmusic);
        UIUtils.getInstance(this.getApplicationContext());

        mTextView3 = findViewById(R.id.tvText3);
        mTextView4 = findViewById(R.id.tvText4);

        CalculateUtils.setViewLinearLayoutParams(mTextView3, LinearLayout.LayoutParams.WRAP_CONTENT, 50);
        CalculateUtils.setViewLinearLayoutParams(mTextView4, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10, 10, 10, 10);
    }
}
