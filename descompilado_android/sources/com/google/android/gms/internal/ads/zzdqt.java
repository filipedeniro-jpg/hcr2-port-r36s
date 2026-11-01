package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdqt<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzhlt;

    public zzdqt(Iterator<Map.Entry<K, Object>> it) {
        this.zzhlt = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzhlt.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.zzhlt.remove();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        Map.Entry<K, Object> next = this.zzhlt.next();
        return next.getValue() instanceof zzdqo ? new zzdqq(next) : next;
    }
}
