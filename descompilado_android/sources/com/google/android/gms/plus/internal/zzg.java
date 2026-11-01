package com.google.android.gms.plus.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.ICancelToken;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzg extends com.google.android.gms.internal.plus.zza implements zzf {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.plus.internal.IPlusService");
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final String getAccountName() throws RemoteException {
        Parcel parcelTransactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken());
        String string = parcelTransactAndReadException.readString();
        parcelTransactAndReadException.recycle();
        return string;
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final ICancelToken zza(zzb zzbVar, int i, int i2, int i3, String str) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.plus.zzc.zza(parcelObtainAndWriteInterfaceToken, zzbVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i);
        parcelObtainAndWriteInterfaceToken.writeInt(i2);
        parcelObtainAndWriteInterfaceToken.writeInt(-1);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        Parcel parcelTransactAndReadException = transactAndReadException(16, parcelObtainAndWriteInterfaceToken);
        ICancelToken iCancelTokenAsInterface = ICancelToken.Stub.asInterface(parcelTransactAndReadException.readStrongBinder());
        parcelTransactAndReadException.recycle();
        return iCancelTokenAsInterface;
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final void zza() throws RemoteException {
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final void zza(zzb zzbVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.plus.zzc.zza(parcelObtainAndWriteInterfaceToken, zzbVar);
        transactAndReadExceptionReturnVoid(19, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.plus.internal.zzf
    public final void zza(zzb zzbVar, List<String> list) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.plus.zzc.zza(parcelObtainAndWriteInterfaceToken, zzbVar);
        parcelObtainAndWriteInterfaceToken.writeStringList(list);
        transactAndReadExceptionReturnVoid(34, parcelObtainAndWriteInterfaceToken);
    }
}
