package com.wadedwyane.www.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Scroller;

import java.io.IOException;
import java.io.InputStream;

public class BigImageView extends View implements GestureDetector.OnGestureListener,
        View.OnTouchListener, GestureDetector.OnDoubleTapListener {

    private int mImageHeight;
    private int mImageWidth;
    private Rect mRect;
    private BitmapFactory.Options mOptions;
    private GestureDetector mGestureDetector;
    private Scroller mScroller;
    private BitmapRegionDecoder mDecoder;
    private int mViewWidth;
    private int mViewHeight;
    private float mScaleFactor;
    private Bitmap mBitmap;
    private ScaleGestureDetector mScaleGestureDetector;
    private float originalScale;

    public BigImageView(Context context) {
        this(context, null);
    }

    public BigImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BigImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 第一步：初始化操作
        mRect = new Rect();
        // 创造矩形区域
        mOptions = new BitmapFactory.Options();
        // 手势识别
        mGestureDetector = new GestureDetector(context, this);
        // 滚动类
        mScroller = new Scroller(context);
        // 设置触摸事件
        setOnTouchListener(this);
        // 缩放的手势处理
        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleGesture());
    }

    /**
     * 第二步：设置图片
     * @param inputStream
     */
    public void setImage(InputStream inputStream) {
        // 不能将整张图片加进内存
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, mOptions);
        mImageHeight = mOptions.outHeight;
        mImageWidth = mOptions.outWidth;
        // 开启复用
        mOptions.inMutable = true;
        // 设置格式
        mOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        mOptions.inJustDecodeBounds = false;
        // 创建一个区域解码器
        try {
            mDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 去测量下
        requestLayout();
    }

    /**
     * 第三步：测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();

        // 这个是没加手势识别的时候的代码
        /*mRect.left = 0;
        mRect.top = 0;
        mRect.right = mImageWidth;
        // 计算缩放因子
        mScaleFactor = mViewWidth / (float)mImageWidth;
        mRect.bottom = (int)(mImageHeight / mScaleFactor);*/

        // 下面是添加了手势识别的代码
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = Math.min(mImageWidth, mViewWidth);
        mRect.bottom = Math.min(mImageHeight, mViewHeight);

        // 在定义一个原始的缩放因子
        originalScale = mViewWidth / (float) mImageWidth;
        mScaleFactor = originalScale;
    }

    /**
     * 第四步：绘画出具体的内容
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDecoder == null){
            return;
        }
        // 复用内存,复用内存必须跟即将要解码的尺寸一致
        mOptions.inBitmap = mBitmap;
        mBitmap = mDecoder.decodeRegion(mRect, mOptions);
        Matrix matrix = new Matrix();
        // 这个是没添加手势缩放的代码
//        matrix.setScale(mScaleFactor, mScaleFactor);
        // 下面这个是添加了手势缩放的代码
        matrix.setScale(mViewWidth/(float) mRect.width(),
                mViewWidth/(float) mRect.width());
        canvas.drawBitmap(mBitmap, matrix, null);
    }

    /**
     * 第五步：处理点击事件
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 直接将事件交给手势处理(传递单点事件)
        mGestureDetector.onTouchEvent(event);
        // 处理双击事件
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }

    /**
     * 第六步：处理触摸事件
     * @param e
     * @return
     */
    @Override
    public boolean onDown(MotionEvent e) {
        // 如果移动没有停止，就强行停止
        if (!mScroller.isFinished()) {
            mScroller.forceFinished(true);
        }
        return true;
    }

    /**
     * 第七步：处理滑动事件
     * @param e1 开始事件，获取坐标
     * @param e2 当前事件
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // 去改变矩形的上下左右的数值
        mRect.offset((int)distanceX, (int) distanceY);
        // 移动时，到达底部和顶部的情况
        if (mRect.bottom > mImageHeight) {
            mRect.bottom = mImageHeight;
            mRect.top = mImageHeight - (int)(mViewHeight/mScaleFactor);
        }

        if (mRect.top < 0) {
            mRect.top = 0;
            mRect.bottom = (int)(mViewHeight/mScaleFactor);
        }

        // 添加了缩放后,需要计算right和left
        if (mRect.right > mImageWidth) {
            mRect.right = mImageWidth;
            mRect.left = mImageWidth - (int)(mViewWidth/mScaleFactor);
        }

        if (mRect.left < 0) {
            mRect.left = 0;
            mRect.right = (int)(mViewWidth/mScaleFactor);
        }

        invalidate();
        return false;
    }

    /**
     * 第八步：处理拉动时候的惯性问题
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        /*mScroller.fling(0, mRect.top, 0, -(int)velocityY, 0,
                0, 0, mImageHeight - (int) (mViewHeight/mScaleFactor));*/
        mScroller.fling(mRect.left, mRect.top, -(int)velocityX, -(int)velocityY, 0,
                mImageWidth - (int)(mViewWidth / mScaleFactor),
                0, mImageHeight - (int) (mViewHeight/mScaleFactor));
        return false;
    }

    /**
     * 第九步：处理滚动结果
     */
    @Override
    public void computeScroll() {
        if (mScroller.isFinished()) {
            return;
        }

        // 左右滑动,不需要处理惯性事件
        if (mScroller.computeScrollOffset()) {
            mRect.top = mScroller.getCurrY();
            mRect.bottom = mRect.top + (int)(mViewHeight/mScaleFactor);
            invalidate();
        }
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    /**
     * 第十一步:双击会调用这个方法,双击变大的逻辑处理
     * @param e
     * @return
     */
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if (mScaleFactor < originalScale * 3) {
            // 这个3是自定义的
            mScaleFactor = originalScale * 5;
        }else {
            mScaleFactor = originalScale;
        }
        mRect.right = mRect.left + (int) (mViewWidth/mScaleFactor);
        mRect.bottom = mRect.top + (int) (mViewHeight/mScaleFactor);
        // 移动时,处理抵达顶部的情况
        if (mRect.bottom > mImageHeight) {
            mRect.bottom = mImageHeight;
            mRect.top = mImageHeight - (int)(mViewHeight/mScaleFactor);
        }

        if (mRect.top < 0) {
            mRect.top = 0;
            mRect.bottom = (int)(mViewHeight/mScaleFactor);
        }

        // 添加了缩放后,需要计算right和left
        if (mRect.right > mImageWidth) {
            mRect.right = mImageWidth;
            mRect.left = mImageWidth - (int)(mViewWidth/mScaleFactor);
        }

        if (mRect.left < 0) {
            mRect.left = 0;
            mRect.right = (int)(mViewWidth/mScaleFactor);
        }
        invalidate();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    /**
     * 第十步:处理缩放的逻辑
     */
    class ScaleGesture extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = mScaleFactor;
            // scale + 缩放因子的变化量
            scale += detector.getScaleFactor() -1 ;
            if (scale <= originalScale) {
                // 最小的缩放因子
                scale = originalScale;
            }else if (scale > originalScale * 5){
                // 最大只能放大到5倍
                scale = originalScale * 5;
            }
            mRect.right = mRect.left + (int)(mViewWidth / scale);
            mRect.bottom = mRect.top + (int)(mViewHeight / scale);
            mScaleFactor = scale;
            invalidate();
            return true;
        }
    }
}
