package com.wadedwyane.www.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.wadedwyane.www.R;
import com.wadedwyane.www.bean.Ball;

import java.util.ArrayList;

/**
 * @author kui.liu
 * @time 2019/6/12 13:42
 */
public class BallSplitView extends View {

    private Paint paint;
    private Bitmap bitmap;
    private float radius = 5;
    private ArrayList<Ball> mBalls = new ArrayList<>();
    private ValueAnimator valueAnimator;

    public BallSplitView(Context context) {
        this(context, null);
    }

    public BallSplitView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BallSplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pic);
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                Ball ball = new Ball();
                ball.color = bitmap.getPixel(i, j);
                ball.x = i * radius + radius / 2;
                ball.y = j * radius + radius / 2;
                ball.r = radius / 2;

                //速度(-20, 20)
                ball.vx = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.vy = rangInt(-15, 15);

                //加速度
                ball.ax = 0;
                ball.ay = 0.98f;
                mBalls.add(ball);
            }
        }

        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBall();
                invalidate();
            }
        });
    }

    private void updateBall() {
        //更新粒子位置
        for (Ball ball : mBalls) {
            ball.x += ball.vx;
            ball.y += ball.vy;

            ball.vx += ball.ax;
            ball.vy += ball.ay;
        }
    }

    private int rangInt(int i, int j) {
        int max = Math.max(i, j);
        int min = Math.min(i, j) - 1;
        //在0到(max - min)范围内变化，取大于x的最小整数 再随机
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(500, 500);
        for(Ball ball : mBalls) {
            paint.setColor(ball.color);
            canvas.drawCircle(ball.x, ball.y, ball.r, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            valueAnimator.start();
        }
        return super.onTouchEvent(event);
    }
}
