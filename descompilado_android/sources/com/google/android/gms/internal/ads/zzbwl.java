package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbwl implements zzdvi<zzbwh> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzbwa> zzfnt;

    public zzbwl(zzdvv<Executor> zzdvvVar, zzdvv<zzbwa> zzdvvVar2) {
        this.zzfbt = zzdvvVar;
        this.zzfnt = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbwh(this.zzfbt.get(), this.zzfnt.get());
    }
}
