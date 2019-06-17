package com.wadedwyane.customokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wadedwyane.customokhttp.okhttp.IJsonDataListener;
import com.wadedwyane.customokhttp.okhttp.OkHttp;

public class MainActivity extends AppCompatActivity {

    private String url = "http://v.juhe.cn/historyWeather/citys?province_id=2&key=bb52107206585ab074f5e59a8c73875b";
//    private String url = "http://asdascasdcsadfa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
    }

    private void sendRequest() {
        OkHttp.sendJsonRequest(null, url, Response.class, new IJsonDataListener<Response>() {

            @Override
            public void onSuccess(Response response) {
                Toast.makeText(MainActivity.this, "请求成功: " + response.toString() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
