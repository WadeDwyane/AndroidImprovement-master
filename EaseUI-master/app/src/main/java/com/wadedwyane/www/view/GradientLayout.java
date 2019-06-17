package com.wadedwyane.www.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wadedwyane.www.R;

/**
 * @author kui.liu
 * @time 2019/6/5 9:18
 * 主要介绍Paint的高级用法:常用API/着色器
 */
public class GradientLayout extends View {


    private Paint paint;
    private String text;
    private Rect rect;
    private Bitmap bitmap;

    public GradientLayout(Context context) {
        this(context, null);
    }

    public GradientLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public GradientLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
//        paint.setARGB(255, 255, 0, 0);
//        paint.setAlpha(100);
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setStyle(Paint.Style.FILL);//设置描边
        paint.setStrokeWidth(5);//设置描边宽度
        paint.setStrokeCap(Paint.Cap.SQUARE);//圆角风格
        paint.setStrokeJoin(Paint.Join.MITER);//拐角风格
        paint.setFilterBitmap(true);//设置双线性过滤
//        paint.setTextScaleX(2.0f);
        paint.setTextSize(20.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setUnderlineText(true);

        text = "Android高级工程师";
        /*rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        paint.measureText(text);
        paint.getFontMetrics();*/

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.beauty);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawText
//        canvas.drawText(text, 100, 50, paint);

        //渲染器
        //线性着色器
        /*LinearGradient linearGradient = new LinearGradient(0, 0, 500, 500,
                new int[]{Color.RED, Color.BLUE, Color.GREEN},
                new float[]{0.f,0.7f,1}, Shader.TileMode.REPEAT);
        paint.setShader(linearGradient);
        canvas.drawRect(100, 100, 150, 150, paint);*/

        //环形渲染
        /*RadialGradient radialGradient = new RadialGradient(250, 250, 50,
                new int[]{Color.GREEN, Color.YELLOW, Color.RED}, null, Shader.TileMode.CLAMP);
        paint.setShader(radialGradient);
        canvas.drawCircle(250, 250, 50, paint);*/

        //扫描渲染
        /*SweepGradient sweepGradient = new SweepGradient(250, 250, Color.RED, Color.GREEN);
        paint.setShader(sweepGradient);
        canvas.drawCircle(250, 250, 50, paint);*/

        //位图渲染
//        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
//        paint.setShader(bitmapShader);
//                canvas.drawRect(200,200,400, 400, paint);
//        canvas.drawCircle(250, 250, 250, paint);

        //组合渲染
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        LinearGradient linearGradient = new LinearGradient(0, 0, 1000, 1600,
                new int[]{Color.RED, Color.GREEN, Color.BLUE}, null, Shader.TileMode.CLAMP);
        ComposeShader composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.ADD);
        paint.setShader(composeShader);
        canvas.drawCircle(250, 250, 100, paint);
    }

}
