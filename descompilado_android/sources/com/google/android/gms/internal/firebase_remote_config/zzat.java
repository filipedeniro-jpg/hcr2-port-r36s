package com.google.android.gms.internal.firebase_remote_config;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
final class zzat extends FilterInputStream {
    private long zzcy;
    private final /* synthetic */ zzaq zzcz;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzat(zzaq zzaqVar, InputStream inputStream) {
        super(inputStream);
        this.zzcz = zzaqVar;
        this.zzcy = 0L;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.in.read(bArr, i, i2);
        if (i3 == -1) {
            zzap();
        } else {
            this.zzcy += (long) i3;
        }
        return i3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        int i = this.in.read();
        if (i == -1) {
            zzap();
        } else {
            this.zzcy++;
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) throws IOException {
        long jSkip = this.in.skip(j);
        this.zzcy += jSkip;
        return jSkip;
    }

    private final void zzap() throws IOException {
        long contentLength = this.zzcz.getContentLength();
        if (contentLength == -1) {
            return;
        }
        long j = this.zzcy;
        if (j == 0 || j >= contentLength) {
            return;
        }
        StringBuilder sb = new StringBuilder(102);
        sb.append("Connection closed prematurely: bytesRead = ");
        sb.append(j);
        sb.append(", Content-Length = ");
        sb.append(contentLength);
        throw new IOException(sb.toString());
    }
}
