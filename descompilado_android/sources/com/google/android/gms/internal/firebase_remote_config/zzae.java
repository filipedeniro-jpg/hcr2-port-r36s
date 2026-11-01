package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzae {
    String message;
    int statusCode;
    zzw zzbc;
    String zzbv;
    String zzby;

    public zzae(int i, String str, zzw zzwVar) {
        if (!(i >= 0)) {
            throw new IllegalArgumentException();
        }
        this.statusCode = i;
        this.zzbv = str;
        this.zzbc = (zzw) zzdt.checkNotNull(zzwVar);
    }

    public zzae(zzac zzacVar) {
        this(zzacVar.getStatusCode(), zzacVar.getStatusMessage(), zzacVar.zzx());
        try {
            this.zzby = zzacVar.zzae();
            if (this.zzby.length() == 0) {
                this.zzby = null;
            }
        } catch (IOException e) {
            zzea.zza(e);
        }
        StringBuilder sbZzc = zzaf.zzc(zzacVar);
        if (this.zzby != null) {
            sbZzc.append(zzcl.zzgg);
            sbZzc.append(this.zzby);
        }
        this.message = sbZzc.toString();
    }

    public final zzae zzx(String str) {
        this.message = str;
        return this;
    }

    public final zzae zzy(String str) {
        this.zzby = str;
        return this;
    }
}
