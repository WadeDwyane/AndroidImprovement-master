package demo.mvp.com.mvpdemo2.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M extends BaseModel, V extends BaseView, Contract> {

    protected M model;

    public WeakReference<V> mVWeakReference;

    public BasePresenter() {
        model = getModel();
    }

    protected abstract M getModel();

    public void bindView(V v) {
        mVWeakReference = new WeakReference<>(v);
    }

    public void unbindView() {
        if (null != mVWeakReference) {
            mVWeakReference.clear();
            mVWeakReference = null;
            System.gc();
        }
    }

    public abstract Contract getContract();

    public V getView() {
        if (mVWeakReference != null) {
            return mVWeakReference.get();
        }
        return null;
    }
}
