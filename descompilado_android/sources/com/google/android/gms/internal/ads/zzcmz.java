package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.ViewGroup;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcmz implements zzdvi<zzcmx> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<ViewGroup> zzfyq;
    private final zzdvv<zzdcs> zzgdl;

    public zzcmz(zzdvv<zzdcs> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<zzcvk> zzdvvVar3, zzdvv<ViewGroup> zzdvvVar4) {
        this.zzgdl = zzdvvVar;
        this.zzejn = zzdvvVar2;
        this.zzfds = zzdvvVar3;
        this.zzfyq = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcmx(this.zzgdl.get(), this.zzejn.get(), this.zzfds.get(), this.zzfyq.get());
    }
}
