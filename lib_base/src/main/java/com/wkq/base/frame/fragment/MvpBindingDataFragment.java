package com.wkq.base.frame.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wkq.base.frame.ReflectUtil;
import com.wkq.base.frame.mosby.delegate.MvpPresenter;
import com.wkq.base.frame.mosby.delegate.MvpView;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/5/30
 * <p>
 * 简介: 自动绑定data 的基类
 */
public abstract class MvpBindingDataFragment<V extends MvpView, P extends MvpPresenter<V>, D, T extends ViewDataBinding> extends MvpFragment<V, P> {
    //页面的binding
    public T binding;
    //绑定的数据
    public D data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        //通多反射调用 binding的反射的方法
        ReflectUtil.invoke(this.binding, "setData", this.getViewData());
        return binding.getRoot();
    }

    /**
     * 根据反射创建绑定的数据
     * @return
     */
    private D initLayoutData() {
        return ReflectUtil.instance(2, this);
    }

    @NonNull
    private D getViewData() {
        if (this.data == null) {
            this.data = this.initLayoutData();
        }
        return this.data;
    }

}
