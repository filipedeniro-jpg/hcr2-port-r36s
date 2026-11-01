package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class bj extends OutputStream {
    private static final byte[] a = new byte[0];
    private final List<byte[]> b;
    private int c;
    private int d;
    private byte[] e;
    private int f;

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public bj() {
        this(1024);
    }

    public bj(int i) {
        this.b = new ArrayList();
        if (i < 0) {
            throw new IllegalArgumentException("Negative initial size: " + i);
        }
        synchronized (this) {
            a(i);
        }
    }

    private void a(int i) {
        if (this.c < this.b.size() - 1) {
            this.d += this.e.length;
            this.c++;
            this.e = this.b.get(this.c);
            return;
        }
        byte[] bArr = this.e;
        if (bArr == null) {
            this.d = 0;
        } else {
            i = Math.max(bArr.length << 1, i - this.d);
            this.d += this.e.length;
        }
        this.c++;
        this.e = new byte[i];
        this.b.add(this.e);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        int i3;
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return;
        }
        synchronized (this) {
            int i4 = this.f + i2;
            int i5 = this.f - this.d;
            while (i2 > 0) {
                int iMin = Math.min(i2, this.e.length - i5);
                System.arraycopy(bArr, i3 - i2, this.e, i5, iMin);
                i2 -= iMin;
                if (i2 > 0) {
                    a(i4);
                    i5 = 0;
                }
            }
            this.f = i4;
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i) {
        int i2 = this.f - this.d;
        if (i2 == this.e.length) {
            a(this.f + 1);
            i2 = 0;
        }
        this.e[i2] = (byte) i;
        this.f++;
    }

    public synchronized byte[] a() {
        int i = this.f;
        if (i == 0) {
            return a;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (byte[] bArr2 : this.b) {
            int iMin = Math.min(bArr2.length, i);
            System.arraycopy(bArr2, 0, bArr, i2, iMin);
            i2 += iMin;
            i -= iMin;
            if (i == 0) {
                break;
            }
        }
        return bArr;
    }

    public String toString() {
        return new String(a());
    }
}
