package com.custom.cniaoshopingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by xuchichi on 2017/12/21.
 */
public abstract class BaseFragment extends Fragment {
    public Context context;
    View view;

    public abstract int setLayout();

    public abstract void initView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=getActivity();
        view=LayoutInflater.from(context).inflate(setLayout(),null);
        ButterKnife.inject(view);
        initView();
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

    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(view);
    }
}
