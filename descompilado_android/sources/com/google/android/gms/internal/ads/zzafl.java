package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzafl extends IInterface {
    void destroy() throws RemoteException;

    zzwk getVideoController() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzafn zzafnVar) throws RemoteException;
}
