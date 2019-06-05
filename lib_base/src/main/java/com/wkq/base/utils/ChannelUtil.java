package com.wkq.base.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;


/**
 * Created by xiansong on 2014/12/26.
 */
public class ChannelUtil {
//    public static String getChannelFromApk(Context context) {
//        String channel = "";
//        String defaultChannel = AppConfig.isDebug() ? "debug" : "release";
//
//        try {
//            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
//            channel = appInfo.metaData.getString("APP_CHANNEL_NAME");
//        } catch (Exception e) {
//            Log.e("ChannelUtil", "", e);
//        }
//
//        channel = TextUtils.isEmpty(channel) ? defaultChannel : channel;
//        Log.e("ChannelUtil", channel);
//        return channel;
//    }
}
