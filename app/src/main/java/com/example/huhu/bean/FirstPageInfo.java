package com.example.huhu.bean;

/**
 * 首页商品信息
 * Created by Administrator on 2016/3/31.
 */
public class FirstPageInfo {

    /**
     * 产品名
     */
    private String name;

    /**
     * 产品简介
     */
    private String intro;

    /**
     * 产品价格
     */
    private int price;

    /**
     * 产品详情url地址
     */
    private String detailUrl;

    /**
     * 产品图片url地址
     */
    private String pictureUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
