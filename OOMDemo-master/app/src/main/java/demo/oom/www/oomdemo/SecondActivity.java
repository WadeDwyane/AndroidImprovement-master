package demo.oom.www.oomdemo;

import android.app.Activity;
import android.os.Bundle;

import demo.oom.www.oomdemo.R;
import demo.oom.www.oomdemo.singleton.SingletonActivityContext;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        SingletonActivityContext context = SingletonActivityContext.getInstance(this);
    }
}
