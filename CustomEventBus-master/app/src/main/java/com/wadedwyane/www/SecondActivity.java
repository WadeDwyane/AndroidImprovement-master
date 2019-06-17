package com.wadedwyane.www;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wadedwyane.www.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.tv_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post(new EventBean("eventbus", " nice"));
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        EventBus.getDefault().post(new EventBean("haha", "zixiancheng"));
                    }
                }.start();
            }
        });
    }
}
