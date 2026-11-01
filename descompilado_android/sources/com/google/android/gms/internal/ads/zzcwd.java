package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcwd implements zzdvi<zzcwa> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzatj> zzeod;

    private zzcwd(zzdvv<Context> zzdvvVar, zzdvv<zzatj> zzdvvVar2) {
        this.zzejn = zzdvvVar;
        this.zzeod = zzdvvVar2;
    }

    public static zzcwd zzav(zzdvv<Context> zzdvvVar, zzdvv<zzatj> zzdvvVar2) {
        return new zzcwd(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcwa(this.zzejn.get(), this.zzeod.get());
    }
}
