package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzlm {
    private final zziw zzapi;
    private final zziu[] zzbat;
    private zziu zzbau;

    public zzlm(zziu[] zziuVarArr, zziw zziwVar) {
        this.zzbat = zziuVarArr;
        this.zzapi = zziwVar;
    }

    public final zziu zza(zzix zzixVar, Uri uri) throws InterruptedException, IOException {
        zziu zziuVar = this.zzbau;
        if (zziuVar != null) {
            return zziuVar;
        }
        for (zziu zziuVar2 : this.zzbat) {
            try {
                if (zziuVar2.zza(zzixVar)) {
                    this.zzbau = zziuVar2;
                    zzixVar.zzfz();
                    break;
                }
                zzixVar.zzfz();
            } catch (EOFException unused) {
                zzixVar.zzfz();
            } catch (Throwable th) {
                zzixVar.zzfz();
                throw th;
            }
        }
        zziu zziuVar3 = this.zzbau;
        if (zziuVar3 == null) {
            String strZza = zzoh.zza(this.zzbat);
            StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 58);
            sb.append("None of the available extractors (");
            sb.append(strZza);
            sb.append(") could read the stream.");
            throw new zzml(sb.toString(), uri);
        }
        zziuVar3.zza(this.zzapi);
        return this.zzbau;
    }

    public final void release() {
        zziu zziuVar = this.zzbau;
        if (zziuVar != null) {
            zziuVar.release();
            this.zzbau = null;
        }
    }
}
