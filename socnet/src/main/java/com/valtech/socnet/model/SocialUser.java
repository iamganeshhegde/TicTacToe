package com.valtech.socnet.model;

import java.io.Serializable;

/**
 * Created by ankit.thakur on 16-02-2016.
 */

public class SocialUser implements Serializable{

    private String userId;
    private String emailId;
    private String username;
    private String ProfileImage;
    private String coverImage;
    private String screeName;
    private String phoneNumber;
    private String gender;
    private String birthDay;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }
    public String getScreeName() {
        return screeName;
    }

    public void setScreeName(String screeName) {
        this.screeName = screeName;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }


    public String getUserId() {
        return userId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "  "+userId+" "+username+" "+emailId+" "+ProfileImage+" "+coverImage+" "+screeName+" "+phoneNumber;
    }
}
