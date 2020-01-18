package com.wadedwyane.www.customhandlerdemo.core;

public class Looper {

    public MessageQueue mQueue;
    static final ThreadLocal<Looper> mThreadLocal = new ThreadLocal<>();

    public Looper() {
        mQueue = new MessageQueue();
    }

    public static void prepare() {
        if (mThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        mThreadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return mThreadLocal.get();
    }

    public static void loop() {
        //获取looper对象
        Looper me = myLooper();
        //获取消息队列
        MessageQueue queue = me.mQueue;

       //从消息队列中取出消息
        while(true) {
            Message message = queue.next();

            if(null != message) {
                 if(message.target != null) {
                     message.target.dispatchMessage(message);
                 }
            }
        }
    }
}
