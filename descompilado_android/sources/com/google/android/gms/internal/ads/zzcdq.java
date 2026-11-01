package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcdq implements zzdvi<zzcdo> {
    private final zzdvv<Context> zzejn;

    public zzcdq(zzdvv<Context> zzdvvVar) {
        this.zzejn = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcdo(this.zzejn.get());
    }
}
