package com.wadedwyane.customokhttp.okhttp;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author kui.liu
 * @time 2019/4/10 10:08
 */
public class JsonHttpRequest implements IHttpRequest{

    private String url;
    private byte[] data;
    private CallBackListener listener;
    private HttpURLConnection urlConnection;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public void setListener(CallBackListener listener) {
        this.listener = listener;
    }

    @Override
    public void execute() {
        //具体去做访问网络的操作
        URL url = null;
        try {
            url = new URL(this.url);
            //打开http连接
            urlConnection = (HttpURLConnection) url.openConnection();
            //设置连接超时时间
            urlConnection.setConnectTimeout(6000);
            //不使用缓存
            urlConnection.setUseCaches(false);
            //设置响应的超时时间
            urlConnection.setReadTimeout(3000);
            //设置可以写入数据
            urlConnection.setDoInput(true);
            //设置可以输出数据
            urlConnection.setDoOutput(true);
            //设置消息类型
            urlConnection.setRequestProperty("Content-Type", "application/json;chartset=UTF-8");
            //连接
            urlConnection.connect();
            //使用字节发送数据
            OutputStream stream = urlConnection.getOutputStream();
            //缓冲字节流包装字符
            BufferedOutputStream bos = new BufferedOutputStream(stream);
            //把这个字节写入缓冲区
            bos.write(data);
            //刷新缓冲区
            bos.flush();
            stream.close();

            //字符流写入数据
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                InputStream inputStream = urlConnection.getInputStream();
                listener.onSuccess(inputStream);
            }else {
                throw new RuntimeException("请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("请求失败");
        }finally {
            urlConnection.disconnect();
        }

    }
}
