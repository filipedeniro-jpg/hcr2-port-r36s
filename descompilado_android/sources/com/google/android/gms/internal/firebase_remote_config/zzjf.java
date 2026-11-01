package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
interface zzjf<T> {
    boolean equals(T t, T t2);

    int hashCode(T t);

    T newInstance();

    void zza(T t, zzjg zzjgVar, zzgx zzgxVar) throws IOException;

    void zza(T t, zzku zzkuVar) throws IOException;

    void zza(T t, byte[] bArr, int i, int i2, zzfw zzfwVar) throws IOException;

    void zze(T t, T t2);

    void zzm(T t);

    int zzw(T t);

    boolean zzy(T t);
}
