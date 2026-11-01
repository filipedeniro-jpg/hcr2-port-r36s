package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzwe extends zzfm implements zzwb {
    public zzwe() {
        super("com.google.android.gms.ads.internal.client.IMuteThisAdReason");
    }

    public static zzwb zzg(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMuteThisAdReason");
        if (iInterfaceQueryLocalInterface instanceof zzwb) {
            return (zzwb) iInterfaceQueryLocalInterface;
        }
        return new zzwd(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            String description = getDescription();
            parcel2.writeNoException();
            parcel2.writeString(description);
        } else {
            if (i != 2) {
                return false;
            }
            String strZzor = zzor();
            parcel2.writeNoException();
            parcel2.writeString(strZzor);
        }
        return true;
    }
}
