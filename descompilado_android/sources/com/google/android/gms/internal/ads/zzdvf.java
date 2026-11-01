package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzdvf<K, V, V2> implements zzdvi<Map<K, V2>> {
    private final Map<K, zzdvv<V>> zzhwy;

    zzdvf(Map<K, zzdvv<V>> map) {
        this.zzhwy = Collections.unmodifiableMap(map);
    }

    final Map<K, zzdvv<V>> zzbcz() {
        return this.zzhwy;
    }
}
