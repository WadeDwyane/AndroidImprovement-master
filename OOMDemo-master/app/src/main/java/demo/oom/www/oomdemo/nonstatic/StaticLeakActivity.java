package demo.oom.www.oomdemo.nonstatic;

import android.app.Activity;
import android.os.Bundle;

/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: kui.liu
 * Time: 2019/11/18 12:58
 * Description: 静态变量导致的内存泄漏
 * -----------------------------------------------------------
 */
public class StaticLeakActivity extends Activity {

    public static NonStaticClass nonStaticClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (nonStaticClass == null){
            nonStaticClass = new NonStaticClass();
        }

    }

    public class NonStaticClass {

    }
}
