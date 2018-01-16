package com.custom.cniaoshopingmall.adapter;

import android.content.Context;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseRecycleHolder;
import com.custom.cniaoshopingmall.entity.HotInfo;

import java.util.List;

/**
 * Created by xuchichi on 2018/1/16.
 */

public class HotAdapter extends CommonAdapter<HotInfo.ListBean> {
    public HotAdapter(Context context, List<HotInfo.ListBean> datas) {
        super(context, datas, R.layout.item_hot_wares);
    }

    @Override
    public void bindData(BaseRecycleHolder holder, HotInfo.ListBean listBean) {

    }
}
