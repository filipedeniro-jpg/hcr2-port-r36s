package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbwm {
    public final int type;
    public final String zzce;
    public final String zzfon;
    public final zzaae zzfoo;

    @VisibleForTesting
    public zzbwm(String str, String str2) {
        this.type = 1;
        this.zzce = str;
        this.zzfon = str2;
        this.zzfoo = null;
    }

    @VisibleForTesting
    public zzbwm(String str, zzaae zzaaeVar) {
        this.type = 2;
        this.zzce = str;
        this.zzfon = null;
        this.zzfoo = zzaaeVar;
    }
}
