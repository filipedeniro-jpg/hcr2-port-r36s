package com.facebook.ads.redexgen.X;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class L5 extends Writer {
    public int B;
    public char[] C = new char[1024];
    public final LA D;

    public L5(LA output) {
        this.D = output;
    }

    private void B() {
        this.D.aF(new String(this.C, 0, this.B));
        this.B = 0;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        flush();
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
        if (this.B > 0) {
            B();
        }
    }

    @Override // java.io.Writer
    public final void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            if (cbuf[i] == '\n' || this.B == this.C.length) {
                B();
            } else {
                this.C[this.B] = cbuf[i];
                this.B++;
            }
        }
    }
}
