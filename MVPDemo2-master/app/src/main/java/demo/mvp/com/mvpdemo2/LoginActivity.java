package demo.mvp.com.mvpdemo2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import demo.mvp.com.mvpdemo2.base.BaseView;
import demo.mvp.com.mvpdemo2.login.LoginContract;
import demo.mvp.com.mvpdemo2.login.LoginPresenter;
import demo.mvp.com.mvpdemo2.login.UserInfo;

public class LoginActivity extends BaseView<LoginPresenter, LoginContract.View> {


    private EditText nameEt;
    private EditText pwdEt;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    // 初始化控件
    private void initView() {
        nameEt = findViewById(R.id.et_name);
        pwdEt = findViewById(R.id.et_pwd);
        btn = findViewById(R.id.bt_login);
    }

    // 点击事件
    public void doLoginAction(View view) {
        String name = nameEt.getText().toString();
        String pwd = pwdEt.getText().toString();
        // 发起需求，让Presenter处理
        presenter.getContract().login(name, pwd);
    }

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View<UserInfo>() {
            @Override
            public void handleResult(UserInfo result) {
                Toast.makeText(LoginActivity.this, null != result ? "登陆成功": "登录失败", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }
}
