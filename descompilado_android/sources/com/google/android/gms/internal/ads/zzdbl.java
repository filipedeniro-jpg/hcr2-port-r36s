package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
class zzdbl<InputT, OutputT> extends zzdbf.zzj<OutputT> {
    private static final Logger logger = Logger.getLogger(zzdbl.class.getName());

    @NullableDecl
    private zza zzgqh;

    zzdbl() {
    }

    @Override // com.google.android.gms.internal.ads.zzdbf
    protected final void afterDone() {
        super.afterDone();
        zza zzaVar = this.zzgqh;
        if (zzaVar != null) {
            this.zzgqh = null;
            zzdaf zzdafVar = zzaVar.zzgqd;
            boolean zWasInterrupted = wasInterrupted();
            if (zWasInterrupted) {
                zzaVar.interruptTask();
            }
            if (isCancelled() && (zzdafVar != null)) {
                zzdbb zzdbbVar = (zzdbb) zzdafVar.iterator();
                while (zzdbbVar.hasNext()) {
                    ((zzdcp) zzdbbVar.next()).cancel(zWasInterrupted);
                }
            }
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    abstract class zza extends zzdbm implements Runnable {
        private zzdaf<? extends zzdcp<? extends InputT>> zzgqd;
        private final boolean zzgqe;
        private final boolean zzgqf;

        zza(zzdaf<? extends zzdcp<? extends InputT>> zzdafVar, boolean z, boolean z2) {
            super(zzdafVar.size());
            this.zzgqd = (zzdaf) zzczx.checkNotNull(zzdafVar);
            this.zzgqe = z;
            this.zzgqf = z2;
        }

        void interruptTask() {
        }

        abstract void zza(boolean z, int i, @NullableDecl InputT inputt);

        abstract void zzaox();

        @Override // java.lang.Runnable
        public final void run() {
            zzaov();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzaou() {
            if (this.zzgqd.isEmpty()) {
                zzaox();
                return;
            }
            if (this.zzgqe) {
                int i = 0;
                zzdbb zzdbbVar = (zzdbb) this.zzgqd.iterator();
                while (zzdbbVar.hasNext()) {
                    zzdcp zzdcpVar = (zzdcp) zzdbbVar.next();
                    zzdcpVar.addListener(new zzdbn(this, i, zzdcpVar), zzdbx.INSTANCE);
                    i++;
                }
                return;
            }
            zzdbb zzdbbVar2 = (zzdbb) this.zzgqd.iterator();
            while (zzdbbVar2.hasNext()) {
                ((zzdcp) zzdbbVar2.next()).addListener(this, zzdbx.INSTANCE);
            }
        }

        /* JADX WARN: Code duplicated, block: B:13:0x0027  */
        /* JADX WARN: Code duplicated, block: B:16:0x002e A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:17:0x0030  */
        /* JADX WARN: Code duplicated, block: B:18:0x0033  */
        /* JADX WARN: Code duplicated, block: B:21:? A[RETURN, SYNTHETIC] */
        private final void zzh(Throwable th) {
            boolean exception;
            boolean zZza;
            boolean z;
            String str;
            zzczx.checkNotNull(th);
            if (this.zzgqe) {
                exception = zzdbl.this.setException(th);
                if (exception) {
                    zzaow();
                } else {
                    zZza = zzdbl.zza(zzaoz(), th);
                }
                z = th instanceof Error;
                if ((this.zzgqe & (exception ? false : true) & zZza) || z) {
                    if (z) {
                        str = "Input Future failed with Error";
                    } else {
                        str = "Got more than one input Future failure. Logging failures after the first";
                    }
                    zzdbl.logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFuture$RunningState", "handleException", str, th);
                }
            }
            exception = false;
            zZza = true;
            z = th instanceof Error;
            if ((this.zzgqe & (exception ? false : true) & zZza) || z) {
                if (z) {
                    str = "Input Future failed with Error";
                } else {
                    str = "Got more than one input Future failure. Logging failures after the first";
                }
                zzdbl.logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFuture$RunningState", "handleException", str, th);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdbm
        final void zzg(Set<Throwable> set) {
            if (zzdbl.this.isCancelled()) {
                return;
            }
            zzdbl.zza(set, zzdbl.this.zzaor());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public final void zza(int i, Future<? extends InputT> future) {
            zzczx.checkState(this.zzgqe || !zzdbl.this.isDone() || zzdbl.this.isCancelled(), "Future was done before all dependencies completed");
            try {
                zzczx.checkState(future.isDone(), "Tried to set value from future which is not done");
                if (this.zzgqe) {
                    if (future.isCancelled()) {
                        zzdbl.zza(zzdbl.this, (zza) null);
                        zzdbl.this.cancel(false);
                        return;
                    } else {
                        Object objZzb = zzdcf.zzb(future);
                        if (this.zzgqf) {
                            zza(this.zzgqe, i, objZzb);
                            return;
                        }
                        return;
                    }
                }
                if (!this.zzgqf || future.isCancelled()) {
                    return;
                }
                zza(this.zzgqe, i, zzdcf.zzb(future));
            } catch (ExecutionException e) {
                zzh(e.getCause());
            } catch (Throwable th) {
                zzh(th);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzaov() {
            int iZzapa = zzapa();
            int i = 0;
            zzczx.checkState(iZzapa >= 0, "Less than 0 remaining futures");
            if (iZzapa == 0) {
                if (this.zzgqf & (true ^ this.zzgqe)) {
                    zzdbb zzdbbVar = (zzdbb) this.zzgqd.iterator();
                    while (zzdbbVar.hasNext()) {
                        zza(i, (zzdcp) zzdbbVar.next());
                        i++;
                    }
                }
                zzaox();
            }
        }

        void zzaow() {
            this.zzgqd = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdbf
    protected final String pendingToString() {
        zzdaf zzdafVar;
        zza zzaVar = this.zzgqh;
        if (zzaVar == null || (zzdafVar = zzaVar.zzgqd) == null) {
            return null;
        }
        String strValueOf = String.valueOf(zzdafVar);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 10);
        sb.append("futures=[");
        sb.append(strValueOf);
        sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        return sb.toString();
    }

    final void zzd(zza zzaVar) {
        this.zzgqh = zzaVar;
        zzaVar.zzaou();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zza(Set<Throwable> set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }

    static /* synthetic */ zza zza(zzdbl zzdblVar, zza zzaVar) {
        zzdblVar.zzgqh = null;
        return null;
    }
}
