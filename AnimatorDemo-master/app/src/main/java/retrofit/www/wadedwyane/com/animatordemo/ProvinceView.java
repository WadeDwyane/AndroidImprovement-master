package retrofit.www.wadedwyane.com.animatordemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ProvinceView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    String province = "北京";

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
        invalidate();
    }

    {
        paint.setTextSize(50);
        paint.setColor(Color.parseColor("#ffff0000"));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public ProvinceView(Context context) {
        this(context, null);
    }

    public ProvinceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawText(province, getWidth()/ 2, getHeight() /2 , paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }
}
