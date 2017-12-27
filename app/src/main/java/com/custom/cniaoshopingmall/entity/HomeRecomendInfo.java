package com.custom.cniaoshopingmall.entity;

/**
 * Created by xuchichi on 2017/12/27.
 */
public class HomeRecomendInfo {
    public CpOneBean cpOne;
    public CpTwoBean cpTwo;
    public CpThreeBean cpThree;
    public int id;
    public String title;
    public int campaignOne;
    public int campaignTwo;
    public int campaignThree;

    public static class CpOneBean {
        public int id;
        public String title;
        public String imgUrl;
    }

    public static class CpTwoBean {
        public int id;
        public String title;
        public String imgUrl;
    }

    public static class CpThreeBean {
        public int id;
        public String title;
        public String imgUrl;
    }
}
