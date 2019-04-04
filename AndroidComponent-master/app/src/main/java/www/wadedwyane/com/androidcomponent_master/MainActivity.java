package www.wadedwyane.com.androidcomponent_master;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import www.wadedwyane.com.componentlib.service.factory.ServiceFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        findViewById(R.id.btn_gomime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gomine();
            }
        });

        findViewById(R.id.btn_showmime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmime();
            }
        });
    }

    public void login() {
        //登錄
        ServiceFactory.getInstance().getmLoginService().launch(this,
                "");
    }

    public void showmime() {
        //展示login模塊中的ui
        FragmentManager manager = getSupportFragmentManager();
        ServiceFactory.getInstance().getmLoginService().newUserInfoFragment(manager, R.id.container, new Bundle());
    }

    public void gomine() {
        //跳轉到'mime'
        ServiceFactory.getInstance().getmMineServicel().
                launch(this, 222);
    }
}
