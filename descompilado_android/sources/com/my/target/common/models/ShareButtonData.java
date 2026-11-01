package com.my.target.common.models;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ShareButtonData {

    @Nullable
    public String imageUrl;

    @Nullable
    public String name;

    @Nullable
    public String url;

    public static ShareButtonData newData() {
        return new ShareButtonData();
    }

    private ShareButtonData() {
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    public void setName(@Nullable String str) {
        this.name = str;
    }

    @Nullable
    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(@Nullable String str) {
        this.imageUrl = str;
    }

    @Nullable
    public String getUrl() {
        return this.url;
    }

    public void setUrl(@Nullable String str) {
        this.url = str;
    }
}
