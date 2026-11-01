package com.google.android.gms.internal.firebase_remote_config;

import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: loaded from: classes2.dex */
final class zzdn extends zzdo {
    static final zzdn zzhe = new zzdn();

    private zzdn() {
        super("CharMatcher.none()");
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzdj
    public final boolean zzb(char c) {
        return false;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzdj
    public final int zza(CharSequence charSequence, int i) {
        zzdt.zza(i, charSequence.length(), FirebaseAnalytics.Param.INDEX);
        return -1;
    }
}
