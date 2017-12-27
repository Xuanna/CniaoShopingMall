package com.custom.cniaoshopingmall.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xuchichi on 2017/12/27.
 */
public abstract class BaseAdapter<T,H extends BaseRecycleHolder> extends RecyclerView.Adapter<BaseRecycleHolder> {
    public List<T> datas;
    private Context context;
    private LayoutInflater inflater;
    public int layoutRes;
    public BaseAdapter( Context context,List<T> datas,int layoutRes) {
        this.datas=datas;
        this.context=context;
        this.layoutRes=layoutRes;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public BaseRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View  view = inflater.inflate(layoutRes, null,false);
        return new BaseRecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecycleHolder holder, int position) {
        T t=getItem(position);
        bindData(holder,t);
    }
    public abstract void bindData(BaseRecycleHolder holder,T t);
    public T getItem(int position){
        return datas.get(position);
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
}
