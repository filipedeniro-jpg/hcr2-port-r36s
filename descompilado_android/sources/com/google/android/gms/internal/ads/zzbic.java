package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbic implements zzdvi<zzbia> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzbqg> zzemn;
    private final zzdvv<zzbjt> zzemy;
    private final zzdvv<zzclg> zzewa;
    private final zzdvv<zzcva> zzfdg;
    private final zzdvv<View> zzfdh;
    private final zzdvv<zzbbc> zzfdi;
    private final zzdvv<zzbjr> zzfdj;
    private final zzdvv<zzbui> zzfdk;
    private final zzdvv<Executor> zzfdl;

    public zzbic(zzdvv<zzbjt> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<zzcva> zzdvvVar3, zzdvv<View> zzdvvVar4, zzdvv<zzbbc> zzdvvVar5, zzdvv<zzbjr> zzdvvVar6, zzdvv<zzbui> zzdvvVar7, zzdvv<zzbqg> zzdvvVar8, zzdvv<zzclg> zzdvvVar9, zzdvv<Executor> zzdvvVar10) {
        this.zzemy = zzdvvVar;
        this.zzejn = zzdvvVar2;
        this.zzfdg = zzdvvVar3;
        this.zzfdh = zzdvvVar4;
        this.zzfdi = zzdvvVar5;
        this.zzfdj = zzdvvVar6;
        this.zzfdk = zzdvvVar7;
        this.zzemn = zzdvvVar8;
        this.zzewa = zzdvvVar9;
        this.zzfdl = zzdvvVar10;
    }

    public static zzbia zza(zzbjt zzbjtVar, Context context, zzcva zzcvaVar, View view, zzbbc zzbbcVar, zzbjr zzbjrVar, zzbui zzbuiVar, zzbqg zzbqgVar, zzdvc<zzclg> zzdvcVar, Executor executor) {
        return new zzbia(zzbjtVar, context, zzcvaVar, view, zzbbcVar, zzbjrVar, zzbuiVar, zzbqgVar, zzdvcVar, executor);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbia(this.zzemy.get(), this.zzejn.get(), this.zzfdg.get(), this.zzfdh.get(), this.zzfdi.get(), this.zzfdj.get(), this.zzfdk.get(), this.zzemn.get(), zzdvj.zzao(this.zzewa), this.zzfdl.get());
    }
}
