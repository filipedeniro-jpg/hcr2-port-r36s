package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcja implements zzdvi<zzciw> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzbym> zzfwz;

    public zzcja(zzdvv<Context> zzdvvVar, zzdvv<zzbym> zzdvvVar2) {
        this.zzejn = zzdvvVar;
        this.zzfwz = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzciw(this.zzejn.get(), this.zzfwz.get());
    }
}
