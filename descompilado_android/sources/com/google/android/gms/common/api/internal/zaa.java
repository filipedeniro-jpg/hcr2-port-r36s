package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0038zaa> zacl;

    public zaa(Activity activity) {
        this(C0038zaa.zaa(activity));
    }

    @VisibleForTesting(otherwise = 2)
    private zaa(C0038zaa c0038zaa) {
        this.zacl = new WeakReference<>(c0038zaa);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0038zaa c0038zaa = this.zacl.get();
        if (c0038zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        c0038zaa.zaa(runnable);
        return this;
    }

    /* JADX INFO: renamed from: com.google.android.gms.common.api.internal.zaa$zaa, reason: collision with other inner class name */
    @VisibleForTesting(otherwise = 2)
    static class C0038zaa extends LifecycleCallback {
        private List<Runnable> zacm;

        /* JADX INFO: Access modifiers changed from: private */
        public static C0038zaa zaa(Activity activity) {
            C0038zaa c0038zaa;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                c0038zaa = (C0038zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0038zaa.class);
                if (c0038zaa == null) {
                    c0038zaa = new C0038zaa(fragment);
                }
            }
            return c0038zaa;
        }

        private C0038zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zacm = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zacm.add(runnable);
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        @MainThread
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacm;
                this.zacm = new ArrayList();
            }
            Iterator<Runnable> it = list.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }
    }
}
