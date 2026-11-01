package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzru extends PushbackInputStream {
    private final /* synthetic */ zzrt zzbro;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzru(zzrt zzrtVar, InputStream inputStream, int i) {
        super(inputStream, 1);
        this.zzbro = zzrtVar;
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        this.zzbro.zzbrg.disconnect();
        super.close();
    }
}
