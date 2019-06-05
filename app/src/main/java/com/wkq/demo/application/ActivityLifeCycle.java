package com.wkq.demo.application;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.wkq.base.application.BaseActivityLifecycle;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/5/28
 * <p>
 * 简介:
 */
public class ActivityLifeCycle extends BaseActivityLifecycle {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        super.onActivityCreated(activity, savedInstanceState);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        super.onActivityStarted(activity);
        if (isActive){
            DemoApplication.appOnline=isActive();
            Log.e("测试数据","前台");
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        super.onActivityPaused(activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        super.onActivityStopped(activity);
        if (getActiveCount()==0&&!isActive){
            Log.e("测试数据","后台进程");
        }
    }
}
