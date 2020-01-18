package com.wadedwyane.www.customhandlerdemo.core;

public class Handler {

    private Looper mLooper;
    private MessageQueue mMessageQueue;

    public Handler() {
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException(
                    "Can't create handler inside thread " + Thread.currentThread()
                            + " that has not called Looper.prepare()");
        }
        mMessageQueue = mLooper.mQueue;
    }

    public void dispatchMessage(Message message) {
        handleMessage(message);
    }

    public void handleMessage(Message message) {

    }

    public void sendMessage(Message message) {
        enqueueMessage(message);
    }

    private void enqueueMessage(Message message) {
        // 赋值当前handler
        message.target = this;

        // 使用mQueue，将消息放入
        mMessageQueue.enqueueMessage(message);
    }


}