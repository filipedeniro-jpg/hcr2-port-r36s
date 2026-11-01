package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public enum zzel {
    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION_CODE("authorization_code");

    private final String zzc;

    zzel(String str) {
        this.zzc = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.zzc;
    }
}
