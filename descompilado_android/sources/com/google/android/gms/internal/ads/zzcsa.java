package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsa implements zzdvi<zzcry> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzaor> zzesq;
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<String> zzfqm;

    public zzcsa(zzdvv<zzaor> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<String> zzdvvVar3, zzdvv<zzdcs> zzdvvVar4) {
        this.zzesq = zzdvvVar;
        this.zzejn = zzdvvVar2;
        this.zzfqm = zzdvvVar3;
        this.zzfbt = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcry(this.zzesq.get(), this.zzejn.get(), this.zzfqm.get(), this.zzfbt.get());
    }
}
