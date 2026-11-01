package net.hockeyapp.android.tasks;

import android.content.Context;
import java.net.URL;
import net.hockeyapp.android.listeners.DownloadFileListener;

/* JADX INFO: loaded from: classes2.dex */
public class GetFileSizeTask extends DownloadFileTask {
    private long size;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // net.hockeyapp.android.tasks.DownloadFileTask, android.os.AsyncTask
    public void onProgressUpdate(Integer... numArr) {
    }

    public GetFileSizeTask(Context context, String str, DownloadFileListener downloadFileListener) {
        super(context, str, downloadFileListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // net.hockeyapp.android.tasks.DownloadFileTask, android.os.AsyncTask
    public Long doInBackground(Void... voidArr) {
        try {
            return Long.valueOf(createConnection(new URL(getURLString()), 6).getContentLength());
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // net.hockeyapp.android.tasks.DownloadFileTask, android.os.AsyncTask
    public void onPostExecute(Long l) {
        this.size = l.longValue();
        if (this.size > 0) {
            this.notifier.downloadSuccessful(this);
        } else {
            this.notifier.downloadFailed(this, false);
        }
    }

    public long getSize() {
        return this.size;
    }
}
