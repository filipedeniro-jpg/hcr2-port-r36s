package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdvk<K, V> extends zzdvf<K, V, V> {
    private static final zzdvv<Map<Object, Object>> zzhxc = zzdvl.zzbb(Collections.emptyMap());

    public static <K, V> zzdvm<K, V> zzhp(int i) {
        return new zzdvm<>(i);
    }

    private zzdvk(Map<K, zzdvv<V>> map) {
        super(map);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        LinkedHashMap linkedHashMapZzhn = zzdvh.zzhn(zzbcz().size());
        for (Map.Entry<K, zzdvv<V>> entry : zzbcz().entrySet()) {
            linkedHashMapZzhn.put(entry.getKey(), entry.getValue().get());
        }
        return Collections.unmodifiableMap(linkedHashMapZzhn);
    }
}
