package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
interface zzdsc<T> {
    boolean equals(T t, T t2);

    int hashCode(T t);

    T newInstance();

    void zza(T t, zzdsd zzdsdVar, zzdpq zzdpqVar) throws IOException;

    void zza(T t, zzdtr zzdtrVar) throws IOException;

    void zza(T t, byte[] bArr, int i, int i2, zzdos zzdosVar) throws IOException;

    void zzak(T t);

    int zzau(T t);

    boolean zzaw(T t);

    void zzf(T t, T t2);
}
