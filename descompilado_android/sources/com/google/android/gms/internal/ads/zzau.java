package com.google.android.gms.internal.ads;

import java.io.FilterInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzau extends FilterInputStream {
    private final HttpURLConnection zzcl;

    zzau(HttpURLConnection httpURLConnection) {
        super(zzar.zza(httpURLConnection));
        this.zzcl = httpURLConnection;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        this.zzcl.disconnect();
    }
}
