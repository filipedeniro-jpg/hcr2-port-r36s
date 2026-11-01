package com.facebook.ads.redexgen.X;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class ExecutorC0616Jd implements Executor {
    public static final ExecutorC0616Jd C = new ExecutorC0616Jd();
    private final Handler B = new Handler(Looper.getMainLooper());

    private ExecutorC0616Jd() {
    }

    public static void B(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            C.execute(runnable);
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable command) {
        this.B.post(command);
    }
}
