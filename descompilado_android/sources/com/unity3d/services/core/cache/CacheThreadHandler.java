package com.unity3d.services.core.cache;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.unity3d.services.core.api.Request;
import com.unity3d.services.core.device.Device;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.request.IWebRequestProgressListener;
import com.unity3d.services.core.request.NetworkIOException;
import com.unity3d.services.core.request.WebRequest;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class CacheThreadHandler extends Handler {
    private WebRequest _currentRequest = null;
    private boolean _canceled = false;
    private boolean _active = false;

    CacheThreadHandler() {
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) throws Throwable {
        HashMap<String, List<String>> map;
        Bundle data = message.getData();
        String string = data.getString("source");
        data.remove("source");
        String string2 = data.getString("target");
        data.remove("target");
        int i = data.getInt("connectTimeout");
        data.remove("connectTimeout");
        int i2 = data.getInt("readTimeout");
        data.remove("readTimeout");
        int i3 = data.getInt("progressInterval");
        data.remove("progressInterval");
        boolean z = data.getBoolean("append", false);
        data.remove("append");
        if (data.size() > 0) {
            DeviceLog.debug("There are headers left in data, reading them");
            HashMap<String, List<String>> map2 = new HashMap<>();
            for (String str : data.keySet()) {
                map2.put(str, Arrays.asList(data.getStringArray(str)));
            }
            map = map2;
        } else {
            map = null;
        }
        File file = new File(string2);
        if ((z && !file.exists()) || (!z && file.exists())) {
            this._active = false;
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_STATE_WRONG, string, string2, Boolean.valueOf(z), Boolean.valueOf(file.exists()));
        } else {
            if (message.what != 1) {
                return;
            }
            downloadFile(string, string2, i, i2, i3, map, z);
        }
    }

    public void setCancelStatus(boolean z) {
        WebRequest webRequest;
        this._canceled = z;
        if (!z || (webRequest = this._currentRequest) == null) {
            return;
        }
        this._active = false;
        webRequest.cancel();
    }

    public boolean isActive() {
        return this._active;
    }

    /* JADX WARN: Code duplicated, block: B:119:0x0319 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:139:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v61, types: [com.unity3d.services.core.webview.WebViewApp] */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v31 */
    /* JADX WARN: Type inference failed for: r14v32 */
    /* JADX WARN: Type inference failed for: r14v33 */
    /* JADX WARN: Type inference failed for: r14v34 */
    /* JADX WARN: Type inference failed for: r14v35 */
    /* JADX WARN: Type inference failed for: r14v36 */
    /* JADX WARN: Type inference failed for: r14v37 */
    /* JADX WARN: Type inference failed for: r14v38 */
    /* JADX WARN: Type inference failed for: r14v39 */
    /* JADX WARN: Type inference failed for: r14v40 */
    /* JADX WARN: Type inference failed for: r14v41 */
    /* JADX WARN: Type inference failed for: r14v42 */
    /* JADX WARN: Type inference failed for: r14v43 */
    /* JADX WARN: Type inference failed for: r14v44 */
    /* JADX WARN: Type inference failed for: r14v45 */
    /* JADX WARN: Type inference failed for: r14v55 */
    /* JADX WARN: Type inference failed for: r14v56 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9, types: [com.unity3d.services.core.request.WebRequest] */
    /* JADX WARN: Type inference failed for: r23v0, types: [com.unity3d.services.core.cache.CacheThreadHandler] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r3v13, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v14, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10, types: [int] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v48 */
    /* JADX WARN: Type inference failed for: r4v49 */
    /* JADX WARN: Type inference failed for: r4v51 */
    /* JADX WARN: Type inference failed for: r4v52 */
    /* JADX WARN: Type inference failed for: r4v53 */
    /* JADX WARN: Type inference failed for: r4v54 */
    /* JADX WARN: Type inference failed for: r4v55 */
    /* JADX WARN: Type inference failed for: r4v56 */
    /* JADX WARN: Type inference failed for: r4v57 */
    /* JADX WARN: Type inference failed for: r4v58 */
    /* JADX WARN: Type inference failed for: r4v59 */
    /* JADX WARN: Type inference failed for: r4v60 */
    /* JADX WARN: Type inference failed for: r4v61 */
    /* JADX WARN: Type inference failed for: r4v62 */
    /* JADX WARN: Type inference failed for: r4v63 */
    /* JADX WARN: Type inference failed for: r4v64 */
    /* JADX WARN: Type inference failed for: r4v65 */
    /* JADX WARN: Type inference failed for: r4v70 */
    /* JADX WARN: Type inference failed for: r4v71 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v37, types: [long] */
    /* JADX WARN: Type inference failed for: r6v38 */
    /* JADX WARN: Type inference failed for: r6v39 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v40 */
    /* JADX WARN: Type inference failed for: r6v41 */
    /* JADX WARN: Type inference failed for: r6v42 */
    /* JADX WARN: Type inference failed for: r6v43 */
    /* JADX WARN: Type inference failed for: r6v44 */
    /* JADX WARN: Type inference failed for: r6v45 */
    /* JADX WARN: Type inference failed for: r6v46 */
    /* JADX WARN: Type inference failed for: r6v47 */
    /* JADX WARN: Type inference failed for: r6v48 */
    /* JADX WARN: Type inference failed for: r6v49 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v50 */
    /* JADX WARN: Type inference failed for: r6v51 */
    /* JADX WARN: Type inference failed for: r6v52 */
    /* JADX WARN: Type inference failed for: r6v53 */
    /* JADX WARN: Type inference failed for: r6v54 */
    /* JADX WARN: Type inference failed for: r6v55 */
    /* JADX WARN: Type inference failed for: r6v56 */
    /* JADX WARN: Type inference failed for: r6v57 */
    /* JADX WARN: Type inference failed for: r6v58 */
    /* JADX WARN: Type inference failed for: r6v59 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    private void downloadFile(String str, String str2, int i, int i2, final int i3, HashMap<String, List<String>> map, boolean z) throws Throwable {
        ?? r4;
        ?? length;
        char c;
        char c2;
        WebRequest webRequest;
        int i4;
        ?? r6;
        WebRequest webRequest2;
        int i5;
        ?? r7;
        WebRequest webRequest3;
        int i6;
        ?? r8;
        WebRequest webRequest4;
        int i7;
        ?? r9;
        WebRequest webRequest5;
        int i8;
        ?? r10;
        Throwable th;
        ?? r14;
        ?? r11;
        int i9;
        FileOutputStream fileOutputStream;
        WebRequest webRequest6;
        char c3;
        boolean z2;
        WebRequest webRequest7;
        int i10;
        boolean z3;
        WebRequest webRequest8;
        int i11;
        boolean z4;
        WebRequest webRequest9;
        int i12;
        boolean z5;
        WebRequest webRequest10;
        int i13;
        boolean z6;
        WebRequest webRequest11;
        int i14;
        boolean z7;
        ?? r15 = str;
        if (this._canceled || r15 == 0 || str2 == null) {
            return;
        }
        final File file = new File(str2);
        if (z) {
            ?? sb = new StringBuilder();
            sb.append("Unity Ads cache: resuming download ");
            sb.append(r15);
            sb.append(" to ");
            sb.append(str2);
            sb.append(" at ");
            length = file.length();
            sb.append(length);
            sb.append(" bytes");
            DeviceLog.debug(sb.toString());
            r4 = "Unity Ads cache: resuming download ";
        } else {
            DeviceLog.debug("Unity Ads cache: start downloading " + r15 + " to " + str2);
            r4 = "Unity Ads cache: start downloading ";
        }
        if (!Device.isActiveNetworkConnected()) {
            DeviceLog.debug("Unity Ads cache: download cancelled, no internet connection available");
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, new Object[]{CacheError.NO_INTERNET, r15});
            return;
        }
        this._active = true;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                FileOutputStream fileOutputStream3 = new FileOutputStream(file, z);
                try {
                    this._currentRequest = getWebRequest(r15, i, i2, map);
                    this._currentRequest.setProgressListener(new IWebRequestProgressListener() { // from class: com.unity3d.services.core.cache.CacheThreadHandler.1
                        private long lastProgressEventTime = System.currentTimeMillis();

                        @Override // com.unity3d.services.core.request.IWebRequestProgressListener
                        public void onRequestStart(String str3, long j, int i15, Map<String, List<String>> map2) {
                            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_STARTED, str3, Long.valueOf(file.length()), Long.valueOf(j + file.length()), Integer.valueOf(i15), Request.getResponseHeadersMap(map2));
                        }

                        @Override // com.unity3d.services.core.request.IWebRequestProgressListener
                        public void onRequestProgress(String str3, long j, long j2) {
                            if (i3 <= 0 || System.currentTimeMillis() - this.lastProgressEventTime <= i3) {
                                return;
                            }
                            this.lastProgressEventTime = System.currentTimeMillis();
                            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_PROGRESS, str3, Long.valueOf(j), Long.valueOf(j2));
                        }
                    });
                    long jMakeStreamRequest = this._currentRequest.makeStreamRequest(fileOutputStream3);
                    this._active = false;
                    fileOutputStream = fileOutputStream3;
                    WebRequest webRequest12 = null;
                    c = 1;
                    c2 = 2;
                    try {
                        postProcessDownload(jElapsedRealtime, str, file, jMakeStreamRequest, this._currentRequest.getContentLength(), this._currentRequest.isCanceled(), this._currentRequest.getResponseCode(), this._currentRequest.getResponseHeaders());
                        this._currentRequest = null;
                        try {
                            fileOutputStream.close();
                        } catch (Exception e) {
                            DeviceLog.exception("Error closing stream", e);
                            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_IO_ERROR, str, e.getMessage());
                        }
                    } catch (NetworkIOException e2) {
                        e = e2;
                        i14 = 3;
                        z7 = false;
                        webRequest11 = webRequest12;
                        fileOutputStream2 = fileOutputStream;
                        i8 = i14;
                        r10 = z7;
                        webRequest5 = webRequest11;
                        DeviceLog.exception("Network error", e);
                        this._active = r10;
                        WebViewApp currentApp = WebViewApp.getCurrentApp();
                        WebViewEventCategory webViewEventCategory = WebViewEventCategory.CACHE;
                        CacheEvent cacheEvent = CacheEvent.DOWNLOAD_ERROR;
                        Object[] objArr = new Object[i8];
                        objArr[r10] = CacheError.NETWORK_ERROR;
                        objArr[c] = str;
                        objArr[c2] = e.getMessage();
                        currentApp.sendEvent(webViewEventCategory, cacheEvent, objArr);
                        this._currentRequest = webRequest5;
                        r4 = i8;
                        length = r10;
                        r15 = webRequest5;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                                r4 = i8;
                                length = r10;
                                r15 = webRequest5;
                            } catch (Exception e3) {
                                DeviceLog.exception("Error closing stream", e3);
                                WebViewApp currentApp2 = WebViewApp.getCurrentApp();
                                WebViewEventCategory webViewEventCategory2 = WebViewEventCategory.CACHE;
                                CacheEvent cacheEvent2 = CacheEvent.DOWNLOAD_ERROR;
                                Object[] objArr2 = new Object[i8];
                                objArr2[r10] = CacheError.FILE_IO_ERROR;
                                objArr2[c] = str;
                                objArr2[c2] = e3.getMessage();
                                currentApp2.sendEvent(webViewEventCategory2, cacheEvent2, objArr2);
                                r4 = objArr2;
                                length = r10;
                                r15 = webRequest5;
                            }
                        }
                    } catch (FileNotFoundException e4) {
                        e = e4;
                        i13 = 3;
                        z6 = false;
                        webRequest10 = webRequest12;
                        fileOutputStream2 = fileOutputStream;
                        i7 = i13;
                        r9 = z6;
                        webRequest4 = webRequest10;
                        DeviceLog.exception("Couldn't create target file", e);
                        this._active = r9;
                        WebViewApp currentApp3 = WebViewApp.getCurrentApp();
                        WebViewEventCategory webViewEventCategory3 = WebViewEventCategory.CACHE;
                        CacheEvent cacheEvent3 = CacheEvent.DOWNLOAD_ERROR;
                        Object[] objArr3 = new Object[i7];
                        objArr3[r9] = CacheError.FILE_IO_ERROR;
                        objArr3[c] = str;
                        objArr3[c2] = e.getMessage();
                        currentApp3.sendEvent(webViewEventCategory3, cacheEvent3, objArr3);
                        this._currentRequest = webRequest4;
                        r4 = i7;
                        length = r9;
                        r15 = webRequest4;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                                r4 = i7;
                                length = r9;
                                r15 = webRequest4;
                            } catch (Exception e5) {
                                DeviceLog.exception("Error closing stream", e5);
                                WebViewApp currentApp4 = WebViewApp.getCurrentApp();
                                WebViewEventCategory webViewEventCategory4 = WebViewEventCategory.CACHE;
                                CacheEvent cacheEvent4 = CacheEvent.DOWNLOAD_ERROR;
                                Object[] objArr4 = new Object[i7];
                                objArr4[r9] = CacheError.FILE_IO_ERROR;
                                objArr4[c] = str;
                                objArr4[c2] = e5.getMessage();
                                currentApp4.sendEvent(webViewEventCategory4, cacheEvent4, objArr4);
                                r4 = objArr4;
                                length = r9;
                                r15 = webRequest4;
                            }
                        }
                    } catch (IllegalStateException e6) {
                        e = e6;
                        i12 = 3;
                        z5 = false;
                        webRequest9 = webRequest12;
                        fileOutputStream2 = fileOutputStream;
                        i4 = i12;
                        r6 = z5;
                        webRequest = webRequest9;
                        DeviceLog.exception("Illegal state", e);
                        this._active = r6;
                        WebViewApp currentApp5 = WebViewApp.getCurrentApp();
                        WebViewEventCategory webViewEventCategory5 = WebViewEventCategory.CACHE;
                        CacheEvent cacheEvent5 = CacheEvent.DOWNLOAD_ERROR;
                        Object[] objArr5 = new Object[i4];
                        objArr5[r6] = CacheError.ILLEGAL_STATE;
                        objArr5[c] = str;
                        objArr5[c2] = e.getMessage();
                        currentApp5.sendEvent(webViewEventCategory5, cacheEvent5, objArr5);
                        this._currentRequest = webRequest;
                        r4 = i4;
                        length = r6;
                        r15 = webRequest;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                                r4 = i4;
                                length = r6;
                                r15 = webRequest;
                            } catch (Exception e7) {
                                DeviceLog.exception("Error closing stream", e7);
                                WebViewApp currentApp6 = WebViewApp.getCurrentApp();
                                WebViewEventCategory webViewEventCategory6 = WebViewEventCategory.CACHE;
                                CacheEvent cacheEvent6 = CacheEvent.DOWNLOAD_ERROR;
                                Object[] objArr6 = new Object[i4];
                                objArr6[r6] = CacheError.FILE_IO_ERROR;
                                objArr6[c] = str;
                                objArr6[c2] = e7.getMessage();
                                currentApp6.sendEvent(webViewEventCategory6, cacheEvent6, objArr6);
                                r4 = objArr6;
                                length = r6;
                                r15 = webRequest;
                            }
                        }
                    } catch (MalformedURLException e8) {
                        e = e8;
                        i11 = 3;
                        z4 = false;
                        webRequest8 = webRequest12;
                        fileOutputStream2 = fileOutputStream;
                        i6 = i11;
                        r8 = z4;
                        webRequest3 = webRequest8;
                        DeviceLog.exception("Malformed URL", e);
                        this._active = r8;
                        WebViewApp currentApp7 = WebViewApp.getCurrentApp();
                        WebViewEventCategory webViewEventCategory7 = WebViewEventCategory.CACHE;
                        CacheEvent cacheEvent7 = CacheEvent.DOWNLOAD_ERROR;
                        Object[] objArr7 = new Object[i6];
                        objArr7[r8] = CacheError.MALFORMED_URL;
                        objArr7[c] = str;
                        objArr7[c2] = e.getMessage();
                        currentApp7.sendEvent(webViewEventCategory7, cacheEvent7, objArr7);
                        this._currentRequest = webRequest3;
                        r4 = i6;
                        length = r8;
                        r15 = webRequest3;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                                r4 = i6;
                                length = r8;
                                r15 = webRequest3;
                            } catch (Exception e9) {
                                DeviceLog.exception("Error closing stream", e9);
                                WebViewApp currentApp8 = WebViewApp.getCurrentApp();
                                WebViewEventCategory webViewEventCategory8 = WebViewEventCategory.CACHE;
                                CacheEvent cacheEvent8 = CacheEvent.DOWNLOAD_ERROR;
                                Object[] objArr8 = new Object[i6];
                                objArr8[r8] = CacheError.FILE_IO_ERROR;
                                objArr8[c] = str;
                                objArr8[c2] = e9.getMessage();
                                currentApp8.sendEvent(webViewEventCategory8, cacheEvent8, objArr8);
                                r4 = objArr8;
                                length = r8;
                                r15 = webRequest3;
                            }
                        }
                    } catch (IOException e10) {
                        e = e10;
                        i10 = 3;
                        z3 = false;
                        webRequest7 = webRequest12;
                        fileOutputStream2 = fileOutputStream;
                        i5 = i10;
                        r7 = z3;
                        webRequest2 = webRequest7;
                        DeviceLog.exception("Couldn't request stream", e);
                        this._active = r7;
                        WebViewApp currentApp9 = WebViewApp.getCurrentApp();
                        WebViewEventCategory webViewEventCategory9 = WebViewEventCategory.CACHE;
                        CacheEvent cacheEvent9 = CacheEvent.DOWNLOAD_ERROR;
                        Object[] objArr9 = new Object[i5];
                        objArr9[r7] = CacheError.FILE_IO_ERROR;
                        objArr9[c] = str;
                        objArr9[c2] = e.getMessage();
                        currentApp9.sendEvent(webViewEventCategory9, cacheEvent9, objArr9);
                        this._currentRequest = webRequest2;
                        r4 = i5;
                        length = r7;
                        r15 = webRequest2;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                                r4 = i5;
                                length = r7;
                                r15 = webRequest2;
                            } catch (Exception e11) {
                                DeviceLog.exception("Error closing stream", e11);
                                WebViewApp currentApp10 = WebViewApp.getCurrentApp();
                                WebViewEventCategory webViewEventCategory10 = WebViewEventCategory.CACHE;
                                CacheEvent cacheEvent10 = CacheEvent.DOWNLOAD_ERROR;
                                Object[] objArr10 = new Object[i5];
                                objArr10[r7] = CacheError.FILE_IO_ERROR;
                                objArr10[c] = str;
                                objArr10[c2] = e11.getMessage();
                                currentApp10.sendEvent(webViewEventCategory10, cacheEvent10, objArr10);
                                r4 = objArr10;
                                length = r7;
                                r15 = webRequest2;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        c3 = 3;
                        z2 = false;
                        webRequest6 = webRequest12;
                        th = th;
                        fileOutputStream2 = fileOutputStream;
                        i9 = c3;
                        r11 = z2;
                        r14 = webRequest6;
                        this._currentRequest = r14;
                        if (fileOutputStream2 == null) {
                            throw th;
                        }
                        try {
                            fileOutputStream2.close();
                            throw th;
                        } catch (Exception e12) {
                            DeviceLog.exception("Error closing stream", e12);
                            WebViewApp currentApp11 = WebViewApp.getCurrentApp();
                            WebViewEventCategory webViewEventCategory11 = WebViewEventCategory.CACHE;
                            CacheEvent cacheEvent11 = CacheEvent.DOWNLOAD_ERROR;
                            Object[] objArr11 = new Object[i9];
                            objArr11[r11] = CacheError.FILE_IO_ERROR;
                            objArr11[c] = str;
                            objArr11[c2] = e12.getMessage();
                            currentApp11.sendEvent(webViewEventCategory11, cacheEvent11, objArr11);
                            throw th;
                        }
                    }
                } catch (NetworkIOException e13) {
                    e = e13;
                    fileOutputStream = fileOutputStream3;
                    webRequest11 = null;
                    i14 = 3;
                    z7 = false;
                    c = 1;
                    c2 = 2;
                } catch (FileNotFoundException e14) {
                    e = e14;
                    fileOutputStream = fileOutputStream3;
                    webRequest10 = null;
                    i13 = 3;
                    z6 = false;
                    c = 1;
                    c2 = 2;
                } catch (IllegalStateException e15) {
                    e = e15;
                    fileOutputStream = fileOutputStream3;
                    webRequest9 = null;
                    i12 = 3;
                    z5 = false;
                    c = 1;
                    c2 = 2;
                } catch (MalformedURLException e16) {
                    e = e16;
                    fileOutputStream = fileOutputStream3;
                    webRequest8 = null;
                    i11 = 3;
                    z4 = false;
                    c = 1;
                    c2 = 2;
                } catch (IOException e17) {
                    e = e17;
                    fileOutputStream = fileOutputStream3;
                    webRequest7 = null;
                    i10 = 3;
                    z3 = false;
                    c = 1;
                    c2 = 2;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = fileOutputStream3;
                    webRequest6 = null;
                    c3 = 3;
                    z2 = false;
                    c = 1;
                    c2 = 2;
                }
            } catch (Throwable th4) {
                th = th4;
                th = th;
                i9 = r4;
                r11 = length;
                r14 = r15;
                this._currentRequest = r14;
                if (fileOutputStream2 == null) {
                    throw th;
                }
                fileOutputStream2.close();
                throw th;
            }
        } catch (NetworkIOException e18) {
            e = e18;
            webRequest5 = null;
            i8 = 3;
            r10 = 0;
            c = 1;
            c2 = 2;
        } catch (FileNotFoundException e19) {
            e = e19;
            webRequest4 = null;
            i7 = 3;
            r9 = 0;
            c = 1;
            c2 = 2;
        } catch (MalformedURLException e20) {
            e = e20;
            webRequest3 = null;
            i6 = 3;
            r8 = 0;
            c = 1;
            c2 = 2;
        } catch (IOException e21) {
            e = e21;
            webRequest2 = null;
            i5 = 3;
            r7 = 0;
            c = 1;
            c2 = 2;
        } catch (IllegalStateException e22) {
            e = e22;
            webRequest = null;
            i4 = 3;
            r6 = 0;
            c = 1;
            c2 = 2;
        } catch (Throwable th5) {
            th = th5;
            r15 = 0;
            r4 = 3;
            length = 0;
            c = 1;
            c2 = 2;
            th = th;
            i9 = r4;
            r11 = length;
            r14 = r15;
            this._currentRequest = r14;
            if (fileOutputStream2 == null) {
                throw th;
            }
            fileOutputStream2.close();
            throw th;
        }
    }

    private void postProcessDownload(long j, String str, File file, long j2, long j3, boolean z, int i, Map<String, List<String>> map) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - j;
        if (!file.setReadable(true, false)) {
            DeviceLog.debug("Unity Ads cache: could not set file readable!");
        }
        if (!z) {
            DeviceLog.debug("Unity Ads cache: File " + file.getName() + " of " + j2 + " bytes downloaded in " + jElapsedRealtime + "ms");
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_END, str, Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(jElapsedRealtime), Integer.valueOf(i), Request.getResponseHeadersMap(map));
            return;
        }
        DeviceLog.debug("Unity Ads cache: downloading of " + str + " stopped");
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_STOPPED, str, Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(jElapsedRealtime), Integer.valueOf(i), Request.getResponseHeadersMap(map));
    }

    private WebRequest getWebRequest(String str, int i, int i2, HashMap<String, List<String>> map) throws MalformedURLException {
        HashMap map2 = new HashMap();
        if (map != null) {
            map2.putAll(map);
        }
        return new WebRequest(str, HttpRequest.METHOD_GET, map2, i, i2);
    }
}
