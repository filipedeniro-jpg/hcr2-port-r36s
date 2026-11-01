package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
final class zzbe extends zzes<Void, com.google.firebase.auth.internal.zzab> {
    public zzbe() {
        super(5);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "delete";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzdu, Void> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{com.google.android.gms.internal.firebase_auth.zze.zza}).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzbd
            private final zzbe zza;

            {
                this.zza = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                zzbe zzbeVar = this.zza;
                zzdu zzduVar = (zzdu) obj;
                zzbeVar.zzh = new zzez(zzbeVar, (TaskCompletionSource) obj2);
                if (zzbeVar.zzu) {
                    zzduVar.zza().zzg(zzbeVar.zze.zzf(), zzbeVar.zzc);
                } else {
                    zzduVar.zza().zza(new com.google.android.gms.internal.firebase_auth.zzcb(zzbeVar.zze.zzf()), zzbeVar.zzc);
                }
            }
        }).build();
    }

    @Override // com.google.firebase.auth.api.internal.zzes
    public final void zze() {
        ((com.google.firebase.auth.internal.zzab) this.zzf).zza();
        zzb((Object) null);
    }
}
