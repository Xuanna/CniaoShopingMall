package com.custom.cniaoshopingmall.adapter;

import android.content.Context;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseRecycleHolder;
import com.custom.cniaoshopingmall.entity.CategoryListInfo;

import java.util.List;

/**
 * Created by xuchichi on 2018/1/8.
 */
public class CategaryFirstAdapter extends CommonAdapter<CategoryListInfo> {
    public CategaryFirstAdapter(Context context, List<CategoryListInfo> datas) {
        super(context, datas, R.layout.item_categary_first);
    }

    @Override
    public void bindData(BaseRecycleHolder holder, CategoryListInfo info) {
        holder.getTextView(R.id.tv).setText(info.name);
    }
}
