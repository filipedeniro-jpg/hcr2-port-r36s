package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbli implements zzdvi<com.google.android.gms.ads.internal.zza> {
    private final zzdvv<Context> zzejn;
    private final zzblf zzffy;
    private final zzdvv<zzaro> zzffz;

    private zzbli(zzblf zzblfVar, zzdvv<Context> zzdvvVar, zzdvv<zzaro> zzdvvVar2) {
        this.zzffy = zzblfVar;
        this.zzejn = zzdvvVar;
        this.zzffz = zzdvvVar2;
    }

    public static zzbli zza(zzblf zzblfVar, zzdvv<Context> zzdvvVar, zzdvv<zzaro> zzdvvVar2) {
        return new zzbli(zzblfVar, zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (com.google.android.gms.ads.internal.zza) zzdvo.zza(new com.google.android.gms.ads.internal.zza(this.zzejn.get(), this.zzffz.get(), null), "Cannot return null from a non-@Nullable @Provides method");
    }
}
