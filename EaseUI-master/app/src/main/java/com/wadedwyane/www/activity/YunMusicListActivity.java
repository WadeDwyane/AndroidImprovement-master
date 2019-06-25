package com.wadedwyane.www.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.wadedwyane.www.R;
import com.wadedwyane.www.adapter.MusicAdapter;
import com.wadedwyane.www.utils.CalculateUtils;
import com.wadedwyane.www.utils.StatusBarUtils;
import com.wadedwyane.www.utils.UIUtils;
import com.wadedwyane.www.widget.MyNestedScrollView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class YunMusicListActivity extends AppCompatActivity {

    public final static String IMAGE_URL_MEDIUM = "http://p3.music.126.net/iRbTMHGfy-grDtx7o2T_dA==/109951164009413034.jpg?param=400y400";

    private Toolbar toolbar;
    private ImageView toolbar_bg;
    private ImageView header_bg;
    private RecyclerView music_recycler;
    private LinearLayout lv_header_contail;
    private ImageView header_music_log;
    private ImageView header_image_item;
    private MyNestedScrollView myNestedScrollView;
    private int slidingDistance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yunmusic_list);
        UIUtils.getInstance(this);
        initView();
        initRcy();
        postImage();
        initScrollViewListener();
    }

    private void postImage() {
        Glide.with(this)
                .load(IMAGE_URL_MEDIUM)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .override(400, 400)
                .into(header_image_item);

        // "14":模糊度；"3":图片缩放3倍后再进行模糊
        Glide.with(this)
                .load(IMAGE_URL_MEDIUM)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .transform(new BlurTransformation(this, 200, 3))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        lv_header_contail.setBackground(resource);
                    }
                });

        Glide.with(this).load(IMAGE_URL_MEDIUM)
                .error(R.drawable.stackblur_default)
                .transform(new BlurTransformation(this, 250, 6))// 设置高斯模糊
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        toolbar.setBackgroundColor(Color.TRANSPARENT);
                        toolbar_bg.setImageAlpha(0);
                        toolbar_bg.setVisibility(View.VISIBLE);
                        return false;
                    }
                }).into(toolbar_bg);
    }

    private void initScrollViewListener() {
        //        最好的 测量 就是不测量    view.getParams().height
        slidingDistance = (int) UIUtils.getInstance().getHeight(490);
        myNestedScrollView.setOnScrollListener(new MyNestedScrollView.ScrollListener() {


            @Override
            public void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollChangeHeader(scrollY);
            }
        });
    }

    private void scrollChangeHeader(int scrolledY) {
        if (scrolledY < 0) {
            scrolledY = 0;
        }
        //0  1
        float alpha = Math.abs(scrolledY) * 1.0f / (slidingDistance);
        Drawable drawable = toolbar_bg.getDrawable();
        if (drawable != null) {
            //490
            if (scrolledY <= slidingDistance) {
                drawable.mutate().setAlpha((int) (alpha * 255));
                toolbar_bg.setImageDrawable(drawable);
            } else {
                //490
                drawable.mutate().setAlpha(255);
                toolbar_bg.setImageDrawable(drawable);
            }
        }
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.more));

        toolbar_bg = findViewById(R.id.toolbar_bg);
        header_bg = findViewById(R.id.header_bg);
        music_recycler = findViewById(R.id.music_recycler);
        myNestedScrollView = findViewById(R.id.nsv_scrollview);
        header_music_log = findViewById(R.id.header_music_log);
        LinearLayout lv_header_detail = findViewById(R.id.lv_header_detail);
        RelativeLayout rv_header_container = findViewById(R.id.rv_header_container);
        lv_header_contail = findViewById(R.id.lv_header_contail);
        header_image_item = findViewById(R.id.header_image_item);

        CalculateUtils.setViewRelativeLayoutParams(toolbar, 1080, 164, 0, 0, 0, 0);
        CalculateUtils.setViewLinearLayoutParams(rv_header_container, 1080, 770, 164, 0, 0, 0);
        CalculateUtils.setViewRelativeLayoutParams(toolbar_bg, 1080, 164 + UIUtils.getInstance().getSystemStatusBarHeight(this), 0, 0, 0, 0);
        CalculateUtils.setViewRelativeLayoutParams(header_bg, 1080, 850, 0, 0, 0, 0);
        CalculateUtils.setViewRelativeLayoutParams(lv_header_detail, 1080, 380, 72, 0, 52, 0);
        CalculateUtils.setViewLinearLayoutParams(header_image_item, 380, 380);
        CalculateUtils.setViewRelativeLayoutParams(header_music_log, 60, 60, 59, 0, 52, 0);
        //重新设置个新的StatusBar
        StatusBarUtils.setStatusBar(this, toolbar);
    }

    private void initRcy() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        music_recycler.setLayoutManager(manager);
        //需要添加,不然不流畅
        music_recycler.setNestedScrollingEnabled(false);
        music_recycler.setHasFixedSize(false);

        MusicAdapter adapter = new MusicAdapter(this);
        adapter.notifyDataSetChanged();
        music_recycler.setAdapter(adapter);
    }

    //生成menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_music, menu);
        return true;
    }
}
