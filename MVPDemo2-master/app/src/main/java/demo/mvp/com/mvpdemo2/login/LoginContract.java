package demo.mvp.com.mvpdemo2.login;

import demo.mvp.com.mvpdemo2.base.BaseEntity;

public interface LoginContract {


    interface Model {
        //第二步(Model层的子类去完成登录操作)
        void login(String name, String password);
    }

    interface View<T extends BaseEntity> {
        // 第四步(真正去执行刷新的操作)
        void handleResult(T result);
    }

    interface Presenter<T> {
        //第一步(让第二步去做)
        void login(String name, String password);

        //第三步(让第四布去做)
        void refreshUI(T t);
    }
}
