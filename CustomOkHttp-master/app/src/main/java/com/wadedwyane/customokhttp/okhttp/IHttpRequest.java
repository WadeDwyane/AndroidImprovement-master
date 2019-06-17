package com.wadedwyane.customokhttp.okhttp;

/**
 * @author kui.liu
 * @time 2019/4/10 9:55
 */
public interface IHttpRequest {

    void setUrl(String url);

    void setData(byte[] data);

    void setListener(CallBackListener listener);

    void execute();
}
