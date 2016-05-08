package com.example.huhu.bean;

/**
 * VIP商品信息
 * Created by Administrator on 2016/4/1.
 */
public class VIPProInfo {

    /**
     * VIP商品名称
     */
    private String pro_name_VIP;
    /**
     * VIP商品价格
     */
    private float price_VIP;
    /**
     * VIP商品简介
     */
    private String intro_VIP;
    /**
     * VIP商品图片Url
     */
    private String pictureUrl_VIP;
    /**
     * VIP商品详情Url
     */
    private String detailUrl_VIP;

    public String getPro_name_VIP() {
        return pro_name_VIP;
    }

    public void setPro_name_VIP(String pro_name_VIP) {
        this.pro_name_VIP = pro_name_VIP;
    }

    public float getPrice_VIP() {
        return price_VIP;
    }

    public void setPrice_VIP(float price_VIP) {
        this.price_VIP = price_VIP;
    }

    public String getIntro_VIP() {
        return intro_VIP;
    }

    public void setIntro_VIP(String intro_VIP) {
        this.intro_VIP = intro_VIP;
    }

    public String getPictureUrl_VIP() {
        return pictureUrl_VIP;
    }

    public void setPictureUrl_VIP(String pictureUrl_VIP) {
        this.pictureUrl_VIP = pictureUrl_VIP;
    }

    public String getDetailUrl_VIP() {
        return detailUrl_VIP;
    }

    public void setDetailUrl_VIP(String detailUrl_VIP) {
        this.detailUrl_VIP = detailUrl_VIP;
    }
}
