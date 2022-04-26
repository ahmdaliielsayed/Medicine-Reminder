package com.ahmdalii.medicinereminder.friendrequest.repository;

public class FriendRequestPojo {
    private String userId;
    private String username;
    private String profile_image_uri;

    public FriendRequestPojo(String userId, String username, String profile_image_uri) {
        this.userId = userId;
        this.username = username;
        this.profile_image_uri = profile_image_uri;
    }

    public FriendRequestPojo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_image_uri() {
        return profile_image_uri;
    }

    public void setProfile_image_uri(String profile_image_uri) {
        this.profile_image_uri = profile_image_uri;
    }
}
