package com.integralads.avid.library.inmobi.walking.async;

/* JADX INFO: loaded from: classes2.dex */
public class AvidCleanupAsyncTask extends AvidAsyncTask {
    public AvidCleanupAsyncTask(AvidAsyncTask.StateProvider stateProvider) {
        super(stateProvider);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(Object... objArr) {
        this.stateProvider.setPreviousState(null);
        return null;
    }
}
