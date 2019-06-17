package com.wadedwyane.customokhttp.okhttp;

/**
 * @author kui.liu
 * @time 2019/4/10 10:50
 */
public interface IJsonDataListener<T> {

    void onSuccess(T t);

    void onFailure();
}
