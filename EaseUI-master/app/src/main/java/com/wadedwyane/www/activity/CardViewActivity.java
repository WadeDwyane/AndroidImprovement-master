package com.wadedwyane.www.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.wadedwyane.www.R;
import com.wadedwyane.www.utils.StatusBarUtils;
import com.wadedwyane.www.utils.UIUtils;

public class CardViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);
        //设置沉浸式
        immersive();
        //将toolbar下移动
        setViewAndPadding(this, findViewById(R.id.toolbar));
    }

    private void setViewAndPadding(Context context, View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height += UIUtils.getInstance(context).getSystemStatusBarHeight(context);
        view.setPadding(view.getPaddingLeft(),
                (int) (view.getPaddingTop() + UIUtils.getInstance(context).getSystemStatusBarHeight(context)),
                view.getPaddingRight(),
                view.getPaddingTop()
        );
    }

    private void immersive() {
        //Android4.4 之前没有沉浸式
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Android 5.0 之后
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色透明
            window.setStatusBarColor(Color.TRANSPARENT);

            int visibility = window.getDecorView().getSystemUiVisibility();
            //全屏展示
            visibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            //隐藏虚拟导航栏
            visibility |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            //防止内容区域发生变化
            visibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(visibility);
        } else {
            //Android 5.0 之前
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //设置成沉浸式
//        StatusBarUtils.setTranslucentStatusBar(this);

    }
}
