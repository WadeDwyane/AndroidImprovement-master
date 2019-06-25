package com.wadedwyane.www.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wadedwyane.www.R;
import com.wadedwyane.www.activity.CustomComposeActivity;

public class CustomToolbar extends RelativeLayout {

    private ImageView iv_titlebar_left;
    private ImageView iv_titlebar_right;
    private TextView tv_titlebar_title;
    private int mTextColor = Color.WHITE;
    private String titlename;
    private float mTitle_text_size;
    private int mTitle_text_color;
    private String mTitle_text;

    public CustomToolbar(Context context) {
        super(context);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.CustomToolbar);
        mTitle_text_size = array.getDimension(R.styleable.CustomToolbar_title_text_size, 0);
        mTitle_text_color = array.getColor(R.styleable.CustomToolbar_title_text_color, Color.WHITE);
        mTitle_text = array.getString(R.styleable.CustomToolbar_title_text);
        array.recycle();

        LayoutInflater.from(context).inflate(R.layout.titlebar, this, true);
        iv_titlebar_left = (ImageView) findViewById(R.id.iv_titlebar_left);
        iv_titlebar_right = (ImageView) findViewById(R.id.iv_titlebar_right);
        tv_titlebar_title = (TextView) findViewById(R.id.tv_titlebar_title);

        tv_titlebar_title.setTextColor(mTitle_text_color);
        tv_titlebar_title.setTextSize(mTitle_text_size);
        tv_titlebar_title.setText(mTitle_text);
    }

    public void setTitleText(String text) {
        if (!TextUtils.isEmpty(text)) {
            tv_titlebar_title.setText(text);
        }
    }

    public void setTitleTextSize(float size) {
        tv_titlebar_title.setTextSize(size);
    }

    public void setTitleTextColor(int color) {
        tv_titlebar_title.setTextColor(color);
    }

    public void setOnLeftListener(OnClickListener listener) {
        iv_titlebar_left.setOnClickListener(listener);
    }

    public void setOnRightListener(OnClickListener listener) {
        iv_titlebar_right.setOnClickListener(listener);
    }
}
