package com.wadedwyane.www.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.view.ViewHelper;
import com.wadedwyane.www.R;
import com.wadedwyane.www.activity.XiaoHongShuSplashActivity;
import com.wadedwyane.www.adapter.ParallaxAdapter;
import com.wadedwyane.www.bean.ParallaxViewTag;
import com.wadedwyane.www.fragment.ParallaxFragment;

import java.util.ArrayList;
import java.util.List;

public class ParallaxContainer extends FrameLayout implements ViewPager.OnPageChangeListener {

    private List<ParallaxFragment> list = new ArrayList<>();

    private ImageView iv_man;
    private ParallaxAdapter mAdapter;

    public void setIv_man(ImageView iv_man) {
        this.iv_man = iv_man;
    }

    public ParallaxContainer(@NonNull Context context) {
        super(context);
    }

    public ParallaxContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxContainer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUp(int... childs) {
        for (int i = 0; i < childs.length; i++) {
            ParallaxFragment parallaxFragment = new ParallaxFragment();
            Bundle bundle = new Bundle();
            //设置布局id
            bundle.putInt("layoutId", childs[i]);
            parallaxFragment.setArguments(bundle);
            list.add(parallaxFragment);
        }

        ViewPager vp = new ViewPager(getContext());
        vp.setId(R.id.parallax_pager);
        //实例化适配器
        XiaoHongShuSplashActivity activity = (XiaoHongShuSplashActivity) getContext();
        mAdapter = new ParallaxAdapter(activity.getSupportFragmentManager(), list);
        vp.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        vp.setAdapter(mAdapter);
        addView(vp, 0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        动画
        int containerWidth = getWidth();
        ParallaxFragment outFragment = null;

        try {
            outFragment = list.get(position - 1);
        } catch (Exception e) {}
        //获取到退出的页面
        ParallaxFragment inFragment = null;
        try {
            inFragment = list.get(position);
        } catch (Exception e) {}

        if (outFragment != null) {
            //获取Fragment上所有的视图，实现动画效果
            List<View> inViews = outFragment.getParallaxList();
            //            动画
            if (inViews != null) {
                for (View view : inViews) {
                    //
                    ParallaxViewTag tag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                    if (tag == null) {
                        continue;
                    }
                    ViewHelper.setTranslationX(view, (containerWidth - positionOffsetPixels) * tag.xIn);
                    ViewHelper.setTranslationY(view, (containerWidth - positionOffsetPixels) * tag.yIn);
                }

            }

        }
        if(inFragment != null){
            List<View> outViews = inFragment.getParallaxList();
            if (outViews != null) {
                for (View view : outViews) {
                    ParallaxViewTag tag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                    if (tag == null) {
                        continue;
                    }
                    //仔细观察退出的fragment中view从原始位置开始向上移动，translationY应为负数
                    ViewHelper.setTranslationY(view, 0 - positionOffsetPixels * tag.yOut);
                    ViewHelper.setTranslationX(view, 0 - positionOffsetPixels * tag.xOut);
                }
            }
        }
    }

    @Override
    public void onPageSelected(int i) {
        if (i == mAdapter.getCount() - 1) {
            iv_man.setVisibility(INVISIBLE);
        }else {
            iv_man.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_man.getBackground();
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                animationDrawable.start();
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                animationDrawable.stop();
                break;
        }
    }
}
