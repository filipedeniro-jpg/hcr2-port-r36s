package com.vungle.warren.network;

import android.content.Context;
import android.util.Pair;
import com.tonyodev.fetch.Fetch;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class APKDirectDownloader extends FetchDownloader {
    public APKDirectDownloader(Context context) {
        super(context);
        new Fetch.Settings(context).setConcurrentDownloadsLimit(5).enableLogging(true).apply();
    }

    @Override // com.vungle.warren.network.FetchDownloader, com.vungle.warren.network.Downloader
    public void pause() {
        if (this.operations != null) {
            Iterator<Map.Entry<Long, Pair<Downloader.Listener, File>>> it = this.operations.entrySet().iterator();
            while (it.hasNext()) {
                this.fetch.pause(it.next().getKey().longValue());
            }
        }
    }

    @Override // com.vungle.warren.network.FetchDownloader, com.vungle.warren.network.Downloader
    public void resume() {
        if (this.operations != null) {
            Iterator<Map.Entry<Long, Pair<Downloader.Listener, File>>> it = this.operations.entrySet().iterator();
            while (it.hasNext()) {
                this.fetch.resume(it.next().getKey().longValue());
            }
        }
    }

    public boolean isDownloadTaskRunning() {
        return (this.operations == null || this.operations.isEmpty()) ? false : true;
    }
}
