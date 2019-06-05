package com.wkq.base.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class PixelsUtil {
    public PixelsUtil() {
    }

    public static int dip2px(float dipValue) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

    public static int px2dip(float pxValue) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static int sp2px(float spVal) {
        return (int)TypedValue.applyDimension(2, spVal, Resources.getSystem().getDisplayMetrics());
    }

    public static float px2sp(float pxVal) {
        return pxVal / Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param dpValue 虚拟像素
     * @return 像素
     */
    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }
}
