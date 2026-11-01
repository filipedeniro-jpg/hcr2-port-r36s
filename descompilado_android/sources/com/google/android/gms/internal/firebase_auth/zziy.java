package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zziy<K, V> {
    static <K, V> void zza(zzhh zzhhVar, zzjb<K, V> zzjbVar, K k, V v) throws IOException {
        zzhq.zza(zzhhVar, zzjbVar.zza, 1, k);
        zzhq.zza(zzhhVar, zzjbVar.zzc, 2, v);
    }

    static <K, V> int zza(zzjb<K, V> zzjbVar, K k, V v) {
        return zzhq.zza(zzjbVar.zza, 1, k) + zzhq.zza(zzjbVar.zzc, 2, v);
    }
}
