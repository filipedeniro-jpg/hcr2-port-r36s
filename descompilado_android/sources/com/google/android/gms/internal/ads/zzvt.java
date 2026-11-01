package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzvt extends zzfm implements zzvu {
    public zzvt() {
        super("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                initialize();
                parcel2.writeNoException();
                return true;
            case 2:
                setAppVolume(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 3:
                zzby(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 4:
                setAppMuted(zzfp.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 5:
                zzc(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                zzb(parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 7:
                float fZzoo = zzoo();
                parcel2.writeNoException();
                parcel2.writeFloat(fZzoo);
                return true;
            case 8:
                boolean zZzop = zzop();
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zZzop);
                return true;
            case 9:
                String versionString = getVersionString();
                parcel2.writeNoException();
                parcel2.writeString(versionString);
                return true;
            case 10:
                zzbz(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 11:
                zza(zzajg.zzz(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 12:
                zza(zzafd.zzx(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 13:
                List<zzaex> listZzoq = zzoq();
                parcel2.writeNoException();
                parcel2.writeTypedList(listZzoq);
                return true;
            case 14:
                zza((zzxw) zzfp.zza(parcel, zzxw.CREATOR));
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
