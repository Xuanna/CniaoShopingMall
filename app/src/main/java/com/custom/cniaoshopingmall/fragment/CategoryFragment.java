package com.custom.cniaoshopingmall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseFragment;
import com.custom.cniaoshopingmall.widget.CnToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ivan on 15/9/22.
 */
public class CategoryFragment extends BaseFragment {
    @BindView(R.id.cnToolbar)
    CnToolbar cnToolbar;

    @Override
    public int setLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void initView(View view) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}



