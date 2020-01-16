package retrofit.www.wadedwyane.com.animatordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public CircleView mCircleView;
    public TextView mTextView;
    public Button mBtnClick;
    public PointView mPointView;
    public ProvinceView mProvinceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCircleView = findViewById(R.id.view);
        mTextView = findViewById(R.id.tv);
        mBtnClick = findViewById(R.id.btn_1);
        mPointView = findViewById(R.id.pointView);
        mProvinceView = findViewById(R.id.provinceview);

        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivty.class);
                startActivity(intent);
            }
        });

        // ObjectAnimator  AnimatorSet（属性动画集）
        /*ObjectAnimator radiusObjectAnimator =
                ObjectAnimator.ofFloat(mCircleView, "radius", 50f, 200f, 100f, 50f);
        radiusObjectAnimator.setDuration(3000);

        ObjectAnimator rightXAnimator =
                ObjectAnimator.ofFloat(mCircleView, "rightX", 20, 40, 60, 80, 100, 20);
        rightXAnimator.setDuration(1500);

        ObjectAnimator bottomYAnimator =
                ObjectAnimator.ofFloat(mCircleView, "bottomY", 100, 120, 140, 160, 180, 20);
        rightXAnimator.setDuration(1500);

        //多个属性同时改变
        AnimatorSet animatorSet = new AnimatorSet();
        // 多个属性动画同时执行
//        animatorSet.playTogether(radiusObjectAnimator, rightXAnimator, bottomYAnimator);
        // 多个属性动画按顺序执行
        animatorSet.playSequentially(radiusObjectAnimator, rightXAnimator, bottomYAnimator);
        animatorSet.setStartDelay(2000);
        animatorSet.start();*/

        // 同一个对象有多个属性,可以使用PropertyValuesHolder
        /*PropertyValuesHolder radiusHolder = PropertyValuesHolder.ofFloat("radius", 50f, 200f, 100f, 50f);
        PropertyValuesHolder rightXHolder = PropertyValuesHolder.ofFloat("rightX", 20, 40, 60, 80, 100, 20);
        PropertyValuesHolder bottomYHolder = PropertyValuesHolder.ofFloat("bottomY", 100, 120, 140, 160, 180, 20);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mCircleView, radiusHolder, rightXHolder, bottomYHolder);
        animator.setDuration(2000);
        animator.setStartDelay(1500);
        animator.start();*/

        //设置关键帧动画
        /*int length = 500;
        Keyframe frame1 = Keyframe.ofFloat(0, 0 * length);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 0.4f * length);
        Keyframe frame3 = Keyframe.ofFloat(0.6f, 0.5f * length);
        Keyframe frame4 = Keyframe.ofFloat(0.9f, -0.1f * length);
        Keyframe frame5 = Keyframe.ofFloat(1f, 0);

        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("translationX",
                frame1, frame2, frame3, frame4, frame5);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTextView, holder);
        animator.setDuration(2000);
        animator.setStartDelay(1000);
        animator.start();*/

        //插值器
        /*mBtnClick.animate()
                .translationY(300)
                .setStartDelay(2000)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(1500)
                .start();*/

        //估值器
        /*Point targetPoint = new Point(300, 300);
        ObjectAnimator animator = ObjectAnimator.ofObject(mPointView,
                "point", new PointValuator(), targetPoint);
        animator.setDuration(2000);
        animator.setStartDelay(500);
        animator.start();*/

        ObjectAnimator provinceAnimator = ObjectAnimator.ofObject(
                mProvinceView,
                "province",
                new ProvinceValuator(),
                "浙江"
        );
        provinceAnimator.setDuration(2000);
        provinceAnimator.setStartDelay(1000);
        provinceAnimator.start();


//        LayoutInflater.from(this).inflate()
    }

    class PointValuator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            int x = (int) (startValue.x + (endValue.x - startValue.x) * fraction);
            int y = (int) (startValue.y + (endValue.y - startValue.y) * fraction);
            return new Point(x, y);
        }
    }

    class ProvinceValuator implements TypeEvaluator<String> {

        @Override
        public String evaluate(float fraction, String startValue, String endValue) {
            List<String> provinces = ProvinceUtil.provinceList;
            int startIndex = provinces.indexOf(startValue);
            int endIndex = provinces.indexOf(endValue);
            int index = (int) (startIndex + (endIndex - startIndex) * fraction);
            return provinces.get(index);
        }
    }
}
