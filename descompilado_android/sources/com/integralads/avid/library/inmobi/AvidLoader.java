package com.integralads.avid.library.inmobi;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.inmobi.utils.NetworkUtils;

/* JADX INFO: loaded from: classes2.dex */
public class AvidLoader implements DownloadAvidTask.DownloadAvidTaskListener {
    private static final String AVID_URL = "https://mobile-static.adsafeprotected.com/avid-v2.js";
    private static final int DOWNLOAD_ATTEMPT_PERIOD = 2000;
    private static AvidLoader avidLoader = new AvidLoader();
    private DownloadAvidTask activeTask;
    private Context context;
    private AvidLoaderListener listener;
    private TaskRepeater taskRepeater;
    private TaskExecutor taskExecutor = new TaskExecutor();
    private final Runnable avidDownloadRunnable = new Runnable() { // from class: com.integralads.avid.library.inmobi.AvidLoader.1
        @Override // java.lang.Runnable
        public void run() {
            if (AvidLoader.this.context == null || !NetworkUtils.isNetworkAvailable(AvidLoader.this.context)) {
                AvidLoader.this.repeatLoading();
            } else {
                AvidLoader.this.loadAvidJSFromUrl();
            }
        }
    };

    public interface AvidLoaderListener {
        void onAvidLoaded();
    }

    public static AvidLoader getInstance() {
        return avidLoader;
    }

    public void registerAvidLoader(Context context) {
        this.context = context;
        this.taskRepeater = new TaskRepeater();
        loadAvidJSFromUrl();
    }

    public void unregisterAvidLoader() {
        TaskRepeater taskRepeater = this.taskRepeater;
        if (taskRepeater != null) {
            taskRepeater.cleanup();
            this.taskRepeater = null;
        }
        this.listener = null;
        this.context = null;
    }

    public void setListener(AvidLoaderListener avidLoaderListener) {
        this.listener = avidLoaderListener;
    }

    public AvidLoaderListener getListener() {
        return this.listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAvidJSFromUrl() {
        if (AvidBridge.isAvidJsReady() || this.activeTask != null) {
            return;
        }
        this.activeTask = new DownloadAvidTask();
        this.activeTask.setListener(this);
        this.taskExecutor.executeTask(this.activeTask);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void repeatLoading() {
        TaskRepeater taskRepeater = this.taskRepeater;
        if (taskRepeater != null) {
            taskRepeater.repeatLoading();
        }
    }

    @Override // com.integralads.avid.library.inmobi.DownloadAvidTask.DownloadAvidTaskListener
    public void onLoadAvid(String str) {
        this.activeTask = null;
        AvidBridge.setAvidJs(str);
        AvidLoaderListener avidLoaderListener = this.listener;
        if (avidLoaderListener != null) {
            avidLoaderListener.onAvidLoaded();
        }
    }

    @Override // com.integralads.avid.library.inmobi.DownloadAvidTask.DownloadAvidTaskListener
    public void failedToLoadAvid() {
        this.activeTask = null;
        repeatLoading();
    }

    @VisibleForTesting
    DownloadAvidTask getAsyncTask() {
        return this.activeTask;
    }

    @VisibleForTesting
    TaskRepeater getTaskRepeater() {
        return this.taskRepeater;
    }

    @VisibleForTesting
    void setTaskRepeater(TaskRepeater taskRepeater) {
        this.taskRepeater = taskRepeater;
    }

    @VisibleForTesting
    void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @VisibleForTesting
    static void setAvidLoaderInstance(AvidLoader avidLoader2) {
        avidLoader = avidLoader2;
    }

    public class TaskExecutor {
        public TaskExecutor() {
        }

        public void executeTask(DownloadAvidTask downloadAvidTask) {
            if (Build.VERSION.SDK_INT >= 11) {
                AvidLoader.this.activeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, AvidLoader.AVID_URL);
            } else {
                AvidLoader.this.activeTask.execute(AvidLoader.AVID_URL);
            }
        }
    }

    public class TaskRepeater {
        private Handler handler = new Handler();

        public TaskRepeater() {
        }

        public void repeatLoading() {
            this.handler.postDelayed(AvidLoader.this.avidDownloadRunnable, 2000L);
        }

        public void cleanup() {
            this.handler.removeCallbacks(AvidLoader.this.avidDownloadRunnable);
        }
    }
}
