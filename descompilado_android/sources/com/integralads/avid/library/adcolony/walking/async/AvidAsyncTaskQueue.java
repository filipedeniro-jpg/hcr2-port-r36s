package com.integralads.avid.library.adcolony.walking.async;

import androidx.annotation.VisibleForTesting;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class AvidAsyncTaskQueue implements AvidAsyncTask.AvidAsyncTaskListener {
    private static final int a = 1;
    private final ArrayDeque<AvidAsyncTask> d = new ArrayDeque<>();
    private AvidAsyncTask e = null;
    private final BlockingQueue<Runnable> b = new LinkedBlockingQueue();
    private final ThreadPoolExecutor c = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, this.b);

    public void submitTask(AvidAsyncTask avidAsyncTask) {
        avidAsyncTask.setListener(this);
        this.d.add(avidAsyncTask);
        if (this.e == null) {
            c();
        }
    }

    private void c() {
        this.e = this.d.poll();
        AvidAsyncTask avidAsyncTask = this.e;
        if (avidAsyncTask != null) {
            avidAsyncTask.start(this.c);
        }
    }

    @Override // com.integralads.avid.library.adcolony.walking.async.AvidAsyncTask.AvidAsyncTaskListener
    public void onTaskCompleted(AvidAsyncTask avidAsyncTask) {
        this.e = null;
        c();
    }

    @VisibleForTesting
    AvidAsyncTask a() {
        return this.e;
    }

    @VisibleForTesting
    ArrayDeque<AvidAsyncTask> b() {
        return this.d;
    }
}
