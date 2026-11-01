package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbh<K, V> extends zzbg<Map.Entry<K, V>> {
    private final transient zzbe<K, V> zza;
    private final transient Object[] zzb;
    private final transient int zzc = 0;
    private final transient int zzd;

    zzbh(zzbe<K, V> zzbeVar, Object[] objArr, int i, int i2) {
        this.zza = zzbeVar;
        this.zzb = objArr;
        this.zzd = i2;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    /* JADX INFO: renamed from: zzb */
    public final zzbo<Map.Entry<K, V>> iterator() {
        return (zzbo) zzc().iterator();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    final int zza(Object[] objArr, int i) {
        return zzc().zza(objArr, i);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbg
    final zzaz<Map.Entry<K, V>> zza() {
        return new zzbk(this);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbg, com.google.android.gms.internal.firebase_auth.zzba, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
