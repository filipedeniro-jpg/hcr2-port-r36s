package net.nend.android.internal.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/* JADX INFO: compiled from: NetworkTaskHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class l {
    private static final Object[] a = new Object[0];

    public static int a(int i) {
        if (i > 99999) {
            return 99999;
        }
        if (i > 30) {
            return i;
        }
        return 30;
    }

    public static g.f<Bitmap> a(final String str) {
        return new g.f<>(new g.b<Bitmap>() { // from class: net.nend.android.internal.utilities.l.1
            @Override // net.nend.android.internal.utilities.g.b
            public String getRequestUrl() {
                return str;
            }

            @Override // net.nend.android.internal.utilities.g.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Bitmap makeResponse(byte[] bArr) {
                Bitmap bitmapDecodeByteArray;
                if (bArr == null) {
                    return null;
                }
                try {
                    synchronized (l.a) {
                        try {
                            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    return bitmapDecodeByteArray;
                } catch (IllegalStateException e) {
                    j.a(k.ERR_HTTP_REQUEST, e);
                    return null;
                } catch (OutOfMemoryError e2) {
                    System.gc();
                    j.a(k.ERR_HTTP_REQUEST, e2);
                    return null;
                }
            }
        });
    }
}
