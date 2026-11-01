package com.facebook.ads.redexgen.X;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7k, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03137k<K, V> extends C03127j<K, V> implements Map<K, V> {
    public AbstractC03107h<K, V> B;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7k != com.facebook.ads.internal.androidx.support.v4.util.ArrayMap<K, V> */
    private AbstractC03107h<K, V> B() {
        if (this.B == null) {
            this.B = new AbstractC03107h<K, V>() { // from class: com.facebook.ads.redexgen.X.7i
                @Override // com.facebook.ads.redexgen.X.AbstractC03107h
                public final void A() {
                    this.B.clear();
                }

                @Override // com.facebook.ads.redexgen.X.AbstractC03107h
                public final Object B(int index, int offset) {
                    return ((C03127j) this.B).B[(index << 1) + offset];
                }

                @Override // com.facebook.ads.redexgen.X.AbstractC03107h
                public final Map<K, V> C() {
                    return this.B;
                }

                @Override // com.facebook.ads.redexgen.X.AbstractC03107h
                public final int D() {
                    return this.B.D;
                }

                @Override // com.facebook.ads.redexgen.X.AbstractC03107h
                public final int E(Object key) {
                    return this.B.B(key);
                }

                @Override // com.facebook.ads.redexgen.X.AbstractC03107h
                public final int F(Object value) {
                    return this.B.C(value);
                }

                @Override // com.facebook.ads.redexgen.X.AbstractC03107h
                public final void G(K k, V v) {
                    this.B.put(k, v);
                }

                @Override // com.facebook.ads.redexgen.X.AbstractC03107h
                public final void H(int index) {
                    this.B.E(index);
                }

                @Override // com.facebook.ads.redexgen.X.AbstractC03107h
                public final V I(int index, V v) {
                    V value = this.B.F(index, v);
                    return value;
                }
            };
        }
        return this.B;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7k != com.facebook.ads.internal.androidx.support.v4.util.ArrayMap<K, V> */
    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return B().J();
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7k != com.facebook.ads.internal.androidx.support.v4.util.ArrayMap<K, V> */
    @Override // java.util.Map
    public final Set<K> keySet() {
        return B().K();
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7k != com.facebook.ads.internal.androidx.support.v4.util.ArrayMap<K, V> */
    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        A(this.D + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7k != com.facebook.ads.internal.androidx.support.v4.util.ArrayMap<K, V> */
    @Override // java.util.Map
    public final Collection<V> values() {
        return B().L();
    }
}
