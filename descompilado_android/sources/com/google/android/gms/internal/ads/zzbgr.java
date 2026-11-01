package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgr implements zzdvi<zzbgs> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzpf> zzfbf;

    private zzbgr(zzdvv<Context> zzdvvVar, zzdvv<zzpf> zzdvvVar2) {
        this.zzejn = zzdvvVar;
        this.zzfbf = zzdvvVar2;
    }

    public static zzbgr zza(zzdvv<Context> zzdvvVar, zzdvv<zzpf> zzdvvVar2) {
        return new zzbgr(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbgs(this.zzejn.get(), this.zzfbf.get());
    }
}
