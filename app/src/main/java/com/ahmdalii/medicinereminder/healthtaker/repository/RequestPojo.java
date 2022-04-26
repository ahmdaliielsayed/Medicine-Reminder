package com.ahmdalii.medicinereminder.healthtaker.repository;

public class RequestPojo {
    private String receiverId;
    private String senderId;
    private String senderUsername;
    private String profile_image_uri;
    private String status;


    public RequestPojo(String receiverId, String senderId, String senderUsername, String profile_image_uri, String status) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.status = status;
        this.profile_image_uri = profile_image_uri;
    }

    public RequestPojo() {
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getProfile_image_uri() {
        return profile_image_uri;
    }

    public void setProfile_image_uri(String profile_image_uri) {
        this.profile_image_uri = profile_image_uri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
