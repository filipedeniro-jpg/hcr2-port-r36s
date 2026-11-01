package com.adcolony.sdk;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes.dex */
class z {
    URL a;

    public z(URL url) {
        this.a = url;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0080 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:45:0x0087  */
    /* JADX WARN: Code duplicated, block: B:47:0x008c  */
    /* JADX WARN: Code duplicated, block: B:49:0x0092  */
    public int a(String str) throws Throwable {
        HttpURLConnection httpURLConnection;
        GZIPOutputStream gZIPOutputStream;
        DataOutputStream dataOutputStream;
        boolean z = true;
        DataOutputStream dataOutputStream2 = null;
        dataOutputStream2 = null;
        dataOutputStream2 = null;
        HttpURLConnection httpURLConnection2 = null;
        boolean z2 = false;
        try {
            httpURLConnection = (HttpURLConnection) this.a.openConnection();
            try {
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_ENCODING, HttpRequest.ENCODING_GZIP);
                httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/json");
                httpURLConnection.setDoInput(true);
                gZIPOutputStream = new GZIPOutputStream(httpURLConnection.getOutputStream());
                try {
                    dataOutputStream = new DataOutputStream(gZIPOutputStream);
                    try {
                        dataOutputStream.write(str.getBytes(Charset.forName("UTF-8")));
                        dataOutputStream.close();
                        try {
                            int responseCode = httpURLConnection.getResponseCode();
                            gZIPOutputStream.close();
                            if (httpURLConnection != null) {
                                if (httpURLConnection.getInputStream() != null) {
                                    httpURLConnection.getInputStream().close();
                                }
                                httpURLConnection.disconnect();
                            }
                            return responseCode;
                        } catch (IOException e) {
                            e = e;
                            httpURLConnection2 = httpURLConnection;
                            try {
                                throw e;
                            } catch (Throwable th) {
                                th = th;
                                z2 = z;
                                httpURLConnection = httpURLConnection2;
                                dataOutputStream2 = dataOutputStream;
                                if (dataOutputStream2 != null && !z2) {
                                    dataOutputStream2.close();
                                }
                                if (gZIPOutputStream != null) {
                                    gZIPOutputStream.close();
                                }
                                if (httpURLConnection != null) {
                                    if (httpURLConnection.getInputStream() != null) {
                                        httpURLConnection.getInputStream().close();
                                    }
                                    httpURLConnection.disconnect();
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            dataOutputStream2 = dataOutputStream;
                            z2 = true;
                            if (dataOutputStream2 != null) {
                                dataOutputStream2.close();
                            }
                            if (gZIPOutputStream != null) {
                                gZIPOutputStream.close();
                            }
                            if (httpURLConnection != null) {
                                if (httpURLConnection.getInputStream() != null) {
                                    httpURLConnection.getInputStream().close();
                                }
                                httpURLConnection.disconnect();
                            }
                            throw th;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        httpURLConnection2 = httpURLConnection;
                        z = false;
                        throw e;
                    } catch (Throwable th3) {
                        th = th3;
                        dataOutputStream2 = dataOutputStream;
                        if (dataOutputStream2 != null) {
                            dataOutputStream2.close();
                        }
                        if (gZIPOutputStream != null) {
                            gZIPOutputStream.close();
                        }
                        if (httpURLConnection != null) {
                            if (httpURLConnection.getInputStream() != null) {
                                httpURLConnection.getInputStream().close();
                            }
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                    dataOutputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (IOException e4) {
                e = e4;
                gZIPOutputStream = null;
                dataOutputStream = null;
            } catch (Throwable th5) {
                th = th5;
                gZIPOutputStream = null;
            }
        } catch (IOException e5) {
            e = e5;
            gZIPOutputStream = null;
            dataOutputStream = null;
        } catch (Throwable th6) {
            th = th6;
            httpURLConnection = null;
            gZIPOutputStream = null;
        }
    }
}
