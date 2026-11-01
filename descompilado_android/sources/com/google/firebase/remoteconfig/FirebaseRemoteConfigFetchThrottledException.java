package com.google.firebase.remoteconfig;

/* JADX INFO: loaded from: classes2.dex */
public class FirebaseRemoteConfigFetchThrottledException extends FirebaseRemoteConfigFetchException {
    private final long zzjo;

    public FirebaseRemoteConfigFetchThrottledException(long j) {
        this("Fetch was throttled.", j);
    }

    public FirebaseRemoteConfigFetchThrottledException(String str, long j) {
        super(str);
        this.zzjo = j;
    }

    public long getThrottleEndTimeMillis() {
        return this.zzjo;
    }
}
