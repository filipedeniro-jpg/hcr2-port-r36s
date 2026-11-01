package com.google.android.gms.internal.firebase_remote_config;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: loaded from: classes2.dex */
final class zzjm<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzjk zzwy;
    private Iterator<Map.Entry<K, V>> zzwz;

    private zzjm(zzjk zzjkVar) {
        this.zzwy = zzjkVar;
        this.pos = this.zzwy.zzwt.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzwy.zzwt.size()) || zziw().hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zziw() {
        if (this.zzwz == null) {
            this.zzwz = this.zzwy.zzww.entrySet().iterator();
        }
        return this.zzwz;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        if (zziw().hasNext()) {
            return zziw().next();
        }
        List list = this.zzwy.zzwt;
        int i = this.pos - 1;
        this.pos = i;
        return (Map.Entry) list.get(i);
    }

    /* synthetic */ zzjm(zzjk zzjkVar, zzjj zzjjVar) {
        this(zzjkVar);
    }
}
