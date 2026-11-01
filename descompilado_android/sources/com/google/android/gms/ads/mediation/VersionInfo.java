package com.google.android.gms.ads.mediation;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class VersionInfo {
    private final int zzein;
    private final int zzeio;
    private final int zzeip;

    public VersionInfo(int i, int i2, int i3) {
        this.zzein = i;
        this.zzeio = i2;
        this.zzeip = i3;
    }

    public final int getMajorVersion() {
        return this.zzein;
    }

    public final int getMinorVersion() {
        return this.zzeio;
    }

    public final int getMicroVersion() {
        return this.zzeip;
    }
}
