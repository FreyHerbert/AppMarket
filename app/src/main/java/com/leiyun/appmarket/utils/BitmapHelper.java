package com.leiyun.appmarket.utils;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by LeiYun on 2017/2/12 0012.
 */

public class BitmapHelper {
    private static BitmapUtils sBitmapUtils = null;

    // 单例，懒汉模式
    public static BitmapUtils getBitmapUtils() {
        if (sBitmapUtils == null) {
            synchronized (BitmapHelper.class) {
                if (sBitmapUtils == null)
                    sBitmapUtils = new BitmapUtils(UIUtils.getContext());
            }
        }

        return sBitmapUtils;
    }
}
