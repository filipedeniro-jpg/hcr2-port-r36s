package com.google.firebase.auth.api.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.auth.PhoneAuthCredential;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzeb extends com.google.android.gms.internal.firebase_auth.zzb implements zzdz {
    zzeb(IBinder iBinder) {
        super(iBinder, "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzeyVar);
        zzb(1, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar, com.google.android.gms.internal.firebase_auth.zzes zzesVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzeyVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzesVar);
        zzb(2, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(com.google.android.gms.internal.firebase_auth.zzei zzeiVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzeiVar);
        zzb(3, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(zzff zzffVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzffVar);
        zzb(4, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(Status status) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, status);
        zzb(5, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void a_() throws RemoteException {
        zzb(6, zza());
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zzb() throws RemoteException {
        zzb(7, zza());
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzb(8, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zzb(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzb(9, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, phoneAuthCredential);
        zzb(10, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zzc(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzb(11, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, status);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, phoneAuthCredential);
        zzb(12, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zzc() throws RemoteException {
        zzb(13, zza());
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(com.google.android.gms.internal.firebase_auth.zzeb zzebVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzebVar);
        zzb(14, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(com.google.android.gms.internal.firebase_auth.zzed zzedVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzedVar);
        zzb(15, parcelZza);
    }
}
