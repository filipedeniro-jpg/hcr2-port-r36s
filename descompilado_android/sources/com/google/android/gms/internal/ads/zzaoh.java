package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaoh extends zzfm implements zzaoe {
    public zzaoh() {
        super("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzb((ParcelFileDescriptor) zzfp.zza(parcel, ParcelFileDescriptor.CREATOR));
        } else {
            if (i != 2) {
                return false;
            }
            zza((zzauw) zzfp.zza(parcel, zzauw.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
