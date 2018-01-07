package com.custom.cniaoshopingmall.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;
import java.util.List;

/**
 * Created by xuchichi on 2017/12/27.
 */
public abstract class BaseAdapter<T,H extends BaseRecycleHolder> extends RecyclerView.Adapter<BaseRecycleHolder> {
    public List<T> datas;
    private Context context;
    private LayoutInflater inflater;
    public int layoutRes;
    OnItemClickListener onItemClickListener;


    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public BaseAdapter( Context context,List<T> datas,int layoutRes) {
        this.datas=datas;
        this.context=context;
        this.layoutRes=layoutRes;
        inflater=LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }


    /**
     * 删除某条数据
     * @param position
     */
    public void removeData(int position){
        datas.remove(position);
        notifyItemRemoved(position);
    }
    public void addData(List<T> list){
        addData(0,list);
    }
    public void addData(int position,List<T> list){
        if (list!=null&&list.size()>0){
            for (T t:list) {
                datas.add(position,t);
                notifyItemInserted(position);

            }
        }
    }
//    public void add(List<T> list){
//        public void addData(List<T> list){//int position,
//            if (list!=null&&list.size()>0){
//                for (int i=0;i<list.size();i++){
//                    datas.add(list.get(i));
//                    notifyItemInserted(i);
//                }
//            }
//    }

    public void refreshData(List<T> list){
        if (list!=null&&list.size()>0){
            clear();
           for (int i=0;i<list.size();i++){
               datas.add(i,list.get(i));
               notifyItemInserted(i);
           }

        }
    }
    public void addMore(List<T> list){

        int begin = datas.size();
        if (list!=null&&list.size()>0){
            for (int i=0;i<list.size();i++){
                datas.add(list.get(i));
                notifyItemInserted(i+begin);
            }
        }
    }

    public void clear(){
//        int itemCount = datas.size();
//        datas.clear();
//        this.notifyItemRangeRemoved(0,itemCount);

        for (Iterator it = datas.iterator(); it.hasNext();){

            T t = (T) it.next();
            int position = datas.indexOf(t);
            it.remove();
            notifyItemRemoved(position);
        }
    }

    @Override
    public BaseRecycleHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
       View  view = inflater.inflate(layoutRes, null,false);
        return new BaseRecycleHolder(view,onItemClickListener);

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
