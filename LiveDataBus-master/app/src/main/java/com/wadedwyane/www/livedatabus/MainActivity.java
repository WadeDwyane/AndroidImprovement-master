package com.wadedwyane.www.livedatabus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wadedwyane.www.livedatabus.livedatabus.LiveDataBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //订阅通道
        LiveDataBus.getInstance().getChannel("event",String.class)
                .observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void jump(View view) {
    }

    public void send(View view) {
        LiveDataBus.getInstance().
                getChannel("event",String.class).
                postValue("请弹出一个吐司");
    }
}
