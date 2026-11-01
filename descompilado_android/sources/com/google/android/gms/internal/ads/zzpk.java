package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzpk implements Application.ActivityLifecycleCallbacks {
    private final Application zzxh;
    private final WeakReference<Application.ActivityLifecycleCallbacks> zzxi;
    private boolean zzxj = false;

    public zzpk(Application application, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.zzxi = new WeakReference<>(activityLifecycleCallbacks);
        this.zzxh = application;
    }

    private final void zza(zzps zzpsVar) {
        try {
            Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = this.zzxi.get();
            if (activityLifecycleCallbacks != null) {
                zzpsVar.zza(activityLifecycleCallbacks);
            } else {
                if (this.zzxj) {
                    return;
                }
                this.zzxh.unregisterActivityLifecycleCallbacks(this);
                this.zzxj = true;
            }
        } catch (Exception e) {
            zzatm.zzc("Error while dispatching lifecycle callback.", e);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(new zzpn(this, activity, bundle));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        zza(new zzpm(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zza(new zzpp(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        zza(new zzpo(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        zza(new zzpr(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zza(new zzpq(this, activity, bundle));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        zza(new zzpt(this, activity));
    }
}
