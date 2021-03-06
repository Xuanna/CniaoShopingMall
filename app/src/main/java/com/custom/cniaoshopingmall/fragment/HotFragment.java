package com.custom.cniaoshopingmall.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.adapter.HotAdapter;
import com.custom.cniaoshopingmall.base.BaseFragment;
import com.custom.cniaoshopingmall.entity.HotInfo;
import com.custom.cniaoshopingmall.net.Business;
import com.custom.cniaoshopingmall.net.DialogCallback;
import com.custom.cniaoshopingmall.utils.ToastUtils;
import com.custom.cniaoshopingmall.widget.CnToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Response;


/**
 * Created by Ivan on 15/9/22.
 */
public class HotFragment extends BaseFragment {
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;
    Unbinder unbinder;

    List<HotInfo.ListBean> list=new ArrayList<>();
    int curPage=1;
    int pageSizde=10;
    HotAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView(View view) {
        initAdapter();
        getHot();
    }

//    @Override
//    public void getData() {
//        super.getData();
//        getHot();
//
//    }
    int refreshMode;

    public void initAdapter(){
        adapter=new HotAdapter(context,list);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true));
        refresh.setLoadMore(true);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                curPage=1;
                refreshMode = 1;
                getHot();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                refreshMode = 2;
                curPage=curPage+1;
                getHot();
            }
        });
    }


    public void getHot(){
        Business.getHotList(curPage, pageSizde, new DialogCallback<HotInfo>(context) {
            @Override
            public void onRequessSuccess(Response response, HotInfo hotInfo) {

                if (hotInfo.list!=null){
                    list= hotInfo.list;
                    if (refreshMode==0){
                        adapter.addData(list);
                    }
                    if (refreshMode==1){
                        refresh.finishRefresh();
                        adapter.refreshData(list);
                    }
                    if (refreshMode==2){
                        refresh.finishRefreshLoadMore();
                        adapter.addMore(list);
                    }

                }
            }

            @Override
            public void onRequessError(Response response) {
                refresh.finishRefreshLoadMore();
                refresh.finishRefresh();
                try{
                    ToastUtils.show(context,"onRequessError");

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
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
