package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzajg extends zzfm implements zzajd {
    public zzajg() {
        super("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }

    public static zzajd zzz(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        if (iInterfaceQueryLocalInterface instanceof zzajd) {
            return (zzajd) iInterfaceQueryLocalInterface;
        }
        return new zzajf(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzaji zzajiVarZzcz = zzcz(parcel.readString());
            parcel2.writeNoException();
            zzfp.zza(parcel2, zzajiVarZzcz);
        } else if (i == 2) {
            boolean zZzda = zzda(parcel.readString());
            parcel2.writeNoException();
            zzfp.writeBoolean(parcel2, zZzda);
        } else {
            if (i != 3) {
                return false;
            }
            zzalj zzaljVarZzdb = zzdb(parcel.readString());
            parcel2.writeNoException();
            zzfp.zza(parcel2, zzaljVarZzdb);
        }
        return true;
    }
}
