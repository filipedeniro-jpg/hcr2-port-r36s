package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdsp<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzdsh zzhnt;
    private Iterator<Map.Entry<K, V>> zzhnu;
    private boolean zzhny;

    private zzdsp(zzdsh zzdshVar) {
        this.zzhnt = zzdshVar;
        this.pos = -1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.pos + 1 < this.zzhnt.zzhno.size() || (!this.zzhnt.zzhnp.isEmpty() && zzbbo().hasNext());
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.zzhny) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzhny = false;
        this.zzhnt.zzbbm();
        if (this.pos < this.zzhnt.zzhno.size()) {
            zzdsh zzdshVar = this.zzhnt;
            int i = this.pos;
            this.pos = i - 1;
            zzdshVar.zzhb(i);
            return;
        }
        zzbbo().remove();
    }

    private final Iterator<Map.Entry<K, V>> zzbbo() {
        if (this.zzhnu == null) {
            this.zzhnu = this.zzhnt.zzhnp.entrySet().iterator();
        }
        return this.zzhnu;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        this.zzhny = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i >= this.zzhnt.zzhno.size()) {
            return zzbbo().next();
        }
        return (Map.Entry) this.zzhnt.zzhno.get(this.pos);
    }

    /* synthetic */ zzdsp(zzdsh zzdshVar, zzdsg zzdsgVar) {
        this(zzdshVar);
    }
}
