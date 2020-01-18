package com.wadedwyane.www.aopdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DBOperation {

    private Button mBtnJump;

    private DBOperation mDBOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnJump = findViewById(R.id.btn_jump);
        mBtnJump.setOnClickListener(this);

//        采用代理的方式去处理。
        mDBOperation = (DBOperation) Proxy.newProxyInstance(DBOperation.class.getClassLoader(),
                new Class[]{DBOperation.class},
                new DBOperationHandler(this));
    }

    class DBOperationHandler implements InvocationHandler {

        private DBOperation mDBOperation;

        private DBOperationHandler(DBOperation operation) {
            this.mDBOperation = operation;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (null != mDBOperation) {
                Log.e("MainActivity", "操作之前先备份");
                save();
                Log.e("MainActivity", "备份完毕");
                return method.invoke(mDBOperation, args);
            }
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, OrderInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void insert() {
        Log.e("MainActivity", "insert data");
    }

    @Override
    public void delete() {
        Log.e("MainActivity", "delete data");
    }

    @Override
    public void update() {
        Log.e("MainActivity", "update data");
    }

    @Override
    public void select() {
        Log.e("MainActivity", "select data");
    }

    @Override
    public void save() {
        Log.e("MainActivity", "save data");
    }

    public void insert(View view) {
        mDBOperation.insert();
    }

    public void delete(View view) {
        mDBOperation.delete();
    }

    public void update(View view) {
        mDBOperation.update();
    }

    public void select(View view) {
        mDBOperation.select();
    }
}
