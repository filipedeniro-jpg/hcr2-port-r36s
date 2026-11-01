package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class zzay implements zzci {
    private final zzaw zzda;
    private final Set<String> zzdc;

    protected zzay(zzbb zzbbVar) {
        this.zzda = zzbbVar.zzda;
        this.zzdc = new HashSet(zzbbVar.zzdf);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzci
    public final <T> T zza(InputStream inputStream, Charset charset, Class<T> cls) throws IOException {
        zzba zzbaVarZza = this.zzda.zza(inputStream, charset);
        if (!this.zzdc.isEmpty()) {
            try {
                boolean z = (zzbaVarZza.zza(this.zzdc) == null || zzbaVarZza.zzba() == zzbg.END_OBJECT) ? false : true;
                Object[] objArr = {this.zzdc};
                if (!z) {
                    throw new IllegalArgumentException(zzdy.zza("wrapper key(s) not found: %s", objArr));
                }
            } catch (Throwable th) {
                zzbaVarZza.close();
                throw th;
            }
        }
        return (T) zzbaVarZza.zza(cls, true, null);
    }

    public final zzaw zzl() {
        return this.zzda;
    }

    public final Set<String> zzar() {
        return Collections.unmodifiableSet(this.zzdc);
    }
}
