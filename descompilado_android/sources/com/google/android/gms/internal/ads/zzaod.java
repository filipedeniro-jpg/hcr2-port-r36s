package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaod extends zzfn implements zzaob {
    zzaod(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    @Override // com.google.android.gms.internal.ads.zzaob
    public final zzanx zza(zzanv zzanvVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzanvVar);
        Parcel parcelTransactAndReadException = transactAndReadException(1, parcelObtainAndWriteInterfaceToken);
        zzanx zzanxVar = (zzanx) zzfp.zza(parcelTransactAndReadException, zzanx.CREATOR);
        parcelTransactAndReadException.recycle();
        return zzanxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaob
    public final void zza(zzanv zzanvVar, zzaoc zzaocVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzanvVar);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzaocVar);
        zza(2, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.ads.zzaob
    public final void zza(zzaok zzaokVar, zzaoe zzaoeVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzaokVar);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzaoeVar);
        zza(4, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.ads.zzaob
    public final void zzb(zzaok zzaokVar, zzaoe zzaoeVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzaokVar);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzaoeVar);
        zza(5, parcelObtainAndWriteInterfaceToken);
    }
}
