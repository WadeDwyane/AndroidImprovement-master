package com.wadedwyane.www.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.wadedwyane.www.R;


public class RotateSplashView extends View {

    private Paint paint;
    private Paint mHolePaint;
    private int mBackgroundColor = Color.WHITE;
    private int[] mColors;

    private SplashState mState;
    private float mRotateRadius = 90;
    private float mCurrentRotateAngle = 0F;
    private float mCurrentRotateRadius = mRotateRadius;
    private float mCircleRadius = 12.0F;
    private float mCenterX;
    private float mCenterY;
    private ValueAnimator mValueAnimator;
    private float mDistance;
    private long mDuration = 1200L;
    //旋转大圆的半径
    private float mCurrentHoleRaduis = 0F;


    public RotateSplashView(Context context) {
        this(context, null);
    }

    public RotateSplashView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint.setStyle(Paint.Style.STROKE);
        mHolePaint.setColor(mBackgroundColor);
        mColors = getResources().getIntArray(R.array.splash_circle_colors);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w * 1.0f / 2;
        mCenterY = h * 1.0f / 2;
        mDistance = (float) (Math.hypot(w, h) / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mState) {
            mState = new RotateState();
        }
        mState.drawState(canvas);
    }

    //绘制旋转的效果
    public class RotateState extends SplashState {

        public RotateState() {
            mValueAnimator = ValueAnimator.ofFloat(0, (float) (Math.PI * 2));
            mValueAnimator.setRepeatCount(1);
            mValueAnimator.setDuration(mDuration);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotateAngle = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });

            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mState = new MerginState();
                }
            });

            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            //绘制背景
            drawBackground(canvas);
            //绘制6个小球
            drawCircles(canvas);
        }
    }

    //绘制聚合的效果
    public class MerginState extends SplashState {

        public MerginState() {
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius, mRotateRadius);
            mValueAnimator.setDuration(mDuration);
            mValueAnimator.setInterpolator(new OvershootInterpolator(10.0f));
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotateRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });

            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mState = new ExpandState();
                }
            });
            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackground(canvas);
            drawCircles(canvas);
        }
    }

    //绘制水波纹的效果
    public class ExpandState extends SplashState {

        public ExpandState() {
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius, mDistance);
            mValueAnimator.setDuration(mDuration);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentHoleRaduis = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackground(canvas);
        }
    }

    private void drawCircles(Canvas canvas) {
        float rotateAngle = (float) (2 * Math.PI / mColors.length);
        for (int i = 0; i < mColors.length; i++) {
            float angle = i * rotateAngle + mCurrentRotateAngle;
            float centerx = (float) (Math.cos(angle) * mCurrentRotateRadius + mCenterX);
            float centery = (float) (Math.sin(angle) * mCurrentRotateRadius + mCenterX);
            paint.setColor(mColors[i]);
            canvas.drawCircle(centerx, centery, mCircleRadius, paint);
        }
    }

    private void drawBackground(Canvas canvas) {
        if (mCurrentHoleRaduis > 0) {
            float strokeWidth = mDistance - mCurrentHoleRaduis;
            float radius = strokeWidth / 2 + mCurrentHoleRaduis;
            mHolePaint.setStrokeWidth(strokeWidth);
            canvas.drawCircle(mCenterX, mCenterY, radius, mHolePaint);
        }else {
            canvas.drawColor(mBackgroundColor);
        }
    }

    public abstract class SplashState {
        abstract void drawState(Canvas canvas);
    }
}
