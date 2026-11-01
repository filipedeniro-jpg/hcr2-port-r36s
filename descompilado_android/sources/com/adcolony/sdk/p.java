package com.adcolony.sdk;

import android.app.Activity;
import com.appsflyer.share.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class p implements Runnable {
    boolean c;
    int d;
    int e;
    private HttpURLConnection f;
    private InputStream g;
    private af h;
    private a i;
    private String k;
    private Map<String, List<String>> n;
    private final int j = 4096;
    private int l = 0;
    private boolean m = false;
    private String o = "";
    String a = "";
    String b = "";

    interface a {
        void a(p pVar, af afVar, Map<String, List<String>> map);
    }

    p(af afVar, a aVar) {
        this.h = afVar;
        this.i = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z = false;
        this.c = false;
        try {
            if (b()) {
                this.c = c();
                if (this.h.d().equals("WebServices.post") && this.e != 200) {
                    this.c = false;
                }
            }
        } catch (IOException e) {
            new aa.a().a("Download of ").a(this.a).a(" failed: ").a(e.toString()).a(aa.f);
            int i = this.e;
            if (i == 0) {
                i = 504;
            }
            this.e = i;
        } catch (IllegalStateException e2) {
            new aa.a().a("okhttp error: ").a(e2.toString()).a(aa.g);
            e2.printStackTrace();
        } catch (OutOfMemoryError unused) {
            new aa.a().a("Out of memory error - disabling AdColony. (").a(this.d).a(Constants.URL_PATH_DELIMITER).a(this.l).a("): " + this.a).a(aa.g);
            com.adcolony.sdk.a.a().a(true);
        } catch (MalformedURLException e3) {
            new aa.a().a("MalformedURLException: ").a(e3.toString()).a(aa.h);
            this.c = true;
        } catch (Exception e4) {
            new aa.a().a("Exception: ").a(e4.toString()).a(aa.g);
            e4.printStackTrace();
        }
        z = true;
        if (this.c) {
            new aa.a().a("Downloaded ").a(this.a).a(aa.d);
        }
        if (z) {
            this.i.a(this, this.h, this.n);
        }
    }

    private boolean b() throws IOException {
        JSONObject jSONObjectC = this.h.c();
        String strB = y.b(jSONObjectC, FirebaseAnalytics.Param.CONTENT_TYPE);
        String strB2 = y.b(jSONObjectC, "content");
        boolean zD = y.d(jSONObjectC, "no_redirect");
        this.a = y.b(jSONObjectC, "url");
        this.o = y.b(jSONObjectC, "filepath");
        this.k = y.b(jSONObjectC, "encoding");
        this.l = y.a(jSONObjectC, "max_size", 0);
        this.m = this.l != 0;
        this.d = 0;
        this.g = null;
        this.f = null;
        this.n = null;
        if (this.a.startsWith("file://")) {
            if (this.a.startsWith("file:///android_asset/")) {
                Activity activityC = com.adcolony.sdk.a.c();
                if (activityC != null) {
                    this.g = activityC.getAssets().open(this.a.substring(22));
                }
            } else {
                this.g = new FileInputStream(this.a.substring(7));
            }
        } else {
            this.f = (HttpURLConnection) new URL(this.a).openConnection();
            this.f.setInstanceFollowRedirects(!zD);
            this.f.setRequestProperty(HttpRequest.HEADER_ACCEPT_CHARSET, "UTF-8");
            if (!strB.equals("")) {
                this.f.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, strB);
            }
            if (this.h.d().equals("WebServices.post")) {
                this.f.setDoOutput(true);
                this.f.setFixedLengthStreamingMode(strB2.getBytes("UTF-8").length);
                new PrintStream(this.f.getOutputStream()).print(strB2);
            }
        }
        return (this.f == null && this.g == null) ? false : true;
    }

    private boolean c() throws Exception {
        OutputStream byteArrayOutputStream;
        String strD = this.h.d();
        if (this.g != null) {
            if (this.o.length() == 0) {
                byteArrayOutputStream = new ByteArrayOutputStream(4096);
            } else {
                byteArrayOutputStream = new FileOutputStream(new File(this.o).getAbsolutePath());
            }
        } else if (strD.equals("WebServices.download")) {
            this.g = this.f.getInputStream();
            byteArrayOutputStream = new FileOutputStream(this.o);
        } else if (strD.equals("WebServices.get")) {
            this.g = this.f.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream(4096);
        } else if (strD.equals("WebServices.post")) {
            this.f.connect();
            this.g = this.f.getResponseCode() == 200 ? this.f.getInputStream() : this.f.getErrorStream();
            byteArrayOutputStream = new ByteArrayOutputStream(4096);
        } else {
            byteArrayOutputStream = null;
        }
        HttpURLConnection httpURLConnection = this.f;
        if (httpURLConnection != null) {
            this.e = httpURLConnection.getResponseCode();
            this.n = this.f.getHeaderFields();
        }
        return a(this.g, byteArrayOutputStream);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0096  */
    /* JADX WARN: Code duplicated, block: B:42:0x009b  */
    /* JADX WARN: Code duplicated, block: B:44:0x00a0  */
    private boolean a(InputStream inputStream, OutputStream outputStream) throws Exception {
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i = bufferedInputStream2.read(bArr, 0, 4096);
                        if (i != -1) {
                            this.d += i;
                            if (this.m && this.d > this.l) {
                                throw new Exception("Data exceeds expected maximum (" + this.d + Constants.URL_PATH_DELIMITER + this.l + "): " + this.f.getURL().toString());
                            }
                            outputStream.write(bArr, 0, i);
                        } else {
                            String str = "UTF-8";
                            if (this.k != null && !this.k.isEmpty()) {
                                str = this.k;
                            }
                            if (outputStream instanceof ByteArrayOutputStream) {
                                this.b = ((ByteArrayOutputStream) outputStream).toString(str);
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            bufferedInputStream2.close();
                            return true;
                        }
                    }
                } catch (Exception e) {
                    throw e;
                }
            } catch (Throwable th2) {
                th = th2;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (0 != 0) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            throw e2;
        } catch (Throwable th3) {
            th = th3;
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (0 != 0) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    af a() {
        return this.h;
    }
}
