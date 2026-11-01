package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzdah<E> {
    zzdah() {
    }

    public abstract zzdah<E> zzab(E e);

    public zzdah<E> zze(Iterable<? extends E> iterable) {
        Iterator<? extends E> it = iterable.iterator();
        while (it.hasNext()) {
            zzab(it.next());
        }
        return this;
    }

    public zzdah<E> zza(Iterator<? extends E> it) {
        while (it.hasNext()) {
            zzab(it.next());
        }
        return this;
    }
}
