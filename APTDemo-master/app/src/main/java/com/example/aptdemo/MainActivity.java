package com.example.aptdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.annonation.ARouter;

@ARouter(path = "/main/MainActivity", group = "main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_jump_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class targetClass = OrderActivity$$ARouter.findTargetClass("/main/OrderActivity");
                startActivity(new Intent(MainActivity.this, targetClass));
            }
        });
    }
}
