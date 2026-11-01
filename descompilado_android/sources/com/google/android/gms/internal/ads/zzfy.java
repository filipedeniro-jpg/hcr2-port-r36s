package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzfy extends IInterface {
    void zza(IObjectWrapper iObjectWrapper, String str, String str2) throws RemoteException;

    void zza(int[] iArr) throws RemoteException;

    void zzb(IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    void zzc(byte[] bArr) throws RemoteException;

    void zzdf() throws RemoteException;

    void zzl(int i) throws RemoteException;

    void zzm(int i) throws RemoteException;
}
