package com.wadedwyane.customokhttp.okhttp;

import java.io.InputStream;

/**
 * @author kui.liu
 * @time 2019/4/10 9:57
 */
public interface CallBackListener {

    //为什么要用inputStream?
    void onSuccess(InputStream inputStream);

    void onFailure();

}
