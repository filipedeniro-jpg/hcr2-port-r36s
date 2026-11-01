package com.google.android.gms.internal.firebase_remote_config;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class zzik<K, V> extends LinkedHashMap<K, V> {
    private static final zzik zzvm;
    private boolean zzol;

    private zzik() {
        this.zzol = true;
    }

    private zzik(Map<K, V> map) {
        super(map);
        this.zzol = true;
    }

    public static <K, V> zzik<K, V> zzhv() {
        return zzvm;
    }

    public final void zza(zzik<K, V> zzikVar) {
        zzhx();
        if (zzikVar.isEmpty()) {
            return;
        }
        putAll(zzikVar);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzhx();
        super.clear();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        zzhx();
        zzhm.checkNotNull(k);
        zzhm.checkNotNull(v);
        return (V) super.put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        zzhx();
        for (K k : map.keySet()) {
            zzhm.checkNotNull(k);
            zzhm.checkNotNull(map.get(k));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        zzhx();
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

    private static int zzv(Object obj) {
        if (obj instanceof byte[]) {
            return zzhm.hashCode((byte[]) obj);
        }
        if (obj instanceof zzhl) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int iZzv = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            iZzv += zzv(entry.getValue()) ^ zzv(entry.getKey());
        }
        return iZzv;
    }

    public final zzik<K, V> zzhw() {
        return isEmpty() ? new zzik<>() : new zzik<>(this);
    }

    public final void zzes() {
        this.zzol = false;
    }

    public final boolean isMutable() {
        return this.zzol;
    }

    private final void zzhx() {
        if (!this.zzol) {
            throw new UnsupportedOperationException();
        }
    }

    static {
        zzik zzikVar = new zzik();
        zzvm = zzikVar;
        zzikVar.zzol = false;
    }
}
