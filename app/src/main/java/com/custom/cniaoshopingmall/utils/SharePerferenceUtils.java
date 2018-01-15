package com.custom.cniaoshopingmall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xuchichi on 2018/1/15.
 */
public class SharePerferenceUtils {
    final  String shared_pre_data = "shared_pre_data";
    private static SharedPreferences sp;

    public SharePerferenceUtils(Context context) {
        sp=context.getSharedPreferences(shared_pre_data,Context.MODE_PRIVATE);
    }

    public static  void setString(String key, String value){
        if (sp!=null){
            sp.edit().putString(key,value).commit();
        }else{
            LogUtils.e("sp为空");
        }

    }
    public static String getString(String key,String defValue){
            return sp.getString(key,defValue);
    }
    public static void setBoolean(String key,boolean value){
            sp.edit().putBoolean(key,value).commit();
    }
    public static boolean getBoolean(String key,boolean defValue){
            return sp.getBoolean(key,defValue);
    }
    public static void setFloat(String key,float value){
            sp.edit().putFloat(key,value).commit();
    }
    public static float getFloat(String key,float defValue){
            return sp.getFloat(key,defValue);
    }
}
