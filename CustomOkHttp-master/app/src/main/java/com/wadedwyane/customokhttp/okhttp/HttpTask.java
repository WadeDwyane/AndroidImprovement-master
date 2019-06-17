package com.wadedwyane.customokhttp.okhttp;

import com.google.gson.Gson;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author kui.liu
 * @time 2019/4/10 10:01
 */
public class HttpTask<T> implements Runnable, Delayed {

    private IHttpRequest mIHttpRequest;

    //延时时间
    private long delayTime;

    //重试次数
    private long retryCount;

    public HttpTask(String Url, T requestData, IHttpRequest request, CallBackListener listener) {
        request.setListener(listener);
        request.setUrl(Url);
        Gson gson = new Gson();
        String content = gson.toJson(requestData);
        request.setData(content.getBytes());
        this.mIHttpRequest = request;
    }

    @Override
    public void run() {
        //最终run方法被执行,具体执行请求的方法
        try {
            mIHttpRequest.execute();
        } catch (RuntimeException e) {
            //请求失败,将任务放到重试队列里面
            ThreadPoolManager.getInstance().addDelayTask(this);
        }
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //TODO:重要
        return unit.convert(this.delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        //TODO:重要
        this.delayTime = System.currentTimeMillis() + delayTime;
    }

    public long getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(long retryCount) {
        this.retryCount = retryCount;
    }
}
