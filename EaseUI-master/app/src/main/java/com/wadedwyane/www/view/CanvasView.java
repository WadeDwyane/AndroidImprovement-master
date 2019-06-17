package com.wadedwyane.www.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author kui.liu
 * @time 2019/6/6 9:34
 */
public class CanvasView extends View {

    private Paint paint;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //平移操作
        /*canvas.drawRect(0, 0, 200, 200, paint);
        canvas.translate(200, 200);
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, 200, 200, paint);
        canvas.drawLine(0, 0, 400, 400, paint);*/

        //缩放操作
        /*canvas.scale(2, 2);
        canvas.drawRect(0, 0, 200, 200, paint);
        canvas.drawLine(0, 0, 400, 400, paint);*/

        //旋转操作
        /*canvas.translate(50,50);
        canvas.drawRect(0,0, 200,200, paint);
        canvas.rotate(45);
        paint.setColor(Color.GRAY);
        canvas.drawRect(0,0, 200,200, paint);*/

        //倾斜操作
        /*canvas.translate(50, 50);
        canvas.drawRect(0, 0, 200, 200, paint);
        canvas.skew(0, 1);//分别指的是x,y方向倾斜角度的tan值
        canvas.drawRect(0, 0, 200, 200, paint);*/

        //切割操作
        /*canvas.drawRect(50, 50, 200, 200, paint);
        canvas.drawRect(50, 50, 350, 350, paint);
        canvas.clipRect(50, 50, 200, 200);
        canvas.drawRect(50, 50, 150, 150, paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(50, 50, 400, 400, paint);*/   //超出画布部分的画不出来

        //反切割
        /*canvas.drawRect(200, 200, 400, 400, paint);
        canvas.clipOutRect(200, 200, 300, 300);
        canvas.drawCircle(100, 100, 50, paint);*/

        //矩阵操作
        canvas.drawRect(0,0,300,300, paint);
        Matrix matrix = new Matrix();
//        matrix.setTranslate(50,50);
//        matrix.setRotate(45);
        matrix.setScale(0.5f, 0.5f);
        canvas.setMatrix(matrix);
        paint.setColor(Color.GRAY);
        canvas.drawRect(0,0,300,300, paint);

    }

}
