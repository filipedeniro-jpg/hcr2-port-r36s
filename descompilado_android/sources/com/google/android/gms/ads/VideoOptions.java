package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzyc;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class VideoOptions {
    private final boolean zzabp;
    private final boolean zzabq;
    private final boolean zzabr;

    public VideoOptions(zzyc zzycVar) {
        this.zzabp = zzycVar.zzabp;
        this.zzabq = zzycVar.zzabq;
        this.zzabr = zzycVar.zzabr;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    public static final class Builder {
        private boolean zzabp = true;
        private boolean zzabq = false;
        private boolean zzabr = false;

        public final Builder setStartMuted(boolean z) {
            this.zzabp = z;
            return this;
        }

        public final Builder setCustomControlsRequested(boolean z) {
            this.zzabq = z;
            return this;
        }

        public final Builder setClickToExpandRequested(boolean z) {
            this.zzabr = z;
            return this;
        }

        public final VideoOptions build() {
            return new VideoOptions(this);
        }
    }

    private VideoOptions(Builder builder) {
        this.zzabp = builder.zzabp;
        this.zzabq = builder.zzabq;
        this.zzabr = builder.zzabr;
    }

    public final boolean getStartMuted() {
        return this.zzabp;
    }

    public final boolean getCustomControlsRequested() {
        return this.zzabq;
    }

    public final boolean getClickToExpandRequested() {
        return this.zzabr;
    }
}
