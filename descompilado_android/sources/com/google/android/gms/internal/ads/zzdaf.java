package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzdaf<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] zzgok = new Object[0];

    zzdaf() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public abstract boolean contains(@NullableDecl Object obj);

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* JADX INFO: renamed from: zzaoe, reason: merged with bridge method [inline-methods] */
    public abstract zzdbb<E> iterator();

    Object[] zzaof() {
        return null;
    }

    abstract boolean zzaoj();

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray() {
        return toArray(zzgok);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final <T> T[] toArray(T[] tArr) {
        zzczx.checkNotNull(tArr);
        int size = size();
        if (tArr.length < size) {
            Object[] objArrZzaof = zzaof();
            if (objArrZzaof != null) {
                return (T[]) Arrays.copyOfRange(objArrZzaof, zzaog(), zzaoh(), tArr.getClass());
            }
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        zza(tArr, 0);
        return tArr;
    }

    int zzaog() {
        throw new UnsupportedOperationException();
    }

    int zzaoh() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public zzdak<E> zzaoi() {
        return isEmpty() ? zzdak.zzaok() : zzdak.zzc(toArray());
    }

    int zza(Object[] objArr, int i) {
        zzdbb zzdbbVar = (zzdbb) iterator();
        while (zzdbbVar.hasNext()) {
            objArr[i] = zzdbbVar.next();
            i++;
        }
        return i;
    }
}
