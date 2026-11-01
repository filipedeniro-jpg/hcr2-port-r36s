package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdsb<E> extends zzdon<E> {
    private static final zzdsb<Object> zzhni;
    private final List<E> zzhlw;

    public static <E> zzdsb<E> zzbbd() {
        return (zzdsb<E>) zzhni;
    }

    zzdsb() {
        this(new ArrayList(10));
    }

    private zzdsb(List<E> list) {
        this.zzhlw = list;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final void add(int i, E e) {
        zzaxf();
        this.zzhlw.add(i, e);
        this.modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int i) {
        return this.zzhlw.get(i);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final E remove(int i) {
        zzaxf();
        E eRemove = this.zzhlw.remove(i);
        this.modCount++;
        return eRemove;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final E set(int i, E e) {
        zzaxf();
        E e2 = this.zzhlw.set(i, e);
        this.modCount++;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzhlw.size();
    }

    @Override // com.google.android.gms.internal.ads.zzdqk
    public final /* synthetic */ zzdqk zzfm(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzhlw);
        return new zzdsb(arrayList);
    }

    static {
        zzdsb<Object> zzdsbVar = new zzdsb<>(new ArrayList(0));
        zzhni = zzdsbVar;
        zzdsbVar.zzaxe();
    }
}
