package com.custom.cniaoshopingmall.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xuchichi on 2018/1/9.
 */
public abstract class ListViewBaseAdapter<T> extends android.widget.BaseAdapter{
   private List<T> mlist;
    private LayoutInflater inflater;
    private Context context;
    private int layoutId;

    public ListViewBaseAdapter(Context context,List<T> mlist, int layoutId) {
        this.mlist = mlist;
        this.context = context;
        this.layoutId=layoutId;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListBaseHolder holder;
        if (convertView==null){
            convertView=inflater.inflate(layoutId,null);
            holder= new ListBaseHolder(convertView);
        }else{
            holder= (ListBaseHolder) convertView.getTag();
        }
        bindData(holder,position);
        return convertView;
    }
    public abstract void bindData(ListBaseHolder holder,int position);


    class ListBaseHolder {
        View itemView;
        SparseArray<View> viewArray;

        public ListBaseHolder(View itemView) {
            this.itemView = itemView;
            viewArray = new SparseArray();
        }

        public View getView(int viewId) {
            return findViewId(viewId);
        }
        public TextView getTextView(int viewId){
            return findViewId(viewId);
        }
        public <T extends View> T findViewId(int id) {
            View view = viewArray.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                viewArray.put(id, view);
            }
            return (T) view;
        }
    }
}
