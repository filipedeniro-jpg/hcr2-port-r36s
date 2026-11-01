package com.google.firebase.auth.api.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.internal.firebase_auth.zzdv;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzef extends com.google.android.gms.internal.firebase_auth.zzb implements zzee {
    zzef(IBinder iBinder) {
        super(iBinder, "com.google.firebase.auth.api.internal.IFirebaseAuthService");
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(String str, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(1, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzb(String str, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(2, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(zzfr zzfrVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzfrVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(3, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, userProfileChangeRequest);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(4, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(String str, String str2, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(5, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzb(String str, String str2, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(6, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzc(String str, String str2, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(7, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzd(String str, String str2, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(8, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzc(String str, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(9, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzd(String str, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(10, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(String str, String str2, String str3, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        parcelZza.writeString(str3);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(11, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(String str, zzfr zzfrVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzfrVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(12, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zze(String str, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(13, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zze(String str, String str2, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(14, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzf(String str, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(15, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(16, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzg(String str, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(17, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzh(String str, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(18, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzi(String str, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(19, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzj(String str, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(20, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzf(String str, String str2, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(21, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzfk zzfkVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzfkVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(22, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(PhoneAuthCredential phoneAuthCredential, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, phoneAuthCredential);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(23, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(String str, PhoneAuthCredential phoneAuthCredential, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, phoneAuthCredential);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(24, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(String str, ActionCodeSettings actionCodeSettings, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, actionCodeSettings);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(25, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzb(String str, ActionCodeSettings actionCodeSettings, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, actionCodeSettings);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(26, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzk(String str, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(27, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zzc(String str, ActionCodeSettings actionCodeSettings, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, actionCodeSettings);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(28, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(EmailAuthCredential emailAuthCredential, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, emailAuthCredential);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(29, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzch zzchVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzchVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(101, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdf zzdfVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdfVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(102, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdd zzddVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzddVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(103, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdx zzdxVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdxVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(104, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzbr zzbrVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzbrVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(105, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzbt zzbtVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzbtVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(106, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzbz zzbzVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzbzVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(107, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdh zzdhVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdhVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(108, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzcj zzcjVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzcjVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(109, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzcl zzclVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzclVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(111, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzcn zzcnVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzcnVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(112, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdt zzdtVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdtVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(113, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(zzdv zzdvVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdvVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(114, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzcr zzcrVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzcrVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(115, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdb zzdbVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdbVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(116, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzcb zzcbVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzcbVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(117, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzbv zzbvVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzbvVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(119, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzbp zzbpVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzbpVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(120, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzbx zzbxVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzbxVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(121, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzcx zzcxVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzcxVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(122, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdl zzdlVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdlVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(123, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzcp zzcpVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzcpVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzct zzctVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzctVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(126, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzcz zzczVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzczVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(Notifications.NOTIFICATION_TYPES_ALL, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzcv zzcvVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzcvVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(128, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdj zzdjVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdjVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(TsExtractor.TS_STREAM_TYPE_AC3, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdn zzdnVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdnVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(TsExtractor.TS_STREAM_TYPE_HDMV_DTS, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdr zzdrVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdrVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(131, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzcd zzcdVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzcdVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(132, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdp zzdpVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdpVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(133, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzcf zzcfVar, zzdz zzdzVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzcfVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        zza(TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, parcelZza);
    }

    @Override // com.google.firebase.auth.api.internal.zzee
    public final void zza(com.google.android.gms.internal.firebase_auth.zzdz zzdzVar, zzdz zzdzVar2) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar);
        com.google.android.gms.internal.firebase_auth.zzd.zza(parcelZza, zzdzVar2);
        zza(TsExtractor.TS_STREAM_TYPE_E_AC3, parcelZza);
    }
}
