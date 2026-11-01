package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0M, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0M {
    private final JV C;

    @Nullable
    private Application E;

    @Nullable
    private C0L G;
    private long D = 0;

    @Nullable
    private String F = null;

    @Nullable
    private AnonymousClass08 B = null;

    /* JADX WARN: Type inference failed for: r0v2, types: [com.facebook.ads.redexgen.X.0L] */
    private C0M(JV eventManager, final Activity activity, int buildVersion) {
        this.C = eventManager;
        this.E = activity.getApplication();
        this.G = new Application.ActivityLifecycleCallbacks(activity, this) { // from class: com.facebook.ads.redexgen.X.0L
            private final WeakReference<Activity> B;

            @Nullable
            private C0M C;

            {
                this.B = new WeakReference<>(activity);
                this.C = this;
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public final void onActivityCreated(Activity activity2, Bundle savedInstanceState) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public final void onActivityDestroyed(Activity activity2) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public final void onActivityPaused(Activity activity2) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public final void onActivityResumed(Activity activity2) {
                if (this.C == null) {
                    return;
                }
                Activity activity3 = this.B.get();
                if (activity3 != null && (activity3 == null || !activity2.equals(activity3))) {
                    return;
                }
                this.C.C();
                this.C = null;
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public final void onActivitySaveInstanceState(Activity activity2, Bundle outState) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public final void onActivityStarted(Activity activity2) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public final void onActivityStopped(Activity activity2) {
            }
        };
    }

    public final void A(@Nullable AnonymousClass08 actionOutcome) {
        this.B = actionOutcome;
    }

    public static C0M B(JV eventManager, Activity activity) {
        return C(eventManager, activity, Build.VERSION.SDK_INT);
    }

    @TargetApi(14)
    public final void B(String trackingToken) {
        this.F = trackingToken;
        if (this.G == null || this.E == null) {
            D(trackingToken, -1L, -1L, AnonymousClass08.CANNOT_TRACK);
        } else {
            this.D = System.currentTimeMillis();
            this.E.registerActivityLifecycleCallbacks(this.G);
        }
    }

    @VisibleForTesting
    @Nullable
    private static C0M C(JV eventManager, Activity activity, int buildVersion) {
        if (activity == null || buildVersion < 14) {
            return null;
        }
        return new C0M(eventManager, activity, buildVersion);
    }

    @TargetApi(14)
    public final void C() {
        D(this.F, this.D, System.currentTimeMillis(), this.B);
        if (this.E != null && this.G != null) {
            this.E.unregisterActivityLifecycleCallbacks(this.G);
            this.G = null;
            this.E = null;
        }
    }

    private void D(String trackingToken, long leaveTime, long backTime, @Nullable AnonymousClass08 actionOutcome) {
        HashMap map = new HashMap();
        map.put("leave_time", Long.toString(leaveTime));
        map.put("back_time", Long.toString(backTime));
        if (actionOutcome != null) {
            map.put("outcome", actionOutcome.name());
        }
        this.C.CD(trackingToken, map);
    }
}
