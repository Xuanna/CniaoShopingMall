package com.custom.cniaoshopingmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.entity.HomeRecomendInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xuchichi on 2017/12/26.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.MyHolder> {

    private int VIEWTYPE_LEFT = 0;
    private int VIEWTYPE_RIGHT = 1;
    List<HomeRecomendInfo> recomendInfos;

    public HomeListAdapter(List<HomeRecomendInfo> recomendInfos, Context context) {
        this.recomendInfos = recomendInfos;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return VIEWTYPE_RIGHT;
        } else {
            return VIEWTYPE_LEFT;
        }
    }

    private Context context;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEWTYPE_LEFT) {
            view = LayoutInflater.from(context).inflate(R.layout.template_home_cardview, null);
        } else if (viewType == VIEWTYPE_RIGHT) {
            view = LayoutInflater.from(context).inflate(R.layout.template_home_cardview2, null);
        }
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        HomeRecomendInfo info = recomendInfos.get(position);
        holder.textTitle.setText(info.title+"");
        Picasso.with(context).load(info.cpOne.imgUrl).into(holder.imgviewBig);
        Picasso.with(context).load(info.cpTwo.imgUrl).into(holder.imgviewSmallTop);
        Picasso.with(context).load(info.cpThree.imgUrl).into(holder.imgviewSmallBottom);
    }

    @Override
    public int getItemCount() {
        return recomendInfos.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        ImageView imgviewSmallTop;
        View line2;
        ImageView imgviewSmallBottom;
        LinearLayout layoutLeft;
        View line;
        ImageView imgviewBig;

        public MyHolder(View itemView) {
            super(itemView);
            textTitle= (TextView) itemView.findViewById(R.id.text_title);
            imgviewSmallTop= (ImageView) itemView.findViewById(R.id.imgview_small_top);
            imgviewSmallBottom= (ImageView) itemView.findViewById(R.id.imgview_small_bottom);
            imgviewBig= (ImageView) itemView.findViewById(R.id.imgview_big);
        }
    }
}
