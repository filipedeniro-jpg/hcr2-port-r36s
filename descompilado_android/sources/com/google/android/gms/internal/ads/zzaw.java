package com.google.android.gms.internal.ads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaw extends ByteArrayOutputStream {
    private final zzaj zzbw;

    public zzaw(zzaj zzajVar, int i) {
        this.zzbw = zzajVar;
        this.buf = this.zzbw.zzc(Math.max(i, 256));
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.zzbw.zza(this.buf);
        this.buf = null;
        super.close();
    }

    public final void finalize() {
        this.zzbw.zza(this.buf);
    }

    private final void zzd(int i) {
        if (this.count + i <= this.buf.length) {
            return;
        }
        byte[] bArrZzc = this.zzbw.zzc((this.count + i) << 1);
        System.arraycopy(this.buf, 0, bArrZzc, 0, this.count);
        this.zzbw.zza(this.buf);
        this.buf = bArrZzc;
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public final synchronized void write(byte[] bArr, int i, int i2) {
        zzd(i2);
        super.write(bArr, i, i2);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public final synchronized void write(int i) {
        zzd(1);
        super.write(i);
    }
}
