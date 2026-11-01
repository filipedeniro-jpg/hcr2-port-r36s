package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcxx implements zzdvi<zzaid> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzawv> zzfgk;
    private final zzcxu zzgmk;

    public zzcxx(zzcxu zzcxuVar, zzdvv<Context> zzdvvVar, zzdvv<zzawv> zzdvvVar2) {
        this.zzgmk = zzcxuVar;
        this.zzejn = zzdvvVar;
        this.zzfgk = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzaid) zzdvo.zza(new zzahy().zzb(this.zzejn.get(), this.zzfgk.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
