package demo.mvp.com.mvpdemo2.base;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseView<P extends BasePresenter, Contract> extends Activity {

    public P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //弱引用
        presenter = getPresenter();
        //绑定(将presenter和view进行绑定)
        presenter.bindView(this);
    }

    public abstract Contract getContract();

    public abstract P getPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbindView();
    }
}
