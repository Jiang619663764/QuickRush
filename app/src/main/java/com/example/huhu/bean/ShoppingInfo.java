package com.example.huhu.bean;

/**
 * Created by Administrator on 2016/4/8.
 * 购物车内对象
 */
public class ShoppingInfo {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 购物车中商品名
     */
    private String proName;
    /**
     * 购物车中商品价格
     */
    private float proPrice;
    /**
     * 购物车中商品个数
     */
    private int proCount;
    /**
     * 购物车中商品图片
     */
    private String proImage;
    /**
     * 购物车中商品详情
     */
    private String proDetail;

    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public float getProPrice() {
        return proPrice;
    }

    public void setProPrice(float proPrice) {
        this.proPrice = proPrice;
    }

    public int getProCount() {
        return proCount;
    }

    public void setProCount(int proCount) {
        this.proCount = proCount;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public String getProDetail() {
        return proDetail;
    }

    public void setProDetail(String proDetail) {
        this.proDetail = proDetail;
    }
}
