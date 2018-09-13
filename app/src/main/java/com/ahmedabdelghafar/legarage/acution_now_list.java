package com.ahmedabdelghafar.legarage;

/**
 * Created by orcl on 22/04/2018.
 */

public class acution_now_list {
    String id_order	;
    String discreption ;
    String ddate ;

    public String getId_order() {
        return id_order;
    }

    public String getDiscreption() {
        return discreption;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public void setDiscreption(String discreption) {
        this.discreption = discreption;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate_of_open(String date_of_open) {
        this.date_of_open = date_of_open;
    }

    public void setDate_of_close(String date_of_close) {
        this.date_of_close = date_of_close;
    }

    public void setImagess(String imagess) {
        this.imagess = imagess;
    }

    public String getDdate() {
        return ddate;
    }

    public String getUrl() {
        return url;
    }

    public String getDate_of_open() {
        return date_of_open;
    }

    public String getDate_of_close() {
        return date_of_close;
    }

    public String getImagess() {
        return imagess;
    }

    String url ;
    String date_of_open;
    String date_of_close ;
    String imagess;
}
