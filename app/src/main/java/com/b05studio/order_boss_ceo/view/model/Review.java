package com.b05studio.order_boss_ceo.view.model;

/**
 * Created by mansu on 2017-06-02.
 */

public class Review {
    private String reviewId;

    private String userName;
    private String userProfileUrl;
    private long timestamp;
    private String restarauntName;
    private String reviewContent;
    private String reviewImgUrl;
    private int likeNumber;

    public Review(String reviewId, String userName, String userProfileUrl, long timestamp, String restarauntName, String reviewContent, String reviewImgUrl, int likeNumber) {
        this.reviewId = reviewId;
        this.userName = userName;
        this.userProfileUrl = userProfileUrl;
        this.timestamp = timestamp;
        this.restarauntName = restarauntName;
        this.reviewContent = reviewContent;
        this.reviewImgUrl = reviewImgUrl;
        this.likeNumber = likeNumber;

    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewImgUrls() {
        return reviewImgUrl;
    }

    public void setReviewImgUrls(String reviewImgUrl) {
        this.reviewImgUrl = reviewImgUrl;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public String getRestarauntName() {
        return restarauntName;
    }

    public void setRestarauntName(String restarauntName) {
        this.restarauntName = restarauntName;
    }
}
