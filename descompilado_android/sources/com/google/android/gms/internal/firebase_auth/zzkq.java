package com.google.android.gms.internal.firebase_auth;

import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzkq extends RuntimeException {
    private final List<String> zza;

    public zzkq(zzjg zzjgVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zza = null;
    }
}
