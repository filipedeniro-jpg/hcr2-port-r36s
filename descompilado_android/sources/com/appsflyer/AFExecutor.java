package com.appsflyer;

import android.os.AsyncTask;
import android.os.Build;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AFExecutor {

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static AFExecutor f4;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private ScheduledExecutorService f5;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private Executor f6;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private Executor f7;

    private AFExecutor() {
    }

    public static AFExecutor getInstance() {
        if (f4 == null) {
            f4 = new AFExecutor();
        }
        return f4;
    }

    public Executor getSerialExecutor() {
        if (this.f7 == null) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.f7 = AsyncTask.SERIAL_EXECUTOR;
            } else {
                return Executors.newSingleThreadExecutor();
            }
        }
        return this.f7;
    }

    public Executor getThreadPoolExecutor() {
        Executor executor = this.f6;
        if (executor == null || ((executor instanceof ThreadPoolExecutor) && (((ThreadPoolExecutor) executor).isShutdown() || ((ThreadPoolExecutor) this.f6).isTerminated() || ((ThreadPoolExecutor) this.f6).isTerminating()))) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.f6 = Executors.newFixedThreadPool(2);
            } else {
                return Executors.newSingleThreadExecutor();
            }
        }
        return this.f6;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final ScheduledThreadPoolExecutor m6() {
        ScheduledExecutorService scheduledExecutorService = this.f5;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown() || this.f5.isTerminated()) {
            this.f5 = Executors.newScheduledThreadPool(2);
        }
        return (ScheduledThreadPoolExecutor) this.f5;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m7() {
        try {
            m5(this.f5);
            if (this.f6 instanceof ThreadPoolExecutor) {
                m5((ThreadPoolExecutor) this.f6);
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog("failed to stop Executors", th);
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static void m5(ExecutorService executorService) {
        try {
            AFLogger.afRDLog("shut downing executor ...");
            executorService.shutdown();
            executorService.awaitTermination(10L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            AFLogger.afRDLog("InterruptedException!!!");
        } finally {
            if (!executorService.isTerminated()) {
                AFLogger.afRDLog("killing non-finished tasks");
            }
            executorService.shutdownNow();
        }
    }
}
