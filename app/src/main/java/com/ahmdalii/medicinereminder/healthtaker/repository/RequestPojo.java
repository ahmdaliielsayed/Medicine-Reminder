package com.ahmdalii.medicinereminder.healthtaker.repository;

public class RequestPojo {
    private String senderId;
    private String senderUsername;
    private String status;


    public RequestPojo(String senderId, String senderUsername, String status) {
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.status = status;
    }

    public RequestPojo() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
