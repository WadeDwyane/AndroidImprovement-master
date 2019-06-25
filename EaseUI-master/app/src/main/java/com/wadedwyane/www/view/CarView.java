package com.wadedwyane.www.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wadedwyane.www.R;

public class CarView extends View {


    private Bitmap mCarBmp;
    private Paint mCirclePaint;
    private Paint mCarPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private Matrix mCarMatrix;
    private float distanceRatio;

    public CarView(Context context) {
        this(context, null);
    }

    public CarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mCarBmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_car);

        mPath = new Path();
        mPath.addCircle(0, 0, 200, Path.Direction.CW);

        mPathMeasure = new PathMeasure(mPath, false);

        mCirclePaint = new Paint();
        mCirclePaint.setStrokeWidth(5);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.BLACK);

        mCarPaint = new Paint();
        mCarPaint.setStrokeWidth(2);
        mCarPaint.setStyle(Paint.Style.STROKE);
        mCarPaint.setAntiAlias(true);
        mCarPaint.setColor(Color.BLACK);

        mCarMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.drawColor(Color.WHITE);
        //先将矩阵清空
        mCarMatrix.reset();

        distanceRatio += 0.006f;
        //distanceRatio在0-1之间变化
        if (distanceRatio >= 1) {
            distanceRatio = 0;
        }

        float[] pos = new float[2];//记录位置信息
        float[] tan = new float[2];//记录tan值, tan[0]表示cos  tan[1]表示sin
        float distance = mPathMeasure.getLength() * distanceRatio;
        mPathMeasure.getPosTan(distance, pos, tan);

        //计算小车旋转角度
        float degree = (float) (Math.atan2(tan[1], tan[0]) * 180 / Math.PI);
        //设置小车旋转中心和旋转角度
        mCarMatrix.postRotate(degree, mCarBmp.getWidth() / 2, mCarBmp.getHeight() / 2);
        mCarMatrix.postTranslate(pos[0] - mCarBmp.getWidth() / 2, pos[1] - mCarBmp.getHeight() / 2);

        canvas.drawPath(mPath, mCirclePaint);
        canvas.drawBitmap(mCarBmp, mCarMatrix, mCarPaint);
        invalidate();
    }
}
