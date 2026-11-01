package com.google.firebase.auth.api.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.auth.PhoneAuthCredential;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzdz extends IInterface {
    void a_() throws RemoteException;

    void zza(Status status) throws RemoteException;

    void zza(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException;

    void zza(com.google.android.gms.internal.firebase_auth.zzeb zzebVar) throws RemoteException;

    void zza(com.google.android.gms.internal.firebase_auth.zzed zzedVar) throws RemoteException;

    void zza(com.google.android.gms.internal.firebase_auth.zzei zzeiVar) throws RemoteException;

    void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar) throws RemoteException;

    void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar, com.google.android.gms.internal.firebase_auth.zzes zzesVar) throws RemoteException;

    void zza(zzff zzffVar) throws RemoteException;

    void zza(PhoneAuthCredential phoneAuthCredential) throws RemoteException;

    void zza(String str) throws RemoteException;

    void zzb() throws RemoteException;

    void zzb(String str) throws RemoteException;

    void zzc() throws RemoteException;

    void zzc(String str) throws RemoteException;
}
