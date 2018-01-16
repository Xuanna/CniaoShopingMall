package com.custom.cniaoshopingmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.activity.WareDetailActivity;
import com.custom.cniaoshopingmall.adapter.CategaryFirstAdapter;
import com.custom.cniaoshopingmall.adapter.CategoryWaresAdapter;
import com.custom.cniaoshopingmall.base.BaseAdapter;
import com.custom.cniaoshopingmall.base.BaseFragment;
import com.custom.cniaoshopingmall.entity.BannerInfo;
import com.custom.cniaoshopingmall.entity.CategoryListInfo;
import com.custom.cniaoshopingmall.entity.CategoryWaresInfo;
import com.custom.cniaoshopingmall.net.Business;
import com.custom.cniaoshopingmall.net.DialogCallback;
import com.custom.cniaoshopingmall.utils.GildeImageLoader;
import com.custom.cniaoshopingmall.widget.CnToolbar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * Created by Ivan on 15/9/22.
 */
public class CategoryFragment extends BaseFragment {
    @BindView(R.id.cnToolbar)
    CnToolbar cnToolbar;
    @BindView(R.id.recycle_category)
    RecyclerView recycleCategory;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recycle_content)
    RecyclerView recycleContent;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;

    CategaryFirstAdapter firstAdapter;
    List<CategoryListInfo> firstList = new ArrayList<>();
    List<String> images = new ArrayList<>();
    CategoryWaresAdapter waresAdapter;
    List<CategoryWaresInfo.ListBean> wareList = new ArrayList<>();
    String categoryId;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    @Override
    public int setLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void initView(View view) {
        initFirstAdapter();
        initContentAdapter();
        getBanner();
        initBanner();
    }

    @Override
    public void getData() {
        super.getData();
        getCategoryList();
    }

    public void initBanner() {
        banner.setImageLoader(new GildeImageLoader());
        banner.setImages(images);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
    }

    public void initFirstAdapter() {
        firstAdapter = new CategaryFirstAdapter(context, firstList);
        recycleCategory.setLayoutManager(new LinearLayoutManager(context));
        recycleCategory.setAdapter(firstAdapter);
        recycleCategory.setItemAnimator(new DefaultItemAnimator());
        firstAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                categoryId = firstList.get(position).id;
                wareList.clear();
                waresAdapter.clear();
                getWaresList();
            }
        });
    }

    int refreshMode;

    public void initContentAdapter() {
        waresAdapter = new CategoryWaresAdapter(context, wareList);
        recycleContent.setAdapter(waresAdapter);
        recycleContent.setLayoutManager(new GridLayoutManager(context, 2));
        waresAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, WareDetailActivity.class);
                intent.putExtra("categoryId",wareList.get(position).categoryId);
                startActivity(intent);
            }
        });
        refresh.setLoadMore(true);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refreshMode = 1;
                getWaresList();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                refreshMode = 2;
                getWaresList();
            }
        });
    }

    /**
     * 获取banner
     */
    public void getBanner() {
        Business.getBanner(1, new DialogCallback<List<BannerInfo>>(getContext()) {
            @Override
            public void onRequessSuccess(Response response, List<BannerInfo> lists) {
                try {
                    images.clear();
                    for (BannerInfo info : lists) {
                        images.add(info.getImgUrl());
                    }
                    initBanner();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onRequessError(Response response) {
                try {
                    Log.e("onRequessError", response.body().string());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取一级菜单
     */
    public void getCategoryList() {
        Business.getCategoryList(new DialogCallback<List<CategoryListInfo>>(context) {
            @Override
            public void onRequessSuccess(Response response, List<CategoryListInfo> categoryListInfo) {
                if (firstList != null) {
                    firstList.clear();
                }
                categoryId = categoryListInfo.get(0).id;
                firstList = categoryListInfo;
                firstAdapter.addData(firstList);
                getWaresList();
            }

            @Override
            public void onRequessError(Response response) {

            }
        });
    }

    /**
     * 获取菜单下的商品
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    int pageIndex = 1;
    int pageSize = 10;

    public void getWaresList() {
        Business.getWaresList(categoryId, pageIndex, pageSize, new DialogCallback<CategoryWaresInfo>(context) {
            @Override
            public void onRequessSuccess(Response response, CategoryWaresInfo info) {

                refresh.finishRefreshLoadMore();
                refresh.finishRefresh();
                if (info.list != null) {
                    tvEmpty.setVisibility(View.GONE);
                    wareList = info.list;
                    if (refreshMode == 0) {
                        waresAdapter.addData(wareList);
                    } else if (refreshMode == 1) {
                        waresAdapter.refreshData(wareList);
                    } else if (refreshMode == 2) {
                        waresAdapter.addMore(wareList);
                    }
                } else {
                    tvEmpty.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onRequessError(Response response) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}



