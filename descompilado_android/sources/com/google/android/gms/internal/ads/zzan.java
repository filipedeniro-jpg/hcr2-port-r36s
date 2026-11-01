package com.google.android.gms.internal.ads;

import androidx.annotation.VisibleForTesting;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
final class zzan extends FilterInputStream {
    private final long zzcb;
    private long zzcc;

    zzan(InputStream inputStream, long j) {
        super(inputStream);
        this.zzcb = j;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        int i = super.read();
        if (i != -1) {
            this.zzcc++;
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = super.read(bArr, i, i2);
        if (i3 != -1) {
            this.zzcc += (long) i3;
        }
        return i3;
    }

    final long zzn() {
        return this.zzcb - this.zzcc;
    }
}
