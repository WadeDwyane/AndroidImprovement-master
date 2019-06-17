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
 * @time 2019/6/12 10:16
 */
public class CanvasSaveRestoreView extends View {

    private Paint paint;

    public CanvasSaveRestoreView(Context context) {
        this(context, null);
    }

    public CanvasSaveRestoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasSaveRestoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         /*Log.i("CanvasSaveRestoreView", "count = " + canvas.getSaveCount());
        canvas.drawRect(100, 100, 200, 200, paint);
        int state = canvas.save();
        Log.i("CanvasSaveRestoreView", "count = " + canvas.getSaveCount() + " ,state = " + state);

        canvas.translate(100, 100);
        canvas.drawRect(100, 100, 200, 200, paint);
        int state1 = canvas.save();
        Log.i("CanvasSaveRestoreView", "count = " + canvas.getSaveCount() + " ,state = " + state1);

        canvas.translate(50, 50);
        int state2 = canvas.save();
        canvas.drawRect(100, 100, 200, 200, paint);
        Log.i("CanvasSaveRestoreView", "count = " + canvas.getSaveCount() + " ,state = " + state2);

        canvas.translate(50, 50);
        canvas.save();
        canvas.restore();
        paint.setColor(Color.BLUE);
        canvas.drawRect(100, 100, 300, 300, paint);
        Log.i("CanvasSaveRestoreView", "count = " + canvas.getSaveCount() + " ,state = " + state2);

        canvas.restore();
        paint.setColor(Color.GREEN);
        canvas.drawRect(100, 100, 150, 150, paint);
        Log.i("CanvasSaveRestoreView", "count = " + canvas.getSaveCount() + " ,state = " + state2);*/

        canvas.drawRect(50, 50, 250, 250, paint);
        int layerId = canvas.saveLayer(50, 50, 300, 300, paint);
        paint.setColor(Color.RED);
        Matrix matrix = new Matrix();
        matrix.setTranslate(10, 10);
        canvas.setMatrix(matrix);
        canvas.drawRect(50, 50, 200, 200, paint);
        canvas.restoreToCount(layerId);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(150, 150, 20, paint);
    }
}
