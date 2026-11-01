package com.google.android.gms.internal.drive;

import android.content.IntentSender;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzep extends zza implements zzeo {
    zzep(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.drive.internal.IDriveService");
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzek zzekVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzekVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzgq zzgqVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzgqVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(2, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzhf zzhfVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzhfVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzr zzrVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzrVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(4, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzw zzwVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzwVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzy zzyVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzyVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final zzec zza(zzgj zzgjVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzgjVar);
        zzc.zza(parcelZza, zzeqVar);
        Parcel parcelZza2 = zza(7, parcelZza);
        zzec zzecVar = (zzec) zzc.zza(parcelZza2, zzec.CREATOR);
        parcelZza2.recycle();
        return zzecVar;
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzo zzoVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzoVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(8, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzeqVar);
        zzb(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final IntentSender zza(zzgm zzgmVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzgmVar);
        Parcel parcelZza2 = zza(10, parcelZza);
        IntentSender intentSender = (IntentSender) zzc.zza(parcelZza2, IntentSender.CREATOR);
        parcelZza2.recycle();
        return intentSender;
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final IntentSender zza(zzu zzuVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzuVar);
        Parcel parcelZza2 = zza(11, parcelZza);
        IntentSender intentSender = (IntentSender) zzc.zza(parcelZza2, IntentSender.CREATOR);
        parcelZza2.recycle();
        return intentSender;
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzex zzexVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzexVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(13, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzj zzjVar, zzes zzesVar, String str, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzjVar);
        zzc.zza(parcelZza, zzesVar);
        parcelZza.writeString(null);
        zzc.zza(parcelZza, zzeqVar);
        zzb(14, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzgs zzgsVar, zzes zzesVar, String str, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzgsVar);
        zzc.zza(parcelZza, zzesVar);
        parcelZza.writeString(null);
        zzc.zza(parcelZza, zzeqVar);
        zzb(15, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzad zzadVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzadVar);
        zzb(16, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzhb zzhbVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzhbVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(17, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzm zzmVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzmVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(18, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzab zzabVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzabVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(24, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzgw zzgwVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzgwVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(28, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zzb(zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzeqVar);
        zzb(35, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzgu zzguVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzguVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(36, parcelZza);
    }

    @Override // com.google.android.gms.internal.drive.zzeo
    public final void zza(zzhd zzhdVar, zzeq zzeqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzhdVar);
        zzc.zza(parcelZza, zzeqVar);
        zzb(38, parcelZza);
    }
}
