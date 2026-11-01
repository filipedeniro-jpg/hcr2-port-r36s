package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsj implements zzdvi<zzcsh> {
    private final zzdvv<Context> zzfgb;

    public zzcsj(zzdvv<Context> zzdvvVar) {
        this.zzfgb = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcsh(this.zzfgb.get());
    }
}
