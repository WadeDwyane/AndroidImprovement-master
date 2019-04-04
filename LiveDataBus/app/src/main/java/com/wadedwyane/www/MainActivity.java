package com.wadedwyane.www;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wadedwyane.www.databus.LiveDataBus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LiveDataBus.getInstance().getChannel("event", String.class).observe(MainActivity.this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void jump(View view) {

    }

    public void send(View view) {
        LiveDataBus.getInstance().getChannel("event",String.class).postValue("make a toast");
    }
}
