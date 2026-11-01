package com.google.android.gms.internal.firebase_auth;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public enum zzlm {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzgm.zza),
    ENUM(null),
    MESSAGE(null);

    private final Object zzj;

    zzlm(Object obj) {
        this.zzj = obj;
    }
}
