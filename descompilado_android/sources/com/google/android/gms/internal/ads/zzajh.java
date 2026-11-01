package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzajh extends zzfm implements zzaji {
    public zzajh() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzajj zzajlVar = null;
        switch (i) {
            case 1:
                IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zztw zztwVar = (zztw) zzfp.zza(parcel, zztw.CREATOR);
                zztp zztpVar = (zztp) zzfp.zza(parcel, zztp.CREATOR);
                String string = parcel.readString();
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (iInterfaceQueryLocalInterface instanceof zzajj) {
                        zzajlVar = (zzajj) iInterfaceQueryLocalInterface;
                    } else {
                        zzajlVar = new zzajl(strongBinder);
                    }
                }
                zza(iObjectWrapperAsInterface, zztwVar, zztpVar, string, zzajlVar);
                parcel2.writeNoException();
                return true;
            case 2:
                IObjectWrapper iObjectWrapperZzrk = zzrk();
                parcel2.writeNoException();
                zzfp.zza(parcel2, iObjectWrapperZzrk);
                return true;
            case 3:
                IObjectWrapper iObjectWrapperAsInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zztp zztpVar2 = (zztp) zzfp.zza(parcel, zztp.CREATOR);
                String string2 = parcel.readString();
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (iInterfaceQueryLocalInterface2 instanceof zzajj) {
                        zzajlVar = (zzajj) iInterfaceQueryLocalInterface2;
                    } else {
                        zzajlVar = new zzajl(strongBinder2);
                    }
                }
                zza(iObjectWrapperAsInterface2, zztpVar2, string2, zzajlVar);
                parcel2.writeNoException();
                return true;
            case 4:
                showInterstitial();
                parcel2.writeNoException();
                return true;
            case 5:
                destroy();
                parcel2.writeNoException();
                return true;
            case 6:
                IObjectWrapper iObjectWrapperAsInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zztw zztwVar2 = (zztw) zzfp.zza(parcel, zztw.CREATOR);
                zztp zztpVar3 = (zztp) zzfp.zza(parcel, zztp.CREATOR);
                String string3 = parcel.readString();
                String string4 = parcel.readString();
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 != null) {
                    IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (iInterfaceQueryLocalInterface3 instanceof zzajj) {
                        zzajlVar = (zzajj) iInterfaceQueryLocalInterface3;
                    } else {
                        zzajlVar = new zzajl(strongBinder3);
                    }
                }
                zza(iObjectWrapperAsInterface3, zztwVar2, zztpVar3, string3, string4, zzajlVar);
                parcel2.writeNoException();
                return true;
            case 7:
                IObjectWrapper iObjectWrapperAsInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zztp zztpVar4 = (zztp) zzfp.zza(parcel, zztp.CREATOR);
                String string5 = parcel.readString();
                String string6 = parcel.readString();
                IBinder strongBinder4 = parcel.readStrongBinder();
                if (strongBinder4 != null) {
                    IInterface iInterfaceQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (iInterfaceQueryLocalInterface4 instanceof zzajj) {
                        zzajlVar = (zzajj) iInterfaceQueryLocalInterface4;
                    } else {
                        zzajlVar = new zzajl(strongBinder4);
                    }
                }
                zza(iObjectWrapperAsInterface4, zztpVar4, string5, string6, zzajlVar);
                parcel2.writeNoException();
                return true;
            case 8:
                pause();
                parcel2.writeNoException();
                return true;
            case 9:
                resume();
                parcel2.writeNoException();
                return true;
            case 10:
                zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zztp) zzfp.zza(parcel, zztp.CREATOR), parcel.readString(), zzapy.zzaj(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 11:
                zza((zztp) zzfp.zza(parcel, zztp.CREATOR), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 12:
                showVideo();
                parcel2.writeNoException();
                return true;
            case 13:
                boolean zIsInitialized = isInitialized();
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zIsInitialized);
                return true;
            case 14:
                IObjectWrapper iObjectWrapperAsInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zztp zztpVar5 = (zztp) zzfp.zza(parcel, zztp.CREATOR);
                String string7 = parcel.readString();
                String string8 = parcel.readString();
                IBinder strongBinder5 = parcel.readStrongBinder();
                if (strongBinder5 != null) {
                    IInterface iInterfaceQueryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (iInterfaceQueryLocalInterface5 instanceof zzajj) {
                        zzajlVar = (zzajj) iInterfaceQueryLocalInterface5;
                    } else {
                        zzajlVar = new zzajl(strongBinder5);
                    }
                }
                zza(iObjectWrapperAsInterface5, zztpVar5, string7, string8, zzajlVar, (zzaai) zzfp.zza(parcel, zzaai.CREATOR), parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            case 15:
                zzajq zzajqVarZzrl = zzrl();
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzajqVarZzrl);
                return true;
            case 16:
                zzajr zzajrVarZzrm = zzrm();
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzajrVarZzrm);
                return true;
            case 17:
                Bundle bundleZzrn = zzrn();
                parcel2.writeNoException();
                zzfp.zzb(parcel2, bundleZzrn);
                return true;
            case 18:
                Bundle interstitialAdapterInfo = getInterstitialAdapterInfo();
                parcel2.writeNoException();
                zzfp.zzb(parcel2, interstitialAdapterInfo);
                return true;
            case 19:
                Bundle bundleZzro = zzro();
                parcel2.writeNoException();
                zzfp.zzb(parcel2, bundleZzro);
                return true;
            case 20:
                zza((zztp) zzfp.zza(parcel, zztp.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 21:
                zzv(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 22:
                boolean zZzrp = zzrp();
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zZzrp);
                return true;
            case 23:
                zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzapy.zzaj(parcel.readStrongBinder()), parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            case 24:
                zzabo zzaboVarZzrq = zzrq();
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzaboVarZzrq);
                return true;
            case 25:
                setImmersiveMode(zzfp.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 26:
                zzwk videoController = getVideoController();
                parcel2.writeNoException();
                zzfp.zza(parcel2, videoController);
                return true;
            case 27:
                zzajw zzajwVarZzrr = zzrr();
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzajwVarZzrr);
                return true;
            case 28:
                IObjectWrapper iObjectWrapperAsInterface6 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zztp zztpVar6 = (zztp) zzfp.zza(parcel, zztp.CREATOR);
                String string9 = parcel.readString();
                IBinder strongBinder6 = parcel.readStrongBinder();
                if (strongBinder6 != null) {
                    IInterface iInterfaceQueryLocalInterface6 = strongBinder6.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (iInterfaceQueryLocalInterface6 instanceof zzajj) {
                        zzajlVar = (zzajj) iInterfaceQueryLocalInterface6;
                    } else {
                        zzajlVar = new zzajl(strongBinder6);
                    }
                }
                zzb(iObjectWrapperAsInterface6, zztpVar6, string9, zzajlVar);
                parcel2.writeNoException();
                return true;
            case 29:
            default:
                return false;
            case 30:
                zzw(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 31:
                zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzafc.zzw(parcel.readStrongBinder()), parcel.createTypedArrayList(zzafh.CREATOR));
                parcel2.writeNoException();
                return true;
        }
    }
}
