package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzvu extends IInterface {
    String getVersionString() throws RemoteException;

    void initialize() throws RemoteException;

    void setAppMuted(boolean z) throws RemoteException;

    void setAppVolume(float f) throws RemoteException;

    void zza(zzafe zzafeVar) throws RemoteException;

    void zza(zzajd zzajdVar) throws RemoteException;

    void zza(zzxw zzxwVar) throws RemoteException;

    void zzb(String str, IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzby(String str) throws RemoteException;

    void zzbz(String str) throws RemoteException;

    void zzc(IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    float zzoo() throws RemoteException;

    boolean zzop() throws RemoteException;

    List<zzaex> zzoq() throws RemoteException;
}
