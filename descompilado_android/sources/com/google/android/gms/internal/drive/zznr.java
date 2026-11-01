package com.google.android.gms.internal.drive;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: classes2.dex */
public enum zznr {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzjc.zznq),
    ENUM(null),
    MESSAGE(null);

    private final Object zztd;

    zznr(Object obj) {
        this.zztd = obj;
    }
}
