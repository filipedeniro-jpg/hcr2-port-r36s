package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcgk implements zzdvi<zzcgg> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzbit> zzfwz;

    public zzcgk(zzdvv<Context> zzdvvVar, zzdvv<zzbit> zzdvvVar2) {
        this.zzejn = zzdvvVar;
        this.zzfwz = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcgg(this.zzejn.get(), this.zzfwz.get());
    }
}
