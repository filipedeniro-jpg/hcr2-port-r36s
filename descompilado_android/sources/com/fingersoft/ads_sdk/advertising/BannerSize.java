package com.fingersoft.ads_sdk.advertising;

/* JADX INFO: loaded from: classes.dex */
public class BannerSize {
    private int height;
    private int inMobiAdSize;
    private int width;

    public BannerSize(int i, int i2, int i3) {
        this.width = i;
        this.height = i2;
        this.inMobiAdSize = i3;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getInMobiAdSize() {
        return this.inMobiAdSize;
    }

    public String toString() {
        return "Height: " + this.height + ", Width: " + this.width;
    }
}
