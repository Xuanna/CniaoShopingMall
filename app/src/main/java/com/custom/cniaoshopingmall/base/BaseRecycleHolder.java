package com.custom.cniaoshopingmall.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by xuchichi on 2017/12/27.
 */
public class BaseRecycleHolder extends RecyclerView.ViewHolder {
   private SparseArray<View> views;
    public BaseRecycleHolder(View itemView) {
        super(itemView);
    }
    public void findView(int id){

    }
}
