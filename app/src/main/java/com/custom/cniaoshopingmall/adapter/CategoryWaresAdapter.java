package com.custom.cniaoshopingmall.adapter;

import android.content.Context;
import android.net.Uri;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseRecycleHolder;
import com.custom.cniaoshopingmall.entity.CategoryWaresInfo;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xuchichi on 2018/1/8.
 */
public class CategoryWaresAdapter extends CommonAdapter<CategoryWaresInfo.ListBean>{
    Context context;
    public CategoryWaresAdapter(Context context, List<CategoryWaresInfo.ListBean> datas) {
        super(context, datas, R.layout.template_grid_wares);
        this.context=context;
    }

    @Override
    public void bindData(BaseRecycleHolder holder, CategoryWaresInfo.ListBean categoryWaresInfo) {
        holder.getTextView(R.id.text_title).setText(categoryWaresInfo.name);
//        Picasso.with(context).load(categoryWaresInfo.imgUrRl).into(holder.getImageView(R.id.drawee_view));
        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.drawee_view);
        draweeView.setImageURI(Uri.parse(categoryWaresInfo.imgUrl));
        holder.getTextView(R.id.text_price).setText(categoryWaresInfo.price);
    }
}
