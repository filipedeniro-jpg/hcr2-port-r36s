package com.fingersoft.account_sdk;

/* JADX INFO: loaded from: classes.dex */
public class LoginObject {
    String mToken;
    String mTokenID;

    public String getEmail() {
        return "";
    }

    public LoginObject(String str, String str2) {
        this.mToken = str;
        this.mTokenID = str2;
    }

    public String getToken() {
        return this.mToken;
    }

    public String getTokenID() {
        return this.mTokenID;
    }
}
