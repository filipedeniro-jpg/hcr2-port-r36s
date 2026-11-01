package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdaw<K, V> extends zzdan<Map.Entry<K, V>> {
    private final transient int size;
    private final transient Object[] zzgpa;
    private final transient zzdao<K, V> zzgpe;
    private final transient int zzgpf = 0;

    zzdaw(zzdao<K, V> zzdaoVar, Object[] objArr, int i, int i2) {
        this.zzgpe = zzdaoVar;
        this.zzgpa = objArr;
        this.size = i2;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final boolean zzaoj() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    /* JADX INFO: renamed from: zzaoe */
    public final zzdbb<Map.Entry<K, V>> iterator() {
        return (zzdbb) zzaoi().iterator();
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final int zza(Object[] objArr, int i) {
        return zzaoi().zza(objArr, i);
    }

    @Override // com.google.android.gms.internal.ads.zzdan
    final zzdak<Map.Entry<K, V>> zzaom() {
        return new zzdav(this);
    }

    @Override // com.google.android.gms.internal.ads.zzdaf, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zzgpe.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.ads.zzdan, com.google.android.gms.internal.ads.zzdaf, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
