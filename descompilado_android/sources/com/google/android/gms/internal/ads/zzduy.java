package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzduy<E> extends AbstractList<E> {
    private static final zzdva zzcp = zzdva.zzn(zzduy.class);
    List<E> zzhwm;
    Iterator<E> zzhwn;

    public zzduy(List<E> list, Iterator<E> it) {
        this.zzhwm = list;
        this.zzhwn = it;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        if (this.zzhwm.size() > i) {
            return this.zzhwm.get(i);
        }
        if (this.zzhwn.hasNext()) {
            this.zzhwm.add(this.zzhwn.next());
            return get(i);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new zzdvb(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        zzcp.zzhr("potentially expensive size() call");
        zzcp.zzhr("blowup running");
        while (this.zzhwn.hasNext()) {
            this.zzhwm.add(this.zzhwn.next());
        }
        return this.zzhwm.size();
    }
}
