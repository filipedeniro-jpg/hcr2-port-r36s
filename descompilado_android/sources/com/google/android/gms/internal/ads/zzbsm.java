package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbsm implements zzdvi<zzbsj> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzarv> zzfad;
    private final zzdvv<Integer> zzfch;
    private final zzdvv<View> zzfdh;
    private final zzdvv<zzars> zzfes;

    private zzbsm(zzdvv<zzars> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<zzarv> zzdvvVar3, zzdvv<View> zzdvvVar4, zzdvv<Integer> zzdvvVar5) {
        this.zzfes = zzdvvVar;
        this.zzejn = zzdvvVar2;
        this.zzfad = zzdvvVar3;
        this.zzfdh = zzdvvVar4;
        this.zzfch = zzdvvVar5;
    }

    public static zzbsm zze(zzdvv<zzars> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<zzarv> zzdvvVar3, zzdvv<View> zzdvvVar4, zzdvv<Integer> zzdvvVar5) {
        return new zzbsm(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbsj(this.zzfes.get(), this.zzejn.get(), this.zzfad.get(), this.zzfdh.get(), this.zzfch.get().intValue());
    }
}
