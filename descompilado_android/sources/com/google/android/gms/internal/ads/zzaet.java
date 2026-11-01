package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaet extends zzaeq {
    private final /* synthetic */ zzaxf zzbrn;

    zzaet(zzaeu zzaeuVar, zzaxf zzaxfVar) {
        this.zzbrn = zzaxfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaen
    public final void zza(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        this.zzbrn.set(parcelFileDescriptor);
    }
}
