package com.integralads.avid.library.adcolony;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.utils.NetworkUtils;

/* JADX INFO: loaded from: classes2.dex */
public class AvidLoader implements DownloadAvidTask.DownloadAvidTaskListener {
    private static final int a = 2000;
    private static final String b = "https://mobile-static.adsafeprotected.com/avid-v2.js";
    private static AvidLoader c = new AvidLoader();
    private AvidLoaderListener d;
    private DownloadAvidTask e;
    private Context f;
    private TaskRepeater h;
    private TaskExecutor g = new TaskExecutor();
    private final Runnable i = new Runnable() { // from class: com.integralads.avid.library.adcolony.AvidLoader.1
        @Override // java.lang.Runnable
        public void run() {
            if (AvidLoader.this.f == null || !NetworkUtils.isNetworkAvailable(AvidLoader.this.f)) {
                AvidLoader.this.d();
            } else {
                AvidLoader.this.c();
            }
        }
    };

    public interface AvidLoaderListener {
        void onAvidLoaded();
    }

    public static AvidLoader getInstance() {
        return c;
    }

    public void registerAvidLoader(Context context) {
        this.f = context;
        this.h = new TaskRepeater();
        c();
    }

    public void unregisterAvidLoader() {
        TaskRepeater taskRepeater = this.h;
        if (taskRepeater != null) {
            taskRepeater.cleanup();
            this.h = null;
        }
        this.d = null;
        this.f = null;
    }

    public void setListener(AvidLoaderListener avidLoaderListener) {
        this.d = avidLoaderListener;
    }

    public AvidLoaderListener getListener() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (AvidBridge.isAvidJsReady() || this.e != null) {
            return;
        }
        this.e = new DownloadAvidTask();
        this.e.setListener(this);
        this.g.executeTask(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        TaskRepeater taskRepeater = this.h;
        if (taskRepeater != null) {
            taskRepeater.repeatLoading();
        }
    }

    @Override // com.integralads.avid.library.adcolony.DownloadAvidTask.DownloadAvidTaskListener
    public void onLoadAvid(String str) {
        this.e = null;
        AvidBridge.setAvidJs(str);
        AvidLoaderListener avidLoaderListener = this.d;
        if (avidLoaderListener != null) {
            avidLoaderListener.onAvidLoaded();
        }
    }

    @Override // com.integralads.avid.library.adcolony.DownloadAvidTask.DownloadAvidTaskListener
    public void failedToLoadAvid() {
        this.e = null;
        d();
    }

    @VisibleForTesting
    DownloadAvidTask a() {
        return this.e;
    }

    @VisibleForTesting
    TaskRepeater b() {
        return this.h;
    }

    @VisibleForTesting
    void a(TaskRepeater taskRepeater) {
        this.h = taskRepeater;
    }

    @VisibleForTesting
    void a(TaskExecutor taskExecutor) {
        this.g = taskExecutor;
    }

    @VisibleForTesting
    static void a(AvidLoader avidLoader) {
        c = avidLoader;
    }

    public class TaskExecutor {
        public TaskExecutor() {
        }

        public void executeTask(DownloadAvidTask downloadAvidTask) {
            if (Build.VERSION.SDK_INT >= 11) {
                AvidLoader.this.e.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, AvidLoader.b);
            } else {
                AvidLoader.this.e.execute(AvidLoader.b);
            }
        }
    }

    public class TaskRepeater {
        private Handler b = new Handler();

        public TaskRepeater() {
        }

        public void repeatLoading() {
            this.b.postDelayed(AvidLoader.this.i, 2000L);
        }

        public void cleanup() {
            this.b.removeCallbacks(AvidLoader.this.i);
        }
    }
}
