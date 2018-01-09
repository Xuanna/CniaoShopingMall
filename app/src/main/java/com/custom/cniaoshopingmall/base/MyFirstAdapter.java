package com.custom.cniaoshopingmall.base;

import android.content.Context;

import com.custom.cniaoshopingmall.entity.HomeRecomendInfo;

import java.util.List;

/**
 * Created by xuchichi on 2018/1/9.
 */
public class MyFirstAdapter extends ListViewBaseAdapter<HomeRecomendInfo>{

    public MyFirstAdapter(Context context, List<HomeRecomendInfo> mlist, int layoutId) {
        super(context, mlist, layoutId);
    }

    @Override
    public void bindData(ListBaseHolder holder, int position) {
    }
}
