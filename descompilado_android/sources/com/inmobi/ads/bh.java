package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: PicassoWrapper.java */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(14)
public class bh {
    private static final String a = "bh";

    @SuppressLint({"StaticFieldLeak"})
    private static volatile Picasso b;
    private static final Object c = new Object();
    private static List<WeakReference<Context>> d = new ArrayList();
    private static Application.ActivityLifecycleCallbacks e = new Application.ActivityLifecycleCallbacks() { // from class: com.inmobi.ads.bh.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
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

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
            synchronized (bh.c) {
                if (bh.b != null && bh.c(activity)) {
                    activity.getApplication().unregisterActivityLifecycleCallbacks(bh.e);
                    bh.d.remove(activity);
                    if (bh.d.isEmpty()) {
                        String unused = bh.a;
                        StringBuilder sb = new StringBuilder("Picasso instance ");
                        sb.append(bh.b.toString());
                        sb.append(" shutdown");
                        bh.b.shutdown();
                        bh.f();
                    }
                }
            }
        }
    };

    static /* synthetic */ Picasso f() {
        b = null;
        return null;
    }

    public static Picasso a(Context context) {
        synchronized (c) {
            if (!c(context)) {
                d.add(new WeakReference<>(context));
            }
            if (b == null) {
                b = new Picasso.Builder(context).build();
                if (context instanceof Activity) {
                    ((Activity) context).getApplication().registerActivityLifecycleCallbacks(e);
                }
            }
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(Context context) {
        for (int i = 0; i < d.size(); i++) {
            Context context2 = d.get(i).get();
            if (context2 != null && context2.equals(context)) {
                return true;
            }
        }
        return false;
    }
}
