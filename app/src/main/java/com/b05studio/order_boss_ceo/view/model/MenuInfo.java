package com.b05studio.order_boss_ceo.view.model;

/**
 * Created by mansu on 2017-06-02.
 */
public class MenuInfo {
    private String url;
    private String name;
    private int price;

    public MenuInfo(String url, String name, int price) {
        this.url = url;
        this.name = name;
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}