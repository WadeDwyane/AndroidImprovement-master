package demo.mvp.com.mvpdemo2.login;

import demo.mvp.com.mvpdemo2.LoginActivity;
import demo.mvp.com.mvpdemo2.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginModel, LoginActivity, LoginContract.Presenter> {
    @Override
    protected LoginModel getModel() {
        return new LoginModel(this);
    }

    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter<UserInfo>(){

            @Override
            public void login(String name, String password) {
                model.getContract().login(name, password);
            }

            @Override
            public void refreshUI(UserInfo userInfo) {
                getView().getContract().handleResult(userInfo);
            }
        };
    }
}
