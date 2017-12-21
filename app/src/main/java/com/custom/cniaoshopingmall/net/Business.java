package com.custom.cniaoshopingmall.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuchichi on 2017/12/21.
 */
public class Business {
    public static OkhttpHelper okhttpHelper=OkhttpHelper.getInstance();

    public static void getBanner(int type,BaseCallback baseCallback){
        Map<String,String> map=new HashMap<>();
        map.put("type",type+"");
        okhttpHelper.post(Urls.bannerUrl,map,baseCallback);
    }

}
