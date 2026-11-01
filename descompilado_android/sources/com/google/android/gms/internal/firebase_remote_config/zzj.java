package com.google.android.gms.internal.firebase_remote_config;

import com.ironsource.sdk.constants.Constants;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class zzj implements zzi {
    private final String key;
    private final String zzae;

    public zzj() {
        this(null);
    }

    public zzj(String str) {
        this(str, null);
    }

    private zzj(String str, String str2) {
        this.key = str;
        this.zzae = null;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzi
    public final void zza(zzf<?> zzfVar) throws IOException {
        String str = this.key;
        if (str != null) {
            zzfVar.put(Constants.ParametersKeys.KEY, str);
        }
    }
}
