package com.custom.cniaoshopingmall.entity;

/**
 * Created by xuchichi on 2017/12/21.
 */
public class Tab {
    private String title;
    private int drawable;
    private Class fragment;

    public Tab(String title, int drawable, Class fragment) {
        this.title = title;
        this.drawable = drawable;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
