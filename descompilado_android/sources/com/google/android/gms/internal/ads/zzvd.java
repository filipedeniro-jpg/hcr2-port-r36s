package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzvd extends zzfm implements zzve {
    public zzvd() {
        super("com.google.android.gms.ads.internal.client.IAdManager");
    }

    public static zzve zzc(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        if (iInterfaceQueryLocalInterface instanceof zzve) {
            return (zzve) iInterfaceQueryLocalInterface;
        }
        return new zzvg(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzur zzutVar = null;
        zzvh zzvjVar = null;
        zzvs zzvrVar = null;
        zzuq zzusVar = null;
        zzvm zzvoVar = null;
        switch (i) {
            case 1:
                IObjectWrapper iObjectWrapperZzjm = zzjm();
                parcel2.writeNoException();
                zzfp.zza(parcel2, iObjectWrapperZzjm);
                return true;
            case 2:
                destroy();
                parcel2.writeNoException();
                return true;
            case 3:
                boolean zIsReady = isReady();
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zIsReady);
                return true;
            case 4:
                boolean zZza = zza((zztp) zzfp.zza(parcel, zztp.CREATOR));
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zZza);
                return true;
            case 5:
                pause();
                parcel2.writeNoException();
                return true;
            case 6:
                resume();
                parcel2.writeNoException();
                return true;
            case 7:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    if (iInterfaceQueryLocalInterface instanceof zzur) {
                        zzutVar = (zzur) iInterfaceQueryLocalInterface;
                    } else {
                        zzutVar = new zzut(strongBinder);
                    }
                }
                zza(zzutVar);
                parcel2.writeNoException();
                return true;
            case 8:
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
                    if (iInterfaceQueryLocalInterface2 instanceof zzvm) {
                        zzvoVar = (zzvm) iInterfaceQueryLocalInterface2;
                    } else {
                        zzvoVar = new zzvo(strongBinder2);
                    }
                }
                zza(zzvoVar);
                parcel2.writeNoException();
                return true;
            case 9:
                showInterstitial();
                parcel2.writeNoException();
                return true;
            case 10:
                stopLoading();
                parcel2.writeNoException();
                return true;
            case 11:
                zzjn();
                parcel2.writeNoException();
                return true;
            case 12:
                zztw zztwVarZzjo = zzjo();
                parcel2.writeNoException();
                zzfp.zzb(parcel2, zztwVarZzjo);
                return true;
            case 13:
                zza((zztw) zzfp.zza(parcel, zztw.CREATOR));
                parcel2.writeNoException();
                return true;
            case 14:
                zza(zzanc.zzae(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 15:
                zza(zzani.zzag(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 16:
            case 17:
            case 27:
            case 28:
            default:
                return false;
            case 18:
                String mediationAdapterClassName = getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                return true;
            case 19:
                zza(zzzq.zzj(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 20:
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 != null) {
                    IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdClickListener");
                    if (iInterfaceQueryLocalInterface3 instanceof zzuq) {
                        zzusVar = (zzuq) iInterfaceQueryLocalInterface3;
                    } else {
                        zzusVar = new zzus(strongBinder3);
                    }
                }
                zza(zzusVar);
                parcel2.writeNoException();
                return true;
            case 21:
                IBinder strongBinder4 = parcel.readStrongBinder();
                if (strongBinder4 != null) {
                    IInterface iInterfaceQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    if (iInterfaceQueryLocalInterface4 instanceof zzvs) {
                        zzvrVar = (zzvs) iInterfaceQueryLocalInterface4;
                    } else {
                        zzvrVar = new zzvr(strongBinder4);
                    }
                }
                zza(zzvrVar);
                parcel2.writeNoException();
                return true;
            case 22:
                setManualImpressionsEnabled(zzfp.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 23:
                boolean zIsLoading = isLoading();
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zIsLoading);
                return true;
            case 24:
                zza(zzapr.zzai(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 25:
                setUserId(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 26:
                zzwk videoController = getVideoController();
                parcel2.writeNoException();
                zzfp.zza(parcel2, videoController);
                return true;
            case 29:
                zza((zzyc) zzfp.zza(parcel, zzyc.CREATOR));
                parcel2.writeNoException();
                return true;
            case 30:
                zza((zzwq) zzfp.zza(parcel, zzwq.CREATOR));
                parcel2.writeNoException();
                return true;
            case 31:
                String adUnitId = getAdUnitId();
                parcel2.writeNoException();
                parcel2.writeString(adUnitId);
                return true;
            case 32:
                zzvm zzvmVarZzjq = zzjq();
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzvmVarZzjq);
                return true;
            case 33:
                zzur zzurVarZzjr = zzjr();
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzurVarZzjr);
                return true;
            case 34:
                setImmersiveMode(zzfp.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 35:
                String strZzjp = zzjp();
                parcel2.writeNoException();
                parcel2.writeString(strZzjp);
                return true;
            case 36:
                IBinder strongBinder5 = parcel.readStrongBinder();
                if (strongBinder5 != null) {
                    IInterface iInterfaceQueryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdMetadataListener");
                    if (iInterfaceQueryLocalInterface5 instanceof zzvh) {
                        zzvjVar = (zzvh) iInterfaceQueryLocalInterface5;
                    } else {
                        zzvjVar = new zzvj(strongBinder5);
                    }
                }
                zza(zzvjVar);
                parcel2.writeNoException();
                return true;
            case 37:
                Bundle adMetadata = getAdMetadata();
                parcel2.writeNoException();
                zzfp.zzb(parcel2, adMetadata);
                return true;
            case 38:
                zzbm(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 39:
                zza((zztx) zzfp.zza(parcel, zztx.CREATOR));
                parcel2.writeNoException();
                return true;
            case 40:
                zza(zzqx.zzb(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
        }
    }
}
