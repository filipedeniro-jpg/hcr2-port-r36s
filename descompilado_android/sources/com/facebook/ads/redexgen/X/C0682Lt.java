package com.facebook.ads.redexgen.X;

import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0682Lt implements InterfaceC0663La {
    public final String B;
    private HttpURLConnection C;
    private InputStream D;
    private volatile int E;
    private volatile String F;

    public C0682Lt(C0682Lt source) {
        this.E = Integer.MIN_VALUE;
        this.B = source.B;
        this.F = source.F;
        this.E = source.E;
    }

    public C0682Lt(String url) {
        this(url, C0687Ly.G(url));
    }

    private C0682Lt(String url, String mime) {
        this.E = Integer.MIN_VALUE;
        this.B = (String) C0684Lv.C(url);
        this.F = mime;
    }

    public final synchronized String A() throws C0666Ld {
        if (TextUtils.isEmpty(this.F)) {
            B();
        }
        return this.F;
    }

    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final String m262B() {
        return this.B;
    }

    private void B() throws C0669Lg {
        Log.d("ProxyCache", "Read content info from " + this.B);
        HttpURLConnection httpURLConnectionC = null;
        InputStream inputStream = null;
        try {
            httpURLConnectionC = C(0, 10000);
            this.E = httpURLConnectionC.getContentLength();
            this.F = httpURLConnectionC.getContentType();
            inputStream = httpURLConnectionC.getInputStream();
            Log.i("ProxyCache", "Content info for `" + this.B + "`: mime: " + this.F + ", content-length: " + this.E);
        } catch (IOException e) {
            Log.e("ProxyCache", "Error fetching info from " + this.B, e);
        } finally {
            C0687Ly.C(inputStream);
            if (httpURLConnectionC != null) {
                httpURLConnectionC.disconnect();
            }
        }
    }

    private HttpURLConnection C(int offset, int timeout) throws C0669Lg, IOException {
        HttpURLConnection httpURLConnection;
        boolean z;
        int i = 0;
        String url = this.B;
        do {
            Log.d("ProxyCache", "Open connection " + (offset > 0 ? " with offset " + offset : "") + " to " + url);
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            if (offset > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + offset + "-");
            }
            if (timeout > 0) {
                httpURLConnection.setConnectTimeout(timeout);
                httpURLConnection.setReadTimeout(timeout);
            }
            int responseCode = httpURLConnection.getResponseCode();
            z = responseCode == 301 || responseCode == 302 || responseCode == 303;
            if (z) {
                url = httpURLConnection.getHeaderField("Location");
                i++;
                httpURLConnection.disconnect();
            }
            if (i > 5) {
                throw new C0669Lg("Too many redirects: " + i);
            }
        } while (z);
        return httpURLConnection;
    }

    private int D(HttpURLConnection connection, int offset, int responseCode) throws IOException {
        int contentLength = connection.getContentLength();
        if (responseCode == 200) {
            return contentLength;
        }
        return responseCode == 206 ? contentLength + offset : this.E;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0663La
    public final void SF(int offset) throws C0669Lg {
        try {
            this.C = C(offset, -1);
            this.F = this.C.getContentType();
            this.D = new BufferedInputStream(this.C.getInputStream(), 8192);
            this.E = D(this.C, offset, this.C.getResponseCode());
        } catch (IOException e) {
            throw new C0669Lg("Error opening connection for " + this.B + " with offset " + offset, e);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0663La
    public final void close() throws C0669Lg {
        if (this.C != null) {
            try {
                this.C.disconnect();
            } catch (NullPointerException e) {
                throw new C0669Lg("Error disconnecting HttpUrlConnection", e);
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0663La
    public final synchronized int length() throws C0669Lg {
        if (this.E == Integer.MIN_VALUE) {
            B();
        }
        return this.E;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0663La
    public final int read(byte[] buffer) throws C0669Lg {
        if (this.D == null) {
            throw new C0669Lg("Error reading data from " + this.B + ": connection is absent!");
        }
        try {
            return this.D.read(buffer, 0, buffer.length);
        } catch (InterruptedIOException e) {
            throw new C0683Lu("Reading source " + this.B + " is interrupted", e);
        } catch (IOException e2) {
            throw new C0669Lg("Error reading data from " + this.B, e2);
        }
    }

    public final String toString() {
        return "HttpUrlSource{url='" + this.B + "}";
    }
}
