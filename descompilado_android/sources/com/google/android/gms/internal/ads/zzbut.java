package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbut implements zzdvi<zzbup> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzbub> zzelm;
    private final zzdvv<zzbux> zzeni;
    private final zzdvv<zzbtv> zzenk;
    private final zzdvv<zzato> zzeoo;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<zzbtx> zzfju;

    private zzbut(zzdvv<Context> zzdvvVar, zzdvv<zzato> zzdvvVar2, zzdvv<zzcvk> zzdvvVar3, zzdvv<zzbub> zzdvvVar4, zzdvv<zzbtx> zzdvvVar5, zzdvv<zzbux> zzdvvVar6, zzdvv<Executor> zzdvvVar7, zzdvv<Executor> zzdvvVar8, zzdvv<zzbtv> zzdvvVar9) {
        this.zzejn = zzdvvVar;
        this.zzeoo = zzdvvVar2;
        this.zzfds = zzdvvVar3;
        this.zzelm = zzdvvVar4;
        this.zzfju = zzdvvVar5;
        this.zzeni = zzdvvVar6;
        this.zzfdl = zzdvvVar7;
        this.zzfbt = zzdvvVar8;
        this.zzenk = zzdvvVar9;
    }

    public static zzbut zza(zzdvv<Context> zzdvvVar, zzdvv<zzato> zzdvvVar2, zzdvv<zzcvk> zzdvvVar3, zzdvv<zzbub> zzdvvVar4, zzdvv<zzbtx> zzdvvVar5, zzdvv<zzbux> zzdvvVar6, zzdvv<Executor> zzdvvVar7, zzdvv<Executor> zzdvvVar8, zzdvv<zzbtv> zzdvvVar9) {
        return new zzbut(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5, zzdvvVar6, zzdvvVar7, zzdvvVar8, zzdvvVar9);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbup(this.zzejn.get(), this.zzeoo.get(), this.zzfds.get(), this.zzelm.get(), this.zzfju.get(), this.zzeni.get(), this.zzfdl.get(), this.zzfbt.get(), this.zzenk.get());
    }
}
