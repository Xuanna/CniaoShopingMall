package com.custom.cniaoshopingmall.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xuchichi on 2017/12/27.
 */
public class BaseRecycleHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    BaseAdapter.OnItemClickListener listener;

   private SparseArray<View> views;

    public BaseRecycleHolder(View itemView, final BaseAdapter.OnItemClickListener listener) {
        super(itemView);
        views=new SparseArray<>();
        this.listener=listener;
        itemView.setOnClickListener(this);
    }

    public View getView(int id){
        return findView(id);
    }
    public TextView getTextView(int id){
        return findView(id);
    }
    public ImageView getImageView(int id){
        return findView(id);

    }

    public <T extends View>T findView(int id){
      View view=  views.get(id);
      if (view==null){
         view=itemView.findViewById(id);
          views.put(id,view);
      }

      return (T) view;

    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onItemClick(view,getLayoutPosition());
        }
    }
}
