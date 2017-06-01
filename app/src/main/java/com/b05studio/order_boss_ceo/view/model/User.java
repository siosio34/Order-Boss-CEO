package com.b05studio.order_boss_ceo.view.model;

import java.util.ArrayList;

/**
 * Created by mansu on 2017-06-02.
 */

public class User {
    private String userId;
    private String userName;
    private ArrayList<ReservationInfo> reservationInfos = new ArrayList<>();
    private ArrayList<ReservationInfo> accpetedReservationInfos = new ArrayList<>();

    public static User currentUser;
    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<ReservationInfo> getReservationInfos() {
        return reservationInfos;
    }

    public void setReservationInfos(ArrayList<ReservationInfo> reservationInfos) {
        this.reservationInfos = reservationInfos;
    }

    public ArrayList<ReservationInfo> getAccpetedReservationInfos() {
        return accpetedReservationInfos;
    }

    public void setAccpetedReservationInfos(ArrayList<ReservationInfo> accpetedReservationInfos) {
        this.accpetedReservationInfos = accpetedReservationInfos;
    }
}