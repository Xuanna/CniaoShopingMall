package com.custom.cniaoshopingmall.utils;

import android.content.Context;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.custom.cniaoshopingmall.entity.Page;
import com.custom.cniaoshopingmall.net.DialogCallback;
import com.custom.cniaoshopingmall.net.OkhttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by xuchichi on 2018/1/8.
 */
public class Pager<T>{
    public static OkhttpHelper okhttpHelper=OkhttpHelper.getInstance();
    public static Builder builder;
    private static final int STATE_NORMAL=1;
    private static final int STATE_REFRESH=2;
    private static final int STATE_MORE=3;
    private int state=STATE_NORMAL;

    private Pager() {
        initRefresh();
    }
    public static Builder newBuilder(){
        return new Builder();
    }

    public void initRefresh() {
        builder.materialRefreshLayout.setLoadMore(true);
        builder.materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refreshData();
            }
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                loadMoreData();
            }
        });
    }

    public void refreshData(){
        builder.pageIndex=1;
        state=STATE_REFRESH;
        requestData();
    }

    public void loadMoreData(){
        builder.pageIndex+=1;
        state=STATE_MORE;
        requestData();
    }

    public<T> void showData(List<T> data,int pageIndex,int pageSize){
        switch (state){
            case STATE_NORMAL:
                if(builder.onPageListener!=null){
                    builder.onPageListener.load(data,pageIndex,pageSize);
                }
                break;
            case STATE_REFRESH:
                if(builder.onPageListener!=null){
                    builder.onPageListener.refresh(data,pageIndex,pageSize);
                }
                builder.materialRefreshLayout.finishRefresh();
                break;
            case STATE_MORE:
                if(builder.onPageListener!=null){
                    builder.onPageListener.loadMore(data,pageIndex,pageSize);
                }
                builder.materialRefreshLayout.finishRefreshLoadMore();
                break;
        }
    }

    private String buildUrl(){
        return builder.url +"?"+buildUrlParams();
    }


    private   String buildUrlParams() {
        HashMap<String, String> map = builder.params;
        map.put("curPage",builder.pageIndex+"");
        map.put("pageSize",builder.pageSize+"");

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0,s.length()-1);
        }
        return s;
    }
    HashMap<String,String> map;

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    public void requestDataByPost(){
        String url=builder.getUrl();
        okhttpHelper.post(url,getMap(),new RequestCallBack(builder.context));
    }
    public void requestData() {
        String url = buildUrl();
        okhttpHelper.get(url, new RequestCallBack(builder.context));
    }

    public static class Builder{
        private String url;
        private HashMap<String,String> params=new HashMap<>(5);
        private MaterialRefreshLayout materialRefreshLayout;
        private boolean canLoadMore;
        private int totalPage=1;
        private int pageIndex=10;
        private int pageSize=10;
        private OnPageListener onPageListener;
        private Context context;

        public Builder setContent(Context context){
           this.context=context;
            return builder;
        }
        public Builder setUrl(String url){
            this.url=url;
            return builder;
        }

        public Builder putParams(String key,String object){
            params.put(key,object);
            return builder ;
        }
        public Builder MaterialRefreshLayout( MaterialRefreshLayout materialRefreshLayout){
            this.materialRefreshLayout=materialRefreshLayout;
            return builder;
        }

        public Builder setCanLoadMore(boolean canLoadMore){
            this.canLoadMore=canLoadMore;
            return builder;
        }

        public Builder setTotalPage( int totalPage){
            this.totalPage=totalPage;
            return builder;
        }

        public Builder setPageIndex( int pageIndex){
            this.pageIndex=pageIndex;
            return builder;
        }

        public Builder setPageSize( int pageSize){
            this.pageSize=pageSize;
            return builder;
        }
        public Builder setOnPageListener(OnPageListener onPageListener){
            this.onPageListener=onPageListener;
            return builder;
        }
        public String getUrl() {
            return url;
        }

        public Map<String, String> getParams() {
            return params;
        }

        public void setParams(HashMap<String, String> params) {
            this.params = params;
        }

        public MaterialRefreshLayout getMaterialRefreshLayout() {
            return materialRefreshLayout;
        }

        public Builder setMaterialRefreshLayout(MaterialRefreshLayout materialRefreshLayout) {
            this.materialRefreshLayout = materialRefreshLayout;
            return builder;
        }

        public boolean isCanLoadMore() {
            return canLoadMore;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public OnPageListener getOnPageListener() {
            return onPageListener;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }
        public Pager builder(Context context){
            this.context=context;
            valid();
            return new Pager();
        }
        private void valid(){
            if(this.context==null)
                throw  new RuntimeException("content can't be null");
            if(this.url==null || "".equals(this.url))
                throw  new RuntimeException("url can't be  null");
            if(this.materialRefreshLayout==null)
                throw  new RuntimeException("MaterialRefreshLayout can't be  null");
        }

    }
    public interface OnPageListener<T>{
        void load(List<T> data,int pageIndex,int pageSize);
        void refresh(List<T> data,int pageIndex,int pageSize);
        void loadMore(List<T> data,int pageIndex,int pageSize);
    }
    class RequestCallBack<T> extends DialogCallback<Page<T>>{
        public RequestCallBack(Context context) {
            super(context);
        }
        @Override
        public void onRequessSuccess(Response response, Page<T> page) {
            try {
                builder.pageIndex = page.getCurrentPage();
                builder.pageSize = page.getPageSize();
                builder.totalPage = page.getTotalPage();
                showData(page.getList(),page.getTotalPage(),page.getTotalCount());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void onRequessError(Response response) {
            builder.materialRefreshLayout.finishRefresh();
            builder.materialRefreshLayout.finishRefreshLoadMore();
        }
    }
}
