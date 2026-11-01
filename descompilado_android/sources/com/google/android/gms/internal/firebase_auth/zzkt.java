package com.google.android.gms.internal.firebase_auth;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzkt extends AbstractList<String> implements zzir, RandomAccess {
    private final zzir zza;

    public zzkt(zzir zzirVar) {
        this.zza = zzirVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzir
    public final zzir zze() {
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzir
    public final Object zzb(int i) {
        return this.zza.zzb(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzir
    public final void zza(zzgm zzgmVar) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new zzkw(this, i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zzkv(this);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzir
    public final List<?> zzd() {
        return this.zza.zzd();
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return (String) this.zza.get(i);
    }
}
