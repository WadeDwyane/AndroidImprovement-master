package demo.oom.www.oomdemo.singleton;

import android.content.Context;

/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: kui.liu
 * Time: 2019/11/18 12:47
 * Description: 模拟单例模式 引起的内存泄漏（Context 如果是Activity 或者Service 都可能引起内存泄漏）
 * -----------------------------------------------------------
 */
public class SingletonActivityContext {

    public static SingletonActivityContext INSTANCE;
    private Context mContext;

    private SingletonActivityContext(Context context) {
        this.mContext = context;
    }

    public static SingletonActivityContext getInstance(Context context) {
        if (null == INSTANCE) {
            synchronized (SingletonActivityContext.class) {
                if (null == INSTANCE) {
                    INSTANCE = new SingletonActivityContext(context);
                }
            }
        }
        return INSTANCE;
    }

}
