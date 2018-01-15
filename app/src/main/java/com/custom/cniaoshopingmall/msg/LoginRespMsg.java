
package com.custom.cniaoshopingmall.msg;


public class LoginRespMsg extends BaseRespMsg {
    public String token;
    public DataBean data;
    public static class DataBean {
        public String id;
        public String logo_url;
        public String username;
        public String mobi;
    }
}
