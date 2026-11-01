package com.adcolony.sdk;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
class as extends InputStream {
    InputStream a;
    int b;

    as(InputStream inputStream, int i, int i2) throws IOException {
        this.a = inputStream;
        while (i > 0) {
            i -= (int) inputStream.skip(i);
        }
        this.b = i2;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        int iAvailable = this.a.available();
        int i = this.b;
        return iAvailable <= i ? iAvailable : i;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.a.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.b;
        if (i == 0) {
            return -1;
        }
        this.b = i - 1;
        return this.a.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.b;
        if (i3 == 0) {
            return -1;
        }
        if (i2 > i3) {
            i2 = i3;
        }
        int i4 = this.a.read(bArr, i, i2);
        if (i4 == -1) {
            return -1;
        }
        this.b -= i4;
        return i4;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        int iSkip = (int) j;
        if (iSkip <= 0) {
            return 0L;
        }
        int i = this.b;
        if (iSkip > i) {
            iSkip = i;
        }
        this.b -= iSkip;
        while (iSkip > 0) {
            iSkip -= (int) this.a.skip(j);
        }
        return j;
    }
}
