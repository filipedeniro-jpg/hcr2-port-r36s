package com.google.android.gms.internal.ads;

import java.util.AbstractMap;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdav<K, V> extends zzdak<Map.Entry<K, V>> {
    private final /* synthetic */ zzdaw zzgpd;

    zzdav(zzdaw zzdawVar) {
        this.zzgpd = zzdawVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    public final boolean zzaoj() {
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzgpd.size;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int i) {
        zzczx.zzr(i, this.zzgpd.size);
        Object[] objArr = this.zzgpd.zzgpa;
        int i2 = i * 2;
        zzdaw zzdawVar = this.zzgpd;
        Object obj = objArr[i2];
        Object[] objArr2 = zzdawVar.zzgpa;
        zzdaw zzdawVar2 = this.zzgpd;
        return new AbstractMap.SimpleImmutableEntry(obj, objArr2[i2 + 1]);
    }
}
