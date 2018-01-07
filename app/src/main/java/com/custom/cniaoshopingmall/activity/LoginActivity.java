package com.custom.cniaoshopingmall.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseActivity;
import com.custom.cniaoshopingmall.widget.ClearEditText;
import com.custom.cniaoshopingmall.widget.CnToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuchichi on 2018/1/7.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    CnToolbar toolbar;
    @BindView(R.id.etxt_phone)
    ClearEditText etxtPhone;
    @BindView(R.id.etxt_pwd)
    ClearEditText etxtPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_toReg)
    TextView txtToReg;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.txt_toReg,R.id.txt_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_forget://忘记密码
                break;
            case R.id.btn_login://登陆
                break;
            case R.id.txt_toReg://注册账号
                break;
        }
    }
}
