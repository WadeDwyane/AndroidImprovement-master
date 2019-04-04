package www.wadedwyane.com.logincomponent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import www.wadedwyane.com.logincomponent.R;

/**
 * @author kui.liu
 * @time 2019/3/26 16:41
 */
public class LoginActivity extends Activity{

    public static final String EXTRA_TARGET_CLASS = "www.wadedwyane.com.logincomponent.activity.LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
