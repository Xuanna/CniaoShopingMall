package com.custom.cniaoshopingmall;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.custom.cniaoshopingmall.utils.LogUtils;
import com.custom.cniaoshopingmall.utils.SharePerferenceUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by xuchichi on 2017/12/21.
 */
public class MyApplication extends Application {

    public static Context context;

    public static OkHttpClient okHttpClient =new OkHttpClient.Builder()
            .connectTimeout(10*1000, TimeUnit.SECONDS)
    .writeTimeout(10*1000, TimeUnit.SECONDS)
    .readTimeout(10*1000, TimeUnit.SECONDS).build();

    public static SharePerferenceUtils sharePerferenceUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context=this;
        sharePerferenceUtils=new SharePerferenceUtils(this);
    }
}
