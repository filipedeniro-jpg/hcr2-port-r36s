package com.moat.analytics.mobile.iro;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class a {

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static WeakReference<Activity> f497 = null;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static boolean f498 = false;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static boolean f499 = false;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static Application f500;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static int f501;

    a() {
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static void m450(Application application) {
        f500 = application;
        if (f498) {
            return;
        }
        f498 = true;
        f500.registerActivityLifecycleCallbacks(new c());
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static Application m452() {
        return f500;
    }

    static class c implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        c() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
            int unused = a.f501 = 1;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
            try {
                a.f497 = new WeakReference<>(activity);
                int unused = a.f501 = 2;
                if (!a.f499) {
                    m456(true);
                }
                boolean unused2 = a.f499 = true;
                b.m461(3, "ActivityState", this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                o.m569(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
            try {
                a.f497 = new WeakReference<>(activity);
                int unused = a.f501 = 3;
                t.m598().m607();
                b.m461(3, "ActivityState", this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((j) MoatAnalytics.getInstance()).f599) {
                    e.m487(activity);
                }
            } catch (Exception e) {
                o.m569(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
            try {
                int unused = a.f501 = 4;
                if (a.m453(activity)) {
                    a.f497 = new WeakReference<>(null);
                }
                b.m461(3, "ActivityState", this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                o.m569(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            try {
                if (a.f501 != 3) {
                    boolean unused = a.f499 = false;
                    m456(false);
                }
                int unused2 = a.f501 = 5;
                if (a.m453(activity)) {
                    a.f497 = new WeakReference<>(null);
                }
                b.m461(3, "ActivityState", this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                o.m569(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
            try {
                if (a.f501 != 3 && a.f501 != 5) {
                    if (a.f499) {
                        m456(false);
                    }
                    boolean unused = a.f499 = false;
                }
                int unused2 = a.f501 = 6;
                b.m461(3, "ActivityState", this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (a.m453(activity)) {
                    a.f497 = new WeakReference<>(null);
                }
            } catch (Exception e) {
                o.m569(e);
            }
        }

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private static void m456(boolean z) {
            if (z) {
                b.m461(3, "ActivityState", null, "App became visible");
                if (t.m598().f660 != t.c.f677 || ((j) MoatAnalytics.getInstance()).f600) {
                    return;
                }
                k.m554().m562();
                return;
            }
            b.m461(3, "ActivityState", null, "App became invisible");
            if (t.m598().f660 != t.c.f677 || ((j) MoatAnalytics.getInstance()).f600) {
                return;
            }
            k.m554().m561();
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static /* synthetic */ boolean m453(Activity activity) {
        WeakReference<Activity> weakReference = f497;
        return weakReference != null && weakReference.get() == activity;
    }
}
