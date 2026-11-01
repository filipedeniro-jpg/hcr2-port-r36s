package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
final class zzbc extends zzes<AuthResult, com.google.firebase.auth.internal.zza> {

    @NonNull
    final com.google.android.gms.internal.firebase_auth.zzbz zza;

    public zzbc(String str, String str2, @Nullable String str3) {
        super(2);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        Preconditions.checkNotEmpty(str2, "password cannot be null or empty");
        this.zza = new com.google.android.gms.internal.firebase_auth.zzbz(str, str2, str3);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "createUserWithEmailAndPassword";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzdu, AuthResult> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{com.google.android.gms.internal.firebase_auth.zze.zza}).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzbb
            private final zzbc zza;

            {
                this.zza = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzbc zzbcVar = this.zza;
                zzdu zzduVar = (zzdu) obj;
                zzbcVar.zzh = new zzez(zzbcVar, (TaskCompletionSource) obj2);
                if (zzbcVar.zzu) {
                    zzduVar.zza().zzc(zzbcVar.zza.zza(), zzbcVar.zza.zzb(), zzbcVar.zzc);
                } else {
                    zzduVar.zza().zza(zzbcVar.zza, zzbcVar.zzc);
                }
            }
        }).build();
    }

    @Override // com.google.firebase.auth.api.internal.zzes
    public final void zze() {
        com.google.firebase.auth.internal.zzn zznVarZza = zzau.zza(this.zzd, this.zzl);
        ((com.google.firebase.auth.internal.zza) this.zzf).zza(this.zzk, zznVarZza);
        zzb(new com.google.firebase.auth.internal.zzh(zznVarZza));
    }
}
