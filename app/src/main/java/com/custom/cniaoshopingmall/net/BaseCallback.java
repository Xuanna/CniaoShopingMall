package com.custom.cniaoshopingmall.net;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by xuchichi on 2017/12/21.
 */
public abstract class BaseCallback {
    public abstract void onFailure(IOException e);
    public abstract void onResponse(Response response);
    public abstract void onRequessSuccess(Response response);
    public abstract void onRequessError(Response response);
}
