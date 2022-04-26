package com.ahmdalii.medicinereminder.friends.repository;

public class FriendPojo {
    private String friendId;
    private String friendName;
    private String profile_image_uri;

    public FriendPojo(String friendId, String friendName, String profile_image_uri) {
        this.friendId = friendId;
        this.friendName = friendName;
        this.profile_image_uri = profile_image_uri;
    }

    public FriendPojo() {
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getProfile_image_uri() {
        return profile_image_uri;
    }

    public void setProfile_image_uri(String profile_image_uri) {
        this.profile_image_uri = profile_image_uri;
    }
}
