package com.custom.cniaoshopingmall.entity;

import java.util.List;

/**
 * Created by xuchichi on 2018/1/16.
 */

public class HotInfo {


    public int totalCount;
    public int currentPage;
    public int totalPage;
    public int pageSize;
    public List<ListBean> list;


    public static class ListBean {
        public String id;
        public String name;
        public String imgUrl;
        public String description;
        public String price;
    }
}
