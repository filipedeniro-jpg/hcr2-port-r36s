package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcse implements zzdvi<zzcsc> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzrn> zzesq;
    private final zzdvv<zzdcs> zzfbt;

    public zzcse(zzdvv<zzrn> zzdvvVar, zzdvv<zzdcs> zzdvvVar2, zzdvv<Context> zzdvvVar3) {
        this.zzesq = zzdvvVar;
        this.zzfbt = zzdvvVar2;
        this.zzejn = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcsc(this.zzesq.get(), this.zzfbt.get(), this.zzejn.get());
    }
}
