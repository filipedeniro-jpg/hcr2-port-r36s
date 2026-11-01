package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzdbm {
    private static final Logger zzgpo = Logger.getLogger(zzdbm.class.getName());
    private static final zza zzgqi;
    private volatile int remaining;
    private volatile Set<Throwable> seenExceptions = null;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    /* JADX INFO: loaded from: classes2.dex */
    static abstract class zza {
        private zza() {
        }

        abstract void zza(zzdbm zzdbmVar, Set<Throwable> set, Set<Throwable> set2);

        abstract int zzd(zzdbm zzdbmVar);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    /* JADX INFO: loaded from: classes2.dex */
    static final class zzb extends zza {
        private zzb() {
            super();
        }

        @Override // com.google.android.gms.internal.ads.zzdbm.zza
        final void zza(zzdbm zzdbmVar, Set<Throwable> set, Set<Throwable> set2) {
            synchronized (zzdbmVar) {
                if (zzdbmVar.seenExceptions == null) {
                    zzdbmVar.seenExceptions = set2;
                }
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdbm.zza
        final int zzd(zzdbm zzdbmVar) {
            int i;
            synchronized (zzdbmVar) {
                zzdbm.zzb(zzdbmVar);
                i = zzdbmVar.remaining;
            }
            return i;
        }
    }

    zzdbm(int i) {
        this.remaining = i;
    }

    abstract void zzg(Set<Throwable> set);

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    /* JADX INFO: loaded from: classes2.dex */
    static final class zzc extends zza {
        private final AtomicReferenceFieldUpdater<zzdbm, Set<Throwable>> zzgqm;
        private final AtomicIntegerFieldUpdater<zzdbm> zzgqn;

        zzc(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            super();
            this.zzgqm = atomicReferenceFieldUpdater;
            this.zzgqn = atomicIntegerFieldUpdater;
        }

        @Override // com.google.android.gms.internal.ads.zzdbm.zza
        final void zza(zzdbm zzdbmVar, Set<Throwable> set, Set<Throwable> set2) {
            this.zzgqm.compareAndSet(zzdbmVar, null, set2);
        }

        @Override // com.google.android.gms.internal.ads.zzdbm.zza
        final int zzd(zzdbm zzdbmVar) {
            return this.zzgqn.decrementAndGet(zzdbmVar);
        }
    }

    final Set<Throwable> zzaoz() {
        Set<Throwable> set = this.seenExceptions;
        if (set != null) {
            return set;
        }
        Set<Throwable> setNewSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        zzg(setNewSetFromMap);
        zzgqi.zza(this, null, setNewSetFromMap);
        return this.seenExceptions;
    }

    final int zzapa() {
        return zzgqi.zzd(this);
    }

    static /* synthetic */ int zzb(zzdbm zzdbmVar) {
        int i = zzdbmVar.remaining;
        zzdbmVar.remaining = i - 1;
        return i;
    }

    static {
        Throwable th;
        zza zzbVar;
        try {
            zzbVar = new zzc(AtomicReferenceFieldUpdater.newUpdater(zzdbm.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(zzdbm.class, "remaining"));
            th = null;
        } catch (Throwable th2) {
            th = th2;
            zzbVar = new zzb();
        }
        zzgqi = zzbVar;
        if (th != null) {
            zzgpo.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFutureState", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }
}
