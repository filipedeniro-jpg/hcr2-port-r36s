package com.google.android.gms.internal.firebase_remote_config;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class zzbl<K, V> extends AbstractMap<K, V> implements Cloneable {
    int size;
    private Object[] zzed;

    final class zzb extends AbstractSet<Map.Entry<K, V>> {
        zzb() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator<Map.Entry<K, V>> iterator() {
            return new zzc();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return zzbl.this.size;
        }
    }

    final class zzc implements Iterator<Map.Entry<K, V>> {
        private boolean zzee;
        private int zzef;

        zzc() {
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.zzef < zzbl.this.size;
        }

        @Override // java.util.Iterator
        public final void remove() {
            int i = this.zzef - 1;
            if (this.zzee || i < 0) {
                throw new IllegalArgumentException();
            }
            zzbl.this.remove(i);
            this.zzef--;
            this.zzee = true;
        }

        @Override // java.util.Iterator
        public final /* synthetic */ Object next() {
            int i = this.zzef;
            if (i == zzbl.this.size) {
                throw new NoSuchElementException();
            }
            this.zzef++;
            this.zzee = false;
            return new zza(i);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.size;
    }

    final class zza implements Map.Entry<K, V> {
        private int index;

        zza(int i) {
            this.index = i;
        }

        @Override // java.util.Map.Entry
        public final K getKey() {
            return (K) zzbl.this.zzf(this.index);
        }

        @Override // java.util.Map.Entry
        public final V getValue() {
            return (V) zzbl.this.zzg(this.index);
        }

        @Override // java.util.Map.Entry
        public final V setValue(V v) {
            return (V) zzbl.this.set(this.index, v);
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            Object key = getKey();
            Object value = getValue();
            return (key != null ? key.hashCode() : 0) ^ (value != null ? value.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = getKey();
            Object key2 = entry.getKey();
            if (key == key2 || (key != null && key.equals(key2))) {
                Object value = getValue();
                Object value2 = entry.getValue();
                if (value == value2 || (value != null && value.equals(value2))) {
                    return true;
                }
            }
            return false;
        }
    }

    public final K zzf(int i) {
        if (i < 0 || i >= this.size) {
            return null;
        }
        return (K) this.zzed[i << 1];
    }

    public final V zzg(int i) {
        if (i < 0 || i >= this.size) {
            return null;
        }
        return zzh((i << 1) + 1);
    }

    public final V set(int i, V v) {
        int i2 = this.size;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = (i << 1) + 1;
        V vZzh = zzh(i3);
        this.zzed[i3] = v;
        return vZzh;
    }

    public final V remove(int i) {
        return zzi(i << 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return -2 != zze(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(Object obj) {
        return zzh(zze(obj) + 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        int iZze = zze(k) >> 1;
        if (iZze == -1) {
            iZze = this.size;
        }
        if (iZze < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i = iZze + 1;
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] objArr = this.zzed;
        int i2 = i << 1;
        int length = objArr == null ? 0 : objArr.length;
        if (i2 > length) {
            int i3 = ((length / 2) * 3) + 1;
            if (i3 % 2 != 0) {
                i3++;
            }
            if (i3 < i2) {
                i3 = i2;
            }
            if (i3 == 0) {
                this.zzed = null;
            } else {
                int i4 = this.size;
                Object[] objArr2 = this.zzed;
                if (i4 == 0 || i3 != objArr2.length) {
                    Object[] objArr3 = new Object[i3];
                    this.zzed = objArr3;
                    if (i4 != 0) {
                        System.arraycopy(objArr2, 0, objArr3, 0, i4 << 1);
                    }
                }
            }
        }
        int i5 = iZze << 1;
        V vZzh = zzh(i5 + 1);
        zza(i5, k, v);
        if (i > this.size) {
            this.size = i;
        }
        return vZzh;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        return zzi(zze(obj));
    }

    private final void zza(int i, K k, V v) {
        Object[] objArr = this.zzed;
        objArr[i] = k;
        objArr[i + 1] = v;
    }

    private final V zzh(int i) {
        if (i < 0) {
            return null;
        }
        return (V) this.zzed[i];
    }

    private final int zze(Object obj) {
        int i = this.size << 1;
        Object[] objArr = this.zzed;
        for (int i2 = 0; i2 < i; i2 += 2) {
            Object obj2 = objArr[i2];
            if (obj == null) {
                if (obj2 == null) {
                    return i2;
                }
            } else {
                if (obj.equals(obj2)) {
                    return i2;
                }
            }
        }
        return -2;
    }

    private final V zzi(int i) {
        int i2 = this.size << 1;
        if (i < 0 || i >= i2) {
            return null;
        }
        V vZzh = zzh(i + 1);
        Object[] objArr = this.zzed;
        int i3 = (i2 - i) - 2;
        if (i3 != 0) {
            System.arraycopy(objArr, i + 2, objArr, i, i3);
        }
        this.size--;
        zza(i2 - 2, null, null);
        return vZzh;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.size = 0;
        this.zzed = null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        int i = this.size << 1;
        Object[] objArr = this.zzed;
        for (int i2 = 1; i2 < i; i2 += 2) {
            Object obj2 = objArr[i2];
            if (obj == null) {
                if (obj2 == null) {
                    return true;
                }
            } else {
                if (obj.equals(obj2)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return new zzb();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.AbstractMap
    /* JADX INFO: renamed from: zzbs, reason: merged with bridge method [inline-methods] */
    public final zzbl<K, V> clone() {
        try {
            zzbl<K, V> zzblVar = (zzbl) super.clone();
            Object[] objArr = this.zzed;
            if (objArr != null) {
                int length = objArr.length;
                Object[] objArr2 = new Object[length];
                zzblVar.zzed = objArr2;
                System.arraycopy(objArr, 0, objArr2, 0, length);
            }
            return zzblVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }
}
