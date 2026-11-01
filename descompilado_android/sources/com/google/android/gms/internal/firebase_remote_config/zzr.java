package com.google.android.gms.internal.firebase_remote_config;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class zzr implements zzv {
    @Override // com.google.android.gms.internal.firebase_remote_config.zzv
    public final String getName() {
        return HttpRequest.ENCODING_GZIP;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzv
    public final void zza(zzcm zzcmVar, OutputStream outputStream) throws IOException {
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(new zzq(this, outputStream));
        zzcmVar.writeTo(gZIPOutputStream);
        gZIPOutputStream.close();
    }
}
