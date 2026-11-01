package com.google.android.gms.iid;

import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzai {

    @GuardedBy("SdkFlagFactory.class")
    private static zzai zzdd;

    public abstract zzaj<Boolean> zzd(String str, boolean z);

    public static synchronized zzai zzy() {
        if (zzdd == null) {
            zzdd = new zzac();
        }
        return zzdd;
    }
}
