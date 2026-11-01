package com.google.android.gms.internal.firebase_remote_config;

import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfb {
    private long zzlw;
    private int zzlx;
    private FirebaseRemoteConfigSettings zzly;

    private zzfb() {
    }

    public final zzfb zzc(long j) {
        this.zzlw = j;
        return this;
    }

    final zzfb zzn(int i) {
        this.zzlx = i;
        return this;
    }

    final zzfb zzc(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.zzly = firebaseRemoteConfigSettings;
        return this;
    }

    public final zzez zzdf() {
        return new zzez(this.zzlw, this.zzlx, this.zzly);
    }
}
