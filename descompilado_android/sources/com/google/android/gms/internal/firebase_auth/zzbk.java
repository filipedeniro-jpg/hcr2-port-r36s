package com.google.android.gms.internal.firebase_auth;

import java.util.AbstractMap;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbk<K, V> extends zzaz<Map.Entry<K, V>> {
    private final /* synthetic */ zzbh zza;

    zzbk(zzbh zzbhVar) {
        this.zza = zzbhVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzd;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int i) {
        zzao.zza(i, this.zza.zzd);
        Object[] objArr = this.zza.zzb;
        int i2 = i * 2;
        zzbh zzbhVar = this.zza;
        Object obj = objArr[i2];
        Object[] objArr2 = zzbhVar.zzb;
        zzbh zzbhVar2 = this.zza;
        return new AbstractMap.SimpleImmutableEntry(obj, objArr2[i2 + 1]);
    }
}
