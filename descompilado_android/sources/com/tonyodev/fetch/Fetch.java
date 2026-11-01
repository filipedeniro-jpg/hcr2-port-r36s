package com.tonyodev.fetch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.tonyodev.fetch.callback.FetchCall;
import com.tonyodev.fetch.callback.FetchTask;
import com.tonyodev.fetch.exception.EnqueueException;
import com.tonyodev.fetch.listener.FetchListener;
import com.tonyodev.fetch.request.Request;
import com.tonyodev.fetch.request.RequestInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes2.dex */
public final class Fetch implements FetchConst {
    private final LocalBroadcastManager broadcastManager;
    private final Context context;
    private final DatabaseHelper dbHelper;
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final ConcurrentMap<Request, FetchCallRunnable> callsMap = new ConcurrentHashMap();
    private static final FetchCallRunnable.Callback callsCallback = new FetchCallRunnable.Callback() { // from class: com.tonyodev.fetch.Fetch.1
        @Override // com.tonyodev.fetch.FetchCallRunnable.Callback
        public void onDone(Request request) {
            Fetch.callsMap.remove(request);
        }
    };
    private final List<FetchListener> listeners = new ArrayList();
    private volatile boolean isReleased = false;
    private final BroadcastReceiver updateReceiver = new BroadcastReceiver() { // from class: com.tonyodev.fetch.Fetch.4
        private long downloadedBytes;
        private int error;
        private long fileSize;
        private long id;
        private int progress;
        private int status;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            this.id = intent.getLongExtra(FetchService.EXTRA_ID, -1L);
            this.status = intent.getIntExtra(FetchService.EXTRA_STATUS, -1);
            this.progress = intent.getIntExtra(FetchService.EXTRA_PROGRESS, -1);
            this.downloadedBytes = intent.getLongExtra(FetchService.EXTRA_DOWNLOADED_BYTES, -1L);
            this.fileSize = intent.getLongExtra(FetchService.EXTRA_FILE_SIZE, -1L);
            this.error = intent.getIntExtra(FetchService.EXTRA_ERROR, -1);
            try {
                Iterator listenerIterator = Fetch.this.getListenerIterator();
                while (listenerIterator.hasNext()) {
                    ((FetchListener) listenerIterator.next()).onUpdate(this.id, this.status, this.progress, this.downloadedBytes, this.fileSize, this.error);
                }
            } catch (Exception e) {
                if (Fetch.this.isLoggingEnabled()) {
                    e.printStackTrace();
                }
            }
        }
    };
    private final BroadcastReceiver networkReceiver = new BroadcastReceiver() { // from class: com.tonyodev.fetch.Fetch.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            FetchService.processPendingRequests(context);
        }
    };

    private Fetch(Context context) {
        this.context = context.getApplicationContext();
        this.broadcastManager = LocalBroadcastManager.getInstance(this.context);
        this.dbHelper = DatabaseHelper.getInstance(this.context);
        this.dbHelper.setLoggingEnabled(isLoggingEnabled());
        this.broadcastManager.registerReceiver(this.updateReceiver, FetchService.getEventUpdateFilter());
        this.context.registerReceiver(this.networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        startService(this.context);
    }

    public static void startService(@NonNull Context context) {
        FetchService.processPendingRequests(context);
    }

    public static Fetch getInstance(@NonNull Context context) {
        return newInstance(context);
    }

    public static Fetch newInstance(@NonNull Context context) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        return new Fetch(context);
    }

    public static void call(@NonNull Request request, @NonNull FetchCall<String> fetchCall) {
        if (request == null) {
            throw new NullPointerException("Request cannot be null");
        }
        if (fetchCall == null) {
            throw new NullPointerException("FetchCall cannot be null");
        }
        if (callsMap.containsKey(request)) {
            return;
        }
        FetchCallRunnable fetchCallRunnable = new FetchCallRunnable(request, fetchCall, callsCallback);
        callsMap.put(request, fetchCallRunnable);
        new Thread(fetchCallRunnable).start();
    }

    public static void cancelCall(@NonNull Request request) {
        FetchCallRunnable fetchCallRunnable;
        if (request == null || !callsMap.containsKey(request) || (fetchCallRunnable = callsMap.get(request)) == null) {
            return;
        }
        fetchCallRunnable.interrupt();
    }

    public void release() {
        if (isReleased()) {
            return;
        }
        setReleased(true);
        this.listeners.clear();
        this.broadcastManager.unregisterReceiver(this.updateReceiver);
        this.context.unregisterReceiver(this.networkReceiver);
    }

    public void addFetchListener(@NonNull FetchListener fetchListener) {
        Utils.throwIfNotUsable(this);
        if (fetchListener == null) {
            throw new NullPointerException("fetchListener cannot be null");
        }
        if (this.listeners.contains(fetchListener)) {
            return;
        }
        this.listeners.add(fetchListener);
    }

    public void removeFetchListener(@NonNull FetchListener fetchListener) {
        Utils.throwIfNotUsable(this);
        if (fetchListener == null) {
            return;
        }
        this.listeners.remove(fetchListener);
    }

    public void removeFetchListeners() {
        Utils.throwIfNotUsable(this);
        this.listeners.clear();
    }

    public long enqueue(@NonNull Request request) {
        Utils.throwIfNotUsable(this);
        if (request == null) {
            throw new NullPointerException("Request cannot be null");
        }
        long jGenerateRequestId = Utils.generateRequestId();
        try {
            String url = request.getUrl();
            String filePath = request.getFilePath();
            int priority = request.getPriority();
            String strHeaderListToString = Utils.headerListToString(request.getHeaders(), isLoggingEnabled());
            File file = Utils.getFile(filePath);
            if (!this.dbHelper.insert(jGenerateRequestId, url, filePath, FetchConst.STATUS_QUEUED, strHeaderListToString, file.exists() ? file.length() : 0L, 0L, priority, -1)) {
                throw new EnqueueException("could not insert request", FetchConst.ERROR_ENQUEUE_ERROR);
            }
            startService(this.context);
            return jGenerateRequestId;
        } catch (EnqueueException e) {
            if (isLoggingEnabled()) {
                e.printStackTrace();
            }
            return -1L;
        }
    }

    @NonNull
    public List<Long> enqueue(@NonNull List<Request> list) {
        long jGenerateRequestId;
        Utils.throwIfNotUsable(this);
        if (list == null) {
            throw new NullPointerException("Request list cannot be null");
        }
        if (list.size() < 1) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        new ArrayList();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(this.dbHelper.getInsertStatementOpen());
            for (Request request : list) {
                if (request != null) {
                    jGenerateRequestId = Utils.generateRequestId();
                    String url = request.getUrl();
                    String filePath = request.getFilePath();
                    String strHeaderListToString = Utils.headerListToString(request.getHeaders(), isLoggingEnabled());
                    int priority = request.getPriority();
                    File file = Utils.getFile(filePath);
                    sb.append(this.dbHelper.getRowInsertStatement(jGenerateRequestId, url, filePath, FetchConst.STATUS_QUEUED, strHeaderListToString, file.exists() ? file.length() : 0L, 0L, priority, -1));
                    sb.append(", ");
                } else {
                    jGenerateRequestId = -1;
                }
                arrayList.add(Long.valueOf(jGenerateRequestId));
            }
            sb.delete(sb.length() - 2, sb.length()).append(this.dbHelper.getInsertStatementClose());
            if (!this.dbHelper.insert(sb.toString())) {
                throw new EnqueueException("could not insert requests", FetchConst.ERROR_ENQUEUE_ERROR);
            }
            startService(this.context);
            return arrayList;
        } catch (EnqueueException e) {
            if (isLoggingEnabled()) {
                e.printStackTrace();
            }
            arrayList.clear();
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(-1L);
            }
        }
    }

    public void remove(long j) {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_REMOVE);
        bundle.putLong(FetchService.EXTRA_ID, j);
        FetchService.sendToService(this.context, bundle);
    }

    public void removeAll() {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_REMOVE_ALL);
        FetchService.sendToService(this.context, bundle);
    }

    public void removeRequest(long j) {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_REMOVE_REQUEST);
        bundle.putLong(FetchService.EXTRA_ID, j);
        FetchService.sendToService(this.context, bundle);
    }

    public void removeRequests() {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_REMOVE_REQUEST_ALL);
        FetchService.sendToService(this.context, bundle);
    }

    public void pause(long j) {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_PAUSE);
        bundle.putLong(FetchService.EXTRA_ID, j);
        FetchService.sendToService(this.context, bundle);
    }

    public void resume(long j) {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_RESUME);
        bundle.putLong(FetchService.EXTRA_ID, j);
        FetchService.sendToService(this.context, bundle);
    }

    public void setAllowedNetwork(int i) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).setAllowedNetwork(i).apply();
    }

    public void setPriority(long j, int i) {
        Utils.throwIfNotUsable(this);
        int i2 = i != 601 ? 600 : 601;
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_PRIORITY);
        bundle.putLong(FetchService.EXTRA_ID, j);
        bundle.putInt(FetchService.EXTRA_PRIORITY, i2);
        FetchService.sendToService(this.context, bundle);
    }

    public void retry(long j) {
        Utils.throwIfNotUsable(this);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_RETRY);
        bundle.putLong(FetchService.EXTRA_ID, j);
        FetchService.sendToService(this.context, bundle);
    }

    @Nullable
    public synchronized RequestInfo get(long j) {
        Utils.throwIfNotUsable(this);
        return Utils.cursorToRequestInfo(this.dbHelper.get(j), true, isLoggingEnabled());
    }

    @NonNull
    public synchronized List<RequestInfo> get() {
        Utils.throwIfNotUsable(this);
        return Utils.cursorToRequestInfoList(this.dbHelper.get(), true, isLoggingEnabled());
    }

    @NonNull
    public synchronized List<RequestInfo> get(long... jArr) {
        Utils.throwIfNotUsable(this);
        if (jArr == null) {
            return new ArrayList();
        }
        return Utils.cursorToRequestInfoList(this.dbHelper.get(jArr), true, isLoggingEnabled());
    }

    @NonNull
    public synchronized List<RequestInfo> getByStatus(int i) {
        Utils.throwIfNotUsable(this);
        Utils.throwIfInvalidStatus(i);
        return Utils.cursorToRequestInfoList(this.dbHelper.getByStatus(i), true, isLoggingEnabled());
    }

    @Nullable
    public synchronized RequestInfo get(@NonNull Request request) {
        Utils.throwIfNotUsable(this);
        if (request == null) {
            throw new NullPointerException("Request cannot be null.");
        }
        return Utils.cursorToRequestInfo(this.dbHelper.getByUrlAndFilePath(request.getUrl(), request.getFilePath()), true, isLoggingEnabled());
    }

    @Nullable
    public synchronized File getDownloadedFile(long j) {
        Utils.throwIfNotUsable(this);
        RequestInfo requestInfoCursorToRequestInfo = Utils.cursorToRequestInfo(this.dbHelper.get(j), true, isLoggingEnabled());
        if (requestInfoCursorToRequestInfo != null && requestInfoCursorToRequestInfo.getStatus() == 903) {
            File file = Utils.getFile(requestInfoCursorToRequestInfo.getFilePath());
            if (file.exists()) {
                return file;
            }
            return null;
        }
        return null;
    }

    @Nullable
    public synchronized String getFilePath(long j) {
        Utils.throwIfNotUsable(this);
        RequestInfo requestInfoCursorToRequestInfo = Utils.cursorToRequestInfo(this.dbHelper.get(j), true, isLoggingEnabled());
        if (requestInfoCursorToRequestInfo == null) {
            return null;
        }
        return requestInfoCursorToRequestInfo.getFilePath();
    }

    public long addCompletedDownload(@NonNull String str) {
        Utils.throwIfNotUsable(this);
        if (str == null) {
            throw new NullPointerException("File path cannot be null");
        }
        try {
            if (!Utils.fileExist(str)) {
                throw new EnqueueException("File does not exist at filePath: " + str, -102);
            }
            long jGenerateRequestId = Utils.generateRequestId();
            File file = Utils.getFile(str);
            String string = Uri.fromFile(file).toString();
            String strHeaderListToString = Utils.headerListToString(null, isLoggingEnabled());
            long length = file.length();
            if (this.dbHelper.insert(jGenerateRequestId, string, str, FetchConst.STATUS_DONE, strHeaderListToString, length, length, 600, -1)) {
                return jGenerateRequestId;
            }
            throw new EnqueueException("could not insert request:" + str, FetchConst.ERROR_ENQUEUE_ERROR);
        } catch (EnqueueException e) {
            if (isLoggingEnabled()) {
                e.printStackTrace();
            }
            return -1L;
        }
    }

    @NonNull
    public List<Long> addCompletedDownloads(@NonNull List<String> list) {
        long jGenerateRequestId;
        Utils.throwIfNotUsable(this);
        if (list == null) {
            throw new NullPointerException("Request list cannot be null");
        }
        if (list.size() < 1) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        new ArrayList();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(this.dbHelper.getInsertStatementOpen());
            for (String str : list) {
                if (str != null) {
                    File file = Utils.getFile(str);
                    if (!file.exists()) {
                        break;
                    }
                    jGenerateRequestId = Utils.generateRequestId();
                    String string = Uri.fromFile(file).toString();
                    String strHeaderListToString = Utils.headerListToString(null, isLoggingEnabled());
                    long length = file.length();
                    sb.append(this.dbHelper.getRowInsertStatement(jGenerateRequestId, string, str, FetchConst.STATUS_DONE, strHeaderListToString, length, length, 600, -1));
                    sb.append(",");
                } else {
                    jGenerateRequestId = -1;
                }
                arrayList.add(Long.valueOf(jGenerateRequestId));
            }
            sb.delete(sb.length() - 2, sb.length()).append(this.dbHelper.getInsertStatementClose());
            if (!this.dbHelper.insert(sb.toString())) {
                throw new EnqueueException("could not insert requests", FetchConst.ERROR_ENQUEUE_ERROR);
            }
        } catch (EnqueueException e) {
            if (isLoggingEnabled()) {
                e.printStackTrace();
            }
            arrayList.clear();
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(-1L);
            }
        }
        return arrayList;
    }

    public void runOnBackgroundThread(@NonNull final FetchTask fetchTask) {
        Utils.throwIfNotUsable(this);
        Utils.throwIfFetchTaskNull(fetchTask);
        new Thread(new Runnable() { // from class: com.tonyodev.fetch.Fetch.2
            @Override // java.lang.Runnable
            public void run() {
                Fetch fetchNewInstance = Fetch.newInstance(Fetch.this.context);
                fetchTask.onProcess(fetchNewInstance);
                fetchNewInstance.release();
            }
        }).start();
    }

    public void runOnMainThread(@NonNull final FetchTask fetchTask) {
        Utils.throwIfNotUsable(this);
        Utils.throwIfFetchTaskNull(fetchTask);
        mainHandler.post(new Runnable() { // from class: com.tonyodev.fetch.Fetch.3
            @Override // java.lang.Runnable
            public void run() {
                Fetch fetchNewInstance = Fetch.newInstance(Fetch.this.context);
                fetchTask.onProcess(fetchNewInstance);
                fetchNewInstance.release();
            }
        });
    }

    public synchronized boolean contains(@NonNull Request request) {
        Utils.throwIfNotUsable(this);
        if (request == null) {
            throw new NullPointerException("Request cannot be null.");
        }
        return Utils.containsRequest(this.dbHelper.getByUrlAndFilePath(request.getUrl(), request.getFilePath()), true);
    }

    public boolean isValid() {
        return !isReleased();
    }

    boolean isReleased() {
        return this.isReleased;
    }

    private void setReleased(boolean z) {
        this.isReleased = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLoggingEnabled() {
        return FetchService.isLoggingEnabled(this.context);
    }

    public void enableLogging(boolean z) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).enableLogging(z).apply();
    }

    public void setConcurrentDownloadsLimit(int i) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).setConcurrentDownloadsLimit(i).apply();
    }

    public void setOnUpdateInterval(long j) {
        Utils.throwIfNotUsable(this);
        new Settings(this.context).setOnUpdateInterval(j).apply();
    }

    public void updateUrlForRequest(long j, @Nullable String str) {
        Utils.throwIfNotUsable(this);
        if (str == null) {
            throw new NullPointerException("Url cannot be null");
        }
        Utils.throwIfInvalidUrl(str);
        Bundle bundle = new Bundle();
        bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_UPDATE_REQUEST_URL);
        bundle.putLong(FetchService.EXTRA_ID, j);
        bundle.putString(FetchService.EXTRA_URL, str);
        FetchService.sendToService(this.context, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Iterator<FetchListener> getListenerIterator() {
        return this.listeners.iterator();
    }

    public static class Settings {
        private final Context context;
        private final List<Bundle> settings = new ArrayList();

        public Settings(@NonNull Context context) {
            if (context == null) {
                throw new NullPointerException("Context cannot be null");
            }
            this.context = context;
        }

        public Settings enableLogging(boolean z) {
            Bundle bundle = new Bundle();
            bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_LOGGING);
            bundle.putBoolean(FetchService.EXTRA_LOGGING_ID, z);
            this.settings.add(bundle);
            return this;
        }

        public Settings setAllowedNetwork(int i) {
            int i2 = FetchConst.NETWORK_WIFI;
            if (i != 201) {
                i2 = 200;
            }
            Bundle bundle = new Bundle();
            bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_NETWORK);
            bundle.putInt(FetchService.EXTRA_NETWORK_ID, i2);
            this.settings.add(bundle);
            return this;
        }

        public Settings setConcurrentDownloadsLimit(int i) {
            Bundle bundle = new Bundle();
            bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_CONCURRENT_DOWNLOADS_LIMIT);
            bundle.putInt(FetchService.EXTRA_CONCURRENT_DOWNLOADS_LIMIT, i);
            this.settings.add(bundle);
            return this;
        }

        public Settings setOnUpdateInterval(long j) {
            Bundle bundle = new Bundle();
            bundle.putInt(FetchService.ACTION_TYPE, FetchService.ACTION_ON_UPDATE_INTERVAL);
            bundle.putLong(FetchService.EXTRA_ON_UPDATE_INTERVAL, j);
            this.settings.add(bundle);
            return this;
        }

        public void apply() {
            Iterator<Bundle> it = this.settings.iterator();
            while (it.hasNext()) {
                FetchService.sendToService(this.context, it.next());
            }
        }
    }
}
