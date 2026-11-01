package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.appsflyer.share.Constants;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.google.android.gms.games.GamesStatusCodes;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: assets/audience_network.dex */
@WorkerThread
public final class LI {
    private static InterfaceC0602Io N;
    private boolean G;
    private Set<String> I;
    private Set<String> J;
    private static int[] M = new int[20];
    private static final String L = LI.class.getSimpleName();
    public final LK C = new LL();
    public LM F = new LN();
    public int B = 2000;
    public int D = 8000;
    private int H = 3;
    private Map<String, String> K = new TreeMap();
    public final LF E = new LH(this);

    static {
        E();
        if (Build.VERSION.SDK_INT > 8) {
            G();
        }
    }

    @AnyThread
    public LI() {
    }

    @AnyThread
    public final LI A(String name, String value) {
        this.K.put(name, value);
        return this;
    }

    @Nullable
    public final LU B(String path, LW params) {
        return H(new LQ(path, params));
    }

    public static synchronized void B(InterfaceC0602Io commonRequestHeadersFactory) {
        N = commonRequestHeadersFactory;
    }

    public final LW C() {
        return new LW();
    }

    private void C(HttpURLConnection urlConnection) {
        for (String str : this.K.keySet()) {
            String value = this.K.get(str);
            urlConnection.setRequestProperty(str, value);
        }
        synchronized (LI.class) {
            if (N != null) {
                Map<String, String> mapTB = N.tB();
                for (String str2 : mapTB.keySet()) {
                    String value2 = mapTB.get(str2);
                    urlConnection.setRequestProperty(str2, value2);
                }
            }
        }
    }

    private final int D(HttpURLConnection urlConnection, byte[] content) throws Exception {
        OutputStream outputStreamVF = null;
        try {
            outputStreamVF = this.E.VF(urlConnection);
            if (outputStreamVF != null) {
                this.E.EG(outputStreamVF, content);
            }
            return urlConnection.getResponseCode();
        } finally {
            if (outputStreamVF != null) {
                try {
                    outputStreamVF.close();
                } catch (Exception e) {
                }
            }
        }
    }

    @Nullable
    public final LU D(String path, LW params) {
        return H(new LS(path, params));
    }

    @Nullable
    public LU E(String path, String contentType, byte[] data) {
        return H(new LS(path, null, contentType, data));
    }

