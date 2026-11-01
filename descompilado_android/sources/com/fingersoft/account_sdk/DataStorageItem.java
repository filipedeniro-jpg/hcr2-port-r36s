package com.fingersoft.account_sdk;

/* JADX INFO: loaded from: classes.dex */
public class DataStorageItem {
    private String mData;
    private boolean mIsUpdated;

    public void setData(String str) {
        this.mData = str;
        this.mIsUpdated = true;
    }

    public String getData() {
        return this.mData;
    }

    public void clearUpdateFlag() {
        this.mIsUpdated = false;
    }

    public boolean getIsUpdated() {
        return this.mIsUpdated;
    }
}
