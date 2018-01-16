package com.custom.cniaoshopingmall.activity;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseActivity;
import com.custom.cniaoshopingmall.net.Business;
import com.custom.cniaoshopingmall.net.DialogCallback;

import okhttp3.Response;

public class WareDetailActivity extends BaseActivity {
    @Override
    public int setLayout() {
        return R.layout.activity_ware_detail;
    }

    @Override
    public void initView() {
        categoryId=getIntent().getStringExtra("categoryId");
    }
    String categoryId;
    int pageIndex=1;int pageSize=10;

    public void getWareDetails(){
        Business.getWaresList(categoryId, pageIndex, pageSize, new DialogCallback(context) {
            @Override
            public void onRequessSuccess(Response response, Object object) {

            }

            @Override
            public void onRequessError(Response response) {

            }
        });
    }
}
