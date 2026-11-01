package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbfn implements zzdvi<zzcym> {
    private final zzdvv<Context> zzejn;

    public zzbfn(zzdvv<Context> zzdvvVar) {
        this.zzejn = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcym) zzdvo.zza(new zzcym(this.zzejn.get(), com.google.android.gms.ads.internal.zzp.zzkm().zzvz()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
