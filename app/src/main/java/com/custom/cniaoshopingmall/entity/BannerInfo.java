package com.custom.cniaoshopingmall.entity;

/**
 * Created by xuchichi on 2017/12/18.
 */

public class BannerInfo {

    private String id;
    private String name;//广告名称
    private String imgUrl; //图片地址

    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
