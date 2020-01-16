package retrofit.www.wadedwyane.com.animatordemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class PointView extends View {

    public Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public Point point = new Point(50, 50);

    public PointView(Context context) {
        this(context, null);
    }

    {
        paint.setColor(Color.parseColor("#ffff0000"));
        paint.setStrokeWidth(10f);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
        invalidate();
    }

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawPoint(point.x, point.y, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
