package com.wadedwyane.www.customhandlerdemo.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {

    //阻塞队列
    public BlockingQueue<Message> mMessageBlockingQueue = new ArrayBlockingQueue<Message>(50);

    //将消息放入阻塞队列
    public void enqueueMessage(Message message) {
        try {
            mMessageBlockingQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //从阻塞队列中取出消息
    public Message next() {
        try {
            return mMessageBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
