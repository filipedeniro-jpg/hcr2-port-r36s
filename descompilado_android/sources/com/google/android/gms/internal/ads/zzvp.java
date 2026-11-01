package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzvp extends zzfn implements zzvn {
    zzvp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IClientApi");
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzve zza(IObjectWrapper iObjectWrapper, zztw zztwVar, String str, zzajd zzajdVar, int i) throws RemoteException {
        zzve zzvgVar;
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zztwVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzajdVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i);
        Parcel parcelTransactAndReadException = transactAndReadException(1, parcelObtainAndWriteInterfaceToken);
        IBinder strongBinder = parcelTransactAndReadException.readStrongBinder();
        if (strongBinder == null) {
            zzvgVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (iInterfaceQueryLocalInterface instanceof zzve) {
                zzvgVar = (zzve) iInterfaceQueryLocalInterface;
            } else {
                zzvgVar = new zzvg(strongBinder);
            }
        }
        parcelTransactAndReadException.recycle();
        return zzvgVar;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzve zzb(IObjectWrapper iObjectWrapper, zztw zztwVar, String str, zzajd zzajdVar, int i) throws RemoteException {
        zzve zzvgVar;
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zztwVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzajdVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i);
        Parcel parcelTransactAndReadException = transactAndReadException(2, parcelObtainAndWriteInterfaceToken);
        IBinder strongBinder = parcelTransactAndReadException.readStrongBinder();
        if (strongBinder == null) {
            zzvgVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (iInterfaceQueryLocalInterface instanceof zzve) {
                zzvgVar = (zzve) iInterfaceQueryLocalInterface;
            } else {
                zzvgVar = new zzvg(strongBinder);
            }
        }
        parcelTransactAndReadException.recycle();
        return zzvgVar;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzux zza(IObjectWrapper iObjectWrapper, String str, zzajd zzajdVar, int i) throws RemoteException {
        zzux zzuzVar;
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzajdVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i);
        Parcel parcelTransactAndReadException = transactAndReadException(3, parcelObtainAndWriteInterfaceToken);
        IBinder strongBinder = parcelTransactAndReadException.readStrongBinder();
        if (strongBinder == null) {
            zzuzVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            if (iInterfaceQueryLocalInterface instanceof zzux) {
                zzuzVar = (zzux) iInterfaceQueryLocalInterface;
            } else {
                zzuzVar = new zzuz(strongBinder);
            }
        }
        parcelTransactAndReadException.recycle();
        return zzuzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzvu zzg(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzvu zzvwVar;
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        Parcel parcelTransactAndReadException = transactAndReadException(4, parcelObtainAndWriteInterfaceToken);
        IBinder strongBinder = parcelTransactAndReadException.readStrongBinder();
        if (strongBinder == null) {
            zzvwVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            if (iInterfaceQueryLocalInterface instanceof zzvu) {
                zzvwVar = (zzvu) iInterfaceQueryLocalInterface;
            } else {
                zzvwVar = new zzvw(strongBinder);
            }
        }
        parcelTransactAndReadException.recycle();
        return zzvwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzaaw zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper2);
        Parcel parcelTransactAndReadException = transactAndReadException(5, parcelObtainAndWriteInterfaceToken);
        zzaaw zzaawVarZzm = zzaav.zzm(parcelTransactAndReadException.readStrongBinder());
        parcelTransactAndReadException.recycle();
        return zzaawVarZzm;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzapl zza(IObjectWrapper iObjectWrapper, zzajd zzajdVar, int i) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzajdVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i);
        Parcel parcelTransactAndReadException = transactAndReadException(6, parcelObtainAndWriteInterfaceToken);
        zzapl zzaplVarZzah = zzapk.zzah(parcelTransactAndReadException.readStrongBinder());
        parcelTransactAndReadException.recycle();
        return zzaplVarZzah;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzane zzh(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        Parcel parcelTransactAndReadException = transactAndReadException(7, parcelObtainAndWriteInterfaceToken);
        zzane zzaneVarZzaf = zzanh.zzaf(parcelTransactAndReadException.readStrongBinder());
        parcelTransactAndReadException.recycle();
        return zzaneVarZzaf;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzamu zzf(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        Parcel parcelTransactAndReadException = transactAndReadException(8, parcelObtainAndWriteInterfaceToken);
        zzamu zzamuVarZzad = zzamx.zzad(parcelTransactAndReadException.readStrongBinder());
        parcelTransactAndReadException.recycle();
        return zzamuVarZzad;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzvu zza(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        zzvu zzvwVar;
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        parcelObtainAndWriteInterfaceToken.writeInt(i);
        Parcel parcelTransactAndReadException = transactAndReadException(9, parcelObtainAndWriteInterfaceToken);
        IBinder strongBinder = parcelTransactAndReadException.readStrongBinder();
        if (strongBinder == null) {
            zzvwVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            if (iInterfaceQueryLocalInterface instanceof zzvu) {
                zzvwVar = (zzvu) iInterfaceQueryLocalInterface;
            } else {
                zzvwVar = new zzvw(strongBinder);
            }
        }
        parcelTransactAndReadException.recycle();
        return zzvwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzve zza(IObjectWrapper iObjectWrapper, zztw zztwVar, String str, int i) throws RemoteException {
        zzve zzvgVar;
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zztwVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        parcelObtainAndWriteInterfaceToken.writeInt(i);
        Parcel parcelTransactAndReadException = transactAndReadException(10, parcelObtainAndWriteInterfaceToken);
        IBinder strongBinder = parcelTransactAndReadException.readStrongBinder();
        if (strongBinder == null) {
            zzvgVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (iInterfaceQueryLocalInterface instanceof zzve) {
                zzvgVar = (zzve) iInterfaceQueryLocalInterface;
            } else {
                zzvgVar = new zzvg(strongBinder);
            }
        }
        parcelTransactAndReadException.recycle();
        return zzvgVar;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzaaz zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper2);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper3);
        Parcel parcelTransactAndReadException = transactAndReadException(11, parcelObtainAndWriteInterfaceToken);
        zzaaz zzaazVarZzn = zzabc.zzn(parcelTransactAndReadException.readStrongBinder());
        parcelTransactAndReadException.recycle();
        return zzaazVarZzn;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzaqg zzb(IObjectWrapper iObjectWrapper, String str, zzajd zzajdVar, int i) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzajdVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i);
        Parcel parcelTransactAndReadException = transactAndReadException(12, parcelObtainAndWriteInterfaceToken);
        zzaqg zzaqgVarZzal = zzaqj.zzal(parcelTransactAndReadException.readStrongBinder());
        parcelTransactAndReadException.recycle();
        return zzaqgVarZzal;
    }

    @Override // com.google.android.gms.internal.ads.zzvn
    public final zzve zzc(IObjectWrapper iObjectWrapper, zztw zztwVar, String str, zzajd zzajdVar, int i) throws RemoteException {
        zzve zzvgVar;
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzfp.zza(parcelObtainAndWriteInterfaceToken, iObjectWrapper);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zztwVar);
        parcelObtainAndWriteInterfaceToken.writeString(str);
        zzfp.zza(parcelObtainAndWriteInterfaceToken, zzajdVar);
        parcelObtainAndWriteInterfaceToken.writeInt(i);
        Parcel parcelTransactAndReadException = transactAndReadException(13, parcelObtainAndWriteInterfaceToken);
        IBinder strongBinder = parcelTransactAndReadException.readStrongBinder();
        if (strongBinder == null) {
            zzvgVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (iInterfaceQueryLocalInterface instanceof zzve) {
                zzvgVar = (zzve) iInterfaceQueryLocalInterface;
            } else {
                zzvgVar = new zzvg(strongBinder);
            }
        }
        parcelTransactAndReadException.recycle();
        return zzvgVar;
    }
}
