package demo.mvp.com.mvpdemo2.base;

public abstract class BaseModel<P extends BasePresenter, Contract> {

    public P p;

    public BaseModel(P p) {
        this.p = p;
    }

    public abstract Contract getContract();

}
