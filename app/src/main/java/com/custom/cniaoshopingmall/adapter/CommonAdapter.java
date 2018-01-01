package com.custom.cniaoshopingmall.adapter;

import android.content.Context;

import com.custom.cniaoshopingmall.base.BaseAdapter;
import com.custom.cniaoshopingmall.base.BaseRecycleHolder;

import java.util.List;

/**
 * Created by xuchichi on 2017/12/31.
 */

public abstract class CommonAdapter<T> extends BaseAdapter<T,BaseRecycleHolder> {

    public CommonAdapter(Context context, List<T> datas, int layoutRes) {
        super(context, datas, layoutRes);
    }

}
