package com.google.android.gms.internal.firebase_remote_config;

import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/* JADX INFO: loaded from: classes2.dex */
public final class zzez implements FirebaseRemoteConfigInfo {
    private final long zzlt;
    private final int zzlu;
    private final FirebaseRemoteConfigSettings zzlv;

    private zzez(long j, int i, FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.zzlt = j;
        this.zzlu = i;
        this.zzlv = firebaseRemoteConfigSettings;
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public final long getFetchTimeMillis() {
        return this.zzlt;
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public final int getLastFetchStatus() {
        return this.zzlu;
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public final FirebaseRemoteConfigSettings getConfigSettings() {
        return this.zzlv;
    }
}
