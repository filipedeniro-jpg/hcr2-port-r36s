package com.google.android.gms.internal.ads;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdog extends WeakReference<Throwable> {
    private final int zzhev;

    public zzdog(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zzhev = System.identityHashCode(th);
    }

    public final int hashCode() {
        return this.zzhev;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            zzdog zzdogVar = (zzdog) obj;
            if (this.zzhev == zzdogVar.zzhev && get() == zzdogVar.get()) {
                return true;
            }
        }
        return false;
    }
}
