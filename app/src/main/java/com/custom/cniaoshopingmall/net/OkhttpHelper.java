package com.custom.cniaoshopingmall.net;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.custom.cniaoshopingmall.MyApplication;
import com.custom.cniaoshopingmall.utils.LogUtils;
import com.custom.cniaoshopingmall.utils.UserUtil;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xuchichi on 2017/12/21.
 */
public class OkhttpHelper {
    private OkHttpClient okHttpClient  = MyApplication.okHttpClient;
    private Handler handler;
    private Gson gson;

    public OkhttpHelper() {
        handler=new Handler(Looper.getMainLooper());
        gson=new Gson();
    }

    public  static OkhttpHelper getInstance(){
        return new OkhttpHelper();
    }
    public  void get(String url,BaseCallback callback){
        Request request=getRequest(url,null,OkhttpMethod.GET);
        doRequest(request,callback);
    }
    public  void post(String url,Map<String,String> params,BaseCallback callback){
        RequestBody body=formData(params);
        Request request=getRequest(url,body,OkhttpMethod.POST);
        doRequest(request,callback);
    }

    public  void doRequest(Request request, final BaseCallback callback){
        callback.onRequestBefore();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(response);
                if(response.isSuccessful()){
                    String string=response.body().string();
                    try {
                        LogUtils.e(string);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    if(callback.mType==String.class){
                        onRequestSuccess(response,callback,string);
                    }else{
                        try {
                            Object object= gson.fromJson(string,callback.mType);
                            onRequestSuccess(response,callback,object);
                        }catch (JsonParseException ex){
                            ex.printStackTrace();
                            onRequessError(response,callback);
                        }

                    }
                }else{
                    onRequessError(response,callback);
                }
            }
        });
    }
    private void onRequestSuccess(final Response response,final BaseCallback callback,final  Object object){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onRequessSuccess(response,object);
            }
        });

    }
    private void onRequessError(final Response response,final BaseCallback callback){
        handler.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("onError");
                callback.onRequessError(response);
            }
        });

    }
    public static Request getRequest(String url, RequestBody requestBody, OkhttpMethod method){
      Request.Builder builder=new Request.Builder();
//      url=url+"?token"+UserUtil.getToken(MyApplication.context);
        builder.url(url);
        if(method==OkhttpMethod.GET){
            builder.get();
        }else if (method==OkhttpMethod.POST){
            builder.post(requestBody);
        }
        return builder.build();
    }
    public static RequestBody formData(Map<String,String> params){
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String,String> map:params.entrySet()) {
            builder.add(map.getKey(),map.getValue());
        }
//        String token= UserUtil.getToken(MyApplication.context);
//        if (!TextUtils.isEmpty(token)){
//            builder.add("token",token);
//        }

        return  builder.build();
    }
    enum OkhttpMethod{
        GET,
        POST
    }
}
