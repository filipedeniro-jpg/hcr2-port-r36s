package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzfn implements IInterface {
    private final IBinder zzaas;
    private final String zzaat;

    protected zzfn(IBinder iBinder, String str) {
        this.zzaas = iBinder;
        this.zzaat = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zzaas;
    }

    protected final Parcel obtainAndWriteInterfaceToken() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zzaat);
        return parcelObtain;
    }

    protected final Parcel transactAndReadException(int i, Parcel parcel) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                this.zzaas.transact(i, parcel, parcelObtain, 0);
                parcelObtain.readException();
                parcel.recycle();
                return parcelObtain;
            } catch (RuntimeException e) {
                parcelObtain.recycle();
                throw e;
            }
        } catch (Throwable th) {
            parcel.recycle();
            throw th;
        }
    }

    protected final void zza(int i, Parcel parcel) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zzaas.transact(i, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }

    protected final void zzb(int i, Parcel parcel) throws RemoteException {
        try {
            this.zzaas.transact(i, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
