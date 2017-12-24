package com.custom.cniaoshopingmall.net;

import android.os.Handler;
import android.os.Looper;

import com.custom.cniaoshopingmall.MyApplication;

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

    public OkhttpHelper() {
        handler=new Handler(Looper.getMainLooper());
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
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    onRequestSuccess(response,callback);
//                    callback.onRequessSuccess(response);
                }else{
                    onRequessError(response,callback);
//                        callback.onRequessError(response);
                }
            }
        });
    }
    private void onRequestSuccess(final Response response,final BaseCallback callback){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onRequessSuccess(response);
            }
        });

    }
    private void onRequessError(final Response response,final BaseCallback callback){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onRequessError(response);
            }
        });

    }
    public static Request getRequest(String url, RequestBody requestBody, OkhttpMethod method){
      Request.Builder builder=new Request.Builder();
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
        return  builder.build();
    }
    enum OkhttpMethod{
        GET,
        POST
    }
}
