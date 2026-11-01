package com.google.android.gms.internal.drive;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: loaded from: classes2.dex */
final class zzmk<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzvj;
    private final /* synthetic */ zzmi zzvk;

    private zzmk(zzmi zzmiVar) {
        this.zzvk = zzmiVar;
        this.pos = this.zzvk.zzve.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzvk.zzve.size()) || zzew().hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzew() {
        if (this.zzvj == null) {
            this.zzvj = this.zzvk.zzvh.entrySet().iterator();
        }
        return this.zzvj;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        if (zzew().hasNext()) {
            return zzew().next();
        }
        List list = this.zzvk.zzve;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) list.get(i);
    }

    /* synthetic */ zzmk(zzmi zzmiVar, zzmj zzmjVar) {
        this(zzmiVar);
    }
}
