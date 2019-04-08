package com.wadedwyane.www;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wadedwyane.www.databus.LiveDataBus;
import com.wadedwyane.www.databus.LiveDataTimerViewModel;
import com.wadedwyane.www.databus.Observer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;


/**
 * @author kui.liu
 * @time 2019/4/8 14:48
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

       final TextView tvTime =  findViewById(R.id.tv_time);

        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataBus.getInstance().getChannel("event", String.class).postValue("second activity 發送來數據");
            }
        });

        LiveDataTimerViewModel viewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                tvTime.setText(String.valueOf(aLong));
            }
        });
    }
}
