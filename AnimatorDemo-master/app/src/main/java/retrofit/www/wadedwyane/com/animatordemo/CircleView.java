package retrofit.www.wadedwyane.com.animatordemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

    public float radius = 50.0f;
    private Paint circlePaint = new Paint();
    public float rightX = 0;
    public float bottomY = 0;
    private Paint rectPaint = new Paint();

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        // 一定要加invalidate()，让界面失效，下一帧重新绘制
        invalidate();
    }

    public float getRightX() {
        return rightX;
    }

    public void setRightX(float rightX) {
        this.rightX = rightX;
        invalidate();
    }

    public float getBottomY() {
        return bottomY;
    }

    public void setBottomY(float bottomY) {
        this.bottomY = bottomY;
        invalidate();
    }

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.parseColor("#ffff0000"));

        rectPaint.setAntiAlias(true);
        rectPaint.setColor(Color.parseColor("#ff00ff00"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, circlePaint);

        canvas.drawRect(getWidth() / 2, getHeight() / 2, rightX, bottomY, rectPaint);
    }
}
