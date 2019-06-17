package com.wadedwyane.customokhttp.okhttp;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kui.liu
 * @time 2019/4/9 16:42
 * 线程池管理类
 */
public class ThreadPoolManager {

    public static ThreadPoolManager instance;

    //创建队列
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();

    //创建线程池
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                addTask(r);
            }
        });
        //将监听的核心线程放入线程池
        mThreadPoolExecutor.execute(coreThread);
        //将重试的线程放入线程池去执行
        mThreadPoolExecutor.execute(delayThread);
    }

    public Runnable coreThread = new Runnable() {
        Runnable runnable = null;
        @Override
        public void run() {
            //不停的监听队列
            while(true) {
                try {
                    runnable = mQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mThreadPoolExecutor.execute(runnable);
            }
        }
    };

    // 创建演示队列(重试机制是要延迟一定的时间进行重试)
    private DelayQueue<HttpTask> mDelayQueue = new DelayQueue<>();

    //失败的任务添加到延时队列
    public void addDelayTask(HttpTask httpTask) {
        if (null != httpTask) {
            //设置延时三秒
            httpTask.setDelayTime(3000);
            mDelayQueue.offer(httpTask);
        }
    }

    public Runnable delayThread = new Runnable() {
        HttpTask task = null;

        @Override
        public void run() {
            while (true) {
                try {
                    task = mDelayQueue.take();
                    if (task.getRetryCount() < 3) {
                        mThreadPoolExecutor.execute(task);
                        task.setRetryCount(task.getRetryCount() + 1);
                        Log.i("重试机制", "count = " + task.getRetryCount() +
                                " time = " + System.currentTimeMillis());
                    }else {
                        Log.i("重试机制", "重试机制一直不成功");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static ThreadPoolManager getInstance() {
        if (null == instance) {
            synchronized (ThreadPoolManager.class) {
                if (null == instance) {
                    instance = new ThreadPoolManager();
                }
            }
        }
        return instance;
    }

    public void addTask(Runnable runnable) {
        if(null != runnable) {
            try {
                mQueue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

