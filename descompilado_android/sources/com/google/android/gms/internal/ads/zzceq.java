package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzceq implements zzdvi<zzcen> {
    private final zzdvv<Context> zzejn;

    private zzceq(zzdvv<Context> zzdvvVar) {
        this.zzejn = zzdvvVar;
    }

    public static zzceq zzac(zzdvv<Context> zzdvvVar) {
        return new zzceq(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcen(this.zzejn.get());
    }
}
