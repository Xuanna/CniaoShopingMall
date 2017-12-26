package com.custom.cniaoshopingmall.net;

import android.content.Context;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import okhttp3.Response;

/**
 * Created by xuchichi on 2017/12/26.
 */
public abstract class DialogCallback<T> extends BaseCallback<T> {
    Context context;
    SpotsDialog spotsDialog;
    public DialogCallback(Context context) {
        this.context = context;
        spotsDialog=new SpotsDialog(context);
    }
    public void showDialog(){
        spotsDialog.show();
    }
    public void dismissDialog(){
        if (spotsDialog!=null){
            spotsDialog.dismiss();
        }

    }

    @Override
    public void onRequestBefore() {
        showDialog();
    }

    @Override
    public void onFailure(IOException e) {
        dismissDialog();
    }

    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }

}
