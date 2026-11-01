package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzaji extends IInterface {
    void destroy() throws RemoteException;

    Bundle getInterstitialAdapterInfo() throws RemoteException;

    zzwk getVideoController() throws RemoteException;

    boolean isInitialized() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void showVideo() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzaez zzaezVar, List<zzafh> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzapz zzapzVar, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, zzajj zzajjVar) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, zzapz zzapzVar, String str2) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, String str2, zzajj zzajjVar) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, String str2, zzajj zzajjVar, zzaai zzaaiVar, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zztw zztwVar, zztp zztpVar, String str, zzajj zzajjVar) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zztw zztwVar, zztp zztpVar, String str, String str2, zzajj zzajjVar) throws RemoteException;

    void zza(zztp zztpVar, String str) throws RemoteException;

    void zza(zztp zztpVar, String str, String str2) throws RemoteException;

    void zzb(IObjectWrapper iObjectWrapper, zztp zztpVar, String str, zzajj zzajjVar) throws RemoteException;

    IObjectWrapper zzrk() throws RemoteException;

    zzajq zzrl() throws RemoteException;

    zzajr zzrm() throws RemoteException;

    Bundle zzrn() throws RemoteException;

    Bundle zzro() throws RemoteException;

    boolean zzrp() throws RemoteException;

    zzabo zzrq() throws RemoteException;

    zzajw zzrr() throws RemoteException;

    void zzv(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzw(IObjectWrapper iObjectWrapper) throws RemoteException;
}
