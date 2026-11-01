package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
final class zzdc extends zzes<AuthResult, com.google.firebase.auth.internal.zza> {
    public zzdc() {
        super(2);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "unlinkEmailCredential";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzdu, AuthResult> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{com.google.android.gms.internal.firebase_auth.zze.zza}).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzdb
            private final zzdc zza;

            {
                this.zza = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzdc zzdcVar = this.zza;
                zzdu zzduVar = (zzdu) obj;
                zzdcVar.zzh = new zzez(zzdcVar, (TaskCompletionSource) obj2);
                if (zzdcVar.zzu) {
                    zzduVar.zza().zze(zzdcVar.zze.zzf(), zzdcVar.zzc);
                } else {
                    zzduVar.zza().zza(new com.google.android.gms.internal.firebase_auth.zzdt(zzdcVar.zze.zzf()), zzdcVar.zzc);
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
