package demo.mvp.com.mvpdemo2.login;

import demo.mvp.com.mvpdemo2.base.BaseModel;

public class LoginModel extends BaseModel<LoginPresenter, LoginContract.Model> {

    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void login(String name, String password) {
                if ("liukui".equals(name) && "123".equals(password)) {
                    p.getContract().refreshUI(new UserInfo("liukui", "123"));
                }else {
                    p.getContract().refreshUI(null);
                }
            }
        };
    }
}
