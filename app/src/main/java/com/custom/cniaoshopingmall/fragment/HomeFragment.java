package com.custom.cniaoshopingmall.fragment;

import android.util.Log;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseFragment;
import com.custom.cniaoshopingmall.net.BaseCallback;
import com.custom.cniaoshopingmall.net.Business;

import java.io.IOException;

import okhttp3.Response;


/**
 * Created by Ivan on 15/9/25.
 */
public class HomeFragment extends BaseFragment {

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }
    public void getBanner(){
        Business.getBanner(1, new BaseCallback() {
            @Override
            public void onFailure(IOException e) {
                try {
                    Log.e("onFailure",e.toString());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onResponse(Response response) {
                try {
                    Log.e("onResponse",response.body().string());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onRequessSuccess(Response response) {
                   try {
                       Log.e("response",response.body().string());
                      }catch (Exception ex){
                        ex.printStackTrace();
                     }

            }

            @Override
            public void onRequessError(Response response) {
                try {
                    Log.e("onRequessError",response.body().string());
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });
    }
    @Override
    public void initView() {
        getBanner();
    }
}
