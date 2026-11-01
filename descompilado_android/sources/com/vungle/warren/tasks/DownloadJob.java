package com.vungle.warren.tasks;

import android.os.Bundle;
import android.util.Log;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.Storage;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Placement;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadJob implements Job {
    private static final long DEFAUT_DELAY = 5000;
    private static final String PLACEMENT_KEY = "placement";
    static final String TAG = "com.vungle.warren.tasks.DownloadJob";
    private Placement placement;
    private int result;
    private final Storage storage;

    public DownloadJob(Storage storage) {
        this.storage = storage;
    }

    public static JobInfo makeJobInfo(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("placement", str);
        return new JobInfo(TAG + " " + str).setUpdateCurrent(true).setExtras(bundle).setReschedulePolicy(z ? 5000L : -1L, 1).setPriority(4);
    }

    @Override // com.vungle.warren.tasks.Job
    public int onRunJob(Bundle bundle, JobRunner jobRunner) {
        String string = bundle.getString("placement", null);
        Collection<String> validPlacements = this.storage.getValidPlacements();
        if (string != null && validPlacements.contains(string)) {
            this.placement = (Placement) this.storage.load(string, Placement.class);
            if (this.placement == null) {
                return 1;
            }
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            Vungle.loadAd(string, new LoadAdCallback() { // from class: com.vungle.warren.tasks.DownloadJob.1
                @Override // com.vungle.warren.LoadAdCallback
                public void onAdLoad(String str) {
                    DownloadJob.this.result = 0;
                    countDownLatch.countDown();
                }

                @Override // com.vungle.warren.LoadAdCallback
                public void onError(String str, Throwable th) {
                    if (!(th instanceof VungleException)) {
                        DownloadJob.this.result = 2;
                    } else {
                        VungleException vungleException = (VungleException) th;
                        Log.e(DownloadJob.TAG, "scheduleJob: loadAd onError: " + vungleException.getExceptionCode());
                        if (vungleException.getExceptionCode() == 8 || vungleException.getExceptionCode() == 1 || vungleException.getExceptionCode() == 14) {
                            DownloadJob.this.result = 1;
                        } else {
                            DownloadJob.this.result = 2;
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            try {
                if (countDownLatch.await(1L, TimeUnit.MINUTES)) {
                    Log.d(TAG, "scheduleJob: latch await" + this.result);
                    return this.result;
                }
                Log.d(TAG, "scheduleJob: latch await else 2");
                return 2;
            } catch (InterruptedException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
        }
        return 1;
    }
}
