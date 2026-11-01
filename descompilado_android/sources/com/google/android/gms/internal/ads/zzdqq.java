package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdqq<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzdqo> zzhlp;

    private zzdqq(Map.Entry<K, zzdqo> entry) {
        this.zzhlp = entry;
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.zzhlp.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.zzhlp.getValue() == null) {
            return null;
        }
        return zzdqo.zzbaf();
    }

    public final zzdqo zzbah() {
        return this.zzhlp.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (!(obj instanceof zzdrn)) {
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }
        return this.zzhlp.getValue().zzq((zzdrn) obj);
    }
}
