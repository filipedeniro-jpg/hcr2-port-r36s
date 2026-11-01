package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
class a {
    static WeakReference<Activity> a = null;
    private static boolean b = false;
    private static Application c = null;
    private static int d = 0;
    private static boolean e = false;

    /* JADX INFO: renamed from: com.moat.analytics.mobile.vng.a$a, reason: collision with other inner class name */
    private static class C0087a implements Application.ActivityLifecycleCallbacks {
        C0087a() {
        }

        private static void a(boolean z) {
            if (z) {
                p.a(3, "ActivityState", (Object) null, "App became visible");
                if (w.a().a != w.d.ON || ((k) MoatAnalytics.getInstance()).c) {
                    return;
                }
                o.a().c();
                return;
            }
            p.a(3, "ActivityState", (Object) null, "App became invisible");
            if (w.a().a != w.d.ON || ((k) MoatAnalytics.getInstance()).c) {
                return;
            }
            o.a().d();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            int unused = a.d = 1;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            try {
                if (a.d != 3 && a.d != 5) {
                    if (a.e) {
                        a(false);
                    }
                    boolean unused = a.e = false;
                }
                int unused2 = a.d = 6;
                p.a(3, "ActivityState", this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (a.b(activity)) {
                    a.a = new WeakReference<>(null);
                }
            } catch (Exception e) {
                m.a(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            try {
                int unused = a.d = 4;
                if (a.b(activity)) {
                    a.a = new WeakReference<>(null);
                }
                p.a(3, "ActivityState", this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                m.a(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            try {
                a.a = new WeakReference<>(activity);
                int unused = a.d = 3;
                w.a().b();
                p.a(3, "ActivityState", this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((k) MoatAnalytics.getInstance()).b) {
                    f.a(activity);
                }
            } catch (Exception e) {
                m.a(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            try {
                a.a = new WeakReference<>(activity);
                int unused = a.d = 2;
                if (!a.e) {
                    a(true);
                }
                boolean unused2 = a.e = true;
                p.a(3, "ActivityState", this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                m.a(e);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            try {
                if (a.d != 3) {
                    boolean unused = a.e = false;
                    a(false);
                }
                int unused2 = a.d = 5;
                if (a.b(activity)) {
                    a.a = new WeakReference<>(null);
                }
                p.a(3, "ActivityState", this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                m.a(e);
            }
        }
    }

    a() {
    }

    static Application a() {
        return c;
    }

    static void a(Application application) {
        c = application;
        if (b) {
            return;
        }
        b = true;
        c.registerActivityLifecycleCallbacks(new C0087a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Activity activity) {
        WeakReference<Activity> weakReference = a;
        return weakReference != null && weakReference.get() == activity;
    }
}
