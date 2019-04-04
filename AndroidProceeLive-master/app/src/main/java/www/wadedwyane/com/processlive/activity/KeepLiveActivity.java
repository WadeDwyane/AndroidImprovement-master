package www.wadedwyane.com.processlive.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import www.wadedwyane.com.processlive.manager.KeepLiveManager;

public class KeepLiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        设置一像素点在屏幕左上角
        Window window = getWindow();
        window.setGravity(Gravity.START | Gravity.TOP);

//        设置一个像素的像素点
        WindowManager.LayoutParams params = window.getAttributes();
        params.width =1;
        params.height = 1;
        params.x = 0;
        params.y = 0;
        window.setAttributes(params);

        //將Activit和弱引用綁定
        KeepLiveManager.getInstance().setKeepLive(this);
    }
}
