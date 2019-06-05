package com.wkq.base.frame.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.wkq.base.frame.ReflectUtil;
import com.wkq.base.frame.mosby.MvpBaseActivity;
import com.wkq.base.frame.mosby.delegate.MvpPresenter;
import com.wkq.base.frame.mosby.delegate.MvpView;


/**
 * MVP Databinding Base Activity
 * @param <V> MVP View {@link MvpView}
 * @param <P> MVP Presenter {@link MvpPresenter}
 */
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpBaseActivity<V, P> {

    public V view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    protected abstract int getLayoutId();

    @NonNull
    public V createView() {
        return ReflectUtil.instance(0,this);
    }

    @NonNull
    @Override
    public P createPresenter() {
        return ReflectUtil.instance(1, this);
    }

    @NonNull
    @Override
    public V getMvpView() {
        if (view == null) view = createView();
        return view;
    }

}
