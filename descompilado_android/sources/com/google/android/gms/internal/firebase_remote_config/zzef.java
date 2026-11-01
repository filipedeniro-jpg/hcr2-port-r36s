package com.google.android.gms.internal.firebase_remote_config;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class zzef extends WeakReference<Throwable> {
    private final int zziy;

    public zzef(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.zziy = System.identityHashCode(th);
    }

    public final int hashCode() {
        return this.zziy;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            zzef zzefVar = (zzef) obj;
            if (this.zziy == zzefVar.zziy && get() == zzefVar.get()) {
                return true;
            }
        }
        return false;
    }
}
