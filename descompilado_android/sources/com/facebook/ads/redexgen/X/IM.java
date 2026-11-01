package com.facebook.ads.redexgen.X;

import android.support.annotation.NonNull;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class IM extends BufferedInputStream {
    private boolean B;
    private int C;
    private int D;

    public IM(InputStream originalStream) {
        super(originalStream);
        this.C = Integer.MAX_VALUE;
    }

    public final boolean A() {
        return this.B;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public final synchronized void mark(int readlimit) {
        this.C = readlimit;
        super.mark(readlimit);
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        if (this.D + 1 > this.C) {
            this.B = true;
            return -1;
        }
        this.D++;
        return super.read();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(@NonNull byte[] b) throws IOException {
        if (this.D + b.length > this.C) {
            this.B = true;
            return -1;
        }
        return super.read(b);
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public final synchronized int read(byte[] b, int off, int len) throws IOException {
        int i;
        if (this.D + len > this.C) {
            this.B = true;
            i = -1;
        } else {
            i = super.read(b, off, len);
            this.D += i;
        }
        return i;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public final synchronized void reset() throws IOException {
        this.C = Integer.MAX_VALUE;
        super.reset();
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public final synchronized long skip(long n) throws IOException {
        long jSkip;
        if (((long) this.D) + n > this.C) {
            this.B = true;
            jSkip = 0;
        } else {
            this.D = (int) (((long) this.D) + n);
            jSkip = super.skip(n);
        }
        return jSkip;
    }
}
