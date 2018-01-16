package com.custom.cniaoshopingmall.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuchichi on 2017/12/21.
 */
public class Business {
    public static OkhttpHelper okhttpHelper=OkhttpHelper.getInstance();

   /**
     * 首页banner
     * @param type
     * @param baseCallback
     */
    public static void getBanner(int type,BaseCallback baseCallback){
        Map<String,String> map=new HashMap<>();
        map.put("type",type+"");
        okhttpHelper.post(Urls.bannerUrl,map,baseCallback);
    }

    /**
     * 首页活动推荐
     * @param baseCallback
     */
    public static void getRecommend(BaseCallback baseCallback){
        Map<String,String> map=new HashMap<>();
        okhttpHelper.post(Urls.recommendUrl,map,baseCallback);
    }

    /**
     *商品一级分类
     * @param baseCallback
     */
    public static void getCategoryList(BaseCallback baseCallback){
        Map<String,String> map=new HashMap<>();
        okhttpHelper.post(Urls.categoryListUrl,map,baseCallback);
    }

    /**
     *商品分类下的商品
     * @param baseCallback
     */
    public static void getWaresList(String categoryId,int curPage,int pageSize,BaseCallback baseCallback){
        Map<String,String> map=new HashMap<>();
        map.put("categoryId",categoryId+"");
        map.put("curPage",curPage+"");
        map.put("pageSize",pageSize+"");
        okhttpHelper.post(Urls.waresListUrl,map,baseCallback);
    }
    /**
     * 用户登录
      */
    public static void login(String phone,String password,BaseCallback baseCallback){
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone+"");
        map.put("password",password+"");
        okhttpHelper.post(Urls.loginUrl,map,baseCallback);
    }

    /**
     * 获取分类下的商品
     * @param categoryId
     * @param curPage
     * @param pageSize
     * @param baseCallback
     */
    public static void getWareList(String categoryId,int curPage,int pageSize,BaseCallback baseCallback){
        Map<String,String> map=new HashMap<>();
        map.put("categoryId",categoryId+"");
        map.put("curPage",curPage+"");
        map.put("pageSize",pageSize+"");
        okhttpHelper.post(Urls.wareListUrl,map,baseCallback);
    }
}
