package com.custom.cniaoshopingmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.custom.cniaoshopingmall.R;
import com.custom.cniaoshopingmall.activity.LoginActivity;
import com.custom.cniaoshopingmall.base.BaseFragment;
import com.custom.cniaoshopingmall.msg.LoginRespMsg;
import com.custom.cniaoshopingmall.utils.Contants;
import com.custom.cniaoshopingmall.utils.LogUtils;
import com.custom.cniaoshopingmall.utils.ToastUtils;
import com.custom.cniaoshopingmall.utils.UserUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by Ivan on 15/9/22.
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.txt_my_orders)
    TextView txtMyOrders;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    Unbinder unbinder;

    @Override
    public int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {

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

    @OnClick({R.id.img_head, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_head:
                startActivityForResult(new Intent(context, LoginActivity.class), Contants.START_CODE);
                break;
            case R.id.btn_logout:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode==getActivity().RESULT_OK){
//             LoginRespMsg loginRespMsg= UserUtil.getUser();
//            if (loginRespMsg!=null){
//                showView(loginRespMsg);
//                LogUtils.e(UserUtil.getToken());
//            }else{
//                ToastUtils.show(context,"null");
//            }
//        }
    }
    public void showView(LoginRespMsg user){
            Picasso.with(context).load(user.data.logo_url).into(imgHead);
            txtUsername.setText(user.data.username);
    }
}
