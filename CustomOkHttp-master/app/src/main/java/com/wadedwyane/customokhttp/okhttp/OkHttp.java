package com.wadedwyane.customokhttp.okhttp;

/**
 * @author kui.liu
 * @time 2019/4/10 10:58
 */
public class OkHttp {

    public static<T, M> void sendJsonRequest(T requestData, String url, Class<M> response, IJsonDataListener listener) {
        IHttpRequest httpRequest = new JsonHttpRequest();
        JsonCallBackListener callBackListener = new JsonCallBackListener<>(response, listener);
        HttpTask task = new HttpTask(url, requestData, httpRequest, callBackListener);
        ThreadPoolManager.getInstance().addTask(task);
    }

}
