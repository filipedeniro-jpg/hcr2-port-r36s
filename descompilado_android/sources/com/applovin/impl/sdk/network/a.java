package com.applovin.impl.sdk.network;

import com.applovin.impl.sdk.c.g;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.n;
import com.applovin.impl.sdk.utils.j;
import com.applovin.impl.sdk.utils.k;
import com.applovin.impl.sdk.utils.o;
import com.applovin.impl.sdk.utils.p;
import com.applovin.sdk.AppLovinErrorCodes;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.tonyodev.fetch.FetchConst;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private final h a;
    private final n b;

    /* JADX INFO: renamed from: com.applovin.impl.sdk.network.a$a, reason: collision with other inner class name */
    public static class C0027a {
        private long a;
        private long b;

        /* JADX INFO: Access modifiers changed from: private */
        public void a(long j) {
            this.a = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(long j) {
            this.b = j;
        }

        public long a() {
            return this.a;
        }

        public long b() {
            return this.b;
        }
    }

    public interface b<T> {
        void a(int i);

        void a(T t, int i);
    }

    public a(h hVar) {
        this.a = hVar;
        this.b = hVar.w();
    }

    private int a(Throwable th) {
        if (th instanceof UnknownHostException) {
            return AppLovinErrorCodes.NO_NETWORK;
        }
        if (th instanceof SocketTimeoutException) {
            return -102;
        }
        if (th instanceof IOException) {
            String message = th.getMessage();
            return (message == null || !message.toLowerCase(Locale.ENGLISH).contains("authentication challenge")) ? -100 : 401;
        }
        if (th instanceof JSONException) {
            return FetchConst.ERROR_CONNECTION_TIMEOUT;
        }
        return -1;
    }

    private HttpURLConnection a(String str, String str2, int i) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(i < 0 ? ((Integer) this.a.a(com.applovin.impl.sdk.b.b.dF)).intValue() : i);
        if (i < 0) {
            i = ((Integer) this.a.a(com.applovin.impl.sdk.b.b.dG)).intValue();
        }
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private void a(int i, String str) {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.af)).booleanValue()) {
            try {
                c.a(i, str, this.a.z());
            } catch (Throwable th) {
                this.a.w().b("ConnectionManager", "Failed to track response code for URL: " + str, th);
            }
        }
    }

    private static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    private void a(String str) {
        com.applovin.impl.sdk.c.h hVarG;
        g gVar;
        if (k.a(str, com.applovin.impl.sdk.utils.f.g(this.a)) || k.a(str, com.applovin.impl.sdk.utils.f.h(this.a))) {
            hVarG = this.a.G();
            gVar = g.h;
        } else if (k.a(str, com.applovin.impl.mediation.d.b.a(this.a)) || k.a(str, com.applovin.impl.mediation.d.b.b(this.a))) {
            hVarG = this.a.G();
            gVar = g.r;
        } else {
            hVarG = this.a.G();
            gVar = g.i;
        }
        hVarG.a(gVar);
    }

    private <T> void a(String str, int i, String str2, T t, boolean z, b<T> bVar) throws JSONException {
        String strA;
        n nVar;
        StringBuilder sb;
        String str3;
        this.b.a("ConnectionManager", i + " received from \"" + str2 + "\": " + str);
        if (i < 200 || i >= 300) {
            strA = str;
            this.b.d("ConnectionManager", i + " error received from \"" + str2 + "\"");
            bVar.a(i);
            return;
        }
        if (z) {
            strA = j.a(str, this.a.u());
        }
        boolean z2 = strA != null && strA.length() > 2;
        if (i != 204 && z2) {
            try {
                Object objA = strA;
                if (t instanceof String) {
                    t = (T) objA;
                } else if (t instanceof o) {
                    objA = p.a(strA, this.a);
                    t = (T) objA;
                } else if (t instanceof JSONObject) {
                    t = (T) new JSONObject(strA);
                } else {
                    this.b.d("ConnectionManager", "Unable to handle '" + t.getClass().getName() + "'");
                }
            } catch (JSONException e) {
                e = e;
                a(str2);
                nVar = this.b;
                sb = new StringBuilder();
                str3 = "Invalid JSON returned from \"";
                sb.append(str3);
                sb.append(str2);
                sb.append("\"");
                nVar.b("ConnectionManager", sb.toString(), e);
            } catch (SAXException e2) {
                e = e2;
                a(str2);
                nVar = this.b;
                sb = new StringBuilder();
                str3 = "Invalid XML returned from \"";
                sb.append(str3);
                sb.append(str2);
                sb.append("\"");
                nVar.b("ConnectionManager", sb.toString(), e);
            }
        }
        bVar.a(t, i);
    }

    private void a(String str, String str2, int i, long j) {
        this.b.b("ConnectionManager", "Successful " + str + " returned " + i + " in " + ((System.currentTimeMillis() - j) / 1000.0f) + " s over " + com.applovin.impl.sdk.utils.f.f(this.a) + " to \"" + str2 + "\"");
    }

    private void a(String str, String str2, int i, long j, Throwable th) {
        this.b.b("ConnectionManager", "Failed " + str + " returned " + i + " in " + ((System.currentTimeMillis() - j) / 1000.0f) + " s over " + com.applovin.impl.sdk.utils.f.f(this.a) + " to \"" + str2 + "\"", th);
    }

    private static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:83:0x0204 A[Catch: Throwable -> 0x0210, all -> 0x023c, TryCatch #10 {Throwable -> 0x0210, blocks: (B:81:0x01fb, B:83:0x0204, B:84:0x0208), top: B:113:0x01fb }] */
    /* JADX WARN: Code duplicated, block: B:84:0x0208 A[Catch: Throwable -> 0x0210, all -> 0x023c, TRY_LEAVE, TryCatch #10 {Throwable -> 0x0210, blocks: (B:81:0x01fb, B:83:0x0204, B:84:0x0208), top: B:113:0x01fb }] */
    public <T> void a(com.applovin.impl.sdk.network.b<T> bVar, C0027a c0027a, b<T> bVar2) throws Throwable {
        InputStream inputStream;
        HttpURLConnection httpURLConnectionA;
        Throwable th;
        InputStream inputStream2;
        if (bVar == null) {
            throw new IllegalArgumentException("No request specified");
        }
        String strA = bVar.a();
        String strC = bVar.c();
        if (strA == null) {
            throw new IllegalArgumentException("No endpoint specified");
        }
        if (strC == null) {
            throw new IllegalArgumentException("No method specified");
        }
        if (bVar2 == null) {
            throw new IllegalArgumentException("No callback specified");
        }
        if (!strA.toLowerCase().startsWith("http")) {
            this.b.e("ConnectionManager", "Requested postback submission to non HTTP endpoint " + strA + "; skipping...");
            bVar2.a(-900);
            return;
        }
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.dH)).booleanValue() && !strA.contains("https://")) {
            this.a.w().c("ConnectionManager", "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting...");
            strA = strA.replace("http://", "https://");
        }
        boolean zL = bVar.l();
        long jA = com.applovin.impl.sdk.utils.n.a(this.a);
        if ((bVar.b() != null && !bVar.b().isEmpty()) || bVar.h() >= 0) {
            Map<String, String> mapB = bVar.b();
            if (mapB != null && bVar.h() >= 0) {
                mapB.put("current_retry_attempt", String.valueOf(bVar.h()));
            }
            strA = zL ? k.a(strA, TtmlNode.TAG_P, j.a(com.applovin.impl.sdk.utils.n.a(mapB), this.a.u(), jA)) : k.a(strA, mapB);
        }
        String str = strA;
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            this.b.b("ConnectionManager", "Sending " + strC + " request to \"" + str + "\"...");
            httpURLConnectionA = a(str, strC, bVar.j());
            try {
                if (bVar.d() != null) {
                    String strA2 = zL ? j.a(bVar.d().toString(), this.a.u(), jA) : bVar.d().toString();
                    this.b.a("ConnectionManager", "Request to \"" + str + "\" is " + strA2);
                    httpURLConnectionA.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");
                    httpURLConnectionA.setDoOutput(true);
                    httpURLConnectionA.setFixedLengthStreamingMode(strA2.getBytes(Charset.forName("UTF-8")).length);
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(httpURLConnectionA.getOutputStream(), UrlUtils.UTF8));
                    printWriter.print(strA2);
                    printWriter.close();
                }
                try {
                    int responseCode = httpURLConnectionA.getResponseCode();
                    if (responseCode > 0) {
                        a(strC, str, responseCode, jCurrentTimeMillis);
                        InputStream inputStream3 = httpURLConnectionA.getInputStream();
                        try {
                            a(responseCode, str);
                            try {
                                if (bVar.g()) {
                                    String strA3 = com.applovin.impl.sdk.utils.f.a(inputStream3, this.a);
                                    if (c0027a != null) {
                                        if (strA3 != null) {
                                            try {
                                                c0027a.b(strA3.length());
                                            } catch (MalformedURLException unused) {
                                                inputStream = inputStream3;
                                                try {
                                                    try {
                                                        a(-901, str);
                                                        if (bVar.g()) {
                                                            bVar2.a(-901);
                                                        } else {
                                                            bVar2.a(bVar.f(), -901);
                                                        }
                                                    } catch (Throwable th2) {
                                                        th = th2;
                                                        int iA = a(th);
                                                        a(iA, str);
                                                        a(strC, str, iA, jCurrentTimeMillis, th);
                                                        bVar2.a(iA);
                                                    }
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                    a(inputStream);
                                                    a(httpURLConnectionA);
                                                    throw th;
                                                }
                                            } catch (Throwable th4) {
                                                th = th4;
                                                inputStream = inputStream3;
                                                int iA2 = a(th);
                                                a(iA2, str);
                                                a(strC, str, iA2, jCurrentTimeMillis, th);
                                                bVar2.a(iA2);
                                            }
                                        }
                                        c0027a.a(System.currentTimeMillis() - jCurrentTimeMillis);
                                    }
                                    inputStream2 = inputStream3;
                                    a(strA3, httpURLConnectionA.getResponseCode(), str, bVar.f(), zL, bVar2);
                                } else {
                                    inputStream2 = inputStream3;
                                    if (c0027a != null) {
                                        c0027a.a(System.currentTimeMillis() - jCurrentTimeMillis);
                                    }
                                    bVar2.a(bVar.f(), responseCode);
                                }
                            } catch (MalformedURLException unused2) {
                                inputStream = inputStream3;
                                a(-901, str);
                                if (bVar.g()) {
                                    bVar2.a(-901);
                                } else {
                                    bVar2.a(bVar.f(), -901);
                                }
                                a(inputStream);
                                a(httpURLConnectionA);
                            } catch (Throwable th5) {
                                th = th5;
                                inputStream = inputStream3;
                                a(inputStream);
                                a(httpURLConnectionA);
                                throw th;
                            }
                        } catch (MalformedURLException unused3) {
                        } catch (Throwable th6) {
                            th = th6;
                        }
                    } else {
                        a(strC, str, responseCode, jCurrentTimeMillis, null);
                        bVar2.a(responseCode);
                        inputStream2 = null;
                    }
                    inputStream = inputStream2;
                } catch (MalformedURLException unused4) {
                    inputStream = null;
                }
            } catch (Throwable th7) {
                th = th7;
                inputStream = null;
            }
        } catch (Throwable th8) {
            th = th8;
            inputStream = null;
            httpURLConnectionA = null;
        }
        a(inputStream);
        a(httpURLConnectionA);
    }
}
