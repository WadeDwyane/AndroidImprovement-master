package com.wadedwyane.www.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wadedwyane.www.R;
import com.wadedwyane.www.view.RecyclerView;

public class CustomRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_recyclerview);
        RecyclerView rcy = findViewById(R.id.recyclerView);
        rcy.setAdapter(new RecyclerView.Adapter() {
            @Override
            public View onCreateViewHodler(int position, View convertView, ViewGroup parent) {
                convertView = CustomRecyclerViewActivity.this.getLayoutInflater().inflate(R.layout.item_table, parent, false);
                TextView textView = (TextView) convertView.findViewById(R.id.text1);
                textView.setText("网易课堂 " + position);
                Log.i("TAG", "onCreateViewHodler: " + convertView.hashCode());
                return convertView;
            }

            @Override
            public View onBinderViewHodler(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) convertView.findViewById(R.id.text1);
                textView.setText("网易课堂 " + position);
                Log.i("TAG", "onBinderViewHodler: " + convertView.hashCode());
                return convertView;
            }

            @Override
            public int getItemViewType(int row) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public int getCount() {
                return 30;
            }

            @Override
            public int getHeight(int index) {
                return 100;
            }
        });
    }
}
