package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzva extends zzfm implements zzux {
    public zzva() {
        super("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzur zzutVar = null;
        zzvs zzvrVar = null;
        switch (i) {
            case 1:
                zzuw zzuwVarZzon = zzon();
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzuwVarZzon);
                return true;
            case 2:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    if (iInterfaceQueryLocalInterface instanceof zzur) {
                        zzutVar = (zzur) iInterfaceQueryLocalInterface;
                    } else {
                        zzutVar = new zzut(strongBinder);
                    }
                }
                zzb(zzutVar);
                parcel2.writeNoException();
                return true;
            case 3:
                zza(zzabr.zzp(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 4:
                zza(zzabw.zzq(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 5:
                zza(parcel.readString(), zzacc.zzs(parcel.readStrongBinder()), zzabx.zzr(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 6:
                zza((zzaai) zzfp.zza(parcel, zzaai.CREATOR));
                parcel2.writeNoException();
                return true;
            case 7:
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    if (iInterfaceQueryLocalInterface2 instanceof zzvs) {
                        zzvrVar = (zzvs) iInterfaceQueryLocalInterface2;
                    } else {
                        zzvrVar = new zzvr(strongBinder2);
                    }
                }
                zzb(zzvrVar);
                parcel2.writeNoException();
                return true;
            case 8:
                zza(zzacd.zzt(parcel.readStrongBinder()), (zztw) zzfp.zza(parcel, zztw.CREATOR));
                parcel2.writeNoException();
                return true;
            case 9:
                zza((PublisherAdViewOptions) zzfp.zza(parcel, PublisherAdViewOptions.CREATOR));
                parcel2.writeNoException();
                return true;
            case 10:
                zza(zzaci.zzu(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 11:
            case 12:
            default:
                return false;
            case 13:
                zza((zzafj) zzfp.zza(parcel, zzafj.CREATOR));
                parcel2.writeNoException();
                return true;
            case 14:
                zza(zzafs.zzy(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
        }
    }
}
