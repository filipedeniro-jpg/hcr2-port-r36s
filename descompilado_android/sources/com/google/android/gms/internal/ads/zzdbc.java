package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdbc<E> extends zzdan<E> {
    private final transient E zzgpj;
    private transient int zzgpk;

    zzdbc(E e) {
        this.zzgpj = (E) zzczx.checkNotNull(e);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final boolean zzaoj() {
        return false;
    }

    zzdbc(E e, int i) {
        this.zzgpj = e;
        this.zzgpk = i;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.zzgpj.equals(obj);
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    /* JADX INFO: renamed from: zzaoe */
    public final zzdbb<E> iterator() {
        return new zzdap(this.zzgpj);
    }

    @Override // com.google.android.gms.internal.ads.zzdan
    final zzdak<E> zzaom() {
        return zzdak.zzad(this.zzgpj);
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final int zza(Object[] objArr, int i) {
        objArr[i] = this.zzgpj;
        return i + 1;
    }

    @Override // com.google.android.gms.internal.ads.zzdan, java.util.Collection, java.util.Set
    public final int hashCode() {
        int i = this.zzgpk;
        if (i != 0) {
            return i;
        }
        int iHashCode = this.zzgpj.hashCode();
        this.zzgpk = iHashCode;
        return iHashCode;
    }

    @Override // com.google.android.gms.internal.ads.zzdan
    final boolean zzaol() {
        return this.zzgpk != 0;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        String string = this.zzgpj.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 2);
        sb.append('[');
        sb.append(string);
        sb.append(']');
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.ads.zzdan, com.google.android.gms.internal.ads.zzdaf, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
