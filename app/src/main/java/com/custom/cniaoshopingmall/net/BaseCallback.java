package com.custom.cniaoshopingmall.net;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by xuchichi on 2017/12/21.
 */
public abstract class BaseCallback<T> {
    public Type mType;

    static Type getSuperclassTypeParameter(Class<?> subclass)
    {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class)
        {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }


    public BaseCallback()
    {
        mType = getSuperclassTypeParameter(getClass());
    }
    public abstract void onFailure(IOException e);
    public abstract void onRequestBefore();
    public abstract void onResponse(Response response);
    public abstract void onRequessSuccess(Response response,T t);
    public abstract void onRequessError(Response response);
}
