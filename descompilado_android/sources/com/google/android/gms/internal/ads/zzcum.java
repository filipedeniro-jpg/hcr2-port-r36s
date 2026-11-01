package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcum implements zzdvi<zzcul> {
    private final zzdvv<zzbds> zzejk;
    private final zzdvv<zzcvi> zzeyn;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<Context> zzghv;
    private final zzdvv<zzctn<zzbym, zzbyf>> zzghw;
    private final zzdvv<zzcto> zzghx;
    private final zzdvv<zzcvm> zzghy;

    public zzcum(zzdvv<Context> zzdvvVar, zzdvv<Executor> zzdvvVar2, zzdvv<zzbds> zzdvvVar3, zzdvv<zzctn<zzbym, zzbyf>> zzdvvVar4, zzdvv<zzcto> zzdvvVar5, zzdvv<zzcvm> zzdvvVar6, zzdvv<zzcvi> zzdvvVar7) {
        this.zzghv = zzdvvVar;
        this.zzfdl = zzdvvVar2;
        this.zzejk = zzdvvVar3;
        this.zzghw = zzdvvVar4;
        this.zzghx = zzdvvVar5;
        this.zzghy = zzdvvVar6;
        this.zzeyn = zzdvvVar7;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcul(this.zzghv.get(), this.zzfdl.get(), this.zzejk.get(), this.zzghw.get(), this.zzghx.get(), this.zzghy.get(), this.zzeyn.get());
    }
}
