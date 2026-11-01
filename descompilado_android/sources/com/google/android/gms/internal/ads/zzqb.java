package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
@TargetApi(14)
public final class zzqb extends Thread {
    private final Object lock;
    private boolean started;
    private final int zzboi;
    private final int zzbok;
    private final boolean zzbol;
    private boolean zzbpi;
    private boolean zzbpj;
    private final zzpu zzbpk;
    private final int zzbpl;
    private final int zzbpm;
    private final int zzbpn;
    private final int zzbpo;
    private final int zzbpp;
    private final int zzbpq;
    private final String zzbpr;
    private final boolean zzbps;
    private final boolean zzbpt;

    public zzqb() {
        this(new zzpu());
    }

    @VisibleForTesting
    private zzqb(zzpu zzpuVar) {
        this.started = false;
        this.zzbpi = false;
        this.zzbpj = false;
        this.zzbpk = zzpuVar;
        this.lock = new Object();
        this.zzboi = ((Integer) zzuo.zzoj().zzd(zzyt.zzcii)).intValue();
        this.zzbpm = ((Integer) zzuo.zzoj().zzd(zzyt.zzcij)).intValue();
        this.zzbok = ((Integer) zzuo.zzoj().zzd(zzyt.zzcik)).intValue();
        this.zzbpn = ((Integer) zzuo.zzoj().zzd(zzyt.zzcil)).intValue();
        this.zzbpo = ((Integer) zzuo.zzoj().zzd(zzyt.zzcin)).intValue();
        this.zzbpp = ((Integer) zzuo.zzoj().zzd(zzyt.zzcio)).intValue();
        this.zzbpq = ((Integer) zzuo.zzoj().zzd(zzyt.zzcip)).intValue();
        this.zzbpl = ((Integer) zzuo.zzoj().zzd(zzyt.zzcim)).intValue();
        this.zzbpr = (String) zzuo.zzoj().zzd(zzyt.zzcir);
        this.zzbps = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcis)).booleanValue();
        this.zzbol = ((Boolean) zzuo.zzoj().zzd(zzyt.zzciw)).booleanValue();
        this.zzbpt = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcix)).booleanValue();
        setName("ContentFetchTask");
    }

    public final void zzlr() {
        synchronized (this.lock) {
            if (this.started) {
                zzatm.zzdv("Content hash thread already started, quiting...");
            } else {
                this.started = true;
                start();
            }
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0096 */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r4 = this;
        L0:
            boolean r0 = zzls()     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            if (r0 == 0) goto L5a
            com.google.android.gms.internal.ads.zzpx r0 = com.google.android.gms.ads.internal.zzp.zzkb()     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            android.app.Activity r0 = r0.getActivity()     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            if (r0 != 0) goto L19
            java.lang.String r0 = "ContentFetchThread: no activity. Sleeping."
            com.google.android.gms.internal.ads.zzatm.zzdv(r0)     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            r4.zzlu()     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            goto L62
        L19:
            if (r0 == 0) goto L62
            r1 = 0
            android.view.Window r2 = r0.getWindow()     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L7b
            if (r2 == 0) goto L4c
            android.view.Window r2 = r0.getWindow()     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L7b
            android.view.View r2 = r2.getDecorView()     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L7b
            if (r2 == 0) goto L4c
            android.view.Window r0 = r0.getWindow()     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L7b
            android.view.View r0 = r0.getDecorView()     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L7b
            r2 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r0 = r0.findViewById(r2)     // Catch: java.lang.Exception -> L3d java.lang.InterruptedException -> L7b
            r1 = r0
            goto L4c
        L3d:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzatb r2 = com.google.android.gms.ads.internal.zzp.zzkc()     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            java.lang.String r3 = "ContentFetchTask.extractContent"
            r2.zza(r0, r3)     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            java.lang.String r0 = "Failed getting root view of activity. Content not extracted."
            com.google.android.gms.internal.ads.zzatm.zzdv(r0)     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
        L4c:
            if (r1 == 0) goto L62
            if (r1 != 0) goto L51
            goto L62
        L51:
            com.google.android.gms.internal.ads.zzqa r0 = new com.google.android.gms.internal.ads.zzqa     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            r0.<init>(r4, r1)     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            r1.post(r0)     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            goto L62
        L5a:
            java.lang.String r0 = "ContentFetchTask: sleeping"
            com.google.android.gms.internal.ads.zzatm.zzdv(r0)     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            r4.zzlu()     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
        L62:
            int r0 = r4.zzbpl     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            int r0 = r0 * 1000
            long r0 = (long) r0     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            java.lang.Thread.sleep(r0)     // Catch: java.lang.Exception -> L6b java.lang.InterruptedException -> L7b
            goto L81
        L6b:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.ads.zzatm.zzc(r1, r0)
            com.google.android.gms.internal.ads.zzatb r1 = com.google.android.gms.ads.internal.zzp.zzkc()
            java.lang.String r2 = "ContentFetchTask.run"
            r1.zza(r0, r2)
            goto L81
        L7b:
            r0 = move-exception
            java.lang.String r1 = "Error in ContentFetchTask"
            com.google.android.gms.internal.ads.zzatm.zzc(r1, r0)
        L81:
            java.lang.Object r0 = r4.lock
            monitor-enter(r0)
        L84:
            boolean r1 = r4.zzbpi     // Catch: java.lang.Throwable -> L96
            if (r1 == 0) goto L93
            java.lang.String r1 = "ContentFetchTask: waiting"
            com.google.android.gms.internal.ads.zzatm.zzdv(r1)     // Catch: java.lang.InterruptedException -> L84 java.lang.Throwable -> L96
            java.lang.Object r1 = r4.lock     // Catch: java.lang.InterruptedException -> L84 java.lang.Throwable -> L96
            r1.wait()     // Catch: java.lang.InterruptedException -> L84 java.lang.Throwable -> L96
            goto L84
        L93:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L96
            goto L0
        L96:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L96
            goto L9a
        L99:
            throw r1
        L9a:
            goto L99
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqb.run():void");
    }

    @VisibleForTesting
    final void zzi(View view) {
        try {
            zzpv zzpvVar = new zzpv(this.zzboi, this.zzbpm, this.zzbok, this.zzbpn, this.zzbpo, this.zzbpp, this.zzbpq, this.zzbol);
            Context context = com.google.android.gms.ads.internal.zzp.zzkb().getContext();
            if (context != null && !TextUtils.isEmpty(this.zzbpr)) {
                String str = (String) view.getTag(context.getResources().getIdentifier((String) zzuo.zzoj().zzd(zzyt.zzciq), "id", context.getPackageName()));
                if (str != null && str.equals(this.zzbpr)) {
                    return;
                }
            }
            zzqf zzqfVarZza = zza(view, zzpvVar);
            zzpvVar.zzlp();
            if (zzqfVarZza.zzbqa == 0 && zzqfVarZza.zzbqb == 0) {
                return;
            }
            if (zzqfVarZza.zzbqb == 0 && zzpvVar.zzlq() == 0) {
                return;
            }
            if (zzqfVarZza.zzbqb == 0 && this.zzbpk.zza(zzpvVar)) {
                return;
            }
            this.zzbpk.zzc(zzpvVar);
        } catch (Exception e) {
            zzatm.zzc("Exception in fetchContentOnUIThread", e);
            com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "ContentFetchTask.fetchContent");
        }
    }

    @VisibleForTesting
    private static boolean zzls() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            Context context = com.google.android.gms.ads.internal.zzp.zzkb().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance != 100 || keyguardManager.inKeyguardRestrictedInputMode()) {
                        return false;
                    }
                    PowerManager powerManager = (PowerManager) context.getSystemService("power");
                    return powerManager == null ? false : powerManager.isScreenOn();
                }
            }
            return false;
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    @VisibleForTesting
    private final zzqf zza(@Nullable View view, zzpv zzpvVar) {
        boolean z;
        if (view == null) {
            return new zzqf(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (!TextUtils.isEmpty(text)) {
                zzpvVar.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), view.getWidth(), view.getHeight());
                return new zzqf(this, 1, 0);
            }
            return new zzqf(this, 0, 0);
        }
        if ((view instanceof WebView) && !(view instanceof zzbbc)) {
            WebView webView = (WebView) view;
            if (PlatformVersion.isAtLeastKitKat()) {
                zzpvVar.zzln();
                webView.post(new zzqd(this, zzpvVar, webView, globalVisibleRect));
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return new zzqf(this, 0, 1);
            }
            return new zzqf(this, 0, 0);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                zzqf zzqfVarZza = zza(viewGroup.getChildAt(i3), zzpvVar);
                i += zzqfVarZza.zzbqa;
                i2 += zzqfVarZza.zzbqb;
            }
            return new zzqf(this, i, i2);
        }
        return new zzqf(this, 0, 0);
    }

    @VisibleForTesting
    final void zza(zzpv zzpvVar, WebView webView, String str, boolean z) {
        zzpvVar.zzlm();
        try {
            if (!TextUtils.isEmpty(str)) {
                String strOptString = new JSONObject(str).optString(MimeTypes.BASE_TYPE_TEXT);
                if (!this.zzbps && !TextUtils.isEmpty(webView.getTitle())) {
                    String title = webView.getTitle();
                    StringBuilder sb = new StringBuilder(String.valueOf(title).length() + 1 + String.valueOf(strOptString).length());
                    sb.append(title);
                    sb.append("\n");
                    sb.append(strOptString);
                    zzpvVar.zza(sb.toString(), z, webView.getX(), webView.getY(), webView.getWidth(), webView.getHeight());
                } else {
                    zzpvVar.zza(strOptString, z, webView.getX(), webView.getY(), webView.getWidth(), webView.getHeight());
                }
            }
            if (zzpvVar.zzlh()) {
                this.zzbpk.zzb(zzpvVar);
            }
        } catch (JSONException unused) {
            zzatm.zzdv("Json string may be malformed.");
        } catch (Throwable th) {
            zzatm.zzb("Failed to get webview content.", th);
            com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    public final zzpv zzlt() {
        return this.zzbpk.zzn(this.zzbpt);
    }

    public final void wakeup() {
        synchronized (this.lock) {
            this.zzbpi = false;
            this.lock.notifyAll();
            zzatm.zzdv("ContentFetchThread: wakeup");
        }
    }

    private final void zzlu() {
        synchronized (this.lock) {
            this.zzbpi = true;
            boolean z = this.zzbpi;
            StringBuilder sb = new StringBuilder(42);
            sb.append("ContentFetchThread: paused, mPause = ");
            sb.append(z);
            zzatm.zzdv(sb.toString());
        }
    }

    public final boolean zzlv() {
        return this.zzbpi;
    }
}
