package com.example.aptdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.annonation.ARouter;

@ARouter(path = "/main/OrderActivity", group = "main")
public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        findViewById(R.id.btn_jump_personal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class targetClass = PersonalActivity$$ARouter.findTargetClass("/main/PersonalActivity");
                startActivity(new Intent(OrderActivity.this, targetClass));
            }
        });
    }
}
