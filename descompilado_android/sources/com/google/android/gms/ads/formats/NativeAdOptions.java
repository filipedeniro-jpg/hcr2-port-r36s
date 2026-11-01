package com.google.android.gms.ads.formats;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class NativeAdOptions {
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_ANY = 1;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_LANDSCAPE = 2;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_PORTRAIT = 3;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_SQUARE = 4;
    public static final int NATIVE_MEDIA_ASPECT_RATIO_UNKNOWN = 0;

    @Deprecated
    public static final int ORIENTATION_ANY = 0;

    @Deprecated
    public static final int ORIENTATION_LANDSCAPE = 2;

    @Deprecated
    public static final int ORIENTATION_PORTRAIT = 1;
    private final boolean zzbjs;
    private final int zzbjt;
    private final int zzbju;
    private final boolean zzbjv;
    private final int zzbjw;
    private final VideoOptions zzbjx;
    private final boolean zzbjy;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    public @interface AdChoicesPlacement {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    public @interface NativeMediaAspectRatio {
    }

    private NativeAdOptions(Builder builder) {
        this.zzbjs = builder.zzbjs;
        this.zzbjt = builder.zzbjt;
        this.zzbju = builder.zzbju;
        this.zzbjv = builder.zzbjv;
        this.zzbjw = builder.zzbjw;
        this.zzbjx = builder.zzbjx;
        this.zzbjy = builder.zzbjy;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    public static final class Builder {
        private VideoOptions zzbjx;
        private boolean zzbjs = false;
        private int zzbjt = -1;
        private int zzbju = 0;
        private boolean zzbjv = false;
        private int zzbjw = 1;
        private boolean zzbjy = false;

        public final Builder setReturnUrlsForImageAssets(boolean z) {
            this.zzbjs = z;
            return this;
        }

        @Deprecated
        public final Builder setImageOrientation(int i) {
            this.zzbjt = i;
            return this;
        }

        public final Builder setMediaAspectRatio(@NativeMediaAspectRatio int i) {
            this.zzbju = i;
            return this;
        }

        public final Builder setRequestMultipleImages(boolean z) {
            this.zzbjv = z;
            return this;
        }

        public final Builder setAdChoicesPlacement(@AdChoicesPlacement int i) {
            this.zzbjw = i;
            return this;
        }

        public final Builder setVideoOptions(VideoOptions videoOptions) {
            this.zzbjx = videoOptions;
            return this;
        }

        public final Builder setRequestCustomMuteThisAd(boolean z) {
            this.zzbjy = z;
            return this;
        }

        public final NativeAdOptions build() {
            return new NativeAdOptions(this);
        }
    }

    public final boolean shouldReturnUrlsForImageAssets() {
        return this.zzbjs;
    }

    @Deprecated
    public final int getImageOrientation() {
        return this.zzbjt;
    }

    public final int getMediaAspectRatio() {
        return this.zzbju;
    }

    public final boolean shouldRequestMultipleImages() {
        return this.zzbjv;
    }

    public final int getAdChoicesPlacement() {
        return this.zzbjw;
    }

    @Nullable
    public final VideoOptions getVideoOptions() {
        return this.zzbjx;
    }

    public final boolean zzjb() {
        return this.zzbjy;
    }
}
