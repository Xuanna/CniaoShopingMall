package com.custom.cniaoshopingmall.utils;

import android.content.Context;
import android.util.Log;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.custom.cniaoshopingmall.net.DialogCallback;
import com.custom.cniaoshopingmall.net.OkhttpHelper;

import java.util.List;

import okhttp3.Response;

/**
 * Created by xuchichi on 2018/1/8.
 */
public class MyPager{
    public static OkhttpHelper okhttpHelper;
    public static final int STATE_NORMAL=0;
    public static final int STATE_REFRESH=1;
    public static final int STATE_MORE=2;
    public int state=STATE_NORMAL;
    private int pageIndex=1;
    private int pageSize=10;
    private MaterialRefreshLayout refreshLayout;
    private Context context;
    private String url;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MyPager(Context context) {
        this.context=context;
        okhttpHelper=OkhttpHelper.getInstance();
        initRefresh();
    }

    public MaterialRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    public void setRefreshLayout(MaterialRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
    }

    public void initRefresh() {
        if (refreshLayout!=null){
            refreshLayout.setLoadMore(true);
            refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
                @Override
                public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                    pageIndex=1;
                    state=STATE_REFRESH;
                    requestData();
                }
                @Override
                public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                    super.onRefreshLoadMore(materialRefreshLayout);
                    pageIndex=getPageIndex()+1;
                    state=STATE_MORE;
                    requestData();
                }
            });
        }
    }


    public void requestData(){
        Log.e("requestData","requestData");
        okhttpHelper.get(getUrl(),new RequestCallback(context));
    }
    class RequestCallback<T> extends DialogCallback<List<T>>{
        public RequestCallback(Context context) {
            super(context);
        }

        @Override
        public void onRequessSuccess(Response response, List<T> list) {
            switch (state){
                case STATE_NORMAL:
                    onPageListner.showData(list);
                    break;
                case STATE_REFRESH:
                    onPageListner.onRefreh(list);
                    refreshLayout.finishRefresh();
                    break;
                case STATE_MORE:
                    onPageListner.loadMore(list);
                    refreshLayout.finishRefreshLoadMore();
                    break;
            }
        }

        @Override
        public void onRequessError(Response response) {

        }
    }
    public void setOnPageListener(OnPageListner onPageListner){
        this.onPageListner=onPageListner;
    }
    private OnPageListner onPageListner;

    public interface OnPageListner<T>{
        void showData(List<T> data);
        void onRefreh(List<T> data);
        void loadMore(List<T> data);
    }
}
