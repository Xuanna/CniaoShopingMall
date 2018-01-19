package com.custom.cniaoshopingmall.adapter;

import android.content.Context;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseRecycleHolder;

import java.util.List;

/**
 * Created by xuchichi on 2018/1/19.
 */
public class CartAdapter extends CommonAdapter<String> {
    public CartAdapter(Context context, List<String> datas) {
        super(context, datas, R.layout.item_cart);
    }

    @Override
    public void bindData(BaseRecycleHolder holder, String s) {

    }
}
