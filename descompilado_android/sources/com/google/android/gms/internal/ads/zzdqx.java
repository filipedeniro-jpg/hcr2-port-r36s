package com.google.android.gms.internal.ads;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzdqx {
    private static final zzdqx zzhlx;
    private static final zzdqx zzhly;

    private zzdqx() {
    }

    abstract <L> List<L> zza(Object obj, long j);

    abstract <L> void zza(Object obj, Object obj2, long j);

    abstract void zzb(Object obj, long j);

    static zzdqx zzbak() {
        return zzhlx;
    }

    static zzdqx zzbal() {
        return zzhly;
    }

    static {
        zzdqw zzdqwVar = null;
        zzhlx = new zzdqz();
        zzhly = new zzdqy();
    }
}
