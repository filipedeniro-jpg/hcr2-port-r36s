package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzdbh<I, O, F, T> extends zzdcc<O> implements Runnable {

    @NullableDecl
    private zzdcp<? extends I> zzgpl;

    @NullableDecl
    private F zzgqc;

    static <I, O> zzdcp<O> zza(zzdcp<I> zzdcpVar, zzdbq<? super I, ? extends O> zzdbqVar, Executor executor) {
        zzczx.checkNotNull(executor);
        zzdbk zzdbkVar = new zzdbk(zzdcpVar, zzdbqVar);
        zzdcpVar.addListener(zzdbkVar, zzdcr.zza(executor, zzdbkVar));
        return zzdbkVar;
    }

    abstract void setResult(@NullableDecl T t);

    @NullableDecl
    abstract T zzc(F f, @NullableDecl I i) throws Exception;

    static <I, O> zzdcp<O> zza(zzdcp<I> zzdcpVar, zzczs<? super I, ? extends O> zzczsVar, Executor executor) {
        zzczx.checkNotNull(zzczsVar);
        zzdbj zzdbjVar = new zzdbj(zzdcpVar, zzczsVar);
        zzdcpVar.addListener(zzdbjVar, zzdcr.zza(executor, zzdbjVar));
        return zzdbjVar;
    }

    zzdbh(zzdcp<? extends I> zzdcpVar, F f) {
        this.zzgpl = (zzdcp) zzczx.checkNotNull(zzdcpVar);
        this.zzgqc = (F) zzczx.checkNotNull(f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        zzdcp<? extends I> zzdcpVar = this.zzgpl;
        F f = this.zzgqc;
        if ((isCancelled() | (zzdcpVar == null)) || (f == null)) {
            return;
        }
        this.zzgpl = null;
        if (zzdcpVar.isCancelled()) {
            setFuture(zzdcpVar);
            return;
        }
        try {
            try {
                try {
                    Object objZzc = zzc(f, zzdcf.zzb(zzdcpVar));
                    this.zzgqc = null;
                    setResult(objZzc);
                } catch (Throwable th) {
                    setException(th);
                    this.zzgqc = null;
                }
            } catch (Throwable th2) {
                this.zzgqc = null;
                throw th2;
            }
        } catch (Error e) {
            setException(e);
        } catch (CancellationException unused) {
            cancel(false);
        } catch (RuntimeException e2) {
            setException(e2);
        } catch (ExecutionException e3) {
            setException(e3.getCause());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdbf
    protected final void afterDone() {
        maybePropagateCancellationTo(this.zzgpl);
        this.zzgpl = null;
        this.zzgqc = null;
    }

    @Override // com.google.android.gms.internal.ads.zzdbf
    protected final String pendingToString() {
        String string;
        zzdcp<? extends I> zzdcpVar = this.zzgpl;
        F f = this.zzgqc;
        String strPendingToString = super.pendingToString();
        if (zzdcpVar != null) {
            String strValueOf = String.valueOf(zzdcpVar);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 16);
            sb.append("inputFuture=[");
            sb.append(strValueOf);
            sb.append("], ");
            string = sb.toString();
        } else {
            string = "";
        }
        if (f == null) {
            if (strPendingToString == null) {
                return null;
            }
            String strValueOf2 = String.valueOf(string);
            String strValueOf3 = String.valueOf(strPendingToString);
            return strValueOf3.length() != 0 ? strValueOf2.concat(strValueOf3) : new String(strValueOf2);
        }
        String strValueOf4 = String.valueOf(f);
        StringBuilder sb2 = new StringBuilder(String.valueOf(string).length() + 11 + String.valueOf(strValueOf4).length());
        sb2.append(string);
        sb2.append("function=[");
        sb2.append(strValueOf4);
        sb2.append(Constants.RequestParameters.RIGHT_BRACKETS);
        return sb2.toString();
    }
}
