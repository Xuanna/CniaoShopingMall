package com.custom.cniaoshopingmall.utils;

import android.content.Context;

/**
 * Created by xuchichi on 2018/1/4.
 */
public class CommonUtil {
    public static   int px2dp(Context context,int pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static  int dp2Px(Context context,int pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue*scale + 0.5f);
    }
}
