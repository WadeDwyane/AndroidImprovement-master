package com.wadedwyane.www.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathView extends View {

    private Path mPath;
    private Paint mPaint;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.moveTo(100, 100);
        /*mPath.lineTo(200, 200);
        mPath.rLineTo(50, 100);
        mPath.rLineTo(100, 100);*/
        //设置曲线是否闭合
//        mPath.close();

        /*//添加圆弧
        mPath.addArc(100, 100, 300, 300, -90, 90);
        //添加矩形
        mPath.addRect(100, 100, 200, 200, Path.Direction.CW);
        //添加椭圆
        mPath.addOval(200, 300, 400, 400, Path.Direction.CCW);
        //添加圆形
        mPath.addCircle(300, 300, 50, Path.Direction.CCW);

        //向追加图形
        mPath.arcTo(400, 200, 600, 400, -180, 225, false);*/

//        RectF rect = new RectF(200, 200, 250, 250);
//        //添加圆角矩形
//        mPath.addRoundRect(rect, 10, 10,Path.Direction.CCW);

//        mPath.lineTo(150, 200);
//        mPath.lineTo(250, 300);
//
//        Path newPath = new Path();
//        newPath.moveTo(50, 50);
//        newPath.lineTo(500, 200);
//        newPath.lineTo(300, 400);
//        //添加新的路径
//        mPath.addPath(newPath);
//        canvas.drawPath(mPath, mPaint);

        //画二阶贝塞尔曲线
//        mPath.rQuadTo(150, 150, 190, 250);
//        mPath.quadTo(120, 200, 300, 400);
//        canvas.drawPath(mPath, mPaint);

        //画三阶贝塞尔曲线
        mPath.rCubicTo(200, 340, 100, 600, 500, 200);
//        mPath.cubicTo(120, 125, 490, 450, 300, 200);
        canvas.drawPath(mPath, mPaint);
    }
}
