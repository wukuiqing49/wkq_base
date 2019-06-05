package com.wkq.base.frame.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.wkq.base.frame.ReflectUtil;
import com.wkq.base.frame.mosby.delegate.MvpPresenter;
import com.wkq.base.frame.mosby.delegate.MvpView;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/5/30
 * <p>
 * 简介: 自动绑定数据的基类
 */
public abstract class MvpBindingDataActivity<V extends MvpView, P extends MvpPresenter<V>, D, T extends ViewDataBinding> extends MvpActivity<V, P> {
    public T binding;
    public D data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        ReflectUtil.invoke(this.binding, "setData", getBindingData());
    }

    private D getBindingData() {
        if (data == null) data = initBindingData();
        return data;
    }

    private D initBindingData() {
        return ReflectUtil.instance(2, this);
    }

    ;
}
