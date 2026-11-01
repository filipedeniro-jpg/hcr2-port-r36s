package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.CustomParams;

/* JADX INFO: compiled from: AdConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    private static final int DEFAULT_VIDEO_QUALITY = 360;
    private int bannersCount;

    @Nullable
    private String bidId;

    @NonNull
    private final String format;
    private final int slotId;

    @NonNull
    private final CustomParams customParams = new CustomParams();
    private long cachePeriod = 86400000;
    private boolean trackingLocationEnabled = true;
    private boolean trackingEnvironmentEnabled = true;
    private boolean refreshAd = true;
    private boolean autoLoadImages = false;
    private boolean autoLoadVideo = false;
    private int videoQuality = DEFAULT_VIDEO_QUALITY;

    /* JADX INFO: compiled from: AdConfig.java */
    public interface a {
        public static final String a = "standard_320x50";
        public static final String b = "standard_300x250";
        public static final String c = "standard_728x90";
        public static final String d = "fullscreen";
        public static final String e = "fullscreenslider";
        public static final String f = "nativeads";
        public static final String g = "appwall";
        public static final String h = "instreamads";
        public static final String i = "instreamaudioads";
        public static final String j = "showcase";
        public static final String k = "showcaseApps";
        public static final String l = "showcaseGames";
        public static final String m = "instreamresearch";
    }

    @NonNull
    public static b newConfig(int i, @NonNull String str) {
        return new b(i, str);
    }

    public long getCachePeriod() {
        return this.cachePeriod;
    }

    public void setCachePeriod(long j) {
        if (j < 0) {
            this.cachePeriod = 0L;
        } else {
            this.cachePeriod = j;
        }
    }

    @NonNull
    public CustomParams getCustomParams() {
        return this.customParams;
    }

    @NonNull
    public String getFormat() {
        return this.format;
    }

    public int getSlotId() {
        return this.slotId;
    }

    public boolean isAutoLoadImages() {
        return this.autoLoadImages;
    }

    public void setAutoLoadImages(boolean z) {
        this.autoLoadImages = z;
    }

    public boolean isAutoLoadVideo() {
        return this.autoLoadVideo;
    }

    public void setAutoLoadVideo(boolean z) {
        this.autoLoadVideo = z;
    }

    public boolean isTrackingEnvironmentEnabled() {
        return this.trackingEnvironmentEnabled;
    }

    public void setTrackingEnvironmentEnabled(boolean z) {
        this.trackingEnvironmentEnabled = z;
    }

    public boolean isTrackingLocationEnabled() {
        return this.trackingLocationEnabled;
    }

    public void setTrackingLocationEnabled(boolean z) {
        this.trackingLocationEnabled = z;
    }

    public int getVideoQuality() {
        return this.videoQuality;
    }

    public void setVideoQuality(int i) {
        this.videoQuality = i;
    }

    public boolean isRefreshAd() {
        return this.refreshAd;
    }

    public void setRefreshAd(boolean z) {
        this.refreshAd = z;
    }

    public int getBannersCount() {
        return this.bannersCount;
    }

    public void setBannersCount(int i) {
        this.bannersCount = i;
    }

    private b(int i, @NonNull String str) {
        this.slotId = i;
        this.format = str;
    }

    @Nullable
    public String getBidId() {
        return this.bidId;
    }

    public void setBidId(@Nullable String str) {
        this.bidId = str;
    }
}
