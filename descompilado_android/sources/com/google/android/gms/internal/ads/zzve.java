package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public interface zzve extends IInterface {
    void destroy() throws RemoteException;

    Bundle getAdMetadata() throws RemoteException;

    String getAdUnitId() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    zzwk getVideoController() throws RemoteException;

    boolean isLoading() throws RemoteException;

    boolean isReady() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void setManualImpressionsEnabled(boolean z) throws RemoteException;

    void setUserId(String str) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void stopLoading() throws RemoteException;

    void zza(zzand zzandVar) throws RemoteException;

    void zza(zzanj zzanjVar, String str) throws RemoteException;

    void zza(zzapo zzapoVar) throws RemoteException;

    void zza(zzqu zzquVar) throws RemoteException;

    void zza(zztw zztwVar) throws RemoteException;

    void zza(zztx zztxVar) throws RemoteException;

    void zza(zzuq zzuqVar) throws RemoteException;

    void zza(zzur zzurVar) throws RemoteException;

    void zza(zzvh zzvhVar) throws RemoteException;

    void zza(zzvm zzvmVar) throws RemoteException;

    void zza(zzvs zzvsVar) throws RemoteException;

    void zza(zzwq zzwqVar) throws RemoteException;

    void zza(zzyc zzycVar) throws RemoteException;

    void zza(zzzn zzznVar) throws RemoteException;

    boolean zza(zztp zztpVar) throws RemoteException;

    void zzbm(String str) throws RemoteException;

    IObjectWrapper zzjm() throws RemoteException;

    void zzjn() throws RemoteException;

    zztw zzjo() throws RemoteException;

    String zzjp() throws RemoteException;

    zzvm zzjq() throws RemoteException;

    zzur zzjr() throws RemoteException;
}
