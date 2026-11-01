package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzp implements zzs {
    private zzy zzag;
    private long zzah;

    protected zzp(String str) {
        this(str == null ? null : new zzy(str));
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzs
    public final boolean zzn() {
        return true;
    }

    private zzp(zzy zzyVar) {
        this.zzah = -1L;
        this.zzag = zzyVar;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzs
    public final long getLength() throws IOException {
        if (this.zzah == -1) {
            this.zzah = zzca.zzb(this);
        }
        return this.zzah;
    }

    protected final Charset zzo() {
        zzy zzyVar = this.zzag;
        return (zzyVar == null || zzyVar.zzr() == null) ? zzbo.UTF_8 : this.zzag.zzr();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzs
    public final String getType() {
        zzy zzyVar = this.zzag;
        if (zzyVar == null) {
            return null;
        }
        return zzyVar.zzp();
    }
}
