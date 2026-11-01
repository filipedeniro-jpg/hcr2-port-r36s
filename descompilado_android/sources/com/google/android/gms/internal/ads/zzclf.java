package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzclf implements zzdvi<zzclg> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<zzur> zzgbb;
    private final zzdvv<zzbhy> zzgbc;

    public zzclf(zzdvv<Context> zzdvvVar, zzdvv<zzur> zzdvvVar2, zzdvv<zzcvk> zzdvvVar3, zzdvv<zzbhy> zzdvvVar4) {
        this.zzejn = zzdvvVar;
        this.zzgbb = zzdvvVar2;
        this.zzfds = zzdvvVar3;
        this.zzgbc = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzclg(this.zzejn.get(), this.zzgbb.get(), this.zzfds.get(), this.zzgbc.get());
    }
}
