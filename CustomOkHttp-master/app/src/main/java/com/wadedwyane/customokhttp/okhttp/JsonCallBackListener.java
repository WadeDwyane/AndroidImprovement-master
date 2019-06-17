package com.wadedwyane.customokhttp.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author kui.liu
 * @time 2019/4/10 10:23
 */
public class JsonCallBackListener<T> implements CallBackListener {

    public Class<T> responseClass;

    public IJsonDataListener iJsonDataListener;

    public Handler handler = new Handler(Looper.getMainLooper());

    public JsonCallBackListener(Class<T> responseClass, IJsonDataListener iJsonDataListener) {
        this.responseClass = responseClass;
        this.iJsonDataListener = iJsonDataListener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        String response = getContent(inputStream);
        Gson gson = new Gson();
        final T clazz = gson.fromJson(response, responseClass);
        //传到主线程
        handler.post(new Runnable() {
            @Override
            public void run() {
                iJsonDataListener.onSuccess(clazz);
            }
        });
    }

    private String getContent(InputStream inputStream) {
        String content = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;

        while (true) {
            try {
                if ((line = reader.readLine()) != null)  {
                    sb.append(line + "\n");
                }else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
        return content;
    }

    @Override
    public void onFailure() {

    }
}
