package com.wadedwyane.www.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wadedwyane.www.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Intent intent1 = new Intent(this, GradientPaintActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn2:
                Intent intent2 = new Intent(this, XfermodeActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn3:
                Intent intent3 = new Intent(this, XfermodesActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn4:
                Intent intent4 = new Intent(this, XfermodeEraserActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn5:
                Intent intent5 = new Intent(this, CanvasActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn6:
                Intent intent6 = new Intent(this, CanvasSaveRestoreActivity.class);
                startActivity(intent6);
                break;
            case R.id.btn7:
                Intent intent7 = new Intent(this, BallShotActivity.class);
                startActivity(intent7);
                break;
            default:
                break;
        }

    }
}
