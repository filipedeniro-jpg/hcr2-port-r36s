package com.ironsource.mediationsdk;

/* JADX INFO: loaded from: classes2.dex */
public class ISBannerSize {
    public static final ISBannerSize BANNER = new ISBannerSize("BANNER");
    public static final ISBannerSize LARGE = new ISBannerSize("LARGE");
    public static final ISBannerSize RECTANGLE = new ISBannerSize("RECTANGLE");
    public static final ISBannerSize SMART = new ISBannerSize("SMART");
    private String mDescription;
    private int mHeight;
    private int mWidth;

    public ISBannerSize(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mDescription = "CUSTOM";
    }

    public ISBannerSize(String str) {
        this.mDescription = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }
}
