package com.custom.cniaoshopingmall;

import android.app.Application;
import android.content.Context;

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

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();


    }
}
