package com.wkq.demo;

import android.content.Context;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utils.AlertUtil;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/5/30
 * <p>
 * 简介:
 */
public class MvpDemoView implements MvpView {

    public void showMessage(Context context, String mesage) {
        if (context == null) return;
        AlertUtil.showDeftToast(context, mesage);
    }
}
