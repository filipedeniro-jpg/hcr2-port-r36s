package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzday<K> extends zzdan<K> {
    private final transient zzdak<K> zzgop;
    private final transient zzdao<K, ?> zzgpe;

    zzday(zzdao<K, ?> zzdaoVar, zzdak<K> zzdakVar) {
        this.zzgpe = zzdaoVar;
        this.zzgop = zzdakVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final boolean zzaoj() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    /* JADX INFO: renamed from: zzaoe */
    public final zzdbb<K> iterator() {
        return (zzdbb) zzaoi().iterator();
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final int zza(Object[] objArr, int i) {
        return zzaoi().zza(objArr, i);
    }

    @Override // com.google.android.gms.internal.ads.zzdan, com.google.android.gms.internal.ads.zzdaf
    public final zzdak<K> zzaoi() {
        return this.zzgop;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@NullableDecl Object obj) {
        return this.zzgpe.get(obj) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzgpe.size();
    }

    @Override // com.google.android.gms.internal.ads.zzdan, com.google.android.gms.internal.ads.zzdaf, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
