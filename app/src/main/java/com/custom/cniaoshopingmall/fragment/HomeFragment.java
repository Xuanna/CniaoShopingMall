package com.custom.cniaoshopingmall.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseFragment;
import com.custom.cniaoshopingmall.entity.BannerInfo;
import com.custom.cniaoshopingmall.net.Business;
import com.custom.cniaoshopingmall.net.DialogCallback;
import com.custom.cniaoshopingmall.tools.GildeImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Response;


/**
 * Created by Ivan on 15/9/25.
 */
public class HomeFragment extends BaseFragment {
    List<String> images = new ArrayList<>();
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.recycleView)
    RecyclerView recycleView;

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
    List<BannerInfo> list = new ArrayList<>();

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

    @Override
    public void initView() {
        getBanner();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
