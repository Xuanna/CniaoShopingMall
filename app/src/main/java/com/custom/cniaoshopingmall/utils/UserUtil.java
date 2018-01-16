package com.custom.cniaoshopingmall.utils;

import android.content.Context;
import android.text.TextUtils;

import com.custom.cniaoshopingmall.MyApplication;
import com.custom.cniaoshopingmall.msg.LoginRespMsg;

/**
 * Created by xuchichi on 2018/1/15.
 */
public class UserUtil {
    public static void saveUser(LoginRespMsg loginRespMsg){
        MyApplication.sharePerferenceUtils.setString("saveUser",JSONUtil.toJSON(loginRespMsg));
    }
    public static LoginRespMsg getUser(){
        String string=   MyApplication.sharePerferenceUtils.getString("saveUser",null);
        if(TextUtils.isEmpty(string)){
            return null;
        }else{
            return  JSONUtil.fromJson(string,LoginRespMsg.class);
        }
    }
    public static void saveToken(String token){
        MyApplication.sharePerferenceUtils.setString("token",token);
    }
    public static String getToken(){
        return    MyApplication.sharePerferenceUtils.getString("token",null);
    }
}
