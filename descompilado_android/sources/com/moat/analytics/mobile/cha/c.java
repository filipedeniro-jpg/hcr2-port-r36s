package com.moat.analytics.mobile.cha;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class c {

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static boolean f295 = false;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static Application f296 = null;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static boolean f297 = false;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static WeakReference<Activity> f298;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static int f299;

    c() {
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static void m296(Application application) {
        f296 = application;
        if (f297) {
            return;
        }
        f297 = true;
        f296.registerActivityLifecycleCallbacks(new a());
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static Application m294() {
        return f296;
    }

    static class a implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
            int unused = c.f299 = 1;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
            try {
                c.f298 = new WeakReference<>(activity);
                int unused = c.f299 = 2;
                if (!c.f295) {
                    m298(true);
                }
                boolean unused2 = c.f295 = true;
                com.moat.analytics.mobile.cha.a.m273(3, "ActivityState", this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                o.m389(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
            try {
                c.f298 = new WeakReference<>(activity);
                int unused = c.f299 = 3;
                t.m423().m429();
                com.moat.analytics.mobile.cha.a.m273(3, "ActivityState", this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((f) MoatAnalytics.getInstance()).f319) {
                    e.m308(activity);
                }
            } catch (Exception e) {
                o.m389(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
            try {
                int unused = c.f299 = 4;
                if (c.m292(activity)) {
                    c.f298 = new WeakReference<>(null);
                }
                com.moat.analytics.mobile.cha.a.m273(3, "ActivityState", this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                o.m389(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            try {
                if (c.f299 != 3) {
                    boolean unused = c.f295 = false;
                    m298(false);
                }
                int unused2 = c.f299 = 5;
                if (c.m292(activity)) {
                    c.f298 = new WeakReference<>(null);
                }
                com.moat.analytics.mobile.cha.a.m273(3, "ActivityState", this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                o.m389(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
            try {
                if (c.f299 != 3 && c.f299 != 5) {
                    if (c.f295) {
                        m298(false);
                    }
                    boolean unused = c.f295 = false;
                }
                int unused2 = c.f299 = 6;
                com.moat.analytics.mobile.cha.a.m273(3, "ActivityState", this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (c.m292(activity)) {
                    c.f298 = new WeakReference<>(null);
                }
            } catch (Exception e) {
                o.m389(e);
            }
        }

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private static void m298(boolean z) {
            if (z) {
                com.moat.analytics.mobile.cha.a.m273(3, "ActivityState", null, "App became visible");
                if (t.m423().f441 != t.a.f452 || ((f) MoatAnalytics.getInstance()).f317) {
                    return;
                }
                n.m375().m385();
                return;
            }
            com.moat.analytics.mobile.cha.a.m273(3, "ActivityState", null, "App became invisible");
            if (t.m423().f441 != t.a.f452 || ((f) MoatAnalytics.getInstance()).f317) {
                return;
            }
            n.m375().m386();
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static /* synthetic */ boolean m292(Activity activity) {
        WeakReference<Activity> weakReference = f298;
        return weakReference != null && weakReference.get() == activity;
    }
}
