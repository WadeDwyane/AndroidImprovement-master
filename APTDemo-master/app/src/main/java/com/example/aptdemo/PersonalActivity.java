package com.example.aptdemo;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.annonation.ARouter;

@ARouter(path = "/main/PersonalActivity", group = "main")
public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        findViewById(R.id.btn_jump_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class targetClass = MainActivity$$ARouter.findTargetClass("/main/MainActivity");
                startActivity(new Intent(PersonalActivity.this, targetClass));
            }
        });
    }
}
