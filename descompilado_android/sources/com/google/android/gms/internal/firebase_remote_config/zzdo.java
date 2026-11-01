package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
abstract class zzdo extends zzdm {
    private final String description;

    zzdo(String str) {
        this.description = (String) zzdt.checkNotNull(str);
    }

    public final String toString() {
        return this.description;
    }
}
