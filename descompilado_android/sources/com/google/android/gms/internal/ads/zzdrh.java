package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdrh<K, V> extends LinkedHashMap<K, V> {
    private static final zzdrh zzhmi;
    private boolean zzhfd;

    private zzdrh() {
        this.zzhfd = true;
    }

    private zzdrh(Map<K, V> map) {
        super(map);
        this.zzhfd = true;
    }

    public static <K, V> zzdrh<K, V> zzban() {
        return zzhmi;
    }

    public final void zza(zzdrh<K, V> zzdrhVar) {
        zzbap();
        if (zzdrhVar.isEmpty()) {
            return;
        }
        putAll(zzdrhVar);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzbap();
        super.clear();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        zzbap();
        zzdqe.checkNotNull(k);
        zzdqe.checkNotNull(v);
        return (V) super.put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        zzbap();
        for (K k : map.keySet()) {
            zzdqe.checkNotNull(k);
            zzdqe.checkNotNull(map.get(k));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        zzbap();
        return (V) super.remove(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        boolean z;
        boolean zEquals;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this == map) {
                z = true;
            } else {
                if (size() == map.size()) {
                    Iterator<Map.Entry<K, V>> it = entrySet().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Map.Entry<K, V> next = it.next();
                            if (map.containsKey(next.getKey())) {
                                V value = next.getValue();
                                Object obj2 = map.get(next.getKey());
                                if ((value instanceof byte[]) && (obj2 instanceof byte[])) {
                                    zEquals = Arrays.equals((byte[]) value, (byte[]) obj2);
                                } else {
                                    zEquals = value.equals(obj2);
                                }
                                if (!zEquals) {
                                }
                            }
                        } else {
                            z = true;
                        }
                    }
                }
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    private static int zzat(Object obj) {
        if (obj instanceof byte[]) {
            return zzdqe.hashCode((byte[]) obj);
        }
        if (obj instanceof zzdqh) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int iZzat = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            iZzat += zzat(entry.getValue()) ^ zzat(entry.getKey());
        }
        return iZzat;
    }

    public final zzdrh<K, V> zzbao() {
        return isEmpty() ? new zzdrh<>() : new zzdrh<>(this);
    }

    public final void zzaxe() {
        this.zzhfd = false;
    }

    public final boolean isMutable() {
        return this.zzhfd;
    }

    private final void zzbap() {
        if (!this.zzhfd) {
            throw new UnsupportedOperationException();
        }
    }

    static {
        zzdrh zzdrhVar = new zzdrh();
        zzhmi = zzdrhVar;
        zzdrhVar.zzhfd = false;
    }
}
