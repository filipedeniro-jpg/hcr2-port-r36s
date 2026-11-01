package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzdak<E> extends zzdaf<E> implements List<E>, RandomAccess {
    private static final zzdbe<Object> zzgon = new zzdam(zzdau.zzgpb, 0);

    public static <E> zzdak<E> zzaok() {
        return (zzdak<E>) zzdau.zzgpb;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    public final zzdak<E> zzaoi() {
        return this;
    }

    public static <E> zzdak<E> zzad(E e) {
        Object[] objArr = {e};
        for (int i = 0; i <= 0; i++) {
            zzdar.zzd(objArr[0], 0);
        }
        return zzb(objArr, 1);
    }

    public static <E> zzdak<E> zzf(Iterable<? extends E> iterable) {
        zzczx.checkNotNull(iterable);
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection instanceof zzdaf) {
                zzdak<E> zzdakVarZzaoi = ((zzdaf) collection).zzaoi();
                if (!zzdakVarZzaoi.zzaoj()) {
                    return zzdakVarZzaoi;
                }
                Object[] array = zzdakVarZzaoi.toArray();
                return zzb(array, array.length);
            }
            Object[] array2 = collection.toArray();
            int length = array2.length;
            for (int i = 0; i < length; i++) {
                zzdar.zzd(array2[i], i);
            }
            return zzb(array2, array2.length);
        }
        Iterator<? extends E> it = iterable.iterator();
        if (!it.hasNext()) {
            return (zzdak<E>) zzdau.zzgpb;
        }
        E next = it.next();
        if (!it.hasNext()) {
            return zzad(next);
        }
        zzdaj zzdajVar = (zzdaj) ((zzdaj) new zzdaj().zzab(next)).zza(it);
        zzdajVar.zzgom = true;
        return zzb(zzdajVar.zzgol, zzdajVar.size);
    }

    public static <E> zzdak<E> zzb(E[] eArr) {
        if (eArr.length == 0) {
            return (zzdak<E>) zzdau.zzgpb;
        }
        Object[] objArr = (Object[]) eArr.clone();
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            zzdar.zzd(objArr[i], i);
        }
        return zzb(objArr, objArr.length);
    }

    static <E> zzdak<E> zzc(Object[] objArr) {
        return zzb(objArr, objArr.length);
    }

    static <E> zzdak<E> zzb(Object[] objArr, int i) {
        if (i == 0) {
            return (zzdak<E>) zzdau.zzgpb;
        }
        return new zzdau(objArr, i);
    }

    zzdak() {
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    /* JADX INFO: renamed from: zzaoe */
    public final zzdbb<E> iterator() {
        return (zzdbe) listIterator();
    }

    @Override // java.util.List
    public int indexOf(@NullableDecl Object obj) {
        if (obj == null) {
            return -1;
        }
        if (this instanceof RandomAccess) {
            int size = size();
            int i = 0;
            if (obj == null) {
                while (i < size) {
                    if (get(i) == null) {
                        return i;
                    }
                    i++;
                }
            } else {
                while (i < size) {
                    if (obj.equals(get(i))) {
                        return i;
                    }
                    i++;
                }
            }
            return -1;
        }
        ListIterator<E> listIterator = listIterator();
        while (listIterator.hasNext()) {
            if (zzczv.equal(obj, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public int lastIndexOf(@NullableDecl Object obj) {
        if (obj == null) {
            return -1;
        }
        if (!(this instanceof RandomAccess)) {
            ListIterator<E> listIterator = listIterator(size());
            while (listIterator.hasPrevious()) {
                if (zzczv.equal(obj, listIterator.previous())) {
                    return listIterator.nextIndex();
                }
            }
            return -1;
        }
        if (obj == null) {
            for (int size = size() - 1; size >= 0; size--) {
                if (get(size) == null) {
                    return size;
                }
            }
        } else {
            for (int size2 = size() - 1; size2 >= 0; size2--) {
                if (obj.equals(get(size2))) {
                    return size2;
                }
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf, java.util.AbstractCollection, java.util.Collection
    public boolean contains(@NullableDecl Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: zzt, reason: merged with bridge method [inline-methods] */
    public zzdak<E> subList(int i, int i2) {
        zzczx.zzf(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return (zzdak<E>) zzdau.zzgpb;
        }
        return new zzdal(this, i, i3);
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final E remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    int zza(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(@NullableDecl Object obj) {
        if (obj == zzczx.checkNotNull(this)) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if ((this instanceof RandomAccess) && (list instanceof RandomAccess)) {
                    for (int i = 0; i < size; i++) {
                        if (zzczv.equal(get(i), list.get(i))) {
                        }
                    }
                    return true;
                }
                zzdak<E> zzdakVar = this;
                int size2 = zzdakVar.size();
                Iterator<E> it = list.iterator();
                int i2 = 0;
                while (i2 < size2) {
                    if (it.hasNext()) {
                        E e = zzdakVar.get(i2);
                        i2++;
                        if (zzczv.equal(e, it.next())) {
                        }
                    }
                }
                if (!it.hasNext()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        int size = size();
        int iHashCode = 1;
        for (int i = 0; i < size; i++) {
            iHashCode = (((iHashCode * 31) + get(i).hashCode()) ^ (-1)) ^ (-1);
        }
        return iHashCode;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.List
    public /* synthetic */ ListIterator listIterator(int i) {
        zzczx.zzs(i, size());
        if (isEmpty()) {
            return zzgon;
        }
        return new zzdam(this, i);
    }

    @Override // java.util.List
    public /* synthetic */ ListIterator listIterator() {
        return (zzdbe) listIterator(0);
    }
}
