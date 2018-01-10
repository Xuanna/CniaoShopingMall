package com.custom.cniaoshopingmall.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.adapter.CommonAdapter;
import com.custom.cniaoshopingmall.base.BaseAdapter;
import com.custom.cniaoshopingmall.base.BaseFragment;
import com.custom.cniaoshopingmall.base.BaseRecycleHolder;
import com.custom.cniaoshopingmall.entity.BannerInfo;
import com.custom.cniaoshopingmall.entity.HomeRecomendInfo;
import com.custom.cniaoshopingmall.net.Business;
import com.custom.cniaoshopingmall.net.DialogCallback;
import com.custom.cniaoshopingmall.net.Urls;
import com.custom.cniaoshopingmall.utils.CommonUtil;
import com.custom.cniaoshopingmall.utils.GildeImageLoader;
import com.custom.cniaoshopingmall.utils.MyPager;
import com.custom.cniaoshopingmall.utils.Pager;
import com.custom.cniaoshopingmall.utils.RecyclerViewDivider;
import com.custom.cniaoshopingmall.utils.ToastUtils;
import com.custom.cniaoshopingmall.widget.CnToolbar;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Response;


/**
 * Created by Ivan on 15/9/25.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.cnToolbar)
    CnToolbar cnToolbar;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;

    Unbinder unbinder;


    List<String> images = new ArrayList<>();
    List<BannerInfo> list = new ArrayList<>();
    List<HomeRecomendInfo> recomendInfos = new ArrayList<>();
    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    public void initBanner() {
        banner.setImageLoader(new GildeImageLoader());
        banner.setImages(images);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
    }

    int refreshMode;

    public void initRefresh() {
        refresh.setLoadMore(true);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refreshMode = 1;
                getRecommend();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                refreshMode = 2;
                getRecommend();
            }
        });
    }

    @Override
    public void getData() {
        super.getData();
        getBanner();
        getRecommend();
    }

    public void getBanner() {

        Business.getBanner(1, new DialogCallback<List<BannerInfo>>(getContext()) {
            @Override
            public void onRequessSuccess(Response response, List<BannerInfo> lists) {
                try {
                    list.clear();
                    images.clear();
                    list = lists;
                    for (BannerInfo info : list) {
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

    public void getRecommend() {
//     MyPager pager=new MyPager(context);
//        pager.setRefreshLayout(refresh);
//        pager.setUrl(Urls.recommendUrl);
//        pager.requestData();
//        pager.setOnPageListener(new MyPager.OnPageListner<List<HomeRecomendInfo>>() {
//            @Override
//            public void showData(List data) {
//                adapter.addData(data);
//            }
//
//            @Override
//            public void onRefreh(List data) {
//                adapter.refreshData(data);
//            }
//
//            @Override
//            public void loadMore(List data) {
//                adapter.addMore(data);
//            }
//        });
//        pager.setOnPageListener(new MyPager.OnPageListner<List<HomeRecomendInfo>>() {
//            @Override
//            public void showData(List data) {
//                adapter.addData(data);
//            }
//            @Override
//            public void onRefreh(List data) {
//                adapter.refreshData(data);
//            }
//            @Override
//            public void loadMore(List data) {
//                adapter.addMore(data);
//            }
//        });
//        Pager.newBuilder().setUrl(Urls.recommendUrl)
//                .setCanLoadMore(true)
//                .setOnPageListener(new Pager.OnPageListener() {
//                    @Override
//                    public void load(List data, int pageIndex, int pageSize) {
//                        adapter.addData(data);
//                    }
//
//                    @Override
//                    public void refresh(List data, int pageIndex, int pageSize) {
//                        adapter.refreshData(data);
//                    }
//
//                    @Override
//                    public void loadMore(List data, int pageIndex, int pageSize) {
//                        adapter.addMore(data);
//                    }
//                }).builder(context);
        Business.getRecommend(new DialogCallback<List<HomeRecomendInfo>>(context) {
            @Override
            public void onRequessSuccess(Response response, List<HomeRecomendInfo> o) {
                refresh.finishRefreshLoadMore();
                refresh.finishRefresh();
                recomendInfos = o;
                if (refreshMode == 0) {
                    adapter.addData(recomendInfos);
                } else if (refreshMode == 1) {
                    adapter.refreshData(recomendInfos);
                } else if (refreshMode == 2) {
                    adapter.addMore(recomendInfos);
                }
            }
            @Override
            public void onRequessError(Response response) {

            }
        });
    }

    HomeAdapter adapter;

    public void initAdapter() {
        recycleView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new HomeAdapter(context, recomendInfos, R.layout.template_home_cardview);
        recycleView.setAdapter(adapter);
        recycleView.addItemDecoration(new RecyclerViewDivider(context,LinearLayoutManager.VERTICAL, CommonUtil.dp2Px(context,10),context.getResources().getColor(R.color.gray)));
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.show(context,position+"");
            }
        });

    }

    public class HomeAdapter extends CommonAdapter<HomeRecomendInfo> {
        public HomeAdapter(Context context, List<HomeRecomendInfo> datas, int layoutRes) {
            super(context, datas, layoutRes);
        }

        @Override
        public void bindData(BaseRecycleHolder holder, HomeRecomendInfo homeRecomendInfo) {
            holder.getTextView(R.id.text_title).setText(homeRecomendInfo.title);
            Picasso.with(context).load(homeRecomendInfo.cpOne.imgUrl).into(holder.getImageView(R.id.imgview_big));
            Picasso.with(context).load(homeRecomendInfo.cpTwo.imgUrl).into(holder.getImageView(R.id.imgview_small_top));
            Picasso.with(context).load(homeRecomendInfo.cpThree.imgUrl).into(holder.getImageView(R.id.imgview_small_bottom));
        }
    }

    @Override
    public void initView(View view) {
        initRefresh();
        initAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
