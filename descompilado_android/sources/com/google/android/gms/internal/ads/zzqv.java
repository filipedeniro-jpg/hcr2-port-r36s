package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzqv extends zzfm implements zzqs {
    public zzqv() {
        super("com.google.android.gms.ads.internal.appopen.client.IAppOpenAd");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzqz zzqyVar;
        if (i == 2) {
            zzve zzveVarZzmc = zzmc();
            parcel2.writeNoException();
            zzfp.zza(parcel2, zzveVarZzmc);
            return true;
        }
        if (i != 3) {
            return false;
        }
        IBinder strongBinder = parcel.readStrongBinder();
        if (strongBinder == null) {
            zzqyVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.appopen.client.IAppOpenAdPresentationCallback");
            if (iInterfaceQueryLocalInterface instanceof zzqz) {
                zzqyVar = (zzqz) iInterfaceQueryLocalInterface;
            } else {
                zzqyVar = new zzqy(strongBinder);
            }
        }
        zza(zzqyVar);
        parcel2.writeNoException();
        return true;
    }
}
