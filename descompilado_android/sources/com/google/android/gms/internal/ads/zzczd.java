package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzczd extends zzfn implements zzcze {
    zzczd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gass.internal.IGassService");
    }

    @Override // com.google.android.gms.internal.ads.zzcze
    public final zzczc zza(zzcza zzczaVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzczaVar);
        Parcel parcelTransactAndReadException = transactAndReadException(1, parcelObtainAndWriteInterfaceToken);
        zzczc zzczcVar = (zzczc) zzfp.zza(parcelTransactAndReadException, zzczc.CREATOR);
        parcelTransactAndReadException.recycle();
        return zzczcVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcze
    public final void zza(zzcyv zzcyvVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzcyvVar);
        zza(2, parcelObtainAndWriteInterfaceToken);
    }
}
