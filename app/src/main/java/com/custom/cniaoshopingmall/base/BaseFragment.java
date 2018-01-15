package com.custom.cniaoshopingmall.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.custom.cniaoshopingmall.MyApplication;
import com.custom.cniaoshopingmall.utils.SharePerferenceUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuchichi on 2017/12/21.
 */
public abstract class BaseFragment extends Fragment {
    public Context context;
    public SharePerferenceUtils perferenceUtils;
    View view;
    Unbinder unbinder;
    public abstract int setLayout();

    public abstract void initView(View view);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=getActivity();
        view=LayoutInflater.from(context).inflate(setLayout(),null);
        unbinder=ButterKnife.bind(this,view);
        initView(view);
        perferenceUtils=MyApplication.getInstace().sharePerferenceUtils;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            getData();
        }
    }

    public void getData(){

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            getData();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
