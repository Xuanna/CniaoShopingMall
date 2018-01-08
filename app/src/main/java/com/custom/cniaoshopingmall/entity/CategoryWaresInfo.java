package com.custom.cniaoshopingmall.entity;

import java.util.List;

/**
 * Created by xuchichi on 2018/1/8.
 */
public class CategoryWaresInfo{
    public String copyright;
    public int totalCount;
    public int currentPage;
    public int totalPage;
    public int pageSize;
//    public List<?> orders;
    public List<ListBean> list;

    public static class ListBean {
        public String id;
        public String categoryId;
        public String campaignId;
        public String name;
        public String imgUrl;
        public String price;
        public String sale;
    }
}
