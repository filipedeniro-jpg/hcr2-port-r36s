package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzxd extends zzwe {
    private final String description;
    private final String zzcey;

    public zzxd(String str, String str2) {
        this.description = str;
        this.zzcey = str2;
    }

    @Override // com.google.android.gms.internal.ads.zzwb
    public final String getDescription() throws RemoteException {
        return this.description;
    }

    @Override // com.google.android.gms.internal.ads.zzwb
    public final String zzor() throws RemoteException {
        return this.zzcey;
    }
}
