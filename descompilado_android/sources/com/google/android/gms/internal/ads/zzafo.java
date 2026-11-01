package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzafo extends zzfm implements zzafl {
    public zzafo() {
        super("com.google.android.gms.ads.internal.instream.client.IInstreamAd");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzafn zzafqVar;
        if (i == 3) {
            zzwk videoController = getVideoController();
            parcel2.writeNoException();
            zzfp.zza(parcel2, videoController);
            return true;
        }
        if (i == 4) {
            destroy();
            parcel2.writeNoException();
            return true;
        }
        if (i != 5) {
            return false;
        }
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
        IBinder strongBinder = parcel.readStrongBinder();
        if (strongBinder == null) {
            zzafqVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.instream.client.IInstreamAdCallback");
            if (iInterfaceQueryLocalInterface instanceof zzafn) {
                zzafqVar = (zzafn) iInterfaceQueryLocalInterface;
            } else {
                zzafqVar = new zzafq(strongBinder);
            }
        }
        zza(iObjectWrapperAsInterface, zzafqVar);
        parcel2.writeNoException();
        return true;
    }
}
