package com.wkq.demo.application;

import android.app.Application;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/5/28
 * <p>
 * 简介:
 */
public class DemoApplication extends Application {
    //app是否在线
    public static boolean appOnline = false;

    @Override
    public void onCreate() {
        super.onCreate();
        // 注册 程序前后台切换的监听
        registerActivityLifecycleCallbacks(new ActivityLifeCycle());
    }
}
