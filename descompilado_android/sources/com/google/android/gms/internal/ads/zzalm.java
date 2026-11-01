package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzalm extends zzfm implements zzalj {
    public zzalm() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public static zzalj zzac(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        if (iInterfaceQueryLocalInterface instanceof zzalj) {
            return (zzalj) iInterfaceQueryLocalInterface;
        }
        return new zzall(iBinder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.google.android.gms.internal.ads.zzalm] */
    /* JADX WARN: Type inference failed for: r7v10, types: [com.google.android.gms.internal.ads.zzald] */
    /* JADX WARN: Type inference failed for: r7v3, types: [com.google.android.gms.internal.ads.zzakx] */
    /* JADX WARN: Type inference failed for: r7v6, types: [com.google.android.gms.internal.ads.zzalc] */
    /* JADX WARN: Type inference failed for: r7v8, types: [com.google.android.gms.internal.ads.zzali] */
    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzalo zzalqVar;
        zzalo zzaloVar;
        IInterface zzakzVar = null;
        if (i == 1) {
            IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            String string = parcel.readString();
            Bundle bundle = (Bundle) zzfp.zza(parcel, Bundle.CREATOR);
            Bundle bundle2 = (Bundle) zzfp.zza(parcel, Bundle.CREATOR);
            zztw zztwVar = (zztw) zzfp.zza(parcel, zztw.CREATOR);
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder == null) {
                zzaloVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
                if (iInterfaceQueryLocalInterface instanceof zzalo) {
                    zzalqVar = (zzalo) iInterfaceQueryLocalInterface;
                } else {
                    zzalqVar = new zzalq(strongBinder);
                }
                zzaloVar = zzalqVar;
            }
            zza(iObjectWrapperAsInterface, string, bundle, bundle2, zztwVar, zzaloVar);
            parcel2.writeNoException();
        } else if (i == 2) {
            zzalx zzalxVarZzsc = zzsc();
            parcel2.writeNoException();
            zzfp.zzb(parcel2, zzalxVarZzsc);
        } else if (i == 3) {
            zzalx zzalxVarZzsd = zzsd();
            parcel2.writeNoException();
            zzfp.zzb(parcel2, zzalxVarZzsd);
        } else if (i == 5) {
            zzwk videoController = getVideoController();
            parcel2.writeNoException();
            zzfp.zza(parcel2, videoController);
        } else if (i == 10) {
            zzab(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
        } else if (i == 11) {
            zza(parcel.createStringArray(), (Bundle[]) parcel.createTypedArray(Bundle.CREATOR));
            parcel2.writeNoException();
        } else {
            switch (i) {
                case 13:
                    String string2 = parcel.readString();
                    String string3 = parcel.readString();
                    zztp zztpVar = (zztp) zzfp.zza(parcel, zztp.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder2 = parcel.readStrongBinder();
                    if (strongBinder2 != null) {
                        IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
                        if (iInterfaceQueryLocalInterface2 instanceof zzakx) {
                            zzakzVar = (zzakx) iInterfaceQueryLocalInterface2;
                        } else {
                            zzakzVar = new zzakz(strongBinder2);
                        }
                    }
                    zza(string2, string3, zztpVar, iObjectWrapperAsInterface2, zzakzVar, zzajm.zzaa(parcel.readStrongBinder()), (zztw) zzfp.zza(parcel, zztw.CREATOR));
                    parcel2.writeNoException();
                    break;
                case 14:
                    String string4 = parcel.readString();
                    String string5 = parcel.readString();
                    zztp zztpVar2 = (zztp) zzfp.zza(parcel, zztp.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder3 = parcel.readStrongBinder();
                    if (strongBinder3 != null) {
                        IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback");
                        if (iInterfaceQueryLocalInterface3 instanceof zzalc) {
                            zzakzVar = (zzalc) iInterfaceQueryLocalInterface3;
                        } else {
                            zzakzVar = new zzale(strongBinder3);
                        }
                    }
                    zza(string4, string5, zztpVar2, iObjectWrapperAsInterface3, zzakzVar, zzajm.zzaa(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                case 15:
                    boolean zZzac = zzac(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    zzfp.writeBoolean(parcel2, zZzac);
                    break;
                case 16:
                    String string6 = parcel.readString();
                    String string7 = parcel.readString();
                    zztp zztpVar3 = (zztp) zzfp.zza(parcel, zztp.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder4 = parcel.readStrongBinder();
                    if (strongBinder4 != null) {
                        IInterface iInterfaceQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRewardedCallback");
                        if (iInterfaceQueryLocalInterface4 instanceof zzali) {
                            zzakzVar = (zzali) iInterfaceQueryLocalInterface4;
                        } else {
                            zzakzVar = new zzalk(strongBinder4);
                        }
                    }
                    zza(string6, string7, zztpVar3, iObjectWrapperAsInterface4, zzakzVar, zzajm.zzaa(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                case 17:
                    boolean zZzad = zzad(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    zzfp.writeBoolean(parcel2, zZzad);
                    break;
                case 18:
                    String string8 = parcel.readString();
                    String string9 = parcel.readString();
                    zztp zztpVar4 = (zztp) zzfp.zza(parcel, zztp.CREATOR);
                    IObjectWrapper iObjectWrapperAsInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    IBinder strongBinder5 = parcel.readStrongBinder();
                    if (strongBinder5 != null) {
                        IInterface iInterfaceQueryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
                        if (iInterfaceQueryLocalInterface5 instanceof zzald) {
                            zzakzVar = (zzald) iInterfaceQueryLocalInterface5;
                        } else {
                            zzakzVar = new zzalf(strongBinder5);
                        }
                    }
                    zza(string8, string9, zztpVar4, iObjectWrapperAsInterface5, zzakzVar, zzajm.zzaa(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                case 19:
                    zzdh(parcel.readString());
                    parcel2.writeNoException();
                    break;
                default:
                    return false;
            }
        }
        return true;
    }
}
