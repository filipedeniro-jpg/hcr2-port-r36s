package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbwi implements zzdvi<zzbwa> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzbvr> zzesd;
    private final zzdvv<zzbwo> zzesf;
    private final zzdvv<zzawv> zzfaa;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<zzdf> zzfjv;
    private final zzdvv<com.google.android.gms.ads.internal.zzb> zzfok;
    private final zzdvv<zzrz> zzfol;
    private final zzdvv<ScheduledExecutorService> zzfom;

    public zzbwi(zzdvv<Context> zzdvvVar, zzdvv<zzbvr> zzdvvVar2, zzdvv<zzdf> zzdvvVar3, zzdvv<zzawv> zzdvvVar4, zzdvv<com.google.android.gms.ads.internal.zzb> zzdvvVar5, zzdvv<zzrz> zzdvvVar6, zzdvv<Executor> zzdvvVar7, zzdvv<zzcvk> zzdvvVar8, zzdvv<zzbwo> zzdvvVar9, zzdvv<ScheduledExecutorService> zzdvvVar10) {
        this.zzejn = zzdvvVar;
        this.zzesd = zzdvvVar2;
        this.zzfjv = zzdvvVar3;
        this.zzfaa = zzdvvVar4;
        this.zzfok = zzdvvVar5;
        this.zzfol = zzdvvVar6;
        this.zzfbt = zzdvvVar7;
        this.zzfds = zzdvvVar8;
        this.zzesf = zzdvvVar9;
        this.zzfom = zzdvvVar10;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbwa(this.zzejn.get(), this.zzesd.get(), this.zzfjv.get(), this.zzfaa.get(), this.zzfok.get(), this.zzfol.get(), this.zzfbt.get(), this.zzfds.get(), this.zzesf.get(), this.zzfom.get());
    }
}
