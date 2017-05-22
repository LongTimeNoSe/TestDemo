package com.mvp.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by XuYanping on 2017/3/15.
 */

public abstract class BasePresenter<V> {

    protected Reference<V> mReference;

    //附加View (弱引用)
    public void attachView(V view) {
        mReference = new WeakReference<V>(view);
    }

    protected V getView() {
        return mReference.get();
    }

    public boolean isViewAttached() {
        return (mReference != null) && mReference.get() != null;
    }

    public String setTitle() {
        return null;
    }

    //分离View
    public void detachView() {
        if (mReference != null) {
            mReference.clear();
            mReference = null;
        }
    }
}
