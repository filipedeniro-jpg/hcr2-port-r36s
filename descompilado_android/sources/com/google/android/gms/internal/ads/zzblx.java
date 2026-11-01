package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzblx implements zzdvi<Context> {
    private final zzdvv<Context> zzfgb;
    private final zzblu zzfgi;

    private zzblx(zzblu zzbluVar, zzdvv<Context> zzdvvVar) {
        this.zzfgi = zzbluVar;
        this.zzfgb = zzdvvVar;
    }

    public static zzblx zza(zzblu zzbluVar, zzdvv<Context> zzdvvVar) {
        return new zzblx(zzbluVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (Context) zzdvo.zza(this.zzfgi.zzbw(this.zzfgb.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
