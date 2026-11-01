package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzuv extends zzfm implements zzuw {
    public zzuv() {
        super("com.google.android.gms.ads.internal.client.IAdLoader");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzb((zztp) zzfp.zza(parcel, zztp.CREATOR));
            parcel2.writeNoException();
        } else if (i == 2) {
            String mediationAdapterClassName = getMediationAdapterClassName();
            parcel2.writeNoException();
            parcel2.writeString(mediationAdapterClassName);
        } else if (i == 3) {
            boolean zIsLoading = isLoading();
            parcel2.writeNoException();
            zzfp.writeBoolean(parcel2, zIsLoading);
        } else if (i == 4) {
            String strZzjp = zzjp();
            parcel2.writeNoException();
            parcel2.writeString(strZzjp);
        } else {
            if (i != 5) {
                return false;
            }
            zza((zztp) zzfp.zza(parcel, zztp.CREATOR), parcel.readInt());
            parcel2.writeNoException();
        }
        return true;
    }
}
