package demo.oom.www.oomdemo.singleton;

import android.content.Context;

/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: kui.liu
 * Time: 2019/11/18 12:52
 * Description: 单例中如何避免内存泄漏
 * -----------------------------------------------------------
 */
public class SingleApplicationContext {

    private static SingleApplicationContext INSTANCE;
    private Context mContext;

    private SingleApplicationContext(Context context) {
        // 使用Application的Context，让单例的生命周期和Application的生命周期一样长，就可以避免内存泄漏
        mContext = context.getApplicationContext();
    }

    public static SingleApplicationContext getInstance(Context context) {
        if (null == INSTANCE){
            synchronized (SingleApplicationContext.class) {
                if (null == INSTANCE) {
                    INSTANCE = new SingleApplicationContext(context);
                }
            }
        }


        return INSTANCE;
    }
}
