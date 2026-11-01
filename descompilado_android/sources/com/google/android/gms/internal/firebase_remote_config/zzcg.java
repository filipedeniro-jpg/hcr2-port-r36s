package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class zzcg implements zzcm {
    private final Logger logger;
    private final zzcm zzan;
    private final int zzbf;
    private final Level zzgd;

    public zzcg(zzcm zzcmVar, Logger logger, Level level, int i) {
        this.zzan = zzcmVar;
        this.logger = logger;
        this.zzgd = level;
        this.zzbf = i;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzcm
    public final void writeTo(OutputStream outputStream) throws IOException {
        zzcd zzcdVar = new zzcd(outputStream, this.logger, this.zzgd, this.zzbf);
        try {
            this.zzan.writeTo(zzcdVar);
            zzcdVar.zzcc().close();
            outputStream.flush();
        } catch (Throwable th) {
            zzcdVar.zzcc().close();
            throw th;
        }
    }
}
