package com.tonyodev.fetch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.tonyodev.fetch.exception.EnqueueException;
import com.tonyodev.fetch.request.RequestInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
public final class FetchService implements FetchConst {
    public static final int ACTION_CONCURRENT_DOWNLOADS_LIMIT = 321;
    public static final int ACTION_ENQUEUE = 310;
    public static final int ACTION_LOGGING = 320;
    public static final int ACTION_NETWORK = 314;
    public static final int ACTION_ON_UPDATE_INTERVAL = 323;
    public static final int ACTION_PAUSE = 311;
    public static final int ACTION_PRIORITY = 317;
    public static final int ACTION_PROCESS_PENDING = 315;
    public static final int ACTION_QUERY = 316;
    public static final int ACTION_REMOVE = 313;
    public static final int ACTION_REMOVE_ALL = 319;
    public static final int ACTION_REMOVE_REQUEST = 324;
    public static final int ACTION_REMOVE_REQUEST_ALL = 325;
    public static final int ACTION_RESUME = 312;
    public static final int ACTION_RETRY = 318;
    public static final String ACTION_TYPE = "com.tonyodev.fetch.action_type";
    public static final int ACTION_UPDATE_REQUEST_URL = 322;
    public static final String EVENT_ACTION_ENQUEUED = "com.tonyodev.fetch.event_action_enqueued";
    public static final String EVENT_ACTION_ENQUEUE_FAILED = "com.tonyodev.fetch.event_action_enqueue_failed";
    public static final String EVENT_ACTION_QUERY = "com.tonyodev.fetch.event_action_query";
    public static final String EVENT_ACTION_UPDATE = "com.tonyodev.fetch.event_action_update";
    public static final String EXTRA_CONCURRENT_DOWNLOADS_LIMIT = "com.tonyodev.fetch.extra_concurrent_download_limit";
    public static final String EXTRA_DOWNLOADED_BYTES = "com.tonyodev.fetch.extra_downloaded_bytes";
    public static final String EXTRA_ERROR = "com.tonyodev.fetch.extra_error";
    public static final String EXTRA_FILE_PATH = "com.tonyodev.fetch.extra_file_path";
    public static final String EXTRA_FILE_SIZE = "com.tonyodev.fetch.extra_file_size";
    public static final String EXTRA_HEADERS = "com.tonyodev.fetch.extra_headers";
    public static final String EXTRA_HEADER_NAME = "com.tonyodev.fetch.extra_header_name";
    public static final String EXTRA_HEADER_VALUE = "com.tonyodev.fetch.extra_header_value";
    public static final String EXTRA_ID = "com.tonyodev.fetch.extra_id";
    public static final String EXTRA_LOGGING_ID = "com.tonyodev.fetch.extra_logging_id";
    public static final String EXTRA_NETWORK_ID = "com.tonyodev.fetch.extra_network_id";
    public static final String EXTRA_ON_UPDATE_INTERVAL = "com.tonyodev.fetch.extra_on_update_interval";
    public static final String EXTRA_PRIORITY = "com.tonyodev.fetch.extra_priority";
    public static final String EXTRA_PROGRESS = "com.tonyodev.fetch.extra_progress";
    public static final String EXTRA_QUERY_ID = "com.tonyodev.fetch.extra_query_id";
    public static final String EXTRA_QUERY_RESULT = "com.tonyodev.fetch.extra_query_result";
    public static final String EXTRA_QUERY_TYPE = "com.tonyodev.fetch.extra_query_type";
    public static final String EXTRA_STATUS = "com.tonyodev.fetch.extra_status";
    public static final String EXTRA_URL = "com.tonyodev.fetch.extra_url";
    public static final int QUERY_ALL = 481;
    public static final int QUERY_BY_STATUS = 482;
    public static final int QUERY_SINGLE = 480;
    private static final String SHARED_PREFERENCES = "com.tonyodev.fetch.shared_preferences";
    private static FetchService fetchService;
    private final LocalBroadcastManager broadcastManager;
    private final Context context;
    private final DatabaseHelper databaseHelper;
    private int downloadsLimit;
    private boolean loggingEnabled;
    private long onUpdateInterval;
    private int preferredNetwork;
    private final SharedPreferences sharedPreferences;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final List<BroadcastReceiver> registeredReceivers = new ArrayList();
    private final ConcurrentHashMap<Long, FetchRunnable> activeDownloads = new ConcurrentHashMap<>();
    private volatile boolean runningTask = false;
    private volatile boolean shuttingDown = false;
    private final BroadcastReceiver doneReceiver = new BroadcastReceiver() { // from class: com.tonyodev.fetch.FetchService.9
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                long idFromIntent = FetchRunnable.getIdFromIntent(intent);
                if (FetchService.this.activeDownloads.containsKey(Long.valueOf(idFromIntent))) {
                    FetchService.this.activeDownloads.remove(Long.valueOf(idFromIntent));
                }
                FetchService.this.startDownload();
            }
        }
    };

    public static void sendToService(@NonNull Context context, @Nullable Bundle bundle) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        getInstance(context).runAction(bundle);
    }

    public static void processPendingRequests(@NonNull Context context) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        Bundle bundle = new Bundle();
        bundle.putInt(ACTION_TYPE, ACTION_PROCESS_PENDING);
        getInstance(context).runAction(bundle);
    }

    public static FetchService getInstance(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        FetchService fetchService2 = fetchService;
        if (fetchService2 == null || fetchService2.shuttingDown) {
            fetchService = new FetchService(context);
        }
        return fetchService;
    }

    @NonNull
    public static IntentFilter getEventEnqueuedFilter() {
        return new IntentFilter(EVENT_ACTION_ENQUEUED);
    }

    @NonNull
    public static IntentFilter getEventEnqueueFailedFilter() {
        return new IntentFilter(EVENT_ACTION_ENQUEUE_FAILED);
    }

    @NonNull
    public static IntentFilter getEventUpdateFilter() {
        return new IntentFilter(EVENT_ACTION_UPDATE);
    }

    @NonNull
    public static IntentFilter getEventQueryFilter() {
        return new IntentFilter(EVENT_ACTION_QUERY);
    }

    private FetchService(@NonNull Context context) {
        this.downloadsLimit = 1;
        this.loggingEnabled = true;
        this.onUpdateInterval = 2000L;
        this.preferredNetwork = 200;
        this.context = context.getApplicationContext();
        this.broadcastManager = LocalBroadcastManager.getInstance(context);
        this.sharedPreferences = this.context.getSharedPreferences(SHARED_PREFERENCES, 0);
        this.databaseHelper = DatabaseHelper.getInstance(context);
        this.broadcastManager.registerReceiver(this.doneReceiver, FetchRunnable.getDoneFilter());
        this.registeredReceivers.add(this.doneReceiver);
        this.downloadsLimit = getDownloadsLimit();
        this.preferredNetwork = getAllowedNetwork();
        this.loggingEnabled = isLoggingEnabled();
        this.onUpdateInterval = getOnUpdateInterval();
        this.databaseHelper.setLoggingEnabled(this.loggingEnabled);
        if (this.executor.isShutdown()) {
            return;
        }
        this.executor.execute(new Runnable() { // from class: com.tonyodev.fetch.FetchService.1
            @Override // java.lang.Runnable
            public void run() {
                FetchService.this.databaseHelper.clean();
                FetchService.this.databaseHelper.verifyOK();
            }
        });
    }

    public void runAction(@NonNull Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("Bundle cannot be null");
        }
        processAction(bundle);
    }

    public void shutdown() {
        this.shuttingDown = true;
        if (!this.executor.isShutdown()) {
            this.executor.shutdown();
        }
        interruptActiveDownloads();
        Iterator<BroadcastReceiver> it = this.registeredReceivers.iterator();
        while (it.hasNext()) {
            this.broadcastManager.unregisterReceiver(it.next());
        }
        this.registeredReceivers.clear();
    }

    private void processAction(final Bundle bundle) {
        try {
            if (this.executor.isShutdown()) {
                return;
            }
            this.executor.execute(new Runnable() { // from class: com.tonyodev.fetch.FetchService.2
                @Override // java.lang.Runnable
                public void run() {
                    FetchService.this.databaseHelper.clean();
                    long j = bundle.getLong(FetchService.EXTRA_ID, -1L);
                    switch (bundle.getInt(FetchService.ACTION_TYPE, -1)) {
                        case FetchService.ACTION_ENQUEUE /* 310 */:
                            FetchService.this.enqueue(bundle.getString(FetchService.EXTRA_URL), bundle.getString(FetchService.EXTRA_FILE_PATH), bundle.getParcelableArrayList(FetchService.EXTRA_HEADERS), bundle.getInt(FetchService.EXTRA_PRIORITY, 600));
                            break;
                        case FetchService.ACTION_PAUSE /* 311 */:
                            FetchService.this.pause(j);
                            break;
                        case FetchService.ACTION_RESUME /* 312 */:
                            FetchService.this.resume(j);
                            break;
                        case FetchService.ACTION_REMOVE /* 313 */:
                            FetchService.this.remove(j);
                            break;
                        case FetchService.ACTION_NETWORK /* 314 */:
                            FetchService.this.setAllowedNetwork(bundle.getInt(FetchService.EXTRA_NETWORK_ID, 200));
                            break;
                        case FetchService.ACTION_PROCESS_PENDING /* 315 */:
                            FetchService.this.startDownload();
                            break;
                        case FetchService.ACTION_QUERY /* 316 */:
                            long j2 = bundle.getLong(FetchService.EXTRA_QUERY_ID, -1L);
                            FetchService.this.query(bundle.getInt(FetchService.EXTRA_QUERY_TYPE, FetchService.QUERY_ALL), j2, j, bundle.getInt(FetchService.EXTRA_STATUS, -1));
                            break;
                        case FetchService.ACTION_PRIORITY /* 317 */:
                            FetchService.this.setRequestPriority(j, bundle.getInt(FetchService.EXTRA_PRIORITY, 600));
                            break;
                        case FetchService.ACTION_RETRY /* 318 */:
                            FetchService.this.retry(j);
                            break;
                        case FetchService.ACTION_REMOVE_ALL /* 319 */:
                            FetchService.this.removeAll();
                            break;
                        case FetchService.ACTION_LOGGING /* 320 */:
                            FetchService.this.setLoggingEnabled(bundle.getBoolean(FetchService.EXTRA_LOGGING_ID, true));
                            break;
                        case FetchService.ACTION_CONCURRENT_DOWNLOADS_LIMIT /* 321 */:
                            FetchService.this.setDownloadsLimit(bundle.getInt(FetchService.EXTRA_CONCURRENT_DOWNLOADS_LIMIT, 1));
                            break;
                        case FetchService.ACTION_UPDATE_REQUEST_URL /* 322 */:
                            FetchService.this.updateRequestUrl(j, bundle.getString(FetchService.EXTRA_URL));
                            break;
                        case FetchService.ACTION_ON_UPDATE_INTERVAL /* 323 */:
                            FetchService.this.setOnUpdateInterval(bundle.getLong(FetchService.EXTRA_ON_UPDATE_INTERVAL, 2000L));
                            break;
                        case FetchService.ACTION_REMOVE_REQUEST /* 324 */:
                            FetchService.this.removeRequest(j);
                            break;
                        case FetchService.ACTION_REMOVE_REQUEST_ALL /* 325 */:
                            FetchService.this.removeRequestAll();
                            break;
                        default:
                            FetchService.this.startDownload();
                            break;
                    }
                }
            });
        } catch (Exception e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void startDownload() {
        if (!this.shuttingDown && !this.runningTask) {
            boolean zIsNetworkAvailable = Utils.isNetworkAvailable(this.context);
            boolean zIsOnWiFi = Utils.isOnWiFi(this.context);
            if ((!zIsNetworkAvailable || (this.preferredNetwork == 201 && !zIsOnWiFi)) && this.activeDownloads.size() > 0) {
                this.runningTask = true;
                interruptActiveDownloads();
                this.runningTask = false;
            } else if (zIsNetworkAvailable && !this.runningTask && this.activeDownloads.size() < this.downloadsLimit && this.databaseHelper.hasPendingRequests()) {
                this.runningTask = true;
                try {
                    Cursor nextPendingRequest = this.databaseHelper.getNextPendingRequest();
                    if (nextPendingRequest != null && !nextPendingRequest.isClosed() && nextPendingRequest.getCount() > 0) {
                        RequestInfo requestInfoCursorToRequestInfo = Utils.cursorToRequestInfo(nextPendingRequest, true, this.loggingEnabled);
                        FetchRunnable fetchRunnable = new FetchRunnable(this.context, requestInfoCursorToRequestInfo.getId(), requestInfoCursorToRequestInfo.getUrl(), requestInfoCursorToRequestInfo.getFilePath(), requestInfoCursorToRequestInfo.getHeaders(), requestInfoCursorToRequestInfo.getFileSize(), this.loggingEnabled, this.onUpdateInterval);
                        this.databaseHelper.updateStatus(requestInfoCursorToRequestInfo.getId(), FetchConst.STATUS_DOWNLOADING, -1);
                        this.activeDownloads.put(Long.valueOf(fetchRunnable.getId()), fetchRunnable);
                        new Thread(fetchRunnable).start();
                    }
                } catch (Exception e) {
                    if (this.loggingEnabled) {
                        e.printStackTrace();
                    }
                }
                this.runningTask = false;
                if (this.activeDownloads.size() < this.downloadsLimit && this.databaseHelper.hasPendingRequests()) {
                    startDownload();
                }
            } else if (!this.runningTask && this.activeDownloads.size() == 0 && !this.databaseHelper.hasPendingRequests()) {
                this.shuttingDown = true;
                shutdown();
            }
        }
    }

    private void interruptActiveDownloads() {
        Iterator<Long> it = this.activeDownloads.keySet().iterator();
        while (it.hasNext()) {
            FetchRunnable fetchRunnable = this.activeDownloads.get(it.next());
            if (fetchRunnable != null) {
                fetchRunnable.interrupt();
            }
        }
    }

    private void interruptActiveDownload(long j) {
        FetchRunnable fetchRunnable;
        if (!this.activeDownloads.containsKey(Long.valueOf(j)) || (fetchRunnable = this.activeDownloads.get(Long.valueOf(j))) == null) {
            return;
        }
        fetchRunnable.interrupt();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:39:0x00a4 A[Catch: all -> 0x009b, TryCatch #3 {all -> 0x009b, blocks: (B:6:0x000c, B:11:0x001a, B:13:0x0032, B:18:0x003b, B:22:0x005f, B:37:0x00a0, B:39:0x00a4, B:40:0x00a7, B:23:0x0063, B:24:0x006f, B:31:0x007a, B:32:0x009a), top: B:50:0x0006 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r25v0, types: [com.tonyodev.fetch.FetchService] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v8 */
    public void enqueue(String str, String str2, ArrayList<Bundle> arrayList, int i) {
        ?? r8;
        ArrayList<Bundle> arrayList2;
        try {
            if (str == null || str2 == null) {
                try {
                    throw new EnqueueException("Request was not properly formatted. url:" + str + ", filePath:" + str2, FetchConst.ERROR_BAD_REQUEST);
                } catch (EnqueueException e) {
                    e = e;
                    r8 = arrayList;
                }
            } else {
                if (arrayList == null) {
                    try {
                        arrayList2 = new ArrayList<>();
                    } catch (EnqueueException e2) {
                        e = e2;
                        arrayList2 = arrayList;
                        r8 = arrayList2;
                        if (this.loggingEnabled) {
                            e.printStackTrace();
                        }
                        sendEnqueueEvent(EVENT_ACTION_ENQUEUE_FAILED, -1L, str, str2, -900, r8, i, e.getErrorCode());
                        startDownload();
                    }
                } else {
                    arrayList2 = arrayList;
                }
                try {
                    long jGenerateRequestId = Utils.generateRequestId();
                    String strBundleListToHeaderString = Utils.bundleListToHeaderString(arrayList2, this.loggingEnabled);
                    long length = 0;
                    File file = Utils.getFile(str2);
                    if (file.exists()) {
                        try {
                            length = file.length();
                        } catch (EnqueueException e3) {
                            e = e3;
                            r8 = arrayList2;
                        }
                    }
                    long j = length;
                    DatabaseHelper databaseHelper = this.databaseHelper;
                    try {
                        if (!databaseHelper.insert(jGenerateRequestId, str, str2, FetchConst.STATUS_QUEUED, strBundleListToHeaderString, j, 0L, i, -1)) {
                            throw new EnqueueException("could not enqueue request", FetchConst.ERROR_ENQUEUE_ERROR);
                        }
                        sendEnqueueEvent(EVENT_ACTION_ENQUEUED, jGenerateRequestId, str, str2, FetchConst.STATUS_QUEUED, arrayList2, i, -1);
                        startDownload();
                    } catch (EnqueueException e4) {
                        e = e4;
                        r8 = databaseHelper;
                        if (this.loggingEnabled) {
                            e.printStackTrace();
                        }
                        sendEnqueueEvent(EVENT_ACTION_ENQUEUE_FAILED, -1L, str, str2, -900, r8, i, e.getErrorCode());
                    }
                } catch (EnqueueException e5) {
                    e = e5;
                }
            }
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
            sendEnqueueEvent(EVENT_ACTION_ENQUEUE_FAILED, -1L, str, str2, -900, r8, i, e.getErrorCode());
            startDownload();
        } catch (Throwable th) {
            startDownload();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resume(long j) {
        RequestInfo requestInfoCursorToRequestInfo;
        if (this.activeDownloads.containsKey(Long.valueOf(j))) {
            return;
        }
        if (this.databaseHelper.resume(j) && (requestInfoCursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(j), true, this.loggingEnabled)) != null) {
            Utils.sendEventUpdate(this.broadcastManager, requestInfoCursorToRequestInfo.getId(), requestInfoCursorToRequestInfo.getStatus(), requestInfoCursorToRequestInfo.getProgress(), requestInfoCursorToRequestInfo.getDownloadedBytes(), requestInfoCursorToRequestInfo.getFileSize(), requestInfoCursorToRequestInfo.getError());
        }
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pause(final long j) {
        if (this.activeDownloads.containsKey(Long.valueOf(j))) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.tonyodev.fetch.FetchService.3
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    long idFromIntent = FetchRunnable.getIdFromIntent(intent);
                    long j2 = j;
                    if (idFromIntent == j2) {
                        FetchService.this.pauseAction(j2);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownload(j);
            return;
        }
        pauseAction(j);
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pauseAction(long j) {
        RequestInfo requestInfoCursorToRequestInfo;
        if (!this.databaseHelper.pause(j) || (requestInfoCursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(j), true, this.loggingEnabled)) == null) {
            return;
        }
        Utils.sendEventUpdate(this.broadcastManager, requestInfoCursorToRequestInfo.getId(), requestInfoCursorToRequestInfo.getStatus(), requestInfoCursorToRequestInfo.getProgress(), requestInfoCursorToRequestInfo.getDownloadedBytes(), requestInfoCursorToRequestInfo.getFileSize(), requestInfoCursorToRequestInfo.getError());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void remove(final long j) {
        if (this.activeDownloads.containsKey(Long.valueOf(j))) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.tonyodev.fetch.FetchService.4
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    long idFromIntent = FetchRunnable.getIdFromIntent(intent);
                    long j2 = j;
                    if (idFromIntent == j2) {
                        FetchService.this.removeAction(j2);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownload(j);
            return;
        }
        removeAction(j);
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeAction(long j) {
        RequestInfo requestInfoCursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(j), true, this.loggingEnabled);
        if (requestInfoCursorToRequestInfo == null || !this.databaseHelper.delete(j)) {
            return;
        }
        Utils.deleteFile(requestInfoCursorToRequestInfo.getFilePath());
        Utils.sendEventUpdate(this.broadcastManager, j, FetchConst.STATUS_REMOVED, 0, 0L, 0L, -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeAll() {
        if (this.activeDownloads.size() > 0) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.tonyodev.fetch.FetchService.5
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent != null) {
                        FetchService.this.removeAction(FetchRunnable.getIdFromIntent(intent));
                    }
                    if (FetchService.this.activeDownloads.size() == 0) {
                        FetchService.this.removeAllAction();
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownloads();
            return;
        }
        removeAllAction();
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeAllAction() {
        List<RequestInfo> listCursorToRequestInfoList = Utils.cursorToRequestInfoList(this.databaseHelper.get(), true, this.loggingEnabled);
        if (listCursorToRequestInfoList == null || !this.databaseHelper.deleteAll()) {
            return;
        }
        for (RequestInfo requestInfo : listCursorToRequestInfoList) {
            Utils.deleteFile(requestInfo.getFilePath());
            Utils.sendEventUpdate(this.broadcastManager, requestInfo.getId(), FetchConst.STATUS_REMOVED, 0, 0L, 0L, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeRequestAll() {
        if (this.activeDownloads.size() > 0) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.tonyodev.fetch.FetchService.6
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent != null) {
                        FetchService.this.removeRequestAction(FetchRunnable.getIdFromIntent(intent));
                    }
                    if (FetchService.this.activeDownloads.size() == 0) {
                        FetchService.this.removeRequestAllAction();
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownloads();
            return;
        }
        removeRequestAllAction();
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeRequestAllAction() {
        List<RequestInfo> listCursorToRequestInfoList = Utils.cursorToRequestInfoList(this.databaseHelper.get(), true, this.loggingEnabled);
        if (listCursorToRequestInfoList == null || !this.databaseHelper.deleteAll()) {
            return;
        }
        for (RequestInfo requestInfo : listCursorToRequestInfoList) {
            Utils.sendEventUpdate(this.broadcastManager, requestInfo.getId(), FetchConst.STATUS_REMOVED, requestInfo.getProgress(), requestInfo.getDownloadedBytes(), requestInfo.getFileSize(), -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeRequest(final long j) {
        if (this.activeDownloads.containsKey(Long.valueOf(j))) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.tonyodev.fetch.FetchService.7
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    long idFromIntent = FetchRunnable.getIdFromIntent(intent);
                    long j2 = j;
                    if (idFromIntent == j2) {
                        FetchService.this.removeRequestAction(j2);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownload(j);
            return;
        }
        removeRequestAction(j);
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeRequestAction(long j) {
        RequestInfo requestInfoCursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(j), true, this.loggingEnabled);
        if (requestInfoCursorToRequestInfo == null || !this.databaseHelper.delete(j)) {
            return;
        }
        Utils.sendEventUpdate(this.broadcastManager, j, FetchConst.STATUS_REMOVED, requestInfoCursorToRequestInfo.getProgress(), requestInfoCursorToRequestInfo.getDownloadedBytes(), requestInfoCursorToRequestInfo.getFileSize(), -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void query(int i, long j, long j2, int i2) {
        Cursor byStatus;
        if (i == 480) {
            byStatus = this.databaseHelper.get(j2);
        } else if (i == 482) {
            byStatus = this.databaseHelper.getByStatus(i2);
        } else {
            byStatus = this.databaseHelper.get();
        }
        sendEventQuery(j, Utils.cursorToQueryResultList(byStatus, true, this.loggingEnabled));
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestPriority(long j, int i) {
        if (this.databaseHelper.setPriority(j, i) && this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAllowedNetwork(int i) {
        this.preferredNetwork = i;
        this.sharedPreferences.edit().putInt(EXTRA_NETWORK_ID, i).apply();
        if (this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retry(long j) {
        RequestInfo requestInfoCursorToRequestInfo;
        if (this.activeDownloads.containsKey(Long.valueOf(j))) {
            return;
        }
        if (this.databaseHelper.retry(j) && (requestInfoCursorToRequestInfo = Utils.cursorToRequestInfo(this.databaseHelper.get(j), true, this.loggingEnabled)) != null) {
            Utils.sendEventUpdate(this.broadcastManager, requestInfoCursorToRequestInfo.getId(), requestInfoCursorToRequestInfo.getStatus(), requestInfoCursorToRequestInfo.getProgress(), requestInfoCursorToRequestInfo.getDownloadedBytes(), requestInfoCursorToRequestInfo.getFileSize(), requestInfoCursorToRequestInfo.getError());
        }
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRequestUrl(final long j, final String str) {
        if (this.activeDownloads.containsKey(Long.valueOf(j))) {
            this.runningTask = true;
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.tonyodev.fetch.FetchService.8
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    long idFromIntent = FetchRunnable.getIdFromIntent(intent);
                    long j2 = j;
                    if (idFromIntent == j2) {
                        FetchService.this.updateRequestUrlAction(j2, str);
                        FetchService.this.broadcastManager.unregisterReceiver(this);
                        FetchService.this.registeredReceivers.remove(this);
                        FetchService.this.runningTask = false;
                        FetchService.this.startDownload();
                    }
                }
            };
            this.registeredReceivers.add(broadcastReceiver);
            this.broadcastManager.registerReceiver(broadcastReceiver, FetchRunnable.getDoneFilter());
            interruptActiveDownload(j);
            return;
        }
        updateRequestUrlAction(j, str);
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRequestUrlAction(long j, String str) {
        this.databaseHelper.updateUrl(j, str);
        this.databaseHelper.retry(j);
    }

    private int getAllowedNetwork() {
        return this.sharedPreferences.getInt(EXTRA_NETWORK_ID, 200);
    }

    private void sendEnqueueEvent(String str, long j, String str2, String str3, int i, ArrayList<Bundle> arrayList, int i2, int i3) {
        Intent intent = new Intent(str);
        intent.putExtra(EXTRA_ID, j);
        intent.putExtra(EXTRA_STATUS, i);
        intent.putExtra(EXTRA_URL, str2);
        intent.putExtra(EXTRA_FILE_PATH, str3);
        intent.putExtra(EXTRA_HEADERS, arrayList);
        intent.putExtra(EXTRA_PROGRESS, 0);
        intent.putExtra(EXTRA_FILE_SIZE, 0L);
        intent.putExtra(EXTRA_ERROR, i3);
        intent.putExtra(EXTRA_PRIORITY, i2);
        this.broadcastManager.sendBroadcast(intent);
    }

    private void sendEventQuery(long j, ArrayList<Bundle> arrayList) {
        Intent intent = new Intent(EVENT_ACTION_QUERY);
        intent.putExtra(EXTRA_QUERY_ID, j);
        intent.putExtra(EXTRA_QUERY_RESULT, arrayList);
        this.broadcastManager.sendBroadcast(intent);
    }

    private int getDownloadsLimit() {
        return this.sharedPreferences.getInt(EXTRA_CONCURRENT_DOWNLOADS_LIMIT, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDownloadsLimit(int i) {
        if (i < 1) {
            i = 1;
        }
        this.downloadsLimit = i;
        this.sharedPreferences.edit().putInt(EXTRA_CONCURRENT_DOWNLOADS_LIMIT, i).apply();
        if (this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLoggingEnabled(boolean z) {
        this.loggingEnabled = z;
        this.sharedPreferences.edit().putBoolean(EXTRA_LOGGING_ID, z).apply();
        this.databaseHelper.setLoggingEnabled(this.loggingEnabled);
        startDownload();
    }

    private boolean isLoggingEnabled() {
        return this.sharedPreferences.getBoolean(EXTRA_LOGGING_ID, true);
    }

    static boolean isLoggingEnabled(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES, 0).getBoolean(EXTRA_LOGGING_ID, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnUpdateInterval(long j) {
        this.onUpdateInterval = j;
        this.sharedPreferences.edit().putLong(EXTRA_ON_UPDATE_INTERVAL, j).apply();
        if (this.activeDownloads.size() > 0) {
            interruptActiveDownloads();
        }
        startDownload();
    }

    private long getOnUpdateInterval() {
        this.onUpdateInterval = this.sharedPreferences.getLong(EXTRA_ON_UPDATE_INTERVAL, 2000L);
        return this.onUpdateInterval;
    }
}
