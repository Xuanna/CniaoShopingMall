package com.custom.cniaoshopingmall.utils;

import android.text.TextUtils;

import com.custom.cniaoshopingmall.MyApplication;
import com.custom.cniaoshopingmall.msg.LoginRespMsg;

/**
 * Created by xuchichi on 2018/1/15.
 */
public class UserUtil {
    public static void saveUser(LoginRespMsg loginRespMsg){
        MyApplication.getInstace().sharePerferenceUtils.setString("saveUser",JSONUtil.toJSON(loginRespMsg));
    }
    public static LoginRespMsg getUser(){
        String string= MyApplication.getInstace().sharePerferenceUtils.getString("saveUser",null);
        LogUtils.e("string="+string);
        if(TextUtils.isEmpty(string)){
            return null;
        }else{
            return  JSONUtil.fromJson(string,LoginRespMsg.class);
        }
    }
    public static void saveToken(String token){
        MyApplication.getInstace().sharePerferenceUtils.setString("token",token);
    }
    public static String getToken(){
        return  MyApplication.getInstace().sharePerferenceUtils.getString("token",null);
    }
}
