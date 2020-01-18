package demo.oom.www.oomdemo.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: kui.liu
 * Time: 2019/11/18 13:01
 * Description: handler造成的内存泄漏
 * -----------------------------------------------------------
 */
public class HandlerActivity extends Activity {

    private MyHandler mMyHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyHandler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    /**
     * 解决方法：静态内部类 + 弱引用的方式解决内存泄漏
     */
    public static class MyHandler extends Handler{

        private WeakReference<Activity> mWeakReference;

        public MyHandler(Activity activity) {
            mWeakReference = new WeakReference<>(activity);
        }


        @Override
        public void handleMessage(Message msg) {

        }
    }
}
