package com.custom.cniaoshopingmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.custom.cniaoshopingmall.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Ivan on 15/9/22.
 */
public class HotFragment extends Fragment {


    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.inject(this, view);
        return view;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
