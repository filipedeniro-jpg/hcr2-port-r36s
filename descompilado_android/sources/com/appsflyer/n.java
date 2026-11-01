package com.appsflyer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import java.lang.ref.WeakReference;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 14)
final class n implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static n f181;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private boolean f184 = false;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private boolean f183 = true;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private a f182 = null;

    interface a {
        /* JADX INFO: renamed from: ˊ */
        void mo82(WeakReference<Context> weakReference);

        /* JADX INFO: renamed from: ॱ */
        void mo83(Activity activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    n() {
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static /* synthetic */ boolean m161(n nVar) {
        nVar.f184 = false;
        return false;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static n m160() {
        if (f181 == null) {
            f181 = new n();
        }
        return f181;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    public static n m162() {
        n nVar = f181;
        if (nVar != null) {
            return nVar;
        }
        throw new IllegalStateException("Foreground is not initialised - invoke at least once with parameter init/get");
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    public final void m166(Application application, a aVar) {
        this.f182 = aVar;
        if (Build.VERSION.SDK_INT >= 14) {
            application.registerActivityLifecycleCallbacks(f181);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        this.f183 = false;
        boolean z = !this.f184;
        this.f184 = true;
        if (z) {
            try {
                this.f182.mo83(activity);
            } catch (Exception e) {
                AFLogger.afErrorLog("Listener threw exception! ", e);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        this.f183 = true;
        try {
            new b(new WeakReference(activity.getApplicationContext())).executeOnExecutor(AFExecutor.getInstance().getThreadPoolExecutor(), new Void[0]);
        } catch (RejectedExecutionException e) {
            AFLogger.afErrorLog("backgroundTask.executeOnExecutor failed with RejectedExecutionException Exception", e);
        } catch (Throwable th) {
            AFLogger.afErrorLog("backgroundTask.executeOnExecutor failed with Exception", th);
        }
    }

    class b extends AsyncTask<Void, Void, Void> {

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private WeakReference<Context> f185;

        @Override // android.os.AsyncTask
        protected final /* synthetic */ Void doInBackground(Void[] voidArr) {
            return m167();
        }

        public b(WeakReference<Context> weakReference) {
            this.f185 = weakReference;
        }

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private Void m167() {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                AFLogger.afErrorLog("Sleeping attempt failed (essential for background state verification)\n", e);
            }
            if (n.this.f184 && n.this.f183) {
                n.m161(n.this);
                try {
                    n.this.f182.mo82(this.f185);
                } catch (Exception e2) {
                    AFLogger.afErrorLog("Listener threw exception! ", e2);
                    cancel(true);
                }
            }
            this.f185.clear();
            return null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        AFDeepLinkManager.getInstance().collectIntentsFromActivities(activity.getIntent());
    }
}
