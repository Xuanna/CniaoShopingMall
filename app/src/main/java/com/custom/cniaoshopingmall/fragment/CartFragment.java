package com.custom.cniaoshopingmall.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.adapter.CartAdapter;
import com.custom.cniaoshopingmall.base.BaseFragment;
import com.custom.cniaoshopingmall.widget.CnToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Ivan on 15/9/22.
 */
public class CartFragment extends BaseFragment {

    @BindView(R.id.cnToolbar)
    CnToolbar cnToolbar;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;

    CartAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_cart;
    }
    List<String> strings=new ArrayList<>();
    @Override
    public void initView(View view) {
        strings.add("计划的风景");
        strings.add("计划的风景");
        strings.add("计划的风景");
        strings.add("计划的风景");
        strings.add("计划的风景");


        adapter=new CartAdapter(context,strings);
        recycleView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true));
        refresh.setLoadMore(true);
        recycleView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
