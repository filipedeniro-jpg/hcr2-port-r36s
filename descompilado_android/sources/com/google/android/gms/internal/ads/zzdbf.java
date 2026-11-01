package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public class zzdbf<V> extends zzddh implements zzdcp<V> {
    private static final Object NULL;
    private static final zza zzgpp;

    @NullableDecl
    private volatile zzd listeners;

    @NullableDecl
    private volatile Object value;

    @NullableDecl
    private volatile zzk waiters;
    private static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    private static final Logger zzgpo = Logger.getLogger(zzdbf.class.getName());

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    /* JADX INFO: loaded from: classes2.dex */
    static abstract class zza {
        private zza() {
        }

        abstract void zza(zzk zzkVar, zzk zzkVar2);

        abstract void zza(zzk zzkVar, Thread thread);

        abstract boolean zza(zzdbf<?> zzdbfVar, zzd zzdVar, zzd zzdVar2);

        abstract boolean zza(zzdbf<?> zzdbfVar, zzk zzkVar, zzk zzkVar2);

        abstract boolean zza(zzdbf<?> zzdbfVar, Object obj, Object obj2);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    /* JADX INFO: loaded from: classes2.dex */
    static final class zzb {
        static final zzb zzgpq = new zzb(new Throwable("Failure occurred while trying to finish a future.") { // from class: com.google.android.gms.internal.ads.zzdbf.zzb.1
            @Override // java.lang.Throwable
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable exception;

        zzb(Throwable th) {
            this.exception = (Throwable) zzczx.checkNotNull(th);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    static final class zzd {
        static final zzd zzgpt = new zzd(null, null);
        final Executor executor;

        @NullableDecl
        zzd next;
        final Runnable task;

        zzd(Runnable runnable, Executor executor) {
            this.task = runnable;
            this.executor = executor;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    interface zzg<V> extends zzdcp<V> {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    /* JADX INFO: loaded from: classes2.dex */
    static final class zzh extends zza {
        private zzh() {
            super();
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final void zza(zzk zzkVar, Thread thread) {
            zzkVar.thread = thread;
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final void zza(zzk zzkVar, zzk zzkVar2) {
            zzkVar.next = zzkVar2;
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final boolean zza(zzdbf<?> zzdbfVar, zzk zzkVar, zzk zzkVar2) {
            synchronized (zzdbfVar) {
                if (((zzdbf) zzdbfVar).waiters != zzkVar) {
                    return false;
                }
                ((zzdbf) zzdbfVar).waiters = zzkVar2;
                return true;
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final boolean zza(zzdbf<?> zzdbfVar, zzd zzdVar, zzd zzdVar2) {
            synchronized (zzdbfVar) {
                if (((zzdbf) zzdbfVar).listeners != zzdVar) {
                    return false;
                }
                ((zzdbf) zzdbfVar).listeners = zzdVar2;
                return true;
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final boolean zza(zzdbf<?> zzdbfVar, Object obj, Object obj2) {
            synchronized (zzdbfVar) {
                if (((zzdbf) zzdbfVar).value != obj) {
                    return false;
                }
                ((zzdbf) zzdbfVar).value = obj2;
                return true;
            }
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    /* JADX INFO: loaded from: classes2.dex */
    static final class zzi extends zza {
        static final Unsafe zzgpv;
        static final long zzgpw;
        static final long zzgpx;
        static final long zzgpy;
        static final long zzgpz;
        static final long zzgqa;

        private zzi() {
            super();
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final void zza(zzk zzkVar, Thread thread) {
            zzgpv.putObject(zzkVar, zzgpz, thread);
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final void zza(zzk zzkVar, zzk zzkVar2) {
            zzgpv.putObject(zzkVar, zzgqa, zzkVar2);
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final boolean zza(zzdbf<?> zzdbfVar, zzk zzkVar, zzk zzkVar2) {
            return zzgpv.compareAndSwapObject(zzdbfVar, zzgpx, zzkVar, zzkVar2);
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final boolean zza(zzdbf<?> zzdbfVar, zzd zzdVar, zzd zzdVar2) {
            return zzgpv.compareAndSwapObject(zzdbfVar, zzgpw, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final boolean zza(zzdbf<?> zzdbfVar, Object obj, Object obj2) {
            return zzgpv.compareAndSwapObject(zzdbfVar, zzgpy, obj, obj2);
        }

        static {
            Unsafe unsafe;
            try {
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (PrivilegedActionException e) {
                    throw new RuntimeException("Could not initialize intrinsics", e.getCause());
                }
            } catch (SecurityException unused) {
                unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.android.gms.internal.ads.zzdbf.zzi.1
                    @Override // java.security.PrivilegedExceptionAction
                    public /* synthetic */ Unsafe run() throws Exception {
                        for (Field field : Unsafe.class.getDeclaredFields()) {
                            field.setAccessible(true);
                            Object obj = field.get(null);
                            if (Unsafe.class.isInstance(obj)) {
                                return (Unsafe) Unsafe.class.cast(obj);
                            }
                        }
                        throw new NoSuchFieldError("the Unsafe");
                    }
                });
            }
            try {
                zzgpx = unsafe.objectFieldOffset(zzdbf.class.getDeclaredField("waiters"));
                zzgpw = unsafe.objectFieldOffset(zzdbf.class.getDeclaredField("listeners"));
                zzgpy = unsafe.objectFieldOffset(zzdbf.class.getDeclaredField("value"));
                zzgpz = unsafe.objectFieldOffset(zzk.class.getDeclaredField("thread"));
                zzgqa = unsafe.objectFieldOffset(zzk.class.getDeclaredField("next"));
                zzgpv = unsafe;
            } catch (Exception e2) {
                zzdac.zzf(e2);
                throw new RuntimeException(e2);
            }
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    static abstract class zzj<V> extends zzdbf<V> implements zzg<V> {
        zzj() {
        }

        @Override // com.google.android.gms.internal.ads.zzdbf, java.util.concurrent.Future
        public final V get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
            return (V) super.get(j, timeUnit);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    static final class zzk {
        static final zzk zzgqb = new zzk(false);

        @NullableDecl
        volatile zzk next;

        @NullableDecl
        volatile Thread thread;

        private zzk(boolean z) {
        }

        zzk() {
            zzdbf.zzgpp.zza(this, Thread.currentThread());
        }

        final void zzb(zzk zzkVar) {
            zzdbf.zzgpp.zza(this, zzkVar);
        }
    }

    private final void zza(zzk zzkVar) {
        zzkVar.thread = null;
        while (true) {
            zzk zzkVar2 = this.waiters;
            if (zzkVar2 == zzk.zzgqb) {
                return;
            }
            zzk zzkVar3 = null;
            while (zzkVar2 != null) {
                zzk zzkVar4 = zzkVar2.next;
                if (zzkVar2.thread != null) {
                    zzkVar3 = zzkVar2;
                } else if (zzkVar3 != null) {
                    zzkVar3.next = zzkVar4;
                    if (zzkVar3.thread == null) {
                    }
                } else if (zzgpp.zza((zzdbf<?>) this, zzkVar2, zzkVar4)) {
                }
                zzkVar2 = zzkVar4;
            }
            return;
        }
    }

    protected void afterDone() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    static final class zzc {
        static final zzc zzgpr;
        static final zzc zzgps;

        @NullableDecl
        final Throwable cause;
        final boolean wasInterrupted;

        zzc(boolean z, @NullableDecl Throwable th) {
            this.wasInterrupted = z;
            this.cause = th;
        }

        static {
            if (zzdbf.GENERATE_CANCELLATION_CAUSES) {
                zzgps = null;
                zzgpr = null;
            } else {
                zzgps = new zzc(false, null);
                zzgpr = new zzc(true, null);
            }
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    /* JADX INFO: loaded from: classes2.dex */
    static final class zze<V> implements Runnable {
        final zzdcp<? extends V> future;
        final zzdbf<V> zzgpu;

        zze(zzdbf<V> zzdbfVar, zzdcp<? extends V> zzdcpVar) {
            this.zzgpu = zzdbfVar;
            this.future = zzdcpVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (((zzdbf) this.zzgpu).value != this) {
                return;
            }
            if (zzdbf.zzgpp.zza((zzdbf<?>) this.zzgpu, (Object) this, zzdbf.getFutureValue(this.future))) {
                zzdbf.zza((zzdbf<?>) this.zzgpu);
            }
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    /* JADX INFO: loaded from: classes2.dex */
    static final class zzf extends zza {
        final AtomicReferenceFieldUpdater<zzdbf, zzd> listenersUpdater;
        final AtomicReferenceFieldUpdater<zzdbf, Object> valueUpdater;
        final AtomicReferenceFieldUpdater<zzk, zzk> waiterNextUpdater;
        final AtomicReferenceFieldUpdater<zzk, Thread> waiterThreadUpdater;
        final AtomicReferenceFieldUpdater<zzdbf, zzk> waitersUpdater;

        zzf(AtomicReferenceFieldUpdater<zzk, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<zzk, zzk> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<zzdbf, zzk> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<zzdbf, zzd> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<zzdbf, Object> atomicReferenceFieldUpdater5) {
            super();
            this.waiterThreadUpdater = atomicReferenceFieldUpdater;
            this.waiterNextUpdater = atomicReferenceFieldUpdater2;
            this.waitersUpdater = atomicReferenceFieldUpdater3;
            this.listenersUpdater = atomicReferenceFieldUpdater4;
            this.valueUpdater = atomicReferenceFieldUpdater5;
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final void zza(zzk zzkVar, Thread thread) {
            this.waiterThreadUpdater.lazySet(zzkVar, thread);
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final void zza(zzk zzkVar, zzk zzkVar2) {
            this.waiterNextUpdater.lazySet(zzkVar, zzkVar2);
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final boolean zza(zzdbf<?> zzdbfVar, zzk zzkVar, zzk zzkVar2) {
            return this.waitersUpdater.compareAndSet(zzdbfVar, zzkVar, zzkVar2);
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final boolean zza(zzdbf<?> zzdbfVar, zzd zzdVar, zzd zzdVar2) {
            return this.listenersUpdater.compareAndSet(zzdbfVar, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.ads.zzdbf.zza
        final boolean zza(zzdbf<?> zzdbfVar, Object obj, Object obj2) {
            return this.valueUpdater.compareAndSet(zzdbfVar, obj, obj2);
        }
    }

    protected zzdbf() {
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.value;
        if ((obj != null) & (!(obj instanceof zze))) {
            return (V) zzaf(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            zzk zzkVar = this.waiters;
            if (zzkVar != zzk.zzgqb) {
                zzk zzkVar2 = new zzk();
                while (true) {
                    zzkVar2.zzb(zzkVar);
                    if (zzgpp.zza((zzdbf<?>) this, zzkVar, zzkVar2)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                zza(zzkVar2);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.value;
                            if ((obj2 != null) & (!(obj2 instanceof zze))) {
                                return (V) zzaf(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        zza(zzkVar2);
                        break;
                    }
                    zzkVar = this.waiters;
                    if (zzkVar == zzk.zzgqb) {
                    }
                }
            }
            return (V) zzaf(this.value);
        }
        while (nanos > 0) {
            Object obj3 = this.value;
            if ((obj3 != null) & (!(obj3 instanceof zze))) {
                return (V) zzaf(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
        String lowerCase2 = timeUnit.toString().toLowerCase(Locale.ROOT);
        StringBuilder sb = new StringBuilder(String.valueOf(lowerCase2).length() + 28);
        sb.append("Waited ");
        sb.append(j);
        sb.append(" ");
        sb.append(lowerCase2);
        String string2 = sb.toString();
        if (nanos + 1000 < 0) {
            String strConcat = String.valueOf(string2).concat(" (plus ");
            long j2 = -nanos;
            long jConvert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
            long nanos2 = j2 - timeUnit.toNanos(jConvert);
            boolean z = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String strValueOf = String.valueOf(strConcat);
                StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 21 + String.valueOf(lowerCase).length());
                sb2.append(strValueOf);
                sb2.append(jConvert);
                sb2.append(" ");
                sb2.append(lowerCase);
                String string3 = sb2.toString();
                if (z) {
                    string3 = String.valueOf(string3).concat(",");
                }
                strConcat = String.valueOf(string3).concat(" ");
            }
            if (z) {
                String strValueOf2 = String.valueOf(strConcat);
                StringBuilder sb3 = new StringBuilder(String.valueOf(strValueOf2).length() + 33);
                sb3.append(strValueOf2);
                sb3.append(nanos2);
                sb3.append(" nanoseconds ");
                strConcat = sb3.toString();
            }
            string2 = String.valueOf(strConcat).concat("delay)");
        }
        if (isDone()) {
            throw new TimeoutException(String.valueOf(string2).concat(" but future completed as timeout expired"));
        }
        StringBuilder sb4 = new StringBuilder(String.valueOf(string2).length() + 5 + String.valueOf(string).length());
        sb4.append(string2);
        sb4.append(" for ");
        sb4.append(string);
        throw new TimeoutException(sb4.toString());
    }

    @Override // java.util.concurrent.Future
    public V get() throws ExecutionException, InterruptedException {
        Object obj;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.value;
        if ((obj2 != null) & (!(obj2 instanceof zze))) {
            return (V) zzaf(obj2);
        }
        zzk zzkVar = this.waiters;
        if (zzkVar != zzk.zzgqb) {
            zzk zzkVar2 = new zzk();
            do {
                zzkVar2.zzb(zzkVar);
                if (zzgpp.zza((zzdbf<?>) this, zzkVar, zzkVar2)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            zza(zzkVar2);
                            throw new InterruptedException();
                        }
                        obj = this.value;
                    } while (!((obj != null) & (!(obj instanceof zze))));
                    return (V) zzaf(obj);
                }
                zzkVar = this.waiters;
            } while (zzkVar != zzk.zzgqb);
        }
        return (V) zzaf(this.value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static V zzaf(Object obj) throws ExecutionException {
        if (obj instanceof zzc) {
            Throwable th = ((zzc) obj).cause;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof zzb) {
            throw new ExecutionException(((zzb) obj).exception);
        }
        if (obj == NULL) {
            return null;
        }
        return obj;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        Object obj = this.value;
        return (obj != null) & (obj instanceof zze ? false : true);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.value instanceof zzc;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        zzc zzcVar;
        Object obj = this.value;
        if (!(obj == null) && !(obj instanceof zze)) {
            return false;
        }
        if (GENERATE_CANCELLATION_CAUSES) {
            zzcVar = new zzc(z, new CancellationException("Future.cancel() was called."));
        } else if (z) {
            zzcVar = zzc.zzgpr;
        } else {
            zzcVar = zzc.zzgps;
        }
        boolean z2 = false;
        Object obj2 = obj;
        zzdbf<V> zzdbfVar = this;
        while (true) {
            if (zzgpp.zza((zzdbf<?>) zzdbfVar, obj2, (Object) zzcVar)) {
                zza((zzdbf<?>) zzdbfVar);
                if (!(obj2 instanceof zze)) {
                    return true;
                }
                zzdcp<? extends V> zzdcpVar = ((zze) obj2).future;
                if (zzdcpVar instanceof zzg) {
                    zzdbfVar = (zzdbf) zzdcpVar;
                    obj2 = zzdbfVar.value;
                    if (!(obj2 == null) && !(obj2 instanceof zze)) {
                        return true;
                    }
                    z2 = true;
                } else {
                    zzdcpVar.cancel(z);
                    return true;
                }
            } else {
                obj2 = zzdbfVar.value;
                if (!(obj2 instanceof zze)) {
                    return z2;
                }
            }
        }
    }

    protected final boolean wasInterrupted() {
        Object obj = this.value;
        return (obj instanceof zzc) && ((zzc) obj).wasInterrupted;
    }

    @Override // com.google.android.gms.internal.ads.zzdcp
    public void addListener(Runnable runnable, Executor executor) {
        zzd zzdVar;
        zzczx.checkNotNull(runnable, "Runnable was null.");
        zzczx.checkNotNull(executor, "Executor was null.");
        if (!isDone() && (zzdVar = this.listeners) != zzd.zzgpt) {
            zzd zzdVar2 = new zzd(runnable, executor);
            do {
                zzdVar2.next = zzdVar;
                if (zzgpp.zza((zzdbf<?>) this, zzdVar, zzdVar2)) {
                    return;
                } else {
                    zzdVar = this.listeners;
                }
            } while (zzdVar != zzd.zzgpt);
        }
        zza(runnable, executor);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    protected boolean set(@NullableDecl V v) {
        if (v == null) {
            v = (V) NULL;
        }
        if (!zzgpp.zza((zzdbf<?>) this, (Object) null, (Object) v)) {
            return false;
        }
        zza((zzdbf<?>) this);
        return true;
    }

    protected boolean setException(Throwable th) {
        if (!zzgpp.zza((zzdbf<?>) this, (Object) null, (Object) new zzb((Throwable) zzczx.checkNotNull(th)))) {
            return false;
        }
        zza((zzdbf<?>) this);
        return true;
    }

    protected final boolean setFuture(zzdcp<? extends V> zzdcpVar) {
        zzb zzbVar;
        zzczx.checkNotNull(zzdcpVar);
        Object obj = this.value;
        if (obj == null) {
            if (zzdcpVar.isDone()) {
                if (!zzgpp.zza((zzdbf<?>) this, (Object) null, getFutureValue(zzdcpVar))) {
                    return false;
                }
                zza((zzdbf<?>) this);
                return true;
            }
            zze zzeVar = new zze(this, zzdcpVar);
            if (zzgpp.zza((zzdbf<?>) this, (Object) null, (Object) zzeVar)) {
                try {
                    zzdcpVar.addListener(zzeVar, zzdbx.INSTANCE);
                } catch (Throwable th) {
                    try {
                        zzbVar = new zzb(th);
                    } catch (Throwable unused) {
                        zzbVar = zzb.zzgpq;
                    }
                    zzgpp.zza((zzdbf<?>) this, (Object) zzeVar, (Object) zzbVar);
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof zzc) {
            zzdcpVar.cancel(((zzc) obj).wasInterrupted);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static Object getFutureValue(zzdcp<?> zzdcpVar) {
        Throwable thZza;
        if (zzdcpVar instanceof zzg) {
            Object obj = ((zzdbf) zzdcpVar).value;
            if (!(obj instanceof zzc)) {
                return obj;
            }
            zzc zzcVar = (zzc) obj;
            if (!zzcVar.wasInterrupted) {
                return obj;
            }
            if (zzcVar.cause != null) {
                return new zzc(false, zzcVar.cause);
            }
            return zzc.zzgps;
        }
        if ((zzdcpVar instanceof zzddh) && (thZza = zzddk.zza((zzddh) zzdcpVar)) != null) {
            return new zzb(thZza);
        }
        boolean zIsCancelled = zzdcpVar.isCancelled();
        if ((!GENERATE_CANCELLATION_CAUSES) & zIsCancelled) {
            return zzc.zzgps;
        }
        try {
            Object objZza = zza(zzdcpVar);
            if (!zIsCancelled) {
                return objZza == null ? NULL : objZza;
            }
            String strValueOf = String.valueOf(zzdcpVar);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 84);
            sb.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");
            sb.append(strValueOf);
            return new zzc(false, new IllegalArgumentException(sb.toString()));
        } catch (CancellationException e) {
            if (!zIsCancelled) {
                String strValueOf2 = String.valueOf(zzdcpVar);
                StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 77);
                sb2.append("get() threw CancellationException, despite reporting isCancelled() == false: ");
                sb2.append(strValueOf2);
                return new zzb(new IllegalArgumentException(sb2.toString(), e));
            }
            return new zzc(false, e);
        } catch (ExecutionException e2) {
            if (zIsCancelled) {
                String strValueOf3 = String.valueOf(zzdcpVar);
                StringBuilder sb3 = new StringBuilder(String.valueOf(strValueOf3).length() + 84);
                sb3.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");
                sb3.append(strValueOf3);
                return new zzc(false, new IllegalArgumentException(sb3.toString(), e2));
            }
            return new zzb(e2.getCause());
        } catch (Throwable th) {
            return new zzb(th);
        }
    }

    private static <V> V zza(Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static void zza(zzdbf<?> zzdbfVar) {
        zzd zzdVar;
        zzd zzdVar2;
        zzd zzdVar3 = null;
        while (true) {
            zzk zzkVar = ((zzdbf) zzdbfVar).waiters;
            if (zzgpp.zza(zzdbfVar, zzkVar, zzk.zzgqb)) {
                while (zzkVar != null) {
                    Thread thread = zzkVar.thread;
                    if (thread != null) {
                        zzkVar.thread = null;
                        LockSupport.unpark(thread);
                    }
                    zzkVar = zzkVar.next;
                }
                zzdbfVar.afterDone();
                do {
                    zzdVar = ((zzdbf) zzdbfVar).listeners;
                } while (!zzgpp.zza(zzdbfVar, zzdVar, zzd.zzgpt));
                while (true) {
                    zzdVar2 = zzdVar3;
                    zzdVar3 = zzdVar;
                    if (zzdVar3 == null) {
                        break;
                    }
                    zzdVar = zzdVar3.next;
                    zzdVar3.next = zzdVar2;
                }
                while (zzdVar2 != null) {
                    zzdVar3 = zzdVar2.next;
                    Runnable runnable = zzdVar2.task;
                    if (runnable instanceof zze) {
                        zze zzeVar = (zze) runnable;
                        zzdbfVar = zzeVar.zzgpu;
                        if (((zzdbf) zzdbfVar).value == zzeVar) {
                            if (!zzgpp.zza((zzdbf<?>) zzdbfVar, (Object) zzeVar, getFutureValue(zzeVar.future))) {
                            }
                        } else {
                            continue;
                        }
                    } else {
                        zza(runnable, zzdVar2.executor);
                    }
                    zzdVar2 = zzdVar3;
                }
                return;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddh
    @NullableDecl
    protected final Throwable zzaor() {
        if (!(this instanceof zzg)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof zzb) {
            return ((zzb) obj).exception;
        }
        return null;
    }

    final void maybePropagateCancellationTo(@NullableDecl Future<?> future) {
        if ((future != null) && isCancelled()) {
            future.cancel(wasInterrupted());
        }
    }

    public String toString() {
        String string;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            zza(sb);
        } else {
            try {
                string = pendingToString();
            } catch (RuntimeException e) {
                String strValueOf = String.valueOf(e.getClass());
                StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 38);
                sb2.append("Exception thrown from implementation: ");
                sb2.append(strValueOf);
                string = sb2.toString();
            }
            if (string != null && !string.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(string);
                sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
            } else if (isDone()) {
                zza(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NullableDecl
    protected String pendingToString() {
        Object obj = this.value;
        if (obj instanceof zze) {
            String strZzag = zzag(((zze) obj).future);
            StringBuilder sb = new StringBuilder(String.valueOf(strZzag).length() + 12);
            sb.append("setFuture=[");
            sb.append(strZzag);
            sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
            return sb.toString();
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
        StringBuilder sb2 = new StringBuilder(41);
        sb2.append("remaining delay=[");
        sb2.append(delay);
        sb2.append(" ms]");
        return sb2.toString();
    }

    private final void zza(StringBuilder sb) {
        try {
            Object objZza = zza(this);
            sb.append("SUCCESS, result=[");
            sb.append(zzag(objZza));
            sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e2) {
            sb.append("FAILURE, cause=[");
            sb.append(e2.getCause());
            sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        }
    }

    private final String zzag(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }

    private static void zza(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = zzgpo;
            Level level = Level.SEVERE;
            String strValueOf = String.valueOf(runnable);
            String strValueOf2 = String.valueOf(executor);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 57 + String.valueOf(strValueOf2).length());
            sb.append("RuntimeException while executing runnable ");
            sb.append(strValueOf);
            sb.append(" with executor ");
            sb.append(strValueOf2);
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", sb.toString(), (Throwable) e);
        }
    }

    static {
        Throwable th;
        Throwable th2;
        zza zzhVar;
        try {
            zzhVar = new zzi();
            th2 = null;
            th = null;
        } catch (Throwable th3) {
            try {
                th2 = null;
                th = th3;
                zzhVar = new zzf(AtomicReferenceFieldUpdater.newUpdater(zzk.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(zzk.class, zzk.class, "next"), AtomicReferenceFieldUpdater.newUpdater(zzdbf.class, zzk.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(zzdbf.class, zzd.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(zzdbf.class, Object.class, "value"));
            } catch (Throwable th4) {
                th = th3;
                th2 = th4;
                zzhVar = new zzh();
            }
        }
        zzgpp = zzhVar;
        if (th2 != null) {
            zzgpo.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", th);
            zzgpo.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", th2);
        }
        NULL = new Object();
    }
}
