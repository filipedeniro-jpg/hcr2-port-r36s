package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzvq extends zzfm implements zzvn {
    public zzvq() {
        super("com.google.android.gms.ads.internal.client.IClientApi");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzve zzveVarZza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zztw) zzfp.zza(parcel, zztw.CREATOR), parcel.readString(), zzajg.zzz(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzveVarZza);
                return true;
            case 2:
                zzve zzveVarZzb = zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zztw) zzfp.zza(parcel, zztw.CREATOR), parcel.readString(), zzajg.zzz(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzveVarZzb);
                return true;
            case 3:
                zzux zzuxVarZza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), zzajg.zzz(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzuxVarZza);
                return true;
            case 4:
                zzvu zzvuVarZzg = zzg(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzvuVarZzg);
                return true;
            case 5:
                zzaaw zzaawVarZzc = zzc(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzaawVarZzc);
                return true;
            case 6:
                zzapl zzaplVarZza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzajg.zzz(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzaplVarZza);
                return true;
            case 7:
                zzane zzaneVarZzh = zzh(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzaneVarZzh);
                return true;
            case 8:
                zzamu zzamuVarZzf = zzf(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzamuVarZzf);
                return true;
            case 9:
                zzvu zzvuVarZza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzvuVarZza);
                return true;
            case 10:
                zzve zzveVarZza2 = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zztw) zzfp.zza(parcel, zztw.CREATOR), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzveVarZza2);
                return true;
            case 11:
                zzaaz zzaazVarZza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzaazVarZza);
                return true;
            case 12:
                zzaqg zzaqgVarZzb = zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), zzajg.zzz(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzaqgVarZzb);
                return true;
            case 13:
                zzve zzveVarZzc = zzc(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zztw) zzfp.zza(parcel, zztw.CREATOR), parcel.readString(), zzajg.zzz(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzveVarZzc);
                return true;
            default:
                return false;
        }
    }
}
