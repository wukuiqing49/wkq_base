package com.wkq.base.frame.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import com.wkq.base.frame.mosby.delegate.MvpPresenter;
import com.wkq.base.frame.mosby.delegate.MvpView;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/5/29
 * <p>
 * 简介:  带Databinding 的基类
 */
public abstract  class MvpBindingActivity <V extends MvpView ,P extends MvpPresenter<V>,T extends ViewDataBinding>extends MvpActivity<V,P> {
    public T binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, this.getLayoutId());
    }


}
