package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.lang.Throwable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzdbd<V, X extends Throwable, F, T> extends zzdcc<V> implements Runnable {

    @NullableDecl
    private zzdcp<? extends V> zzgpl;

    @NullableDecl
    private Class<X> zzgpm;

    @NullableDecl
    private F zzgpn;

    static <X extends Throwable, V> zzdcp<V> zza(zzdcp<? extends V> zzdcpVar, Class<X> cls, zzdbq<? super X, ? extends V> zzdbqVar, Executor executor) {
        zzdbg zzdbgVar = new zzdbg(zzdcpVar, cls, zzdbqVar);
        zzdcpVar.addListener(zzdbgVar, zzdcr.zza(executor, zzdbgVar));
        return zzdbgVar;
    }

    abstract void setResult(@NullableDecl T t);

    @NullableDecl
    abstract T zza(F f, X x) throws Exception;

    zzdbd(zzdcp<? extends V> zzdcpVar, Class<X> cls, F f) {
        this.zzgpl = (zzdcp) zzczx.checkNotNull(zzdcpVar);
        this.zzgpm = (Class) zzczx.checkNotNull(cls);
        this.zzgpn = (F) zzczx.checkNotNull(f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() throws ExecutionException {
        Object objZzb;
        zzdcp<? extends V> zzdcpVar = this.zzgpl;
        Class<X> cls = this.zzgpm;
        F f = this.zzgpn;
        if (((f == null) | (zzdcpVar == null) | (cls == null)) || isCancelled()) {
            return;
        }
        this.zzgpl = null;
        try {
            objZzb = zzdcf.zzb(zzdcpVar);
            th = null;
        } catch (ExecutionException e) {
            th = (Throwable) zzczx.checkNotNull(e.getCause());
            objZzb = null;
        } catch (Throwable th) {
            th = th;
            objZzb = null;
        }
        if (th == null) {
            set(objZzb);
            return;
        }
        try {
            if (!cls.isInstance(th)) {
                setFuture(zzdcpVar);
                return;
            }
            try {
                Object objZza = zza(f, th);
                this.zzgpm = null;
                this.zzgpn = null;
                setResult(objZza);
            } catch (Throwable th2) {
                setException(th2);
                this.zzgpm = null;
                this.zzgpn = null;
            }
        } catch (Throwable th3) {
            this.zzgpm = null;
            this.zzgpn = null;
            throw th3;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdbf
    protected final String pendingToString() {
        String string;
        zzdcp<? extends V> zzdcpVar = this.zzgpl;
        Class<X> cls = this.zzgpm;
        F f = this.zzgpn;
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
        if (cls == null || f == null) {
            if (strPendingToString == null) {
                return null;
            }
            String strValueOf2 = String.valueOf(string);
            String strValueOf3 = String.valueOf(strPendingToString);
            return strValueOf3.length() != 0 ? strValueOf2.concat(strValueOf3) : new String(strValueOf2);
        }
        String strValueOf4 = String.valueOf(cls);
        String strValueOf5 = String.valueOf(f);
        StringBuilder sb2 = new StringBuilder(String.valueOf(string).length() + 29 + String.valueOf(strValueOf4).length() + String.valueOf(strValueOf5).length());
        sb2.append(string);
        sb2.append("exceptionType=[");
        sb2.append(strValueOf4);
        sb2.append("], fallback=[");
        sb2.append(strValueOf5);
        sb2.append(Constants.RequestParameters.RIGHT_BRACKETS);
        return sb2.toString();
    }

    @Override // com.google.android.gms.internal.ads.zzdbf
    protected final void afterDone() {
        maybePropagateCancellationTo(this.zzgpl);
        this.zzgpl = null;
        this.zzgpm = null;
        this.zzgpn = null;
    }
}
