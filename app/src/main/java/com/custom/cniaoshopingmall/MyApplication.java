package com.custom.cniaoshopingmall;

import android.app.Application;
import android.content.Context;

/**
 * Created by xuchichi on 2017/12/21.
 */
public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
}
