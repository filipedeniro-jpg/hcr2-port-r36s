package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdm implements Application.ActivityLifecycleCallbacks {
    private final Application zzxh;
    private final WeakReference<Application.ActivityLifecycleCallbacks> zzxi;
    private boolean zzxj = false;

    public zzdm(Application application, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.zzxi = new WeakReference<>(activityLifecycleCallbacks);
        this.zzxh = application;
    }

    private final void zza(zzdu zzduVar) {
        try {
            Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = this.zzxi.get();
            if (activityLifecycleCallbacks != null) {
                zzduVar.zza(activityLifecycleCallbacks);
            } else {
                if (this.zzxj) {
                    return;
                }
                this.zzxh.unregisterActivityLifecycleCallbacks(this);
                this.zzxj = true;
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(new zzdl(this, activity, bundle));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        zza(new zzdo(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zza(new zzdn(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        zza(new zzdq(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        zza(new zzdp(this, activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zza(new zzds(this, activity, bundle));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        zza(new zzdr(this, activity));
    }
}
