package com.custom.cniaoshopingmall.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.base.BaseActivity;
import com.custom.cniaoshopingmall.msg.LoginRespMsg;
import com.custom.cniaoshopingmall.net.Business;
import com.custom.cniaoshopingmall.net.DialogCallback;
import com.custom.cniaoshopingmall.utils.Contants;
import com.custom.cniaoshopingmall.utils.DESUtil;
import com.custom.cniaoshopingmall.utils.ToastUtils;
import com.custom.cniaoshopingmall.utils.UserUtil;
import com.custom.cniaoshopingmall.widget.ClearEditText;
import com.custom.cniaoshopingmall.widget.CnToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Response;

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
    public void login(){
        String phone=etxtPhone.getText().toString();
        String pwd=etxtPwd.getText().toString();
        Business.login(phone, DESUtil.encode(Contants.DES_KEY,pwd), new DialogCallback<LoginRespMsg>(context) {
            @Override
            public void onRequessSuccess(Response response, LoginRespMsg object) {
                hideKeyboard();
                UserUtil.saveToken(object.token);
                UserUtil.saveUser(object);
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onRequessError(Response response) {
                   try {
                       Log.e("error",response.body().string());
                      }catch (Exception ex){
                        ex.printStackTrace();
                     }

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
                inputCheck();
                break;
            case R.id.txt_toReg://注册账号
                break;
        }
    }
    public void inputCheck(){
        if (TextUtils.isEmpty(etxtPhone.getText().toString())){
            ToastUtils.show(context,getString(R.string.inputNum));
            return;
        }
        if (TextUtils.isEmpty(etxtPwd.getText().toString())){
            ToastUtils.show(context,getString(R.string.inputLoginPwd));
            return;
        }
        if (!isMobileNO(etxtPhone.getText().toString())){
            ToastUtils.show(context,getString(R.string.inputTrueNum));
            return;
        }
        login();

    }
    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
}
