package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzii<K, V> {
    static <K, V> void zza(zzgs zzgsVar, zzih<K, V> zzihVar, K k, V v) throws IOException {
        zzha.zza(zzgsVar, zzihVar.zzvi, 1, k);
        zzha.zza(zzgsVar, zzihVar.zzvk, 2, v);
    }

    static <K, V> int zza(zzih<K, V> zzihVar, K k, V v) {
        return zzha.zza(zzihVar.zzvi, 1, k) + zzha.zza(zzihVar.zzvk, 2, v);
    }
}
