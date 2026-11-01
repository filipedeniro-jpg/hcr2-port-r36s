package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcds implements zzdvi<zzcdp> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzarv> zzfad;

    private zzcds(zzdvv<Context> zzdvvVar, zzdvv<zzarv> zzdvvVar2) {
        this.zzejn = zzdvvVar;
        this.zzfad = zzdvvVar2;
    }

    public static zzcds zzae(zzdvv<Context> zzdvvVar, zzdvv<zzarv> zzdvvVar2) {
        return new zzcds(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcdp(this.zzejn.get(), this.zzfad.get());
    }
}
