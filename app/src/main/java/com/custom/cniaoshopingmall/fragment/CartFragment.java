package com.custom.cniaoshopingmall.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseFragment;
import com.custom.cniaoshopingmall.net.BaseCallback;
import com.custom.cniaoshopingmall.net.Business;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Response;


/**
 * Created by Ivan on 15/9/22.
 */
public class CartFragment extends BaseFragment {
    @InjectView(R.id.tv)
    TextView tv;

    @Override
    public int setLayout() {
        return R.layout.fragment_cart;
    }

    @Override
    public void initView() {
        Business.getBanner(1, new BaseCallback() {
            @Override
            public void onFailure(IOException e) {
                try {
                    Log.e("onFailure", e.toString());
                } catch (Exception es) {

                }
            }

            @Override
            public void onResponse(Response response) {
                try {
                    Log.e("onResponse", response.body().string());
                } catch (Exception e) {

                }
            }

            @Override
            public void onRequessSuccess(Response response) {
                try {
//                    Log.e("onRequessSuccess", response.body().string());
                    tv.setText(response.body().string());
                } catch (Exception e) {
                        e.printStackTrace();
                }

            }

            @Override
            public void onRequessError(Response response) {
                try {
                    Log.e("onRequessError", response.body().string());
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
