package com.custom.cniaoshopingmall.net;

/**
 * Created by xuchichi on 2017/12/21.
 */
public class Urls {
    public static final String baseUrl="http://112.124.22.238:8081/";
    //获取banner
    public static final String bannerUrl=baseUrl+"course_api/banner/query";
    //获取首页商品列表
    public static final String recommendUrl= baseUrl+"course_api/campaign/recommend";
    //获取分类一级菜单
    public static final String categoryListUrl= baseUrl+"course_api/category/list";
    //获取分类下的商品
    public static final String waresListUrl= baseUrl+"course_api/wares/list";
    //用户登录
    public static final String loginUrl= baseUrl+"course_api/auth/login";
}
