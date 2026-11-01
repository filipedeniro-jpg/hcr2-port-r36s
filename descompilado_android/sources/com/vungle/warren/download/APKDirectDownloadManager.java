package com.vungle.warren.download;

import android.R;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.PermissionChecker;
import com.google.android.gms.drive.DriveFile;
import com.vungle.warren.NetworkStateReceiver;
import com.vungle.warren.locale.LocaleString;
import com.vungle.warren.network.APKDirectDownloader;
import com.vungle.warren.network.Downloader;
import com.vungle.warren.ui.VungleWebViewActivity;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class APKDirectDownloadManager {
    private static final String APK_POSTFIX = "apk";
    public static final int DIRECT_DOWNLOAD_FLAG_DISABLED = 0;
    public static final int DIRECT_DOWNLOAD_FLAG_ENABLED = 1;
    public static final int DIRECT_DOWNLOAD_FLAG_NOT_SET = -1;
    private static final String FOLDER_APK = "apk";
    private static final String FOLDER_NAME = "vungle";
    private static final String TAG = "DirectDownloadManager";
    private static APKDirectDownloadManager _instance = new APKDirectDownloadManager();
    private WeakReference<Context> context;
    private APKDirectDownloader downloader;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager notifyManager;
    private List<String> pendingDownloadUrl = new ArrayList();
    private List<Integer> notificationList = new ArrayList();
    private List<Integer> pendingNotificationList = new ArrayList();
    private int apkDirectDownloadStatus = -1;
    private boolean isWifiEnabled = false;
    private boolean isNetworkReceiverEnabled = false;

    public static void init(Context context) {
        _instance.context = new WeakReference<>(context);
        _instance.clearDownloadApkCache();
        APKDirectDownloadManager aPKDirectDownloadManager = _instance;
        if (aPKDirectDownloadManager.downloader == null) {
            aPKDirectDownloadManager.downloader = new APKDirectDownloader(aPKDirectDownloadManager.context.get());
        }
    }

    public static boolean isDirectDownloadEnabled(boolean z, boolean z2) {
        int i = _instance.apkDirectDownloadStatus;
        if (i != -1) {
            if (i != 1) {
                return false;
            }
            return z2;
        }
        if (z) {
            return z2;
        }
        return false;
    }

    public static void setDirectDownloadStatus(int i) {
        _instance.apkDirectDownloadStatus = i;
    }

    public static void download(String str) {
        enableNetworkListener(true, getContext());
        if (TextUtils.isEmpty(str)) {
            return;
        }
        APKDirectDownloadManager aPKDirectDownloadManager = _instance;
        if (isApkUrl(str)) {
            try {
                String strValueOf = String.valueOf(System.currentTimeMillis());
                final int iHashCode = strValueOf.hashCode();
                if (_instance.isWifiEnabled()) {
                    _instance.notificationList.add(Integer.valueOf(iHashCode));
                    final File apkDirectory = _instance.getApkDirectory(strValueOf);
                    _instance.downloader.download(str, apkDirectory, new Downloader.Listener() { // from class: com.vungle.warren.download.APKDirectDownloadManager.1
                        @Override // com.vungle.warren.network.Downloader.Listener
                        public void onComplete(File file) {
                            Log.d(APKDirectDownloadManager.TAG, "download complete :" + file.getAbsolutePath());
                            APKDirectDownloadManager._instance.installApk(apkDirectory);
                            if (!APKDirectDownloadManager.isDownloadTaskRunning()) {
                                APKDirectDownloadManager.enableNetworkListener(false, APKDirectDownloadManager.getContext());
                            }
                            APKDirectDownloadManager._instance.notificationList.remove(Integer.valueOf(iHashCode));
                        }

                        @Override // com.vungle.warren.network.Downloader.Listener
                        public void onProgress(int i) {
                            Log.d(APKDirectDownloadManager.TAG, "download progress :" + i);
                            APKDirectDownloadManager._instance.notifyProgress(iHashCode, i);
                        }

                        @Override // com.vungle.warren.network.Downloader.Listener
                        public void onError(Throwable th) {
                            Log.d(APKDirectDownloadManager.TAG, "download complete :" + th.getMessage());
                            if (APKDirectDownloadManager.isDownloadTaskRunning()) {
                                APKDirectDownloadManager.enableNetworkListener(false, APKDirectDownloadManager.getContext());
                            }
                            APKDirectDownloadManager._instance.dismissNotification(iHashCode);
                        }
                    });
                } else {
                    _instance.notifyProgress(iHashCode, -1);
                    _instance.pendingNotificationList.add(Integer.valueOf(iHashCode));
                    _instance.pendingDownloadUrl.add(str);
                }
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        Intent intent = new Intent();
        intent.setClass(_instance.context.get(), VungleWebViewActivity.class);
        intent.putExtra(VungleWebViewActivity.INTENT_URL, str);
        intent.setFlags(DriveFile.MODE_READ_ONLY);
        _instance.context.get().startActivity(intent);
    }

    public static boolean isApkUrl(String str) {
        return MimeTypeMap.getFileExtensionFromUrl(str).toLowerCase().endsWith("apk");
    }

    private void clearDownloadApkCache() {
        new Thread(new Runnable() { // from class: com.vungle.warren.download.APKDirectDownloadManager.2
            @Override // java.lang.Runnable
            public void run() {
                File[] fileArrListFiles = APKDirectDownloadManager.this.getCacheDirectory().listFiles();
                if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                    return;
                }
                for (int i = 0; i < fileArrListFiles.length; i++) {
                    if (fileArrListFiles[i].isFile() && fileArrListFiles[i].exists() && fileArrListFiles[i].isFile()) {
                        Log.d(APKDirectDownloadManager.TAG, "Clear cache:" + fileArrListFiles[i].getName());
                        fileArrListFiles[i].delete();
                    }
                }
            }
        }).start();
    }

    private File getApkDirectory(String str) throws IllegalStateException {
        File file = new File(getCacheDirectory().getPath() + File.separator);
        if (!file.exists()) {
            file.mkdir();
        }
        return new File(getCacheDirectory().getPath() + File.separator + str + ".apk");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File getCacheDirectory() throws IllegalStateException {
        if (this.context == null) {
            throw new IllegalStateException("Context has expired, cannot continue.");
        }
        File file = new File(this.context.get().getCacheDir().getPath() + File.separator + FOLDER_NAME + File.separator + "apk");
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installApk(File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(DriveFile.MODE_READ_ONLY);
        if (Build.VERSION.SDK_INT > 23) {
            intent.setDataAndType(FileProvider.getUriForFile(this.context.get(), "com.vungle.download.provider", file), "application/vnd.android.package-archive");
            intent.addFlags(3);
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        if (this.context.get().getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            this.context.get().startActivity(intent);
        }
        String name = file.getName();
        String strSubstring = name.substring(0, name.length() - 4);
        Log.d(TAG, "identifier is" + strSubstring);
        dismissNotification(strSubstring.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyProgress(int i, int i2) {
        Log.d(TAG, "notify id is :" + i + " progress:" + i2);
        if (this.notifyManager == null) {
            Context context = this.context.get();
            this.context.get();
            this.notifyManager = (NotificationManager) context.getSystemService("notification");
            this.mBuilder = new NotificationCompat.Builder(this.context.get());
            this.mBuilder.setSmallIcon(R.drawable.stat_sys_download_done);
        }
        if (i2 == -1 || !this.isWifiEnabled) {
            this.mBuilder.setContentTitle(LocaleString.getLocaleText(4)).setContentText(LocaleString.getLocaleText(2)).setProgress(0, 0, false);
        } else if (i2 >= 0 && i2 < 100) {
            this.mBuilder.setContentTitle(LocaleString.getLocaleText(4)).setContentText(LocaleString.getLocaleText(3));
            this.mBuilder.setProgress(100, i2, false);
        } else {
            this.mBuilder.setContentText(LocaleString.getLocaleText(5)).setProgress(0, 0, false);
        }
        this.notifyManager.notify(i, this.mBuilder.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissNotification(int i) {
        NotificationManager notificationManager = this.notifyManager;
        if (notificationManager != null) {
            notificationManager.cancel(i);
        }
    }

    private boolean isWifiEnabled() {
        boolean z = false;
        if (PermissionChecker.checkCallingOrSelfPermission(this.context.get(), "android.permission.ACCESS_NETWORK_STATE") == 0) {
            Context context = this.context.get();
            this.context.get();
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            int type = activeNetworkInfo.getType();
            if (type != 0) {
                if (type == 1 || type == 6) {
                    z = true;
                } else if (type != 7) {
                }
            }
        }
        this.isWifiEnabled = z;
        return z;
    }

    public static void handleDownload(Context context) {
        if (context == null) {
            return;
        }
        if (_instance == null || !isDownloadTaskRunning() || _instance.apkDirectDownloadStatus == 0) {
            enableNetworkListener(false, context);
            return;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if ((activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && activeNetworkInfo.getType() == 1) && !_instance.isWifiEnabled) {
            resumeDownload();
        } else if (_instance.isWifiEnabled) {
            pauseDownload();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void resumeDownload() {
        APKDirectDownloadManager aPKDirectDownloadManager = _instance;
        aPKDirectDownloadManager.isWifiEnabled = true;
        aPKDirectDownloadManager.downloader.resume();
        if (!_instance.pendingNotificationList.isEmpty()) {
            Iterator<Integer> it = _instance.pendingNotificationList.iterator();
            while (it.hasNext()) {
                _instance.dismissNotification(it.next().intValue());
            }
        }
        if (_instance.pendingDownloadUrl.isEmpty()) {
            return;
        }
        Iterator<String> it2 = _instance.pendingDownloadUrl.iterator();
        while (it2.hasNext()) {
            download(it2.next());
        }
        _instance.pendingDownloadUrl.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void pauseDownload() {
        APKDirectDownloadManager aPKDirectDownloadManager = _instance;
        aPKDirectDownloadManager.isWifiEnabled = false;
        aPKDirectDownloadManager.downloader.pause();
        Iterator<Integer> it = _instance.notificationList.iterator();
        while (it.hasNext()) {
            _instance.notifyProgress(it.next().intValue(), -1);
        }
    }

    public static void enableNetworkListener(boolean z, Context context) {
        APKDirectDownloadManager aPKDirectDownloadManager = _instance;
        if (aPKDirectDownloadManager.isNetworkReceiverEnabled == z || context == null) {
            return;
        }
        aPKDirectDownloadManager.isNetworkReceiverEnabled = z;
        if (Build.VERSION.SDK_INT < 24) {
            if (z) {
                NetworkStateReceiver.enableBroadcastReceiver(context, true);
                return;
            } else {
                NetworkStateReceiver.enableBroadcastReceiver(context, false);
                return;
            }
        }
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.vungle.warren.download.APKDirectDownloadManager.3
            @Override // android.net.ConnectivityManager.NetworkCallback
            @RequiresApi(api = 24)
            public void onAvailable(Network network) {
                super.onAvailable(network);
                Log.d(APKDirectDownloadManager.TAG, "onAvailable:" + network);
                APKDirectDownloadManager.resumeDownload();
                APKDirectDownloadManager._instance.isWifiEnabled = true;
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            @RequiresApi(api = 24)
            public void onLost(Network network) {
                super.onLost(network);
                Log.d(APKDirectDownloadManager.TAG, "onLost:" + network);
                APKDirectDownloadManager.pauseDownload();
                APKDirectDownloadManager._instance.isWifiEnabled = false;
            }
        };
        ConnectivityManager connectivityManager = (ConnectivityManager) _instance.context.get().getSystemService("connectivity");
        if (connectivityManager == null) {
            return;
        }
        if (z) {
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addTransportType(1).addCapability(12);
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback);
            return;
        }
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static Context getContext() {
        WeakReference<Context> weakReference;
        APKDirectDownloadManager aPKDirectDownloadManager = _instance;
        if (aPKDirectDownloadManager == null || (weakReference = aPKDirectDownloadManager.context) == null) {
            return null;
        }
        return weakReference.get();
    }

    public static boolean isDownloadTaskRunning() {
        return !_instance.pendingDownloadUrl.isEmpty() || _instance.downloader.isDownloadTaskRunning();
    }
}
