package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(14)
final class zzpw implements Application.ActivityLifecycleCallbacks {
    private Runnable zzbpa;
    private long zzbpb;
    private Context zzlk;

    @Nullable
    private Activity zzzr;
    private final Object lock = new Object();
    private boolean foreground = true;
    private boolean zzbox = false;

    @GuardedBy("lock")
    private final List<zzpy> zzboy = new ArrayList();

    @GuardedBy("lock")
    private final List<zzqn> zzboz = new ArrayList();
    private boolean zzye = false;

    zzpw() {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    public final void zza(Application application, Context context) {
        if (this.zzye) {
            return;
        }
        application.registerActivityLifecycleCallbacks(this);
        if (context instanceof Activity) {
            setActivity((Activity) context);
        }
        this.zzlk = application;
        this.zzbpb = ((Long) zzuo.zzoj().zzd(zzyt.zzckh)).longValue();
        this.zzye = true;
    }

    public final void zza(zzpy zzpyVar) {
        synchronized (this.lock) {
            this.zzboy.add(zzpyVar);
        }
    }

    public final void zzb(zzpy zzpyVar) {
        synchronized (this.lock) {
            this.zzboy.remove(zzpyVar);
        }
    }

    @Nullable
    public final Activity getActivity() {
        return this.zzzr;
    }

    @Nullable
    public final Context getContext() {
        return this.zzlk;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        setActivity(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        setActivity(activity);
        this.zzbox = false;
        boolean z = !this.foreground;
        this.foreground = true;
        if (this.zzbpa != null) {
            zzatv.zzdsk.removeCallbacks(this.zzbpa);
        }
        synchronized (this.lock) {
            Iterator<zzqn> it = this.zzboz.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onActivityResumed(activity);
                } catch (Exception e) {
                    com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "AppActivityTracker.ActivityListener.onActivityResumed");
                    zzawo.zzc("", e);
                }
            }
            if (z) {
                Iterator<zzpy> it2 = this.zzboy.iterator();
                while (it2.hasNext()) {
                    try {
                        it2.next().zzo(true);
                    } catch (Exception e2) {
                        zzawo.zzc("", e2);
                    }
                }
            } else {
                zzatm.zzdv("App is still foreground.");
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        setActivity(activity);
        synchronized (this.lock) {
            Iterator<zzqn> it = this.zzboz.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onActivityPaused(activity);
                } catch (Exception e) {
                    com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "AppActivityTracker.ActivityListener.onActivityPaused");
                    zzawo.zzc("", e);
                }
            }
        }
        this.zzbox = true;
        if (this.zzbpa != null) {
            zzatv.zzdsk.removeCallbacks(this.zzbpa);
        }
        Handler handler = zzatv.zzdsk;
        zzpz zzpzVar = new zzpz(this);
        this.zzbpa = zzpzVar;
        handler.postDelayed(zzpzVar, this.zzbpb);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        synchronized (this.lock) {
            if (this.zzzr == null) {
                return;
            }
            if (this.zzzr.equals(activity)) {
                this.zzzr = null;
            }
            Iterator<zzqn> it = this.zzboz.iterator();
            while (it.hasNext()) {
                try {
                    if (it.next().zza(activity)) {
                        it.remove();
                    }
                } catch (Exception e) {
                    com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "AppActivityTracker.ActivityListener.onActivityDestroyed");
                    zzawo.zzc("", e);
                }
            }
        }
    }

    private final void setActivity(Activity activity) {
        synchronized (this.lock) {
            if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.zzzr = activity;
            }
        }
    }

    static /* synthetic */ boolean zza(zzpw zzpwVar, boolean z) {
        zzpwVar.foreground = false;
        return false;
    }
}
