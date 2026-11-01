package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzccq implements zzdvi<zzdcp<Bundle>> {
    private final zzdvv<zzcrd<Bundle>> zzeqw;
    private final zzdvv<zzcxn> zzfdw;

    private zzccq(zzdvv<zzcxn> zzdvvVar, zzdvv<zzcrd<Bundle>> zzdvvVar2) {
        this.zzfdw = zzdvvVar;
        this.zzeqw = zzdvvVar2;
    }

    public static zzccq zzaa(zzdvv<zzcxn> zzdvvVar, zzdvv<zzcrd<Bundle>> zzdvvVar2) {
        return new zzccq(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzdcp) zzdvo.zza(this.zzfdw.get().zzu(zzcxk.SIGNALS).zzb(this.zzeqw.get().zzs(new Bundle())).zzano(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
