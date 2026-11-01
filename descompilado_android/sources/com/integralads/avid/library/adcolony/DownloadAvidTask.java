package com.integralads.avid.library.adcolony;

import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.utils.AvidLogs;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadAvidTask extends AsyncTask<String, Void, String> {
    private static final int a = 1024;
    private DownloadAvidTaskListener b;

    public interface DownloadAvidTaskListener {
        void failedToLoadAvid();

        void onLoadAvid(String str);
    }

    public DownloadAvidTaskListener getListener() {
        return this.b;
    }

    public void setListener(DownloadAvidTaskListener downloadAvidTaskListener) {
        this.b = downloadAvidTaskListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(String... strArr) throws Throwable {
        BufferedInputStream bufferedInputStream;
        String str = strArr[0];
        InputStream inputStream = null;
        try {
            if (TextUtils.isEmpty(str)) {
                AvidLogs.e("AvidLoader: URL is empty");
                return null;
            }
            try {
                URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
                uRLConnectionOpenConnection.connect();
                bufferedInputStream = new BufferedInputStream(uRLConnectionOpenConnection.getInputStream());
                try {
                    StringWriter stringWriter = new StringWriter();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = bufferedInputStream.read(bArr);
                        if (i == -1) {
                            String string = stringWriter.toString();
                            try {
                                bufferedInputStream.close();
                                return string;
                            } catch (IOException e) {
                                AvidLogs.e("AvidLoader: can not close Stream", e);
                                return null;
                            }
                        }
                        stringWriter.write(new String(bArr, 0, i));
                    }
                } catch (MalformedURLException unused) {
                    AvidLogs.e("AvidLoader: something is wrong with the URL '" + str + "'");
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e2) {
                            AvidLogs.e("AvidLoader: can not close Stream", e2);
                        }
                    }
                    return null;
                } catch (IOException e3) {
                    e = e3;
                    AvidLogs.e("AvidLoader: IO error " + e.getLocalizedMessage());
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                            AvidLogs.e("AvidLoader: can not close Stream", e4);
                        }
                    }
                    return null;
                }
            } catch (MalformedURLException unused2) {
                bufferedInputStream = null;
            } catch (IOException e5) {
                e = e5;
                bufferedInputStream = null;
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e6) {
                        AvidLogs.e("AvidLoader: can not close Stream", e6);
                        return null;
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        if (this.b != null) {
            if (!TextUtils.isEmpty(str)) {
                this.b.onLoadAvid(str);
            } else {
                this.b.failedToLoadAvid();
            }
        }
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        DownloadAvidTaskListener downloadAvidTaskListener = this.b;
        if (downloadAvidTaskListener != null) {
            downloadAvidTaskListener.failedToLoadAvid();
        }
    }

    @VisibleForTesting
    void a(String str) {
        onPostExecute(str);
    }

    @VisibleForTesting
    void a() {
        onCancelled();
    }
}
