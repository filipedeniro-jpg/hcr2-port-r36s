package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class zzav extends zzp {
    private final Object data;
    private final zzaw zzda;
    private String zzdb;

    public zzav(zzaw zzawVar, Object obj) {
        super("application/json; charset=UTF-8");
        this.zzda = (zzaw) zzdt.checkNotNull(zzawVar);
        this.data = zzdt.checkNotNull(obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzcm
    public final void writeTo(OutputStream outputStream) throws IOException {
        zzaz zzazVarZza = this.zzda.zza(outputStream, zzo());
        if (this.zzdb != null) {
            zzazVarZza.zzau();
            zzazVarZza.zzad(this.zzdb);
        }
        zzazVarZza.zzd(this.data);
        if (this.zzdb != null) {
            zzazVarZza.zzav();
        }
        zzazVarZza.flush();
    }

    public final zzav zzab(String str) {
        this.zzdb = str;
        return this;
    }
}
