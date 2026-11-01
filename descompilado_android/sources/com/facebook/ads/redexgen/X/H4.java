package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class H4 {
    private static final Map<String, File> C = new HashMap();
    private final Context B;

    public H4(Context context) {
        this.B = context;
    }

    public final boolean A(H0 cacheFileData) {
        HJ.B(cacheFileData, null);
        String str = cacheFileData.F;
        InputStream inputStream = null;
        try {
            try {
                try {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    File fileB = C0688Lz.B(this.B);
                    String fileName = new M8().sB(str);
                    File targetFile = new File(fileB, fileName);
                    M1 m1 = new M1(targetFile, new MA(67108864L));
                    if (m1.RC()) {
                        synchronized (C) {
                            C.put(str, targetFile);
                        }
                        m1.close();
                        HI.F(str, true, true);
                        if (0 == 0) {
                            return true;
                        }
                        try {
                            inputStream.close();
                            return true;
                        } catch (IOException e) {
                            String fileName2 = H5.C;
                            Log.e(fileName2, "Error closing the file", e);
                            return true;
                        }
                    }
                    HI.F(str, false, true);
                    InputStream inputStreamB = B(str);
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int iC = C(bArr, inputStreamB);
                        if (iC == -1) {
                            break;
                        }
                        m1.YB(bArr, iC);
                    }
                    int iAvailable = m1.available();
                    m1.iB();
                    m1.close();
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    synchronized (C) {
                        C.put(str, targetFile);
                    }
                    HI.E(str, true, HI.D, null, Integer.valueOf(iAvailable), Long.valueOf(jCurrentTimeMillis2));
                    if (inputStreamB != null) {
                        try {
                            inputStreamB.close();
                        } catch (IOException e2) {
                            Log.e(H5.C, "Error closing the file", e2);
                        }
                    }
                    return true;
                } catch (Throwable th) {
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            Log.e(H5.C, "Error closing the file", e3);
                        }
                    }
                    throw th;
                }
            } catch (C0669Lg e4) {
                HI.E(str, true, HI.E, e4.toString(), null, null);
                Log.e(H5.C, "Error caching the file", e4);
                if (0 == 0) {
                    return false;
                }
                try {
                    inputStream.close();
                    return false;
                } catch (IOException e5) {
                    Log.e(H5.C, "Error closing the file", e5);
                    return false;
                }
            }
        } catch (C0667Le e6) {
            HI.E(str, true, HI.C, e6.toString(), null, null);
            Log.e(H5.C, "Error caching the file", e6);
            if (0 == 0) {
                return false;
            }
            try {
                inputStream.close();
                return false;
            } catch (IOException e7) {
                Log.e(H5.C, "Error closing the file", e7);
                return false;
            }
        }
    }

    private InputStream B(String baseUrl) throws C0669Lg {
        try {
            if (baseUrl.startsWith("file:///android_asset/")) {
                return this.B.getAssets().open(baseUrl.substring("file:///android_asset/".length()));
            }
            URL url = new URL(baseUrl);
            URLConnection uRLConnectionOpenConnection = url.openConnection();
            uRLConnectionOpenConnection.connect();
            return new BufferedInputStream(uRLConnectionOpenConnection.getInputStream());
        } catch (IOException e) {
            throw new C0669Lg("Error during opening connection.", e);
        }
    }

    @Nullable
    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final String m253B(String url) {
        File file;
        synchronized (C) {
            file = C.get(url);
        }
        if (file == null) {
            return null;
        }
        return "file://" + file.getPath();
    }

    private static int C(byte[] data, InputStream input) throws C0669Lg {
        try {
            return input.read(data);
        } catch (IOException e) {
            throw new C0669Lg("Error during reading data.", e);
        }
    }
}
