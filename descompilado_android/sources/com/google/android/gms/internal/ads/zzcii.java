package com.google.android.gms.internal.ads;

import android.view.ViewGroup;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcii implements zzdvi<zzcif> {
    private final zzdvv<zzboa> zzfge;
    private final zzdvv<zzbds> zzfiv;
    private final zzdvv<zzblu.zza> zzfiw;
    private final zzdvv<zzbox> zzfix;
    private final zzdvv<zzbsn> zzfiy;
    private final zzdvv<zzcko> zzfyp;
    private final zzdvv<ViewGroup> zzfyq;

    public zzcii(zzdvv<zzbds> zzdvvVar, zzdvv<zzblu.zza> zzdvvVar2, zzdvv<zzcko> zzdvvVar3, zzdvv<zzbox> zzdvvVar4, zzdvv<zzbsn> zzdvvVar5, zzdvv<zzboa> zzdvvVar6, zzdvv<ViewGroup> zzdvvVar7) {
        this.zzfiv = zzdvvVar;
        this.zzfiw = zzdvvVar2;
        this.zzfyp = zzdvvVar3;
        this.zzfix = zzdvvVar4;
        this.zzfiy = zzdvvVar5;
        this.zzfge = zzdvvVar6;
        this.zzfyq = zzdvvVar7;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcif(this.zzfiv.get(), this.zzfiw.get(), this.zzfyp.get(), this.zzfix.get(), this.zzfiy.get(), this.zzfge.get(), this.zzfyq.get());
    }
}
