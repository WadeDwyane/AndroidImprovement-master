package com.wadedwyane.www.widget;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wadedwyane.www.R;

public class PrecentLayout extends RelativeLayout {
    public PrecentLayout(Context context) {
        super(context);
    }

    public PrecentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PrecentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();
            if (checkLayoutParams(layoutParams)) {
                LayoutParams lp = (LayoutParams) layoutParams;
                float widthPrecent = lp.widthPrecent;
                float heightPrecent = lp.heightPrecent;
                float marginLeftPercent = lp.marginLeftPercent;
                float marginRightPercent = lp.marginRightPercent;
                float marginTopPercent = lp.marginTopPercent;
                float marginBottomPercent = lp.marginBottomPercent;

                if (widthPrecent > 0) {
                    layoutParams.width = (int) (width * widthPrecent);
                }

                if (heightPrecent > 0) {
                    layoutParams.height = (int) (height * heightPrecent);
                }

                if (marginLeftPercent > 0) {
                    ((LayoutParams) layoutParams).leftMargin = (int) (width * marginLeftPercent);
                }

                if (marginTopPercent > 0) {
                    ((LayoutParams) layoutParams).topMargin = (int) (height * marginTopPercent);
                }

                if (marginRightPercent > 0) {
                    ((LayoutParams) layoutParams).rightMargin = (int) (width * widthPrecent);
                }

                if (marginBottomPercent > 0) {
                    ((LayoutParams) layoutParams).bottomMargin = (int) (height * heightPrecent);
                }

            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    /**
     * 一定要重写这个方法
     *
     * @param attrs
     * @return
     */
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {

        private float widthPrecent;
        private float heightPrecent;
        private float marginLeftPercent;
        private float marginRightPercent;
        private float marginTopPercent;
        private float marginBottomPercent;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.PrecentLayout);
            widthPrecent = array.getFloat(R.styleable.PrecentLayout_widthPrecent, 0f);
            heightPrecent = array.getFloat(R.styleable.PrecentLayout_heightPrecent, 0f);
            marginLeftPercent = array.getFloat(R.styleable.PrecentLayout_marginLeftPercent, 0f);
            marginRightPercent = array.getFloat(R.styleable.PrecentLayout_marginRightPercent, 0f);
            marginTopPercent = array.getFloat(R.styleable.PrecentLayout_marginTopPercent, 0f);
            marginBottomPercent = array.getFloat(R.styleable.PrecentLayout_marginBottomPercent, 0f);
            array.recycle();
        }
    }

}
