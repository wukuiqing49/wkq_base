package com.wkq.base.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/5/28
 * <p>
 * 简介:
 */
public class BaseActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    //app 是否在前台
    protected static boolean isActive = false;
    //启动Activity的数量
    protected static int activeCount = 0;

    protected static  String  topActivityName;
    //activity 创建
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity!=null)topActivityName=activity.getClass().getName();
    }

    //activity 启动
    @Override
    public void onActivityStarted(Activity activity) {

        if (activity!=null)topActivityName=activity.getClass().getName();
        if (!isActive)  isActive = true;
        activeCount++;
    }

    //activity 展示在前台
    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    //activity 暂停
    @Override
    public void onActivityStopped(Activity activity) {
        activeCount--;
        if (activeCount == 0 && isActive) {
            isActive = false;
        }

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
    /**
     *  获取是否在前台活动的状态
     */
    public static boolean  isActive(){
        return isActive;
    }

    /**
     * 获取前台的页面的名字
     * @return
     */
    public static String getTopActivityName(){
        return topActivityName;
    }

    /**
     * 获取打开Activity的个数
     * @return
     */
    public static int getActiveCount(){
        return activeCount;
    }

}
