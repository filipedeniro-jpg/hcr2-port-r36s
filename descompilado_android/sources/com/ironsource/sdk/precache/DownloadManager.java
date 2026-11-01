package com.ironsource.sdk.precache;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.ironsource.sdk.data.SSAFile;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import com.ironsource.sdk.utils.IronSourceStorageUtils;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadManager {
    public static final String CAMPAIGNS = "campaigns";
    public static final String FILE_ALREADY_EXIST = "file_already_exist";
    protected static final String FILE_NOT_FOUND_EXCEPTION = "file not found exception";
    public static final String GLOBAL_ASSETS = "globalAssets";
    protected static final String HTTP_EMPTY_RESPONSE = "http empty response";
    protected static final String HTTP_ERROR_CODE = "http error code";
    protected static final String HTTP_NOT_FOUND = "http not found";
    protected static final String HTTP_OK = "http ok";
    protected static final String IO_EXCEPTION = "io exception";
    protected static final String MALFORMED_URL_EXCEPTION = "malformed url exception";
    static final int MESSAGE_EMPTY_URL = 1007;
    static final int MESSAGE_FILE_DOWNLOAD_FAIL = 1017;
    static final int MESSAGE_FILE_DOWNLOAD_SUCCESS = 1016;
    static final int MESSAGE_FILE_NOT_FOUND_EXCEPTION = 1018;
    static final int MESSAGE_GENERAL_HTTP_ERROR_CODE = 1011;
    static final int MESSAGE_HTTP_EMPTY_RESPONSE = 1006;
    static final int MESSAGE_HTTP_NOT_FOUND = 1005;
    static final int MESSAGE_INIT_BC_FAIL = 1014;
    static final int MESSAGE_IO_EXCEPTION = 1009;
    static final int MESSAGE_MALFORMED_URL_EXCEPTION = 1004;
    static final int MESSAGE_NUM_OF_BANNERS_TO_CACHE = 1013;
    static final int MESSAGE_NUM_OF_BANNERS_TO_INIT_SUCCESS = 1012;
    static final int MESSAGE_OUT_OF_MEMORY_EXCEPTION = 1019;
    static final int MESSAGE_SOCKET_TIMEOUT_EXCEPTION = 1008;
    static final int MESSAGE_TMP_FILE_RENAME_FAILED = 1020;
    static final int MESSAGE_URI_SYNTAX_EXCEPTION = 1010;
    static final int MESSAGE_ZERO_CAMPAIGNS_TO_INIT_SUCCESS = 1015;
    public static final String NO_DISK_SPACE = "no_disk_space";
    public static final String NO_NETWORK_CONNECTION = "no_network_connection";
    public static final int OPERATION_TIMEOUT = 5000;
    protected static final String OUT_OF_MEMORY_EXCEPTION = "out of memory exception";
    public static final String SETTINGS = "settings";
    protected static final String SOCKET_TIMEOUT_EXCEPTION = "socket timeout exception";
    public static final String STORAGE_UNAVAILABLE = "sotrage_unavailable";
    private static final String TAG = "DownloadManager";
    private static final String TEMP_DIR_FOR_FILES = "temp";
    private static final String TEMP_PREFIX_FOR_FILES = "tmp_";
    private static final String UNABLE_TO_CREATE_FOLDER = "unable_to_create_folder";
    protected static final String URI_SYNTAX_EXCEPTION = "uri syntax exception";
    public static final String UTF8_CHARSET = "UTF-8";
    private static DownloadManager mDownloadManager;
    private String mCacheRootDirectory;
    private DownloadHandler mDownloadHandler = getDownloadHandler();
    private Thread mMobileControllerThread;

    public interface OnPreCacheCompletion {
        void onFileDownloadFail(SSAFile sSAFile);

        void onFileDownloadSuccess(SSAFile sSAFile);
    }

    private DownloadManager(String str) {
        this.mCacheRootDirectory = str;
        IronSourceStorageUtils.deleteFolder(this.mCacheRootDirectory, TEMP_DIR_FOR_FILES);
        IronSourceStorageUtils.makeDir(this.mCacheRootDirectory, TEMP_DIR_FOR_FILES);
    }

    public static synchronized DownloadManager getInstance(String str) {
        if (mDownloadManager == null) {
            mDownloadManager = new DownloadManager(str);
        }
        return mDownloadManager;
    }

    static class DownloadHandler extends Handler {
        OnPreCacheCompletion mListener;

        DownloadHandler() {
        }

        void setOnPreCacheCompletion(OnPreCacheCompletion onPreCacheCompletion) {
            if (onPreCacheCompletion == null) {
                throw new IllegalArgumentException();
            }
            this.mListener = onPreCacheCompletion;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1016) {
                this.mListener.onFileDownloadSuccess((SSAFile) message.obj);
            } else {
                if (i != 1017) {
                    return;
                }
                this.mListener.onFileDownloadFail((SSAFile) message.obj);
            }
        }

        public void release() {
            this.mListener = null;
        }
    }

    DownloadHandler getDownloadHandler() {
        return new DownloadHandler();
    }

    public void setOnPreCacheCompletion(OnPreCacheCompletion onPreCacheCompletion) {
        this.mDownloadHandler.setOnPreCacheCompletion(onPreCacheCompletion);
    }

    public void release() {
        mDownloadManager = null;
        this.mDownloadHandler.release();
        this.mDownloadHandler = null;
    }

    public void downloadFile(SSAFile sSAFile) {
        new Thread(new SingleFileWorkerThread(sSAFile, this.mDownloadHandler, this.mCacheRootDirectory, getTempFilesDirectory())).start();
    }

    public void downloadMobileControllerFile(SSAFile sSAFile) {
        this.mMobileControllerThread = new Thread(new SingleFileWorkerThread(sSAFile, this.mDownloadHandler, this.mCacheRootDirectory, getTempFilesDirectory()));
        this.mMobileControllerThread.start();
    }

    public boolean isMobileControllerThreadLive() {
        Thread thread = this.mMobileControllerThread;
        return thread != null && thread.isAlive();
    }

    static class SingleFileWorkerThread implements Runnable {
        private String mCacheRootDirectory;
        private long mConnectionRetries = getConnectionRetries();
        Handler mDownloadHandler;
        private String mFile;
        private String mFileName;
        private String mPath;
        private final String mTempFilesDirectory;

        SingleFileWorkerThread(SSAFile sSAFile, Handler handler, String str, String str2) {
            this.mFile = sSAFile.getFile();
            this.mPath = sSAFile.getPath();
            this.mFileName = guessFileName(this.mFile);
            this.mCacheRootDirectory = str;
            this.mDownloadHandler = handler;
            this.mTempFilesDirectory = str2;
        }

        String guessFileName(String str) {
            return SDKUtils.getFileName(this.mFile);
        }

        FileWorkerThread getFileWorkerThread(String str, String str2, String str3, long j, String str4) {
            return new FileWorkerThread(str, str2, str3, j, str4);
        }

        Message getMessage() {
            return new Message();
        }

        String makeDir(String str, String str2) {
            return IronSourceStorageUtils.makeDir(str, str2);
        }

        /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
            java.lang.UnsupportedOperationException
            	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1092)
            	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:419)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:31)
            	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:399)
            	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:89)
            	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:31)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
            	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:21)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
            */
        @Override // java.lang.Runnable
        public void run() {
            /*
                r11 = this;
                com.ironsource.sdk.data.SSAFile r0 = new com.ironsource.sdk.data.SSAFile
                java.lang.String r1 = r11.mFileName
                java.lang.String r2 = r11.mPath
                r0.<init>(r1, r2)
                android.os.Message r1 = r11.getMessage()
                r1.obj = r0
                java.lang.String r2 = r11.mCacheRootDirectory
                java.lang.String r3 = r11.mPath
                java.lang.String r6 = r11.makeDir(r2, r3)
                r2 = 1017(0x3f9, float:1.425E-42)
                if (r6 != 0) goto L28
                r1.what = r2
                java.lang.String r2 = "unable_to_create_folder"
                r0.setErrMsg(r2)
                android.os.Handler r0 = r11.mDownloadHandler
                r0.sendMessage(r1)
                goto L6c
            L28:
                java.lang.String r5 = r11.mFile
                java.lang.String r7 = r0.getFile()
                long r8 = r11.mConnectionRetries
                java.lang.String r10 = r11.mTempFilesDirectory
                r4 = r11
                com.ironsource.sdk.precache.DownloadManager$FileWorkerThread r3 = r4.getFileWorkerThread(r5, r6, r7, r8, r10)
                com.ironsource.sdk.precache.DownloadManager$Result r3 = r3.call()
                int r3 = r3.responseCode
                r4 = 200(0xc8, float:2.8E-43)
                if (r3 == r4) goto L63
                r4 = 404(0x194, float:5.66E-43)
                if (r3 == r4) goto L54
                r4 = 1018(0x3fa, float:1.427E-42)
                if (r3 == r4) goto L54
                r4 = 1019(0x3fb, float:1.428E-42)
                if (r3 == r4) goto L54
                switch(r3) {
                    case 1004: goto L54;
                    case 1005: goto L54;
                    case 1006: goto L54;
                    default: goto L50;
                }
            L50:
                switch(r3) {
                    case 1008: goto L54;
                    case 1009: goto L54;
                    case 1010: goto L54;
                    case 1011: goto L54;
                    default: goto L53;
                }
            L53:
                goto L6c
            L54:
                java.lang.String r3 = r11.convertErrorCodeToMessage(r3)
                r1.what = r2
                r0.setErrMsg(r3)
                android.os.Handler r0 = r11.mDownloadHandler
                r0.sendMessage(r1)
                goto L6c
            L63:
                r0 = 1016(0x3f8, float:1.424E-42)
                r1.what = r0
                android.os.Handler r0 = r11.mDownloadHandler
                r0.sendMessage(r1)
            L6c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ironsource.sdk.precache.DownloadManager.SingleFileWorkerThread.run():void");
        }

        String convertErrorCodeToMessage(int i) {
            String str = "not defined message for " + i;
            if (i != 404) {
                if (i == 1018) {
                    return DownloadManager.FILE_NOT_FOUND_EXCEPTION;
                }
                if (i == 1019) {
                    return DownloadManager.OUT_OF_MEMORY_EXCEPTION;
                }
                switch (i) {
                    case 1004:
                        return DownloadManager.MALFORMED_URL_EXCEPTION;
                    case 1005:
                        break;
                    case 1006:
                        return DownloadManager.HTTP_EMPTY_RESPONSE;
                    default:
                        switch (i) {
                            case 1008:
                                return DownloadManager.SOCKET_TIMEOUT_EXCEPTION;
                            case 1009:
                                return DownloadManager.IO_EXCEPTION;
                            case 1010:
                                return DownloadManager.URI_SYNTAX_EXCEPTION;
                            case 1011:
                                return DownloadManager.HTTP_ERROR_CODE;
                            default:
                                return str;
                        }
                }
            }
            return DownloadManager.HTTP_NOT_FOUND;
        }

        public long getConnectionRetries() {
            return Long.parseLong(IronSourceSharedPrefHelper.getSupersonicPrefHelper().getConnectionRetries());
        }
    }

    static class FileWorkerThread implements Callable<Result> {
        private long mConnectionRetries;
        private String mDirectory;
        private String mFileName;
        private String mFileUrl;
        private String mTmpFilesDirectory;

        public FileWorkerThread(String str, String str2, String str3, long j, String str4) {
            this.mFileUrl = str;
            this.mDirectory = str2;
            this.mFileName = str3;
            this.mConnectionRetries = j;
            this.mTmpFilesDirectory = str4;
        }

        int saveFile(byte[] bArr, String str) throws Exception {
            return IronSourceStorageUtils.saveFile(bArr, str);
        }

        boolean renameFile(String str, String str2) throws Exception {
            return IronSourceStorageUtils.renameFile(str, str2);
        }

        byte[] getBytes(InputStream inputStream) throws IOException {
            return DownloadManager.getBytes(inputStream);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Result call() {
            int i;
            if (this.mConnectionRetries == 0) {
                this.mConnectionRetries = 1L;
            }
            Result resultDownloadContent = null;
            for (int i2 = 0; i2 < this.mConnectionRetries && ((i = (resultDownloadContent = downloadContent(this.mFileUrl, i2)).responseCode) == 1008 || i == 1009); i2++) {
            }
            if (resultDownloadContent != null && resultDownloadContent.body != null) {
                String str = this.mDirectory + File.separator + this.mFileName;
                String str2 = this.mTmpFilesDirectory + File.separator + DownloadManager.TEMP_PREFIX_FOR_FILES + this.mFileName;
                try {
                    if (saveFile(resultDownloadContent.body, str2) == 0) {
                        resultDownloadContent.responseCode = 1006;
                    } else if (!renameFile(str2, str)) {
                        resultDownloadContent.responseCode = 1020;
                    }
                } catch (FileNotFoundException unused) {
                    resultDownloadContent.responseCode = 1018;
                } catch (Error e) {
                    if (!TextUtils.isEmpty(e.getMessage())) {
                        Logger.i(DownloadManager.TAG, e.getMessage());
                    }
                    resultDownloadContent.responseCode = 1019;
                } catch (Exception e2) {
                    if (!TextUtils.isEmpty(e2.getMessage())) {
                        Logger.i(DownloadManager.TAG, e2.getMessage());
                    }
                    resultDownloadContent.responseCode = 1009;
                }
            }
            return resultDownloadContent;
        }

        /* JADX WARN: Code duplicated, block: B:105:0x0138 A[PHI: r4 r9
  0x0138: PHI (r4v13 java.net.HttpURLConnection) = 
  (r4v6 java.net.HttpURLConnection)
  (r4v7 java.net.HttpURLConnection)
  (r4v8 java.net.HttpURLConnection)
  (r4v9 java.net.HttpURLConnection)
  (r4v14 java.net.HttpURLConnection)
 binds: [B:58:0x00e1, B:95:0x0126, B:86:0x0116, B:104:0x0136, B:77:0x0106] A[DONT_GENERATE, DONT_INLINE]
  0x0138: PHI (r9v16 int) = (r9v6 int), (r9v8 int), (r9v9 int), (r9v10 int), (r9v18 int) binds: [B:58:0x00e1, B:95:0x0126, B:86:0x0116, B:104:0x0136, B:77:0x0106] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:121:0x00e7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:67:0x00f1  */
        Result downloadContent(String str, int i) throws Throwable {
            HttpURLConnection httpURLConnection;
            HttpURLConnection httpURLConnection2;
            InputStream inputStream;
            int i2;
            Result result = new Result();
            if (TextUtils.isEmpty(str)) {
                result.url = str;
                result.responseCode = 1007;
                return result;
            }
            InputStream inputStream2 = null;
            inputStream = null;
            inputStream = null;
            inputStream = null;
            inputStream = null;
            inputStream = null;
            inputStream = null;
            inputStream = null;
            inputStream2 = null;
            inputStream = null;
            HttpURLConnection httpURLConnection3 = null;
            inputStream = null;
            inputStream = null;
            inputStream = null;
            InputStream inputStream3 = null;
            int i3 = 0;
            try {
                try {
                    URL url = new URL(str);
                    url.toURI();
                    httpURLConnection2 = (HttpURLConnection) url.openConnection();
                    try {
                        httpURLConnection2.setRequestMethod(HttpRequest.METHOD_GET);
                        httpURLConnection2.setConnectTimeout(5000);
                        httpURLConnection2.setReadTimeout(5000);
                        httpURLConnection2.connect();
                        int responseCode = httpURLConnection2.getResponseCode();
                        if (responseCode < 200 || responseCode >= 400) {
                            responseCode = 1011;
                        } else {
                            inputStream3 = httpURLConnection2.getInputStream();
                            result.body = getBytes(inputStream3);
                        }
                        if (responseCode != 200) {
                            Logger.i(DownloadManager.TAG, " RESPONSE CODE: " + responseCode + " URL: " + str + " ATTEMPT: " + i);
                        }
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        result.url = str;
                        result.responseCode = responseCode;
                    } catch (FileNotFoundException unused) {
                        i2 = 1018;
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        result.url = str;
                        result.responseCode = i2;
                    } catch (Error e3) {
                        e = e3;
                        inputStream = inputStream3;
                        httpURLConnection3 = httpURLConnection2;
                        try {
                            if (!TextUtils.isEmpty(e.getMessage())) {
                                Logger.i(DownloadManager.TAG, e.getMessage());
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            if (httpURLConnection3 != null) {
                                httpURLConnection3.disconnect();
                            }
                            result.url = str;
                            result.responseCode = 1019;
                        } catch (Throwable th) {
                            th = th;
                            httpURLConnection = httpURLConnection3;
                            inputStream2 = inputStream;
                            i3 = 1019;
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            result.url = str;
                            result.responseCode = i3;
                            throw th;
                        }
                    } catch (MalformedURLException unused2) {
                        i2 = 1004;
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        result.url = str;
                        result.responseCode = i2;
                    } catch (SocketTimeoutException unused3) {
                        i2 = 1008;
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        result.url = str;
                        result.responseCode = i2;
                    } catch (URISyntaxException unused4) {
                        i2 = 1010;
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (IOException e8) {
                                e8.printStackTrace();
                            }
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        result.url = str;
                        result.responseCode = i2;
                    } catch (Exception e9) {
                        e = e9;
                        if (!TextUtils.isEmpty(e.getMessage())) {
                            Logger.i(DownloadManager.TAG, e.getMessage());
                        }
                        i2 = 1009;
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (IOException e10) {
                                e10.printStackTrace();
                            }
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        result.url = str;
                        result.responseCode = i2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    result.url = str;
                    result.responseCode = i3;
                    throw th;
                }
            } catch (FileNotFoundException unused5) {
                httpURLConnection2 = null;
            } catch (Error e11) {
                e = e11;
                inputStream = null;
            } catch (MalformedURLException unused6) {
                httpURLConnection2 = null;
            } catch (SocketTimeoutException unused7) {
                httpURLConnection2 = null;
            } catch (URISyntaxException unused8) {
                httpURLConnection2 = null;
            } catch (Exception e12) {
                e = e12;
                httpURLConnection2 = null;
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection = null;
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                result.url = str;
                result.responseCode = i3;
                throw th;
            }
            return result;
        }
    }

    String getTempFilesDirectory() {
        return this.mCacheRootDirectory + File.separator + TEMP_DIR_FOR_FILES;
    }

    static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr, 0, bArr.length);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    static class Result {
        byte[] body;
        int responseCode;
        public String url;

        Result() {
        }
    }
}
