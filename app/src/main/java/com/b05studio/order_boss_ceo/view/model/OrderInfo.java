package com.b05studio.order_boss_ceo.view.model;

/**
 * Created by mansu on 2017-06-02.
 */

public class OrderInfo {
    private String restaurantId;
    private MenuInfo menuInfo;
    private int menuNum;

    public OrderInfo(String restaurantId, MenuInfo menuInfo, int menuNum) {
        this.restaurantId = restaurantId;
        this.menuInfo = menuInfo;
        this.menuNum = menuNum;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public MenuInfo getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(MenuInfo menuInfo) {
        this.menuInfo = menuInfo;
    }

    public int getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(int menuNum) {
        this.menuNum = menuNum;
    }
}
