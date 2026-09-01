package com.pravartak.model.farmer_model;

import com.google.cloud.Timestamp;

public class CommunityPost {

    private String postId;
    private String farmerId;
    private String farmerName;
    private String content;
    private String imageUrl;
    private Timestamp timestamp;
    private long likes;

    public CommunityPost() {
        // Required by Firestore
    }

    public CommunityPost(
            String farmerId,
            String farmerName,
            String content,
            String imageUrl) {

        this.farmerId = farmerId;
        this.farmerName = farmerName;
        this.content = content;
        this.imageUrl = imageUrl;
        this.likes = 0;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }
}