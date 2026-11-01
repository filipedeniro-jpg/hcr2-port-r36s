package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class zzo implements zzs {
    @Override // com.google.android.gms.internal.firebase_remote_config.zzs
    public final long getLength() throws IOException {
        return 0L;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzs
    public final String getType() {
        return null;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzs
    public final boolean zzn() {
        return true;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzcm
    public final void writeTo(OutputStream outputStream) throws IOException {
        outputStream.flush();
    }
}
