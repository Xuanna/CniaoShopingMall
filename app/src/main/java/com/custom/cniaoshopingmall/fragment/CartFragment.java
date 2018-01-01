package com.custom.cniaoshopingmall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseFragment;


/**
 * Created by Ivan on 15/9/22.
 */
public class CartFragment extends BaseFragment {

    @Override
    public int setLayout() {
        return R.layout.fragment_cart;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
