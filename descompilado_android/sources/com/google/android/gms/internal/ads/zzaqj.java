package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaqj extends zzfm implements zzaqg {
    public zzaqj() {
        super("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
    }

    public static zzaqg zzal(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
        if (iInterfaceQueryLocalInterface instanceof zzaqg) {
            return (zzaqg) iInterfaceQueryLocalInterface;
        }
        return new zzaqi(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzaqo zzaqqVar = null;
        zzaqt zzaqsVar = null;
        zzaql zzaqnVar = null;
        switch (i) {
            case 1:
                zztp zztpVar = (zztp) zzfp.zza(parcel, zztp.CREATOR);
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdLoadCallback");
                    if (iInterfaceQueryLocalInterface instanceof zzaqo) {
                        zzaqqVar = (zzaqo) iInterfaceQueryLocalInterface;
                    } else {
                        zzaqqVar = new zzaqq(strongBinder);
                    }
                }
                zza(zztpVar, zzaqqVar);
                parcel2.writeNoException();
                return true;
            case 2:
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCallback");
                    if (iInterfaceQueryLocalInterface2 instanceof zzaql) {
                        zzaqnVar = (zzaql) iInterfaceQueryLocalInterface2;
                    } else {
                        zzaqnVar = new zzaqn(strongBinder2);
                    }
                }
                zza(zzaqnVar);
                parcel2.writeNoException();
                return true;
            case 3:
                boolean zIsLoaded = isLoaded();
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zIsLoaded);
                return true;
            case 4:
                String mediationAdapterClassName = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                return true;
            case 5:
                zzl(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 6:
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 != null) {
                    IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdSkuListener");
                    if (iInterfaceQueryLocalInterface3 instanceof zzaqt) {
                        zzaqsVar = (zzaqt) iInterfaceQueryLocalInterface3;
                    } else {
                        zzaqsVar = new zzaqs(strongBinder3);
                    }
                }
                zza(zzaqsVar);
                parcel2.writeNoException();
                return true;
            case 7:
                zza((zzarb) zzfp.zza(parcel, zzarb.CREATOR));
                parcel2.writeNoException();
                return true;
            case 8:
                zza(zzwi.zzh(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 9:
                Bundle adMetadata = getAdMetadata();
                parcel2.writeNoException();
                zzfp.zzb(parcel2, adMetadata);
                return true;
            case 10:
                zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzfp.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 11:
                zzaqf zzaqfVarZzpg = zzpg();
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzaqfVarZzpg);
                return true;
            default:
                return false;
        }
    }
}
