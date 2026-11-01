package com.facebook.ads.redexgen.X;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MI implements Application.ActivityLifecycleCallbacks {
    private static final Deque<WeakReference<Activity>> B = new ArrayDeque();
    private static Context C;

    private MI() {
    }

    @Nullable
    public static synchronized Activity B() {
        Activity activity;
        boolean z = true;
        synchronized (MI.class) {
            activity = !B.isEmpty() ? B.peek().get() : null;
            Activity activityB = null;
            if (activity != null && Build.VERSION.SDK_INT >= 28) {
                z = false;
            }
            if (z) {
                activityB = MG.B();
            }
            if (C != null && z && activity != activityB) {
                C0583Hv c0583Hv = new C0583Hv("Activity discrepancies.", "Res: " + activity + ", ref: " + activityB);
                c0583Hv.D(1);
                C0585Hx.D(C, "act_util", C0584Hw.B, c0583Hv);
            }
            if (activity == null) {
                activity = activityB;
            }
        }
        return activity;
    }

    public static synchronized void C(Context applicationContext) {
        C = applicationContext;
        if (C instanceof Application) {
            ((Application) C).registerActivityLifecycleCallbacks(new MI());
        } else {
            C0585Hx.D(C, "api", C0584Hw.a, new C0583Hv("AppContext is not Application."));
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        boolean z = false;
        Activity activity2 = null;
        if (!B.isEmpty()) {
            activity2 = B.pop().get();
            z = activity2 == activity;
        }
        if (!z) {
            C0583Hv c0583Hv = new C0583Hv("Activity discrepancies.", "Top: " + activity2 + ", Paused: " + activity);
            c0583Hv.D(1);
            C0585Hx.D(C, "act_util", C0584Hw.B, c0583Hv);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        B.push(new WeakReference<>(activity));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
