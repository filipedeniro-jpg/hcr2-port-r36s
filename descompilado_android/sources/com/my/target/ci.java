package com.my.target;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.HashSet;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: Repeater.java */
/* JADX INFO: loaded from: classes2.dex */
public class ci {
    private final int ke;

    @NonNull
    private final WeakHashMap<Runnable, Boolean> kf = new WeakHashMap<>();

    @NonNull
    private final Runnable kg = new Runnable() { // from class: com.my.target.ci.1
        @Override // java.lang.Runnable
        public void run() {
            ci.this.bp();
        }
    };

    @NonNull
    public static final ci kd = new ci(1000);

    @NonNull
    private static final Handler handler = new Handler(Looper.getMainLooper());

    @NonNull
    public static final ci i(int i) {
        return new ci(i);
    }

    @VisibleForTesting
    public int bo() {
        return this.ke;
    }

    private ci(int i) {
        this.ke = i;
    }

    @AnyThread
    public void d(@NonNull Runnable runnable) {
        synchronized (this) {
            int size = this.kf.size();
            if (this.kf.put(runnable, true) == null && size == 0) {
                bq();
            }
        }
    }

    @AnyThread
    public void e(@NonNull Runnable runnable) {
        synchronized (this) {
            this.kf.remove(runnable);
            if (this.kf.size() == 0) {
                handler.removeCallbacks(this.kg);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bp() {
        synchronized (this) {
            Iterator it = new HashSet(this.kf.keySet()).iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            if (this.kf.keySet().size() > 0) {
                bq();
            }
        }
    }

    private void bq() {
        handler.postDelayed(this.kg, this.ke);
    }
}
