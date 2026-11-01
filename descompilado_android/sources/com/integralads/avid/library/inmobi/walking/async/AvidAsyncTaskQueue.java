package com.integralads.avid.library.inmobi.walking.async;

import androidx.annotation.VisibleForTesting;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class AvidAsyncTaskQueue implements AvidAsyncTask.AvidAsyncTaskListener {
    private static final int THREAD_COUNT = 1;
    private final ArrayDeque<AvidAsyncTask> pendingTasks = new ArrayDeque<>();
    private AvidAsyncTask currentTask = null;
    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue();
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, this.workQueue);

    public void submitTask(AvidAsyncTask avidAsyncTask) {
        avidAsyncTask.setListener(this);
        this.pendingTasks.add(avidAsyncTask);
        if (this.currentTask == null) {
            executeNextTask();
        }
    }

    private void executeNextTask() {
        this.currentTask = this.pendingTasks.poll();
        AvidAsyncTask avidAsyncTask = this.currentTask;
        if (avidAsyncTask != null) {
            avidAsyncTask.start(this.threadPoolExecutor);
        }
    }

    @Override // com.integralads.avid.library.inmobi.walking.async.AvidAsyncTask.AvidAsyncTaskListener
    public void onTaskCompleted(AvidAsyncTask avidAsyncTask) {
        this.currentTask = null;
        executeNextTask();
    }

    @VisibleForTesting
    AvidAsyncTask getCurrentTask() {
        return this.currentTask;
    }

    @VisibleForTesting
    ArrayDeque<AvidAsyncTask> getPendingTasks() {
        return this.pendingTasks;
    }
}
