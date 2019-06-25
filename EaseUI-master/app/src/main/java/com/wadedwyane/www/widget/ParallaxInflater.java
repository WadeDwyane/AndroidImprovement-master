package com.wadedwyane.www.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.wadedwyane.www.R;
import com.wadedwyane.www.bean.ParallaxViewTag;
import com.wadedwyane.www.fragment.ParallaxFragment;

public class ParallaxInflater extends LayoutInflater {

    private ParallaxFragment mParallaxFragment;

    public ParallaxInflater(LayoutInflater original, Context newContext, ParallaxFragment fragment) {
        super(original, newContext);
        mParallaxFragment = fragment;
        setFactory2(new ParallaxFactory(original));
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new ParallaxInflater(this, newContext, mParallaxFragment);
    }

    public class ParallaxFactory implements Factory2 {
        private LayoutInflater mInflater;

        private final String[] sClassPrefix = {
                "android.widget.",
                "android.view."
        };

        int[] attrIds = {
                R.attr.a_in,
                R.attr.a_out,
                R.attr.x_in,
                R.attr.x_out,
                R.attr.y_in,
                R.attr.y_out};

        public ParallaxFactory(LayoutInflater inflater) {
            this.mInflater = inflater;
        }

        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            Log.i("ParallaxInflater", "onCreateView : " + parent);
            View view = createMyView(name, context, attrs);
            if (null != view) {
                TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
                if (null != array && array.length() > 0) {
                    ParallaxViewTag tag = new ParallaxViewTag();
                    tag.alphaIn = array.getFloat(R.styleable.MyView_a_in, 0f);
                    tag.alphaOut = array.getFloat(R.styleable.MyView_a_out, 0f);
                    tag.xIn = array.getFloat(R.styleable.MyView_x_in, 0f);
                    tag.xOut = array.getFloat(R.styleable.MyView_x_out, 0f);
                    tag.yIn = array.getFloat(R.styleable.MyView_y_in, 0f);
                    tag.yOut = array.getFloat(R.styleable.MyView_y_out, 0f);
                    view.setTag(R.id.parallax_view_tag, tag);
                }
                mParallaxFragment.getParallaxList().add(view);
                array.recycle();
            }

            return view;
        }

        //name:系统控件;自定义控件  自定义控件含有.
        private View createMyView(String name, Context context, AttributeSet attrs) {
            if (name.contains(".")) {
                View view = reflectView(name, null, context, attrs);
                return view;
            } else {
                //系统控件,可以使用反射
                //系统控件可能在三大类包中:android.widget/android.view
                for (String pck : sClassPrefix) {
                    View view = reflectView(name, pck, context, attrs);
                    if (null != view) {
                        return view;
                    }
                }
            }
            return null;
        }

        private View reflectView(String name, String prefix, Context context, AttributeSet attrs) {
            try {
                return mInflater.createView(name, prefix, attrs);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return null;
        }
    }
}
