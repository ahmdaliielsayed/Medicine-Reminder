package com.ahmdalii.medicinereminder.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @ColumnInfo(name = "userId")
    @NonNull
    private String userId;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "profile_image_uri")
    private String profile_image_uri;
    @ColumnInfo(name = "med_friend_id")
    private String med_friend_id;

    public User() { }

    public User(@NonNull String userId, String username, String email, String password, String profile_image_uri, String med_friend_id) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile_image_uri = profile_image_uri;
        this.med_friend_id = med_friend_id;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile_image_uri() {
        return profile_image_uri;
    }

    public void setProfile_image_uri(String profile_image_uri) {
        this.profile_image_uri = profile_image_uri;
    }

    public String getMed_friend_id() {
        return med_friend_id;
    }

    public void setMed_friend_id(String med_friend_id) {
        this.med_friend_id = med_friend_id;
    }
}
