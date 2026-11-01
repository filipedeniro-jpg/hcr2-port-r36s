package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
final class zzbp extends OutputStream {
    long zzel;

    zzbp() {
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        this.zzel += (long) i2;
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        this.zzel++;
    }
}
