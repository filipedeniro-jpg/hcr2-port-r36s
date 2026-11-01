package com.vungle.warren.tasks;

import android.os.Bundle;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class ReconfigJob implements Job {
    public static final String TAG = "com.vungle.warren.tasks.ReconfigJob";
    private ReconfigCall reconfigCall;

    public interface ReconfigCall {
        void reConfigVungle();
    }

    public ReconfigJob(@NonNull ReconfigCall reconfigCall) {
        this.reconfigCall = reconfigCall;
    }

    public static JobInfo makeJobInfo(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("appID", str);
        return new JobInfo(TAG).setExtras(bundle).setUpdateCurrent(true).setPriority(4);
    }

    @Override // com.vungle.warren.tasks.Job
    @NonNull
    public int onRunJob(Bundle bundle, JobRunner jobRunner) {
        if (bundle.getString("appID", null) == null) {
            return 1;
        }
        this.reconfigCall.reConfigVungle();
        return 0;
    }
}
