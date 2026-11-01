package com.google.android.gms.internal.firebase_remote_config;

import com.google.android.gms.internal.firebase_remote_config.zzhc;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
abstract class zzgz<T extends zzhc<T>> {
    zzgz() {
    }

    abstract int zza(Map.Entry<?, ?> entry);

    abstract Object zza(zzgx zzgxVar, zziq zziqVar, int i);

    abstract <UT, UB> UB zza(zzjg zzjgVar, Object obj, zzgx zzgxVar, zzha<T> zzhaVar, UB ub, zzjx<UT, UB> zzjxVar) throws IOException;

    abstract void zza(zzfx zzfxVar, Object obj, zzgx zzgxVar, zzha<T> zzhaVar) throws IOException;

    abstract void zza(zzjg zzjgVar, Object obj, zzgx zzgxVar, zzha<T> zzhaVar) throws IOException;

    abstract void zza(zzku zzkuVar, Map.Entry<?, ?> entry) throws IOException;

    abstract boolean zze(zziq zziqVar);

    abstract zzha<T> zzk(Object obj);

    abstract zzha<T> zzl(Object obj);

    abstract void zzm(Object obj);
}
