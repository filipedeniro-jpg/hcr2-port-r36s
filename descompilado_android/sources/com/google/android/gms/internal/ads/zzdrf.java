package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdrf<K, V> {
    static <K, V> void zza(zzdpm zzdpmVar, zzdre<K, V> zzdreVar, K k, V v) throws IOException {
        zzdpt.zza(zzdpmVar, zzdreVar.zzhmf, 1, k);
        zzdpt.zza(zzdpmVar, zzdreVar.zzhmh, 2, v);
    }

    static <K, V> int zza(zzdre<K, V> zzdreVar, K k, V v) {
        return zzdpt.zza(zzdreVar.zzhmf, 1, k) + zzdpt.zza(zzdreVar.zzhmh, 2, v);
    }
}
