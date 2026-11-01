package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdqv extends zzdon<String> implements zzdqu, RandomAccess {
    private static final zzdqv zzhlu;
    private static final zzdqu zzhlv;
    private final List<Object> zzhlw;

    public zzdqv() {
        this(10);
    }

    public zzdqv(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    private zzdqv(ArrayList<Object> arrayList) {
        this.zzhlw = arrayList;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzhlw.size();
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection<? extends String> collection) {
        zzaxf();
        if (collection instanceof zzdqu) {
            collection = ((zzdqu) collection).zzbai();
        }
        boolean zAddAll = this.zzhlw.addAll(i, collection);
        this.modCount++;
        return zAddAll;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzaxf();
        this.zzhlw.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdqu
    public final void zzdb(zzdot zzdotVar) {
        zzaxf();
        this.zzhlw.add(zzdotVar);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdqu
    public final Object zzgr(int i) {
        return this.zzhlw.get(i);
    }

    private static String zzam(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzdot) {
            return ((zzdot) obj).zzaxi();
        }
        return zzdqe.zzad((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.ads.zzdqu
    public final List<?> zzbai() {
        return Collections.unmodifiableList(this.zzhlw);
    }

    @Override // com.google.android.gms.internal.ads.zzdqu
    public final zzdqu zzbaj() {
        return zzaxd() ? new zzdsz(this) : this;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        zzaxf();
        return zzam(this.zzhlw.set(i, (String) obj));
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzaxf();
        Object objRemove = this.zzhlw.remove(i);
        this.modCount++;
        return zzam(objRemove);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, com.google.android.gms.internal.ads.zzdqk
    public final /* bridge */ /* synthetic */ boolean zzaxd() {
        return super.zzaxd();
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzaxf();
        this.zzhlw.add(i, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.android.gms.internal.ads.zzdqk
    public final /* synthetic */ zzdqk zzfm(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzhlw);
        return new zzdqv((ArrayList<Object>) arrayList);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzhlw.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzdot) {
            zzdot zzdotVar = (zzdot) obj;
            String strZzaxi = zzdotVar.zzaxi();
            if (zzdotVar.zzaxj()) {
                this.zzhlw.set(i, strZzaxi);
            }
            return strZzaxi;
        }
        byte[] bArr = (byte[]) obj;
        String strZzad = zzdqe.zzad(bArr);
        if (zzdqe.zzac(bArr)) {
            this.zzhlw.set(i, strZzad);
        }
        return strZzad;
    }

    static {
        zzdqv zzdqvVar = new zzdqv();
        zzhlu = zzdqvVar;
        zzdqvVar.zzaxe();
        zzhlv = zzhlu;
    }
}
