package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Method;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzarv {
    private final AtomicReference<ThreadPoolExecutor> zzdow = new AtomicReference<>(null);
    private final Object zzdox = new Object();

    @Nullable
    @GuardedBy("gmpAppIdLock")
    private String zzdoy = null;

    @Nullable
    @GuardedBy("gmpAppIdLock")
    private String zzdoz = null;

    @VisibleForTesting
    private final AtomicBoolean zzdpa = new AtomicBoolean(false);
    private final AtomicInteger zzdpb = new AtomicInteger(-1);
    private final AtomicReference<Object> zzdpc = new AtomicReference<>(null);
    private final AtomicReference<Object> zzdpd = new AtomicReference<>(null);
    private final ConcurrentMap<String, Method> zzdpe = new ConcurrentHashMap(9);
    private final AtomicReference<zzbdl> zzdpf = new AtomicReference<>(null);

    @GuardedBy("proxyReference")
    private final BlockingQueue<FutureTask<?>> zzdpg = new ArrayBlockingQueue(20);
    private final Object zzdph = new Object();

    /* JADX WARN: Code duplicated, block: B:17:0x0059  */
    public final boolean zzaa(Context context) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzciz)).booleanValue() && !this.zzdpa.get()) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjj)).booleanValue()) {
                return true;
            }
            if (this.zzdpb.get() == -1) {
                zzuo.zzof();
                if (!zzawe.zzc(context, 12451000)) {
                    zzuo.zzof();
                    if (zzawe.zzbj(context)) {
                        zzatm.zzeu("Google Play Service is out of date, the Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires updated Google Play Service.");
                        this.zzdpb.set(0);
                    } else {
                        this.zzdpb.set(1);
                    }
                } else {
                    this.zzdpb.set(1);
                }
            }
            if (this.zzdpb.get() == 1) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    private static boolean zzab(Context context) {
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcjh)).booleanValue()) {
            if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcjg)).booleanValue()) {
                return false;
            }
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcji)).booleanValue()) {
            try {
                context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                return false;
            } catch (ClassNotFoundException unused) {
            }
        }
        return true;
    }

    public final void zza(Context context, zzxw zzxwVar) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjm)).booleanValue() && zzaa(context) && zzab(context)) {
            synchronized (this.zzdph) {
            }
        }
    }

    public final void zza(Context context, zztp zztpVar) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjm)).booleanValue() && zzaa(context) && zzab(context)) {
            synchronized (this.zzdph) {
            }
        }
    }

    public final void zze(Context context, final String str) {
        if (zzaa(context)) {
            if (zzab(context)) {
                zza("beginAdUnitExposure", new zzasl(str) { // from class: com.google.android.gms.internal.ads.zzaru
                    private final String zzcys;

                    {
                        this.zzcys = str;
                    }

                    @Override // com.google.android.gms.internal.ads.zzasl
                    public final void zza(zzbdl zzbdlVar) throws RemoteException {
                        zzbdlVar.beginAdUnitExposure(this.zzcys);
                    }
                });
            } else {
                zza(context, str, "beginAdUnitExposure");
            }
        }
    }

    public final void zzf(Context context, final String str) {
        if (zzaa(context)) {
            if (zzab(context)) {
                zza("endAdUnitExposure", new zzasl(str) { // from class: com.google.android.gms.internal.ads.zzasb
                    private final String zzcys;

                    {
                        this.zzcys = str;
                    }

                    @Override // com.google.android.gms.internal.ads.zzasl
                    public final void zza(zzbdl zzbdlVar) throws RemoteException {
                        zzbdlVar.endAdUnitExposure(this.zzcys);
                    }
                });
            } else {
                zza(context, str, "endAdUnitExposure");
            }
        }
    }

    public final String zzac(Context context) {
        if (!zzaa(context)) {
            return "";
        }
        if (zzab(context)) {
            return (String) zza("getCurrentScreenNameOrScreenClass", "", (zzasi<String>) zzasa.zzdpj);
        }
        if (!zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzdpc, true)) {
            return "";
        }
        try {
            String str = (String) zzm(context, "getCurrentScreenName").invoke(this.zzdpc.get(), new Object[0]);
            if (str == null) {
                str = (String) zzm(context, "getCurrentScreenClass").invoke(this.zzdpc.get(), new Object[0]);
            }
            return str != null ? str : "";
        } catch (Exception e) {
            zza(e, "getCurrentScreenName", false);
            return "";
        }
    }

    public final void zzg(final Context context, final String str) {
        if (zzaa(context) && (context instanceof Activity)) {
            if (zzab(context)) {
                zza("setScreenName", new zzasl(context, str) { // from class: com.google.android.gms.internal.ads.zzasd
                    private final String zzcyl;
                    private final Context zzdpm;

                    {
                        this.zzdpm = context;
                        this.zzcyl = str;
                    }

                    @Override // com.google.android.gms.internal.ads.zzasl
                    public final void zza(zzbdl zzbdlVar) throws RemoteException {
                        Context context2 = this.zzdpm;
                        zzbdlVar.zzb(ObjectWrapper.wrap(context2), this.zzcyl, context2.getPackageName());
                    }
                });
                return;
            }
            if (zza(context, "com.google.firebase.analytics.FirebaseAnalytics", this.zzdpd, false)) {
                try {
                    zzn(context, "setCurrentScreen").invoke(this.zzdpd.get(), (Activity) context, str, context.getPackageName());
                } catch (Exception e) {
                    zza(e, "setCurrentScreen", false);
                }
            }
        }
    }

    @Nullable
    public final String zzad(Context context) {
        if (!zzaa(context)) {
            return null;
        }
        synchronized (this.zzdox) {
            if (this.zzdoy != null) {
                return this.zzdoy;
            }
            if (zzab(context)) {
                this.zzdoy = (String) zza("getGmpAppId", this.zzdoy, (zzasi<String>) zzasc.zzdpj);
            } else {
                this.zzdoy = (String) zza("getGmpAppId", context);
            }
            return this.zzdoy;
        }
    }

    @Nullable
    public final String zzae(final Context context) {
        if (!zzaa(context)) {
            return null;
        }
        long jLongValue = ((Long) zzuo.zzoj().zzd(zzyt.zzcje)).longValue();
        if (zzab(context)) {
            try {
                if (jLongValue < 0) {
                    return (String) zza("getAppInstanceId", (Object) null, (zzasi<Object>) zzasf.zzdpj);
                }
                return (String) zzto().submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzase
                    private final zzarv zzdpk;

                    {
                        this.zzdpk = this;
                    }

                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return this.zzdpk.zztp();
                    }
                }).get(jLongValue, TimeUnit.MILLISECONDS);
            } catch (TimeoutException unused) {
                return "TIME_OUT";
            } catch (Exception unused2) {
                return null;
            }
        }
        if (jLongValue < 0) {
            return (String) zza("getAppInstanceId", context);
        }
        try {
            return (String) zzto().submit(new Callable(this, context) { // from class: com.google.android.gms.internal.ads.zzash
                private final Context zzcev;
                private final zzarv zzdpk;

                {
                    this.zzdpk = this;
                    this.zzcev = context;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.zzdpk.zzai(this.zzcev);
                }
            }).get(jLongValue, TimeUnit.MILLISECONDS);
        } catch (TimeoutException unused3) {
            return "TIME_OUT";
        } catch (Exception unused4) {
            return null;
        }
    }

    @Nullable
    public final String zzaf(Context context) {
        if (!zzaa(context)) {
            return null;
        }
        if (zzab(context)) {
            Long l = (Long) zza("getAdEventId", (Object) null, (zzasi<Object>) zzasg.zzdpj);
            if (l != null) {
                return Long.toString(l.longValue());
            }
            return null;
        }
        Object objZza = zza("generateEventId", context);
        if (objZza != null) {
            return objZza.toString();
        }
        return null;
    }

    @Nullable
    public final String zzag(Context context) {
        if (!zzaa(context)) {
            return null;
        }
        synchronized (this.zzdox) {
            if (this.zzdoz != null) {
                return this.zzdoz;
            }
            if (zzab(context)) {
                this.zzdoz = (String) zza("getAppIdOrigin", this.zzdoz, (zzasi<String>) zzarx.zzdpj);
            } else {
                this.zzdoz = "fa";
            }
            return this.zzdoz;
        }
    }

    public final void zzh(Context context, String str) {
        zza(context, "_ac", str, (Bundle) null);
    }

    public final void zzi(Context context, String str) {
        zza(context, "_ai", str, (Bundle) null);
    }

    public final void zzj(Context context, String str) {
        zza(context, "_aq", str, (Bundle) null);
    }

    public final void zzk(Context context, String str) {
        zza(context, "_aa", str, (Bundle) null);
    }

    public final void zza(Context context, String str, String str2, String str3, int i) {
        if (zzaa(context)) {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str2);
            bundle.putString("type", str3);
            bundle.putInt("value", i);
            zza(context, "_ar", str, bundle);
            StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 75);
            sb.append("Log a Firebase reward video event, reward type: ");
            sb.append(str3);
            sb.append(", reward value: ");
            sb.append(i);
            zzatm.zzdy(sb.toString());
        }
    }

    private final void zza(Context context, final String str, String str2, @Nullable Bundle bundle) {
        if (zzaa(context)) {
            final Bundle bundleZzl = zzl(str2, str);
            if (bundle != null) {
                bundleZzl.putAll(bundle);
            }
            if (zzab(context)) {
                zza("logEventInternal", new zzasl(str, bundleZzl) { // from class: com.google.android.gms.internal.ads.zzarw
                    private final String zzcys;
                    private final Bundle zzdpi;

                    {
                        this.zzcys = str;
                        this.zzdpi = bundleZzl;
                    }

                    @Override // com.google.android.gms.internal.ads.zzasl
                    public final void zza(zzbdl zzbdlVar) throws RemoteException {
                        zzbdlVar.logEvent("am", this.zzcys, this.zzdpi);
                    }
                });
                return;
            }
            if (zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzdpc, true)) {
                try {
                    zzah(context).invoke(this.zzdpc.get(), "am", str, bundleZzl);
                } catch (Exception e) {
                    zza(e, "logEventInternal", true);
                }
            }
        }
    }

    private static Bundle zzl(String str, String str2) {
        Bundle bundle = new Bundle();
        try {
            bundle.putLong("_aeid", Long.parseLong(str));
        } catch (NullPointerException | NumberFormatException e) {
            String strValueOf = String.valueOf(str);
            zzatm.zzc(strValueOf.length() != 0 ? "Invalid event ID: ".concat(strValueOf) : new String("Invalid event ID: "), e);
        }
        if ("_ac".equals(str2)) {
            bundle.putInt("_r", 1);
        }
        return bundle;
    }

    @Nullable
    private final Method zzah(Context context) {
        Method method = this.zzdpe.get("logEventInternal");
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod("logEventInternal", String.class, String.class, Bundle.class);
            this.zzdpe.put("logEventInternal", declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, "logEventInternal", true);
            return null;
        }
    }

    @Nullable
    private final Method zzl(Context context, String str) {
        Method method = this.zzdpe.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, String.class);
            this.zzdpe.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    @Nullable
    private final Method zzm(Context context, String str) {
        Method method = this.zzdpe.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[0]);
            this.zzdpe.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final Method zzn(Context context, String str) {
        Method method = this.zzdpe.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod(str, Activity.class, String.class, String.class);
            this.zzdpe.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            zza(e, str, false);
            return null;
        }
    }

    private final void zza(Context context, String str, String str2) {
        if (zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzdpc, true)) {
            try {
                zzl(context, str2).invoke(this.zzdpc.get(), str);
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 37 + String.valueOf(str).length());
                sb.append("Invoke Firebase method ");
                sb.append(str2);
                sb.append(", Ad Unit Id: ");
                sb.append(str);
                zzatm.zzdy(sb.toString());
            } catch (Exception e) {
                zza(e, str2, false);
            }
        }
    }

    private final Object zza(String str, Context context) {
        if (!zza(context, "com.google.android.gms.measurement.AppMeasurement", this.zzdpc, true)) {
            return null;
        }
        try {
            return zzm(context, str).invoke(this.zzdpc.get(), new Object[0]);
        } catch (Exception e) {
            zza(e, str, true);
            return null;
        }
    }

    private final void zza(Exception exc, String str, boolean z) {
        if (this.zzdpa.get()) {
            return;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 30);
        sb.append("Invoke Firebase method ");
        sb.append(str);
        sb.append(" error.");
        zzatm.zzeu(sb.toString());
        if (z) {
            zzatm.zzeu("The Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires the latest Firebase SDK jar, but Firebase SDK is either missing or out of date");
            this.zzdpa.set(true);
        }
    }

    private final ThreadPoolExecutor zzto() {
        if (this.zzdow.get() == null) {
            this.zzdow.compareAndSet(null, new ThreadPoolExecutor(((Integer) zzuo.zzoj().zzd(zzyt.zzcjf)).intValue(), ((Integer) zzuo.zzoj().zzd(zzyt.zzcjf)).intValue(), 1L, TimeUnit.MINUTES, new LinkedBlockingQueue(), new zzasj(this)));
        }
        return this.zzdow.get();
    }

    private final boolean zza(Context context, String str, AtomicReference<Object> atomicReference, boolean z) {
        if (atomicReference.get() == null) {
            try {
                atomicReference.compareAndSet(null, context.getClassLoader().loadClass(str).getDeclaredMethod("getInstance", Context.class).invoke(null, context));
            } catch (Exception e) {
                zza(e, "getInstance", z);
                return false;
            }
        }
        return true;
    }

    private final void zza(final String str, final zzasl zzaslVar) {
        synchronized (this.zzdpf) {
            FutureTask<?> futureTask = new FutureTask<>(new Runnable(this, zzaslVar, str) { // from class: com.google.android.gms.internal.ads.zzarz
                private final String zzdbj;
                private final zzarv zzdpk;
                private final zzasl zzdpl;

                {
                    this.zzdpk = this;
                    this.zzdpl = zzaslVar;
                    this.zzdbj = str;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzdpk.zza(this.zzdpl, this.zzdbj);
                }
            }, null);
            if (this.zzdpf.get() != null) {
                futureTask.run();
            } else {
                this.zzdpg.offer(futureTask);
            }
        }
    }

    private final <T> T zza(String str, @Nullable T t, zzasi<T> zzasiVar) {
        synchronized (this.zzdpf) {
            if (this.zzdpf.get() != null) {
                try {
                    return zzasiVar.zzb(this.zzdpf.get());
                } catch (Exception e) {
                    zza(e, str, false);
                }
            }
            return t;
        }
    }

    final /* synthetic */ void zza(zzasl zzaslVar, String str) {
        if (this.zzdpf.get() != null) {
            try {
                zzaslVar.zza(this.zzdpf.get());
            } catch (Exception e) {
                zza(e, str, false);
            }
        }
    }

    final /* synthetic */ String zzai(Context context) throws Exception {
        return (String) zza("getAppInstanceId", context);
    }

    final /* synthetic */ String zztp() throws Exception {
        return (String) zza("getAppInstanceId", (Object) null, (zzasi<Object>) zzary.zzdpj);
    }
}
