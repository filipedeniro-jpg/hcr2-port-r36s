package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzajj extends IInterface {
    void onAdClicked() throws RemoteException;

    void onAdClosed() throws RemoteException;

    void onAdFailedToLoad(int i) throws RemoteException;

    void onAdImpression() throws RemoteException;

    void onAdLeftApplication() throws RemoteException;

    void onAdLoaded() throws RemoteException;

    void onAdOpened() throws RemoteException;

    void onAppEvent(String str, String str2) throws RemoteException;

    void onVideoEnd() throws RemoteException;

    void onVideoPause() throws RemoteException;

    void onVideoPlay() throws RemoteException;

    void zza(zzabo zzaboVar, String str) throws RemoteException;

    void zza(zzajo zzajoVar) throws RemoteException;

    void zza(zzaqf zzaqfVar) throws RemoteException;

    void zzb(Bundle bundle) throws RemoteException;

    void zzb(zzaqd zzaqdVar) throws RemoteException;

    void zzcm(int i) throws RemoteException;

    void zzde(String str) throws RemoteException;

    void zzrs() throws RemoteException;

    void zzrt() throws RemoteException;
}
