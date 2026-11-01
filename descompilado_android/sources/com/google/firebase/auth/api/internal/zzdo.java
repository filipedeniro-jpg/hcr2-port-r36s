package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
final class zzdo extends zzes<String, com.google.firebase.auth.internal.zza> {
    private final com.google.android.gms.internal.firebase_auth.zzbv zza;

    public zzdo(String str, @Nullable String str2) {
        super(4);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        this.zza = new com.google.android.gms.internal.firebase_auth.zzbv(str, str2);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "verifyPasswordResetCode";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzdu, String> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{com.google.android.gms.internal.firebase_auth.zze.zza}).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzdn
            private final zzdo zza;

            {
                this.zza = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                this.zza.zza((zzdu) obj, (TaskCompletionSource) obj2);
            }
        }).build();
    }

    @Override // com.google.firebase.auth.api.internal.zzes
    public final void zze() {
        if (new com.google.firebase.auth.internal.zzg(this.zzn).getOperation() != 0) {
            zza(new Status(FirebaseError.ERROR_INTERNAL_ERROR));
        } else {
            zzb(this.zzn.zzb());
        }
    }

    final /* synthetic */ void zza(zzdu zzduVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzh = new zzez(this, taskCompletionSource);
        if (this.zzu) {
            zzduVar.zza().zzi(this.zza.zza(), this.zzc);
        } else {
            zzduVar.zza().zza(this.zza, this.zzc);
        }
    }
}
