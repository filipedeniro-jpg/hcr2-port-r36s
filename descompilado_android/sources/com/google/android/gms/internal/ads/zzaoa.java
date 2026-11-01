package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaoa extends zzfm implements zzaob {
    public zzaoa() {
        super("com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            zzaoc zzaofVar = null;
            zzaoe zzaogVar = null;
            zzaoe zzaogVar2 = null;
            if (i == 2) {
                zzanv zzanvVar = (zzanv) zzfp.zza(parcel, zzanv.CREATOR);
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                    if (iInterfaceQueryLocalInterface instanceof zzaoc) {
                        zzaofVar = (zzaoc) iInterfaceQueryLocalInterface;
                    } else {
                        zzaofVar = new zzaof(strongBinder);
                    }
                }
                zza(zzanvVar, zzaofVar);
                parcel2.writeNoException();
            } else if (i == 4) {
                zzaok zzaokVar = (zzaok) zzfp.zza(parcel, zzaok.CREATOR);
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    if (iInterfaceQueryLocalInterface2 instanceof zzaoe) {
                        zzaogVar2 = (zzaoe) iInterfaceQueryLocalInterface2;
                    } else {
                        zzaogVar2 = new zzaog(strongBinder2);
                    }
                }
                zza(zzaokVar, zzaogVar2);
                parcel2.writeNoException();
            } else {
                if (i != 5) {
                    return false;
                }
                zzaok zzaokVar2 = (zzaok) zzfp.zza(parcel, zzaok.CREATOR);
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 != null) {
                    IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    if (iInterfaceQueryLocalInterface3 instanceof zzaoe) {
                        zzaogVar = (zzaoe) iInterfaceQueryLocalInterface3;
                    } else {
                        zzaogVar = new zzaog(strongBinder3);
                    }
                }
                zzb(zzaokVar2, zzaogVar);
                parcel2.writeNoException();
            }
        } else {
            zzanx zzanxVarZza = zza((zzanv) zzfp.zza(parcel, zzanv.CREATOR));
            parcel2.writeNoException();
            zzfp.zzb(parcel2, zzanxVarZza);
        }
        return true;
    }
}
