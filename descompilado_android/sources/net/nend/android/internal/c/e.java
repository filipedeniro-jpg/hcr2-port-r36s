package net.nend.android.internal.c;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;
import org.json.JSONObject;

/* JADX INFO: compiled from: NendURLConnectionHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {
    /* JADX WARN: Code duplicated, block: B:55:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:71:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:73:0x00d4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x00e0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x00bb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:? A[SYNTHETIC] */
    public static f a(String str, String str2, JSONObject jSONObject, boolean z) throws Throwable {
        InputStream inputStream;
        HttpURLConnection httpURLConnection;
        OutputStream outputStream;
        InputStream inputStreamA;
        OutputStream outputStream2 = null;
        int responseCode = 500;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setRequestMethod(str2);
                httpURLConnection2.setReadTimeout(10000);
                httpURLConnection2.setConnectTimeout(10000);
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setUseCaches(false);
                if (jSONObject != null) {
                    httpURLConnection2.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/json");
                    httpURLConnection2.setRequestProperty("Accept", "application/json");
                    httpURLConnection2.setDoOutput(true);
                    outputStream = httpURLConnection2.getOutputStream();
                    try {
                        outputStream.write(jSONObject.toString().getBytes("UTF-8"));
                        outputStream.flush();
                    } catch (IOException e) {
                        e = e;
                        httpURLConnection = httpURLConnection2;
                        inputStream = null;
                        try {
                            j.b(k.ERR_HTTP_REQUEST, e);
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e2) {
                                    j.b(k.ERR_HTTP_REQUEST, e2);
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e3) {
                                    j.b(k.ERR_HTTP_REQUEST, e3);
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return new f(responseCode, null);
                        } catch (Throwable th) {
                            th = th;
                            outputStream2 = outputStream;
                            if (outputStream2 != null) {
                                try {
                                    outputStream2.close();
                                } catch (IOException e4) {
                                    j.b(k.ERR_HTTP_REQUEST, e4);
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e5) {
                                    j.b(k.ERR_HTTP_REQUEST, e5);
                                }
                            }
                            if (httpURLConnection == null) {
                                throw th;
                            }
                            httpURLConnection.disconnect();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        httpURLConnection = httpURLConnection2;
                        inputStream = null;
                        outputStream2 = outputStream;
                        if (outputStream2 != null) {
                            outputStream2.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (httpURLConnection == null) {
                            throw th;
                        }
                        httpURLConnection.disconnect();
                        throw th;
                    }
                } else {
                    outputStream = null;
                }
                httpURLConnection2.connect();
                responseCode = httpURLConnection2.getResponseCode();
                if (z) {
                    inputStreamA = httpURLConnection2.getInputStream();
                } else {
                    inputStreamA = a(httpURLConnection2);
                }
                try {
                    f fVar = new f(responseCode, a.a(inputStreamA));
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e6) {
                            j.b(k.ERR_HTTP_REQUEST, e6);
                        }
                    }
                    if (inputStreamA != null) {
                        try {
                            inputStreamA.close();
                        } catch (IOException e7) {
                            j.b(k.ERR_HTTP_REQUEST, e7);
                        }
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return fVar;
                } catch (IOException e8) {
                    httpURLConnection = httpURLConnection2;
                    inputStream = inputStreamA;
                    e = e8;
                    j.b(k.ERR_HTTP_REQUEST, e);
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return new f(responseCode, null);
                } catch (Throwable th3) {
                    outputStream2 = outputStream;
                    httpURLConnection = httpURLConnection2;
                    inputStream = inputStreamA;
                    th = th3;
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (httpURLConnection == null) {
                        throw th;
                    }
                    httpURLConnection.disconnect();
                    throw th;
                }
            } catch (IOException e9) {
                e = e9;
                httpURLConnection = httpURLConnection2;
                inputStream = null;
                outputStream = null;
            } catch (Throwable th4) {
                th = th4;
                httpURLConnection = httpURLConnection2;
                inputStream = null;
            }
        } catch (IOException e10) {
            e = e10;
            inputStream = null;
            outputStream = null;
            httpURLConnection = null;
        } catch (Throwable th5) {
            th = th5;
            inputStream = null;
            httpURLConnection = null;
        }
    }

    private static InputStream a(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException unused) {
            return httpURLConnection.getErrorStream();
        }
    }

    /* JADX INFO: compiled from: NendURLConnectionHelper.java */
    private static class a {
        static byte[] a(InputStream inputStream) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                try {
                    int i = inputStream.read(bArr);
                    if (i >= 0) {
                        byteArrayOutputStream.write(bArr, 0, i);
                    } else {
                        return byteArrayOutputStream.toByteArray();
                    }
                } catch (IOException unused) {
                    return null;
                }
            }
        }
    }
}
