package com.wkq.base.utils;

import android.content.Context;
import android.view.WindowManager;

import java.text.DecimalFormat;

/**
 * Created by gujun on 2017/10/14.
 */

public class ScreenUtil {

    /**
     * 获取手机宽度的分辨率
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        return screenWidth;
    }

    /**
     * 获取手机高度的分辨率
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenHeight = wm.getDefaultDisplay().getHeight();
        return screenHeight;
    }

    public static int getHeight(Context ctx) {
        return Math.min(
                ctx.getResources().getDisplayMetrics().widthPixels,
                ctx.getResources().getDisplayMetrics().heightPixels);
    }

    public static int getWidth(Context ctx) {
        return Math.max(
                ctx.getResources().getDisplayMetrics().widthPixels,
                ctx.getResources().getDisplayMetrics().heightPixels);
    }

    public static int getRealWidth(Context context, int x1, int x2) {
        float num = (float) x1 / x2;
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        return (int) (Float.parseFloat(decimalFormat.format(num)) * getScreenWidth(context));
    }

    public static int getRealHeight(Context context, int x1, int x2) {
        float num = (float) x1 / x2;
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        return (int) (Float.parseFloat(decimalFormat.format(num)) * getScreenHeight(context));
    }
}
