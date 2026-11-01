package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzaqg extends IInterface {
    Bundle getAdMetadata() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    boolean isLoaded() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException;

    void zza(zzaql zzaqlVar) throws RemoteException;

    void zza(zzaqt zzaqtVar) throws RemoteException;

    void zza(zzarb zzarbVar) throws RemoteException;

    void zza(zztp zztpVar, zzaqo zzaqoVar) throws RemoteException;

    void zza(zzwf zzwfVar) throws RemoteException;

    void zzl(IObjectWrapper iObjectWrapper) throws RemoteException;

    zzaqf zzpg() throws RemoteException;
}