    private static void E() {
        if (Build.VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private final LU F(String path, LR httpMethod, String contentType, byte[] content) throws LT {
        LU lu;
        HttpURLConnection uc = null;
        LU luL = null;
        try {
            try {
                this.G = false;
                HttpURLConnection uc2 = P(path);
                Q(uc2, httpMethod, contentType);
                C(uc2);
                if (this.F.VC()) {
                    this.F.sC(uc2, content);
                }
                uc2.connect();
                this.G = true;
                boolean z = (this.I == null || this.I.isEmpty()) ? false : true;
                boolean pinCerts = (this.J == null || this.J.isEmpty()) ? false : true;
                if ((uc2 instanceof HttpsURLConnection) && (z || pinCerts)) {
                    try {
                        LV.B((HttpsURLConnection) uc2, this.I, this.J);
                    } catch (Exception e) {
                        Log.e(L, "Unable to validate SSL certificates.", e);
                    }
                }
                if (uc2.getDoOutput() && content != null) {
                    D(uc2, content);
                }
                if (uc2.getDoInput()) {
                    lu = J(uc2);
                } else {
                    lu = new LU(uc2, null);
                }
                if (this.F.VC()) {
                    this.F.tC(lu);
                }
                if (uc2 != null) {
                    uc2.disconnect();
                }
                return lu;
            } catch (Exception e2) {
                try {
                    try {
                        luL = L((HttpURLConnection) null);
                        if (luL == null || luL.D() <= 0) {
                            throw new LT(e2, luL);
                        }
                        if (this.F.VC()) {
                            this.F.tC(luL);
                        }
                        if (0 != 0) {
                            uc.disconnect();
                        }
                        return luL;
                    } catch (Throwable th) {
                        if (luL == null || luL.D() <= 0) {
                            throw new LT(e2, luL);
                        }
                        if (this.F.VC()) {
                            this.F.tC(luL);
                        }
                        if (0 != 0) {
                            uc.disconnect();
                            return luL;
                        }
                        return luL;
                    }
                } catch (Exception e3) {
                    e2.printStackTrace();
                    if (luL == null || luL.D() <= 0) {
                        throw new LT(e2, luL);
                    }
                    if (this.F.VC()) {
                        this.F.tC(luL);
                    }
                    if (0 != 0) {
                        uc.disconnect();
                    }
                }
            }
        } catch (Throwable th2) {
            if (this.F.VC()) {
                this.F.tC(luL);
            }
            if (0 != 0) {
                uc.disconnect();
            }
            throw th2;
        }
    }

    public final void F(String path, LW params, AbstractC0650Kl callback) {
        I(new LS(path, params), callback);
    }

    private static void G() {
        if (CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }

    @AnyThread
    public final void G(int connectionTimeout) {
        this.B = connectionTimeout;
    }

    @Nullable
    private final LU H(LP httpRequest) {
        if (BuildConfigApi.isDebug()) {
            N(httpRequest);
        }
        LU luF = null;
        try {
            luF = F(httpRequest.D(), httpRequest.C(), httpRequest.B(), httpRequest.A());
            return luF;
        } catch (LT e) {
            this.E.zD(e);
            return luF;
        } catch (Exception e2) {
            this.E.zD(new LT(e2, luF));
            return luF;
        }
    }

    @AnyThread
    public final void H(int maxRetries) {
        if (maxRetries < 1 || maxRetries > 18) {
            throw new IllegalArgumentException("Maximum retries must be between 1 and 18");
        }
        this.H = maxRetries;
    }

    private final void I(LP httpRequest, AbstractC0650Kl callback) {
        this.C.uB(this, callback).nB(httpRequest);
        if (BuildConfigApi.isDebug()) {
            N(httpRequest);
        }
    }

    @AnyThread
    public final void I(Set<String> pinnedCertificates) {
        this.I = pinnedCertificates;
    }

    private final LU J(HttpURLConnection urlConnection) throws Exception {
        InputStream inputStreamUF = null;
        byte[] bArrHF = null;
        try {
            inputStreamUF = this.E.UF(urlConnection);
            if (inputStreamUF != null) {
                bArrHF = this.E.hF(inputStreamUF);
            }
            return new LU(urlConnection, bArrHF);
        } finally {
            if (inputStreamUF != null) {
                try {
                    inputStreamUF.close();
                } catch (Exception e) {
                }
            }
        }
    }

    @AnyThread
    public final void J(Set<String> pinnedPublicKeys) {
        this.J = pinnedPublicKeys;
    }

    private final int K(int numTries) {
        return M[numTries + 2] * 1000;
    }

    @AnyThread
    /* JADX INFO: renamed from: K, reason: collision with other method in class */
    public final void m259K(int readTimeout) {
        this.D = readTimeout;
    }

    public final LU L(LP httpRequest) throws LT {
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < this.H; i++) {
            try {
                G(K(i));
                if (this.F.VC()) {
                    this.F.dC((i + 1) + "of" + this.H + ", trying " + httpRequest.D());
                }
                jCurrentTimeMillis = System.currentTimeMillis();
                LU luF = F(httpRequest.D(), httpRequest.C(), httpRequest.B(), httpRequest.A());
                if (luF != null) {
                    return luF;
                }
            } catch (LT e) {
                if (M(e, jCurrentTimeMillis) && i < this.H - 1) {
                    continue;
                } else if (this.E.zD(e) && i < this.H - 1) {
                    try {
                        long startTime = this.B;
                        Thread.sleep(startTime);
                    } catch (InterruptedException e2) {
                        throw e;
                    }
                } else {
                    throw e;
                }
            }
        }
        return null;
    }

    private final LU L(HttpURLConnection urlConnection) throws Exception {
        InputStream errorStream = null;
        byte[] bArrHF = null;
        try {
            errorStream = urlConnection.getErrorStream();
            if (errorStream != null) {
                bArrHF = this.E.hF(errorStream);
            }
            return new LU(urlConnection, bArrHF);
        } finally {
            if (errorStream != null) {
                try {
                    errorStream.close();
                } catch (Exception e) {
                }
            }
        }
    }

    private final boolean M(Throwable t, long startTime) {
        long jCurrentTimeMillis = (System.currentTimeMillis() - startTime) + 10;
        if (this.F.VC()) {
            this.F.dC("ELAPSED TIME = " + jCurrentTimeMillis + ", CT = " + this.B + ", RT = " + this.D);
        }
        if (this.G) {
            return jCurrentTimeMillis >= ((long) this.D);
        }
        return jCurrentTimeMillis >= ((long) this.B);
    }

    private void N(LP request) {
        StringBuilder sb = new StringBuilder("curl -k -i");
        if (request.C().equals(LR.POST) && request.A() != null) {
            sb.append(" -d \"");
            sb.append(new String(request.A(), Charset.forName("UTF-8")));
            sb.append("\"");
        }
        for (Map.Entry<String, String> entry : this.K.entrySet()) {
            sb.append(" -H \"");
            sb.append(entry.getKey());
            sb.append(":");
            sb.append(entry.getValue());
            sb.append("\"");
        }
        sb.append(" \"");
        sb.append(request.D());
        sb.append("\"");
        String string = sb.toString();
        O(string, 1, (string.length() / GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND) + 1);
    }

    private void O(String s, int count, int maxCount) {
        String str = L + " curl " + count + Constants.URL_PATH_DELIMITER + maxCount;
        if (s.length() > 4000) {
            Log.v(str, s.substring(0, GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND));
            O(s.substring(GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND), count + 1, maxCount);
        } else {
            Log.v(str, s);
        }
    }

    private final HttpURLConnection P(String path) throws IOException {
        try {
            new URL(path);
            return this.E.TF(path);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(path + " is not a valid URL", e);
        }
    }

    private final void Q(HttpURLConnection urlConnection, LR httpMethod, String contentType) throws IOException {
        urlConnection.setConnectTimeout(this.B);
        urlConnection.setReadTimeout(this.D);
        this.E.ZF(urlConnection, httpMethod, contentType);
    }
}
