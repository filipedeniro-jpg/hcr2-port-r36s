package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: Add missing generic type declarations: [E] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdvb<E> implements Iterator<E> {
    private int pos = 0;
    private final /* synthetic */ zzduy zzhwo;

    zzdvb(zzduy zzduyVar) {
        this.zzhwo = zzduyVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.pos < this.zzhwo.zzhwm.size() || this.zzhwo.zzhwn.hasNext();
    }

    @Override // java.util.Iterator
    public final E next() {
        while (this.pos >= this.zzhwo.zzhwm.size()) {
            this.zzhwo.zzhwm.add(this.zzhwo.zzhwn.next());
        }
        List<E> list = this.zzhwo.zzhwm;
        int i = this.pos;
        this.pos = i + 1;
        return list.get(i);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
