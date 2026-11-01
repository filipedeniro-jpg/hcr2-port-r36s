package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbkk {
    private final zzcvj zzeoa;
    private final zzcvb zzfar;
    private final String zzfev;

    public zzbkk(zzcvj zzcvjVar, zzcvb zzcvbVar, @Nullable String str) {
        this.zzeoa = zzcvjVar;
        this.zzfar = zzcvbVar;
        this.zzfev = str == null ? "com.google.ads.mediation.admob.AdMobAdapter" : str;
    }

    public final zzcvj zzafl() {
        return this.zzeoa;
    }

    public final zzcvb zzafm() {
        return this.zzfar;
    }

    public final String zzafn() {
        return this.zzfev;
    }
}
