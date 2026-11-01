package com.vungle.warren.tasks;

import android.text.TextUtils;
import com.vungle.warren.Storage;
import com.vungle.warren.persistence.Designer;

/* JADX INFO: loaded from: classes2.dex */
public class VungleJobCreator implements JobCreator {
    private final Designer designer;
    private final ReconfigJob.ReconfigCall reconfigCall;
    private final Storage storage;

    public VungleJobCreator(Storage storage, Designer designer, ReconfigJob.ReconfigCall reconfigCall) {
        this.storage = storage;
        this.designer = designer;
        this.reconfigCall = reconfigCall;
    }

    @Override // com.vungle.warren.tasks.JobCreator
    public Job create(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Job tag is not specified");
        }
        if (str.startsWith(ReconfigJob.TAG)) {
            return new ReconfigJob(this.reconfigCall);
        }
        if (str.startsWith(DownloadJob.TAG)) {
            return new DownloadJob(this.storage);
        }
        if (str.startsWith(SendReportsJob.TAG)) {
            return new SendReportsJob(this.storage);
        }
        if (str.startsWith(CleanupJob.TAG)) {
            return new CleanupJob(this.designer, this.storage);
        }
        throw new IllegalArgumentException("Unknown Job Type " + str);
    }
}
