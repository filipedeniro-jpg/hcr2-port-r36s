package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdsj<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzdsh zzhnt;
    private Iterator<Map.Entry<K, V>> zzhnu;

    private zzdsj(zzdsh zzdshVar) {
        this.zzhnt = zzdshVar;
        this.pos = this.zzhnt.zzhno.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzhnt.zzhno.size()) || zzbbo().hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzbbo() {
        if (this.zzhnu == null) {
            this.zzhnu = this.zzhnt.zzhnr.entrySet().iterator();
        }
        return this.zzhnu;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        if (zzbbo().hasNext()) {
            return zzbbo().next();
        }
        List list = this.zzhnt.zzhno;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) list.get(i);
    }

    /* synthetic */ zzdsj(zzdsh zzdshVar, zzdsg zzdsgVar) {
        this(zzdshVar);
    }
}
