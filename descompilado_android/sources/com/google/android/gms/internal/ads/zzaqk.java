package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaqk extends zzfm implements zzaql {
    public zzaqk() {
        super("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzaqf zzaqhVar;
        if (i == 1) {
            onRewardedAdOpened();
        } else if (i == 2) {
            onRewardedAdClosed();
        } else if (i == 3) {
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder == null) {
                zzaqhVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardItem");
                if (iInterfaceQueryLocalInterface instanceof zzaqf) {
                    zzaqhVar = (zzaqf) iInterfaceQueryLocalInterface;
                } else {
                    zzaqhVar = new zzaqh(strongBinder);
                }
            }
            zza(zzaqhVar);
        } else {
            if (i != 4) {
                return false;
            }
            onRewardedAdFailedToShow(parcel.readInt());
        }
        parcel2.writeNoException();
        return true;
    }
}
