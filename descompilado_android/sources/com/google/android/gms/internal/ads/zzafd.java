package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzafd extends zzfm implements zzafe {
    public zzafd() {
        super("com.google.android.gms.ads.internal.initialization.IInitializationCallback");
    }

    public static zzafe zzx(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.initialization.IInitializationCallback");
        if (iInterfaceQueryLocalInterface instanceof zzafe) {
            return (zzafe) iInterfaceQueryLocalInterface;
        }
        return new zzafg(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzc(parcel.createTypedArrayList(zzaex.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
