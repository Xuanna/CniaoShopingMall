package com.custom.cniaoshopingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by xuchichi on 2017/12/21.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(setLayout());
        ButterKnife.inject(this);
        initView();
    }

    public abstract int setLayout();

    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
