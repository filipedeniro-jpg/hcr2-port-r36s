package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.appsflyer.AppsFlyerProperties;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzann implements zzanr {
    private static final Object lock = new Object();

    @VisibleForTesting
    private static zzanr zzdhz = null;

    @VisibleForTesting
    private static zzanr zzdia = null;
    private final Context zzcft;
    private final Object zzdib;
    private final WeakHashMap<Thread, Boolean> zzdic;
    private final zzawv zzdid;
    private final ExecutorService zzxr;

    public static zzanr zzr(Context context) {
        synchronized (lock) {
            if (zzdhz == null) {
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcgi)).booleanValue()) {
                    zzdhz = new zzann(context);
                } else {
                    zzdhz = new zzanq();
                }
            }
        }
        return zzdhz;
    }

    public static zzanr zzc(Context context, zzawv zzawvVar) {
        synchronized (lock) {
            if (zzdia == null) {
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcgi)).booleanValue()) {
                    zzann zzannVar = new zzann(context, zzawvVar);
                    Thread thread = Looper.getMainLooper().getThread();
                    if (thread != null) {
                        synchronized (zzannVar.zzdib) {
                            zzannVar.zzdic.put(thread, true);
                        }
                        thread.setUncaughtExceptionHandler(new zzano(zzannVar, thread.getUncaughtExceptionHandler()));
                    }
                    Thread.setDefaultUncaughtExceptionHandler(new zzanp(zzannVar, Thread.getDefaultUncaughtExceptionHandler()));
                    zzdia = zzannVar;
                } else {
                    zzdia = new zzanq();
                }
            }
        }
        return zzdia;
    }

    private zzann(Context context) {
        this(context, zzawv.zzwk());
    }

    private zzann(Context context, zzawv zzawvVar) {
        this.zzdib = new Object();
        this.zzdic = new WeakHashMap<>();
        this.zzxr = zzczf.zzanz().zzdo(zzczk.zzgny);
        this.zzcft = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzdid = zzawvVar;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0042  */
    protected final void zza(Thread thread, Throwable th) {
        boolean z;
        if (th != null) {
            Throwable cause = th;
            boolean z2 = false;
            boolean z3 = false;
            while (cause != null) {
                boolean z4 = z3;
                boolean z5 = z2;
                for (StackTraceElement stackTraceElement : cause.getStackTrace()) {
                    if (zzawe.zzeo(stackTraceElement.getClassName())) {
                        z5 = true;
                    }
                    if (getClass().getName().equals(stackTraceElement.getClassName())) {
                        z4 = true;
                    }
                }
                cause = cause.getCause();
                z2 = z5;
                z3 = z4;
            }
            z = z2 && !z3;
        }
        if (z) {
            zza(th, "", 1.0f);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzanr
    public final void zza(Throwable th, String str) {
        zza(th, str, 1.0f);
    }

    @Override // com.google.android.gms.internal.ads.zzanr
    public final void zza(Throwable th, String str, float f) {
        if (zzawe.zzc(th) == null) {
            return;
        }
        String name = th.getClass().getName();
        StringWriter stringWriter = new StringWriter();
        zzdof.zza(th, new PrintWriter(stringWriter));
        String string = stringWriter.toString();
        int i = 0;
        boolean z = Math.random() < ((double) f);
        int i2 = f > 0.0f ? (int) (1.0f / f) : 1;
        if (z) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(zza(name, string, str, i2).toString());
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                final String str2 = (String) obj;
                final zzaws zzawsVar = new zzaws();
                this.zzxr.execute(new Runnable(zzawsVar, str2) { // from class: com.google.android.gms.internal.ads.zzanm
                    private final String zzcyl;
                    private final zzaws zzdhy;

                    {
                        this.zzdhy = zzawsVar;
                        this.zzcyl = str2;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zzdhy.zzei(this.zzcyl);
                    }
                });
            }
        }
    }

    @VisibleForTesting
    private final Uri.Builder zza(String str, String str2, String str3, int i) {
        boolean zIsCallerInstantApp;
        String packageName;
        try {
            zIsCallerInstantApp = Wrappers.packageManager(this.zzcft).isCallerInstantApp();
        } catch (Throwable th) {
            zzawo.zzc("Error fetching instant app info", th);
            zIsCallerInstantApp = false;
        }
        try {
            packageName = this.zzcft.getPackageName();
        } catch (Throwable unused) {
            zzawo.zzeu("Cannot obtain package name, proceeding.");
            packageName = "unknown";
        }
        Uri.Builder builderAppendQueryParameter = new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", Boolean.toString(zIsCallerInstantApp)).appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT));
        String str4 = Build.MANUFACTURER;
        String string = Build.MODEL;
        if (!string.startsWith(str4)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str4).length() + 1 + String.valueOf(string).length());
            sb.append(str4);
            sb.append(" ");
            sb.append(string);
            string = sb.toString();
        }
        return builderAppendQueryParameter.appendQueryParameter("device", string).appendQueryParameter("js", this.zzdid.zzbnh).appendQueryParameter(AppsFlyerProperties.APP_ID, packageName).appendQueryParameter("exceptiontype", str).appendQueryParameter("stacktrace", str2).appendQueryParameter("eids", TextUtils.join(",", zzyt.zzpn())).appendQueryParameter("exceptionkey", str3).appendQueryParameter("cl", "258870853").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", zzuo.zzok()).appendQueryParameter(SettingsJsonConstants.ANALYTICS_SAMPLING_RATE_KEY, Integer.toString(i)).appendQueryParameter("pb_tm", String.valueOf(zzuo.zzoj().zzd(zzyt.zzcsd)));
    }
}
