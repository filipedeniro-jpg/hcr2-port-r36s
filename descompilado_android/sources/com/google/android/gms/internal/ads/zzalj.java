package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzalj extends IInterface {
    zzwk getVideoController() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, zztw zztwVar, zzalo zzaloVar) throws RemoteException;

    void zza(String str, String str2, zztp zztpVar, IObjectWrapper iObjectWrapper, zzakx zzakxVar, zzajj zzajjVar, zztw zztwVar) throws RemoteException;

    void zza(String str, String str2, zztp zztpVar, IObjectWrapper iObjectWrapper, zzalc zzalcVar, zzajj zzajjVar) throws RemoteException;

    void zza(String str, String str2, zztp zztpVar, IObjectWrapper iObjectWrapper, zzald zzaldVar, zzajj zzajjVar) throws RemoteException;

    void zza(String str, String str2, zztp zztpVar, IObjectWrapper iObjectWrapper, zzali zzaliVar, zzajj zzajjVar) throws RemoteException;

    void zza(String[] strArr, Bundle[] bundleArr) throws RemoteException;

    void zzab(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzac(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzad(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzdh(String str) throws RemoteException;

    zzalx zzsc() throws RemoteException;

    zzalx zzsd() throws RemoteException;
}
