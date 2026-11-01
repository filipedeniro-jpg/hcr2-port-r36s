package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzpx {
    private final Object zzbpc = new Object();

    @GuardedBy("activityTrackerLock")
    private zzpw zzbpd = null;

    @GuardedBy("activityTrackerLock")
    private boolean zzbpe = false;

    public final void initialize(Context context) {
        synchronized (this.zzbpc) {
            if (!this.zzbpe) {
                if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                    return;
                }
                Context applicationContext = context.getApplicationContext();
                if (applicationContext == null) {
                    applicationContext = context;
                }
                Application application = applicationContext instanceof Application ? (Application) applicationContext : null;
                if (application == null) {
                    zzatm.zzeu("Can not cast Context to Application");
                    return;
                }
                if (this.zzbpd == null) {
                    this.zzbpd = new zzpw();
                }
                this.zzbpd.zza(application, context);
                this.zzbpe = true;
            }
        }
    }

    public final void zza(zzpy zzpyVar) {
        synchronized (this.zzbpc) {
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                if (this.zzbpd == null) {
                    this.zzbpd = new zzpw();
                }
                this.zzbpd.zza(zzpyVar);
            }
        }
    }

    public final void zzb(zzpy zzpyVar) {
        synchronized (this.zzbpc) {
            if (this.zzbpd == null) {
                return;
            }
            this.zzbpd.zzb(zzpyVar);
        }
    }

    @Nullable
    public final Activity getActivity() {
        synchronized (this.zzbpc) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            }
            if (this.zzbpd == null) {
                return null;
            }
            return this.zzbpd.getActivity();
        }
    }

    @Nullable
    public final Context getContext() {
        synchronized (this.zzbpc) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            }
            if (this.zzbpd == null) {
                return null;
            }
            return this.zzbpd.getContext();
        }
    }
}
