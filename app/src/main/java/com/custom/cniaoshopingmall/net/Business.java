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
}
