package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaso extends zzfm implements zzasp {
    public zzaso() {
        super("com.google.android.gms.ads.internal.signals.ISignalGenerator");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzasn zzasmVar;
        if (i != 1) {
            return false;
        }
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
        zzasr zzasrVar = (zzasr) zzfp.zza(parcel, zzasr.CREATOR);
        IBinder strongBinder = parcel.readStrongBinder();
        if (strongBinder == null) {
            zzasmVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.signals.ISignalCallback");
            if (iInterfaceQueryLocalInterface instanceof zzasn) {
                zzasmVar = (zzasn) iInterfaceQueryLocalInterface;
            } else {
                zzasmVar = new zzasm(strongBinder);
            }
        }
        zza(iObjectWrapperAsInterface, zzasrVar, zzasmVar);
        parcel2.writeNoException();
        return true;
    }
}
