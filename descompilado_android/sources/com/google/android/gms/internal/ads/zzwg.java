package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.MuteThisAdReason;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzwg implements MuteThisAdReason {
    private final String description;
    private zzwb zzcdr;

    public zzwg(zzwb zzwbVar) {
        String description;
        this.zzcdr = zzwbVar;
        try {
            description = zzwbVar.getDescription();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            description = null;
        }
        this.description = description;
    }

    @Override // com.google.android.gms.ads.MuteThisAdReason
    public final String getDescription() {
        return this.description;
    }

    public final zzwb zzos() {
        return this.zzcdr;
    }

    public final String toString() {
        return this.description;
    }
}
