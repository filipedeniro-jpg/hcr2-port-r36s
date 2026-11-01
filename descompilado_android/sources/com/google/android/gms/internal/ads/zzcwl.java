package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcwl implements zzdvi<Context> {
    private final zzcwg zzgkn;
    private final zzdvv<zzcwe> zzgkp;

    private zzcwl(zzcwg zzcwgVar, zzdvv<zzcwe> zzdvvVar) {
        this.zzgkn = zzcwgVar;
        this.zzgkp = zzdvvVar;
    }

    public static zzcwl zzc(zzcwg zzcwgVar, zzdvv<zzcwe> zzdvvVar) {
        return new zzcwl(zzcwgVar, zzdvvVar);
    }

    public static Context zzb(zzcwg zzcwgVar, zzcwe zzcweVar) {
        return (Context) zzdvo.zza(zzcweVar.zzzc, "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzb(this.zzgkn, this.zzgkp.get());
    }
}
