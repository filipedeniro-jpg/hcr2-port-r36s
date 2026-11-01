package com.google.android.gms.internal.ads;

import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzaz extends zzdus implements Closeable {
    private static zzdva zzcp = zzdva.zzn(zzaz.class);

    public zzaz(zzduu zzduuVar, zzba zzbaVar) throws IOException {
        zza(zzduuVar, zzduuVar.size(), zzbaVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdus, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.zzhwf.close();
    }

    @Override // com.google.android.gms.internal.ads.zzdus
    public String toString() {
        String string = this.zzhwf.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 7);
        sb.append("model(");
        sb.append(string);
        sb.append(")");
        return sb.toString();
    }
}
