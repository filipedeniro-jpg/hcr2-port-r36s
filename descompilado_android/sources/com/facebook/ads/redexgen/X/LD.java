package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class LD implements Executor {
    private final int B;

    @Nullable
    private Executor C;
    private ThreadPoolExecutor D;
    public static final Executor G = new LD("NETWORK", 0);
    public static final Executor E = new LD("ASYNC_TASK", 32);
    public static final Executor F = new LD("DB", 0);
    private static int H = 32;

    @VisibleForTesting
    private LD(String poolName, int allowedAsyncTaskExecutorCapacity) {
        this.B = allowedAsyncTaskExecutorCapacity;
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        int KEEP_ALIVE_SECONDS = Math.max(2, Math.min(iAvailableProcessors - 1, 4));
        this.D = new ThreadPoolExecutor(KEEP_ALIVE_SECONDS, (iAvailableProcessors * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new LE(this, poolName));
        this.D.allowCoreThreadTimeOut(true);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable command) {
        if (this.C == null && Looper.myLooper() == Looper.getMainLooper()) {
            this.C = AsyncTask.THREAD_POOL_EXECUTOR;
        }
        if ((this.C instanceof ThreadPoolExecutor) && ((ThreadPoolExecutor) this.C).getQueue().size() < this.B) {
            this.C.execute(command);
            return;
        }
        int size = this.D.getQueue().size();
        synchronized (LD.class) {
            int i = H;
            int reservedExecutorQueueSize = H;
            if (size == reservedExecutorQueueSize) {
                int reservedExecutorQueueSize2 = H;
                H = reservedExecutorQueueSize2 * 2;
                Context contextB = HM.B();
                if (contextB != null) {
                    C0585Hx.D(contextB, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.RB, new C0583Hv("Tasks queue too long.", "Size = " + i));
                }
            }
        }
        this.D.execute(command);
    }
}
