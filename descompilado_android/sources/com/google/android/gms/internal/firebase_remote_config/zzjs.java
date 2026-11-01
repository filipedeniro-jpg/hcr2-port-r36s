package com.google.android.gms.internal.firebase_remote_config;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: loaded from: classes2.dex */
final class zzjs<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzjk zzwy;
    private Iterator<Map.Entry<K, V>> zzwz;
    private boolean zzxd;

    private zzjs(zzjk zzjkVar) {
        this.zzwy = zzjkVar;
        this.pos = -1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.pos + 1 < this.zzwy.zzwt.size() || (!this.zzwy.zzwu.isEmpty() && zziw().hasNext());
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.zzxd) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzxd = false;
        this.zzwy.zziu();
        if (this.pos < this.zzwy.zzwt.size()) {
            zzjk zzjkVar = this.zzwy;
            int i = this.pos;
            this.pos = i - 1;
            zzjkVar.zzbn(i);
            return;
        }
        zziw().remove();
    }

    private final Iterator<Map.Entry<K, V>> zziw() {
        if (this.zzwz == null) {
            this.zzwz = this.zzwy.zzwu.entrySet().iterator();
        }
        return this.zzwz;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        this.zzxd = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i >= this.zzwy.zzwt.size()) {
            return zziw().next();
        }
        return (Map.Entry) this.zzwy.zzwt.get(this.pos);
    }

    /* synthetic */ zzjs(zzjk zzjkVar, zzjj zzjjVar) {
        this(zzjkVar);
    }
}
