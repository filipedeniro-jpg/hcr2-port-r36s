package com.vungle.warren;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import com.vungle.warren.tasks.JobCreator;
import com.vungle.warren.tasks.JobInfo;
import com.vungle.warren.tasks.JobRunner;
import com.vungle.warren.tasks.runnable.JobRunnable;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
class VungleJobRunner implements JobRunner {
    private JobCreator creator;
    private Executor executor;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static final String TAG = VungleJobRunner.class.getSimpleName();
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    /* JADX WARN: Illegal instructions before constructor call */
    VungleJobRunner(@NonNull JobCreator jobCreator) {
        int i = NUMBER_OF_CORES;
        this(jobCreator, new ThreadPoolExecutor(i, i, 1L, TimeUnit.SECONDS, new PriorityBlockingQueue()));
    }

    VungleJobRunner(@NonNull JobCreator jobCreator, @NonNull Executor executor) {
        this.creator = jobCreator;
        this.executor = executor;
    }

    @Override // com.vungle.warren.tasks.JobRunner
    public void execute(@NonNull JobInfo jobInfo) {
        JobInfo jobInfoCopy = jobInfo.copy();
        String jobTag = jobInfoCopy.getJobTag();
        long delay = jobInfoCopy.getDelay();
        jobInfoCopy.setDelay(0L);
        final JobRunnable jobRunnable = new JobRunnable(jobInfoCopy, this.creator, this);
        if (delay <= 0) {
            this.executor.execute(jobRunnable);
            return;
        }
        if (jobInfoCopy.getUpdateCurrent()) {
            Log.d(TAG, "replacing pending job with new " + jobTag);
            handler.removeCallbacksAndMessages(jobTag);
        }
        handler.postAtTime(new Runnable() { // from class: com.vungle.warren.VungleJobRunner.1
            @Override // java.lang.Runnable
            public void run() {
                VungleJobRunner.this.executor.execute(jobRunnable);
            }
        }, jobTag, SystemClock.uptimeMillis() + delay);
    }
}
