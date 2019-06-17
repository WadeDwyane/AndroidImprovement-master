package com.wadedwyane.www;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wadedwyane.www.eventbus.EventBus;
import com.wadedwyane.www.eventbus.Subscribe;
import com.wadedwyane.www.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        findViewById(R.id.tv_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }


    //subScribe是注解,只是一个标记的作用,标记该方法都将被eventbus给收录
    //ThreadMode是个枚举,代表
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBean(EventBean eventBean) {
        Toast.makeText(MainActivity.this, eventBean.getStringOne() + eventBean.getStringTwo(), Toast.LENGTH_SHORT).show();
    }

}
