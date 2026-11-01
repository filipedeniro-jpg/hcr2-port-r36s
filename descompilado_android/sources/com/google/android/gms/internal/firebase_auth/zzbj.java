package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzbj<K> extends zzbg<K> {
    private final transient zzbe<K, ?> zza;
    private final transient zzaz<K> zzb;

    zzbj(zzbe<K, ?> zzbeVar, zzaz<K> zzazVar) {
        this.zza = zzbeVar;
        this.zzb = zzazVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    /* JADX INFO: renamed from: zzb */
    public final zzbo<K> iterator() {
        return (zzbo) zzc().iterator();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    final int zza(Object[] objArr, int i) {
        return zzc().zza(objArr, i);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbg, com.google.android.gms.internal.firebase_auth.zzba
    public final zzaz<K> zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(@NullableDecl Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbg, com.google.android.gms.internal.firebase_auth.zzba, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
