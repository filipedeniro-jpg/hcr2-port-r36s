package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdsz extends AbstractList<String> implements zzdqu, RandomAccess {
    private final zzdqu zzhof;

    public zzdsz(zzdqu zzdquVar) {
        this.zzhof = zzdquVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdqu
    public final zzdqu zzbaj() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdqu
    public final Object zzgr(int i) {
        return this.zzhof.zzgr(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzhof.size();
    }

    @Override // com.google.android.gms.internal.ads.zzdqu
    public final void zzdb(zzdot zzdotVar) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new zzdsy(this, i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zzdtb(this);
    }

    @Override // com.google.android.gms.internal.ads.zzdqu
    public final List<?> zzbai() {
        return this.zzhof.zzbai();
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return (String) this.zzhof.get(i);
    }
}
