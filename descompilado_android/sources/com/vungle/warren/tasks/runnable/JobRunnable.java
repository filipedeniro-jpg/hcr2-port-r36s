package com.vungle.warren.tasks.runnable;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import com.vungle.warren.tasks.JobCreator;
import com.vungle.warren.tasks.JobInfo;
import com.vungle.warren.tasks.JobRunner;

/* JADX INFO: loaded from: classes2.dex */
public class JobRunnable extends PriorityRunnable {
    private static final String TAG = "JobRunnable";
    private final JobCreator creator;
    private final JobRunner jobRunner;
    private final JobInfo jobinfo;

    public JobRunnable(@NonNull JobInfo jobInfo, @NonNull JobCreator jobCreator, @NonNull JobRunner jobRunner) {
        this.jobinfo = jobInfo;
        this.creator = jobCreator;
        this.jobRunner = jobRunner;
    }

    @Override // com.vungle.warren.tasks.runnable.PriorityRunnable
    public Integer getPriority() {
        return Integer.valueOf(this.jobinfo.getPriority());
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            String jobTag = this.jobinfo.getJobTag();
            Bundle extras = this.jobinfo.getExtras();
            Log.d(TAG, "Start job " + jobTag + "Thread " + Thread.currentThread().getName());
            int iOnRunJob = this.creator.create(jobTag).onRunJob(extras, this.jobRunner);
            Log.d(TAG, "On job finished " + jobTag + " with result " + iOnRunJob);
            if (iOnRunJob == 2) {
                long jMakeNextRescedule = this.jobinfo.makeNextRescedule();
                if (jMakeNextRescedule > 0) {
                    this.jobinfo.setDelay(jMakeNextRescedule);
                    this.jobRunner.execute(this.jobinfo);
                    Log.d(TAG, "Rescheduling " + jobTag + " in " + jMakeNextRescedule);
                }
            }
        } catch (Throwable th) {
            Log.e(TAG, "Can't start job", th);
        }
    }
}
