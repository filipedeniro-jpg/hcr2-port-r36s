package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class H9 {
    private static final String D = H9.class.getSimpleName();
    private static volatile H9 E;
    private final Context B;
    private final Map<String, C0560Gy> C = Collections.synchronizedMap(new HashMap());

    private H9(Context context) {
        this.B = context;
    }

    @Nullable
    public final Bitmap A(String url, int height, int width) {
        C0560Gy imageData = this.C.get(url);
        return (!HI.B() || imageData == null) ? E(new C0560Gy(url, height, width, "unknown", "unknown"), false) : E(imageData, false);
    }

    public static H9 B(Context context) {
        if (E == null) {
            Context context2 = context.getApplicationContext();
            synchronized (H9.class) {
                if (E == null) {
                    E = new H9(context2);
                }
            }
        }
        return E;
    }

    public final String B(String url) {
        File file = new File(this.B.getCacheDir(), url.hashCode() + ".png");
        return file.exists() ? file.getPath() : url;
    }

    @Nullable
    public final Bitmap C(C0560Gy imageData) {
        return E(imageData, true);
    }

    @Nullable
    private Bitmap C(String url, int height, int width) throws Throwable {
        Bitmap bitmapDecodeStream;
        try {
            if (J(height, width)) {
                bitmapDecodeStream = C0605Ir.C(url.substring("file://".length()), height, width);
            } else {
                bitmapDecodeStream = BitmapFactory.decodeStream(new FileInputStream(url.substring("file://".length())), null, null);
            }
            H(url, bitmapDecodeStream);
            return bitmapDecodeStream;
        } catch (IOException e) {
            Log.e(D, "Failed to copy local image into cache (url=" + url + ").", e);
            return null;
        }
    }

    @Nullable
    private Bitmap D(C0560Gy imageData, boolean duringLoading) throws Throwable {
        Bitmap bitmapG;
        String str = imageData.E;
        int i = imageData.C;
        int i2 = imageData.F;
        long jCurrentTimeMillis = System.currentTimeMillis();
        IOException e = null;
        if (str.startsWith("asset:///")) {
            try {
                try {
                    try {
                        InputStream inputStreamOpen = this.B.getAssets().open(str.substring(9));
                        if (J(i, i2)) {
                            bitmapG = C0605Ir.D(inputStreamOpen, i, i2);
                        } else {
                            bitmapG = BitmapFactory.decodeStream(inputStreamOpen);
                        }
                        if (inputStreamOpen != null) {
                            F(inputStreamOpen);
                        }
                    } catch (OutOfMemoryError e2) {
                        I(e2);
                        bitmapG = null;
                        if (0 != 0) {
                            F(null);
                        }
                    }
                } catch (IOException e3) {
                    I(e3);
                    bitmapG = null;
                    if (0 != 0) {
                        F(null);
                    }
                }
            } catch (Throwable storedThrowable) {
                if (0 != 0) {
                    F(null);
                }
                throw storedThrowable;
            }
        } else if (J(i, i2)) {
            try {
                bitmapG = K(str, i, i2);
            } catch (IOException e4) {
                e = e4;
                I(e);
                bitmapG = G(str);
            }
        } else {
            bitmapG = G(str);
        }
        String string = e != null ? e.toString() : null;
        if (bitmapG == null) {
            HI.D(imageData, duringLoading, HI.E, string, null, null);
        } else {
            int iH = H(str, bitmapG);
            long loadTime = System.currentTimeMillis() - jCurrentTimeMillis;
            if (iH <= 0) {
                HI.D(imageData, duringLoading, HI.C, string, null, null);
            } else {
                HI.D(imageData, duringLoading, HI.D, string, Integer.valueOf(iH), Long.valueOf(loadTime));
            }
        }
        return bitmapG;
    }

    @Nullable
    private final Bitmap E(C0560Gy imageData, boolean duringAdLoading) {
        if (HI.B() && duringAdLoading) {
            this.C.put(imageData.E, imageData);
        }
        String str = imageData.E;
        int height = imageData.C;
        int width = imageData.F;
        HH hh = new HH(imageData.B, imageData.D, MessengerShareContentUtility.MEDIA_IMAGE, duringAdLoading, str);
        File cacheDir = this.B.getCacheDir();
        String url = str.hashCode() + ".png";
        File file = new File(cacheDir, url);
        if (!file.exists()) {
            HI.C(hh, false);
            if (str.startsWith("file://")) {
                return C(str, height, width);
            }
            return D(imageData, duringAdLoading);
        }
        HI.C(hh, true);
        if (J(height, width)) {
            String url2 = file.getAbsolutePath();
            return C0605Ir.C(url2, height, width);
        }
        String url3 = file.getAbsolutePath();
        return BitmapFactory.decodeFile(url3);
    }

    private static void F(@Nullable Closeable c) {
        if (c == null) {
            return;
        }
        try {
            c.close();
        } catch (IOException e) {
        }
    }

    @Nullable
    private Bitmap G(String url) {
        byte[] bArrA;
        LU luB = IB.B(this.B).B(url, null);
        if (luB == null || (bArrA = luB.A()) == null) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArrA, 0, bArrA.length);
    }

    /* JADX INFO: Removed unreachable split cross block B:47:0x0008 */
    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 2441. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    private int H(java.lang.String r10, @android.support.annotation.Nullable android.graphics.Bitmap r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.redexgen.X.H9.H(java.lang.String, android.graphics.Bitmap):int");
    }

    private void I(Throwable t) {
        if (t != null) {
            C0585Hx.D(this.B, MessengerShareContentUtility.MEDIA_IMAGE, C0584Hw.SB, new C0583Hv(t));
        } else {
            C0585Hx.D(this.B, MessengerShareContentUtility.MEDIA_IMAGE, C0584Hw.SB, new C0583Hv("Cache error. Bitmap is null."));
        }
    }

    private boolean J(int height, int width) {
        return height > 0 && width > 0 && JA.v(this.B);
    }

    private Bitmap K(String url, int height, int width) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        InputStream inputStream = httpURLConnection.getInputStream();
        Bitmap bitmapD = C0605Ir.D(inputStream, height, width);
        F(inputStream);
        return bitmapD;
    }
}
