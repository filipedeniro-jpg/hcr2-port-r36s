package com.fingersoft.account_sdk;

/* JADX INFO: loaded from: classes.dex */
public class Friend {
    private String mEmail;
    private String mFacebookID;
    private String mGuid;
    private String mName;
    private String mPlayerData;
    private String mProfilePictureLink;

    public Friend(String str, String str2, String str3, String str4, String str5) {
        this.mFacebookID = str;
        this.mName = str2;
        this.mEmail = str3;
        this.mProfilePictureLink = str4;
        this.mPlayerData = str5;
    }

    public String getFacebookID() {
        return this.mFacebookID;
    }

    public String getName() {
        return this.mName;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getProfilePictureLink() {
        return this.mProfilePictureLink;
    }

    public String getPlayerData() {
        return this.mPlayerData;
    }

    public boolean isFacebookFriend() {
        return this.mFacebookID != null;
    }

    public void setPlayerData(String str) {
        this.mPlayerData = str;
    }

    public void setGuid(String str) {
        this.mGuid = str;
    }

    public String getGuid() {
        return this.mGuid;
    }
}
