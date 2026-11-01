package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzdao<K, V> implements Serializable, Map<K, V> {
    private static final Map.Entry<?, ?>[] zzgor = new Map.Entry[0];
    private transient zzdan<Map.Entry<K, V>> zzgos;
    private transient zzdan<K> zzgot;
    private transient zzdaf<V> zzgou;

    public static <K, V> zzdao<K, V> zza(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        zzdae.zzb(k, v);
        zzdae.zzb(k2, v2);
        zzdae.zzb(k3, v3);
        zzdae.zzb(k4, v4);
        zzdae.zzb(k5, v5);
        return zzdat.zzc(5, new Object[]{k, v, k2, v2, k3, v3, k4, v4, k5, v5});
    }

    @Override // java.util.Map
    public abstract V get(@NullableDecl Object obj);

    abstract zzdan<Map.Entry<K, V>> zzaon();

    abstract zzdan<K> zzaoo();

    abstract zzdaf<V> zzaop();

    zzdao() {
    }

    @Override // java.util.Map
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        return ((zzdaf) values()).contains(obj);
    }

    @Override // java.util.Map
    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    @Override // java.util.Map
    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public int hashCode() {
        return zzdaz.zzf((zzdan) entrySet());
    }

    public String toString() {
        int size = size();
        zzdae.zzf(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) << 3, 1073741824L));
        sb.append('{');
        boolean z = true;
        for (Map.Entry<K, V> entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Map
    public /* synthetic */ Set entrySet() {
        zzdan<Map.Entry<K, V>> zzdanVar = this.zzgos;
        if (zzdanVar != null) {
            return zzdanVar;
        }
        zzdan<Map.Entry<K, V>> zzdanVarZzaon = zzaon();
        this.zzgos = zzdanVarZzaon;
        return zzdanVarZzaon;
    }

    @Override // java.util.Map
    public /* synthetic */ Collection values() {
        zzdaf<V> zzdafVar = this.zzgou;
        if (zzdafVar != null) {
            return zzdafVar;
        }
        zzdaf<V> zzdafVarZzaop = zzaop();
        this.zzgou = zzdafVarZzaop;
        return zzdafVarZzaop;
    }

    @Override // java.util.Map
    public /* synthetic */ Set keySet() {
        zzdan<K> zzdanVar = this.zzgot;
        if (zzdanVar != null) {
            return zzdanVar;
        }
        zzdan<K> zzdanVarZzaoo = zzaoo();
        this.zzgot = zzdanVarZzaoo;
        return zzdanVarZzaoo;
    }
}
