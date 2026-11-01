package com.chartboost.sdk.impl;

import android.os.Handler;
import com.chartboost.sdk.Model.CBError;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class an<T> implements Comparable<an>, Runnable {
    public final ad<T> a;
    private final Executor b;
    private final ao c;
    private final ai d;
    private final com.chartboost.sdk.Libraries.i e;
    private final Handler f;
    private af<T> g;
    private ag h;

    private static boolean a(int i) {
        return ((100 <= i && i < 200) || i == 204 || i == 304) ? false : true;
    }

    an(Executor executor, ao aoVar, ai aiVar, com.chartboost.sdk.Libraries.i iVar, Handler handler, ad<T> adVar) {
        this.b = executor;
        this.c = aoVar;
        this.d = aiVar;
        this.e = iVar;
        this.f = handler;
        this.a = adVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        af<T> afVar = this.g;
        if (afVar != null) {
            try {
                if (afVar.b == null) {
                    this.a.a(this.g.a, this.h);
                } else {
                    this.a.a(this.g.b, this.h);
                }
                return;
            } catch (Exception e) {
                com.chartboost.sdk.Tracking.a.a(getClass(), "deliver result", e);
                return;
            }
        }
        if (this.a.e.compareAndSet(0, 1)) {
            long jB = this.e.b();
            try {
                if (this.d.b()) {
                    this.h = a(this.a);
                    int i = this.h.a;
                    if (i >= 200 && i < 300) {
                        this.g = this.a.a(this.h);
                    } else {
                        this.g = af.a(new CBError(CBError.a.NETWORK_FAILURE, "Failure due to HTTP status code " + i));
                    }
                } else {
                    this.g = af.a(new CBError(CBError.a.INTERNET_UNAVAILABLE, "Internet Unavailable"));
                }
            } catch (Throwable th) {
                this.g = af.a(new CBError(CBError.a.NETWORK_FAILURE, th.toString()));
            } finally {
                this.a.g = this.e.b() - jB;
                int i2 = this.a.j;
                if (i2 == 0) {
                    this.f.post(this);
                } else if (i2 == 1) {
                    this.b.execute(this);
                }
            }
        }
    }

    private ag a(ad<T> adVar) throws IOException {
        int i = 10000;
        int i2 = 0;
        while (true) {
            try {
                return a(adVar, i);
            } catch (SocketTimeoutException e) {
                if (i2 >= 1) {
                    throw e;
                }
                i *= 2;
                i2++;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v9 */
    private ag a(ad<T> adVar, int i) throws IOException {
        byte[] bArrB;
        InputStream errorStream;
        InputStream inputStream;
        ae aeVarA = adVar.a();
        Map<String, String> map = aeVarA.a;
        HttpURLConnection httpURLConnectionA = this.c.a(adVar);
        httpURLConnectionA.setConnectTimeout(i);
        httpURLConnectionA.setReadTimeout(i);
        httpURLConnectionA.setUseCaches(false);
        httpURLConnectionA.setDoInput(true);
        if (map != null) {
            try {
                for (String str : map.keySet()) {
                    httpURLConnectionA.addRequestProperty(str, map.get(str));
                }
            } catch (Throwable th) {
                httpURLConnectionA.disconnect();
                throw th;
            }
        }
        httpURLConnectionA.setRequestMethod(adVar.b);
        ?? r4 = 0;
        DataOutputStream dataOutputStream = null;
        r4 = 0;
        if (adVar.b.equals(HttpRequest.METHOD_POST) && aeVarA.b != null) {
            httpURLConnectionA.setDoOutput(true);
            httpURLConnectionA.setFixedLengthStreamingMode(aeVarA.b.length);
            if (aeVarA.c != null) {
                httpURLConnectionA.addRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, aeVarA.c);
            }
            try {
                DataOutputStream dataOutputStream2 = new DataOutputStream(httpURLConnectionA.getOutputStream());
                try {
                    dataOutputStream2.write(aeVarA.b);
                    try {
                        dataOutputStream2.close();
                    } catch (IOException unused) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        long jB = this.e.b();
        try {
            int responseCode = httpURLConnectionA.getResponseCode();
            long jB2 = this.e.b();
            adVar.h = jB2 - jB;
            if (responseCode == -1) {
                throw new IOException("Could not retrieve response code from HttpUrlConnection.");
            }
            try {
                if (a(responseCode)) {
                    if (adVar.f != null) {
                        File file = new File(adVar.f.getParentFile(), adVar.f.getName() + ".tmp");
                        bArrB = new byte[0];
                        try {
                            inputStream = httpURLConnectionA.getInputStream();
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                try {
                                    bi.a(inputStream, fileOutputStream);
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException unused3) {
                                        }
                                    }
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException unused4) {
                                    }
                                    if (!file.renameTo(adVar.f)) {
                                        if (!file.delete()) {
                                            throw new IOException("Unable to delete " + file.getAbsolutePath() + " after failing to rename to " + adVar.f.getAbsolutePath());
                                        }
                                        throw new IOException("Unable to move " + file.getAbsolutePath() + " to " + adVar.f.getAbsolutePath());
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    r4 = fileOutputStream;
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException unused5) {
                                        }
                                    }
                                    if (r4 != 0) {
                                        try {
                                            r4.close();
                                            throw th;
                                        } catch (IOException unused6) {
                                            throw th;
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            inputStream = null;
                        }
                    } else {
                        try {
                            try {
                                errorStream = httpURLConnectionA.getInputStream();
                            } catch (IOException unused7) {
                                errorStream = httpURLConnectionA.getErrorStream();
                            }
                            InputStream inputStream2 = errorStream;
                            if (inputStream2 != null) {
                                bArrB = bi.b(new BufferedInputStream(inputStream2));
                            } else {
                                bArrB = new byte[0];
                            }
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException unused8) {
                                }
                            }
                        } catch (Throwable th7) {
                            if (0 != 0) {
                                try {
                                    r4.close();
                                } catch (IOException unused9) {
                                }
                            }
                            throw th7;
                        }
                    }
                } else {
                    bArrB = new byte[0];
                }
                adVar.i = this.e.b() - jB2;
                ag agVar = new ag(responseCode, bArrB);
                httpURLConnectionA.disconnect();
                return agVar;
            } catch (Throwable th8) {
                adVar.i = this.e.b() - jB2;
                throw th8;
            }
        } catch (Throwable th9) {
            adVar.h = this.e.b() - jB;
            throw th9;
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(an anVar) {
        return this.a.d - anVar.a.d;
    }
}
