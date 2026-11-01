package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzabx extends zzfm implements zzaby {
    public zzabx() {
        super("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    public static zzaby zzr(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
        if (iInterfaceQueryLocalInterface instanceof zzaby) {
            return (zzaby) iInterfaceQueryLocalInterface;
        }
        return new zzaca(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzabo zzabqVar;
        if (i != 1) {
            return false;
        }
        IBinder strongBinder = parcel.readStrongBinder();
        if (strongBinder == null) {
            zzabqVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
            if (iInterfaceQueryLocalInterface instanceof zzabo) {
                zzabqVar = (zzabo) iInterfaceQueryLocalInterface;
            } else {
                zzabqVar = new zzabq(strongBinder);
            }
        }
        zza(zzabqVar, parcel.readString());
        parcel2.writeNoException();
        return true;
    }
}
