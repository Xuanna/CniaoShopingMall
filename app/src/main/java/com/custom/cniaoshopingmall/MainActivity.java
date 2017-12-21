package com.custom.cniaoshopingmall;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.custom.cniaoshopingmall.base.BaseActivity;
import com.custom.cniaoshopingmall.entity.Tab;
import com.custom.cniaoshopingmall.fragment.CartFragment;
import com.custom.cniaoshopingmall.fragment.CategoryFragment;
import com.custom.cniaoshopingmall.fragment.HomeFragment;
import com.custom.cniaoshopingmall.fragment.HotFragment;
import com.custom.cniaoshopingmall.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.real_fl)
    FrameLayout realFl;
    @InjectView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @InjectView(android.R.id.tabhost)
    FragmentTabHost tabhost;
    List<Tab> list=new ArrayList<>();

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
       initTab();
    }

    public void initTab(){
        list.add(new Tab("首页", R.drawable.selector_icon_home, HomeFragment.class));
        list.add(new Tab("热卖", R.drawable.selector_icon_hot, HotFragment.class));
        list.add(new Tab("分类", R.drawable.selector_icon_category, CategoryFragment.class));
        list.add(new Tab("购物车", R.drawable.selector_icon_cart, CartFragment.class));
        list.add(new Tab("我的", R.drawable.selector_icon_mine, MineFragment.class));
        tabhost.setup(context, getSupportFragmentManager(), R.id.real_fl);
        for (Tab tab:list) {
            addTab(tab);
        }
        tabhost.setCurrentTab(0);
    }
    public void addTab(Tab tab){
        View view = LayoutInflater.from(context).inflate(R.layout.tab_indicator, null);
        ImageView imageView= (ImageView) view.findViewById(R.id.icon_tab);
        TextView textView= (TextView) view.findViewById(R.id.txt_indicator);
        imageView.setImageResource(tab.getDrawable());
        textView.setText(tab.getTitle());
        tabhost.addTab(tabhost.newTabSpec(tab.getTitle()).setIndicator(view), tab.getFragment(),null);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
