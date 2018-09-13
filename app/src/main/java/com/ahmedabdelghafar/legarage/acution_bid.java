package com.ahmedabdelghafar.legarage;

/**
 * Created by orcl on 22/05/2018.
 */

public class acution_bid {


    String seller_id;
    String seller_name;

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    String urlimage;

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public acution_bid setSeller_id(String seller_id) {
        this.seller_id = seller_id;
        return this;
    }

    public String getBid() {
        return bid;
    }

    public acution_bid setBid(String bid) {
        this.bid = bid;
        return this;
    }

    String bid;

}
