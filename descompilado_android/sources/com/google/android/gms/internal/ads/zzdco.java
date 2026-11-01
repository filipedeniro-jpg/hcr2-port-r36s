package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzdco<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable zzgrc = new zzdcq();
    private static final Runnable zzgrd = new zzdcq();
    private static final Runnable zzgre = new zzdcq();

    zzdco() {
    }

    abstract boolean isDone();

    abstract T zzapb() throws Exception;

    abstract String zzapc();

    abstract void zzb(@NullableDecl T t, @NullableDecl Throwable th);

    @Override // java.lang.Runnable
    public final void run() {
        T tZzapb;
        Thread threadCurrentThread = Thread.currentThread();
        if (compareAndSet(null, threadCurrentThread)) {
            boolean z = !isDone();
            if (z) {
                try {
                    tZzapb = zzapb();
                } catch (Throwable th) {
                    if (!compareAndSet(threadCurrentThread, zzgrc)) {
                        Runnable runnable = get();
                        boolean z2 = false;
                        int i = 0;
                        while (true) {
                            if (runnable != zzgrd && runnable != zzgre) {
                                break;
                            }
                            i++;
                            if (i > 1000) {
                                Runnable runnable2 = zzgre;
                                if (runnable == runnable2 || compareAndSet(zzgrd, runnable2)) {
                                    boolean z3 = Thread.interrupted() || z2;
                                    LockSupport.park(this);
                                    z2 = z3;
                                }
                            } else {
                                Thread.yield();
                            }
                            runnable = get();
                        }
                        if (z2) {
                            threadCurrentThread.interrupt();
                        }
                    }
                    if (z) {
                        zzb(null, null);
                    }
                    throw th;
                }
            } else {
                tZzapb = null;
            }
            if (!compareAndSet(threadCurrentThread, zzgrc)) {
                Runnable runnable3 = get();
                boolean z4 = false;
                int i2 = 0;
                while (true) {
                    if (runnable3 != zzgrd && runnable3 != zzgre) {
                        break;
                    }
                    i2++;
                    if (i2 > 1000) {
                        Runnable runnable4 = zzgre;
                        if (runnable3 == runnable4 || compareAndSet(zzgrd, runnable4)) {
                            boolean z5 = Thread.interrupted() || z4;
                            LockSupport.park(this);
                            z4 = z5;
                        }
                    } else {
                        Thread.yield();
                    }
                    runnable3 = get();
                }
                if (z4) {
                    threadCurrentThread.interrupt();
                }
            }
            if (z) {
                zzb(tZzapb, null);
            }
        }
    }

    final void interruptTask() {
        Runnable runnable = get();
        if ((runnable instanceof Thread) && compareAndSet(runnable, zzgrd)) {
            try {
                ((Thread) runnable).interrupt();
            } finally {
                if (getAndSet(zzgrc) == zzgre) {
                    LockSupport.unpark((Thread) runnable);
                }
            }
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String string;
        Runnable runnable = get();
        if (runnable == zzgrc) {
            string = "running=[DONE]";
        } else if (runnable == zzgrd) {
            string = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            String name = ((Thread) runnable).getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 21);
            sb.append("running=[RUNNING ON ");
            sb.append(name);
            sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
            string = sb.toString();
        } else {
            string = "running=[NOT STARTED YET]";
        }
        String strZzapc = zzapc();
        StringBuilder sb2 = new StringBuilder(String.valueOf(string).length() + 2 + String.valueOf(strZzapc).length());
        sb2.append(string);
        sb2.append(", ");
        sb2.append(strZzapc);
        return sb2.toString();
    }
}
