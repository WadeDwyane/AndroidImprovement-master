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
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btn10).setOnClickListener(this);
        findViewById(R.id.btn11).setOnClickListener(this);
        findViewById(R.id.btn12).setOnClickListener(this);
        findViewById(R.id.btn13).setOnClickListener(this);
        findViewById(R.id.btn14).setOnClickListener(this);
        findViewById(R.id.btn15).setOnClickListener(this);
        findViewById(R.id.btn16).setOnClickListener(this);
        findViewById(R.id.btn17).setOnClickListener(this);
        findViewById(R.id.btn18).setOnClickListener(this);
        findViewById(R.id.btn19).setOnClickListener(this);
        findViewById(R.id.btn20).setOnClickListener(this);
        findViewById(R.id.btn21).setOnClickListener(this);
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
            case R.id.btn8:
                Intent intent8 = new Intent(this, RotateSplashActivity.class);
                startActivity(intent8);
                break;
            case R.id.btn9:
                Intent intent9 = new Intent(this, QQUnReadDragActivity.class);
                startActivity(intent9);
                break;
            case R.id.btn10:
                Intent intent10 = new Intent(this, XiaoHongShuSplashActivity.class);
                startActivity(intent10);
                break;
            case R.id.btn11:
                Intent intent11 = new Intent(this, NetEaseYunMusicActivity.class);
                startActivity(intent11);
                break;
            case R.id.btn12:
                Intent intent12 = new Intent(this, PrecentLayoutActivity.class);
                startActivity(intent12);
                break;
            case R.id.btn13:
                Intent intent13 = new Intent(this, ModifyDensityActivity.class);
                startActivity(intent13);
                break;
            case R.id.btn14:
                Intent intent14 = new Intent(this, DisplayCutoutActivity.class);
                startActivity(intent14);
                break;
            case R.id.btn15:
                Intent intent15 = new Intent(this, YunMusicListActivity.class);
                startActivity(intent15);
                break;
            case R.id.btn16:
                Intent intent16 = new Intent(this, YunMusicListActivity.class);
                startActivity(intent16);
                break;
            case R.id.btn17:
                Intent intent17 = new Intent(this, RecyclerViewTitleHoverActivity.class);
                startActivity(intent17);
                break;
            case R.id.btn18:
                Intent intent18 = new Intent(this, CardViewActivity.class);
                startActivity(intent18);
                break;
            case R.id.btn19:
                Intent intent19 = new Intent(this, CustomComposeActivity.class);
                startActivity(intent19);
                break;
            case R.id.btn20:
                Intent intent20 = new Intent(this, CustomRecyclerViewActivity.class);
                startActivity(intent20);
                break;
            case R.id.btn21:
                Intent intent21 = new Intent(this, BigImageActivity.class);
                startActivity(intent21);
                break;
            default:
                break;
        }

    }
}
