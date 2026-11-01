package com.google.firebase.auth.api.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.auth.PhoneAuthCredential;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzec extends com.google.android.gms.internal.firebase_auth.zza implements zzdz {
    public zzec() {
        super("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    @Override // com.google.android.gms.internal.firebase_auth.zza
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((com.google.android.gms.internal.firebase_auth.zzey) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzey.CREATOR));
                return true;
            case 2:
                zza((com.google.android.gms.internal.firebase_auth.zzey) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzey.CREATOR), (com.google.android.gms.internal.firebase_auth.zzes) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzes.CREATOR));
                return true;
            case 3:
                zza((com.google.android.gms.internal.firebase_auth.zzei) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzei.CREATOR));
                return true;
            case 4:
                zza((zzff) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, zzff.CREATOR));
                return true;
            case 5:
                zza((Status) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, Status.CREATOR));
                return true;
            case 6:
                a_();
                return true;
            case 7:
                zzb();
                return true;
            case 8:
                zza(parcel.readString());
                return true;
            case 9:
                zzb(parcel.readString());
                return true;
            case 10:
                zza((PhoneAuthCredential) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, PhoneAuthCredential.CREATOR));
                return true;
            case 11:
                zzc(parcel.readString());
                return true;
            case 12:
                zza((Status) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, Status.CREATOR), (PhoneAuthCredential) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, PhoneAuthCredential.CREATOR));
                return true;
            case 13:
                zzc();
                return true;
            case 14:
                zza((com.google.android.gms.internal.firebase_auth.zzeb) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzeb.CREATOR));
                return true;
            case 15:
                zza((com.google.android.gms.internal.firebase_auth.zzed) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzed.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
