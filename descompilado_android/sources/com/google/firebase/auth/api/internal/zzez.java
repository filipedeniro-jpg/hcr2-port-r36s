package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzez<ResultT, CallbackT> implements zzeq<ResultT> {
    private final zzes<ResultT, CallbackT> zza;
    private final TaskCompletionSource<ResultT> zzb;

    public zzez(zzes<ResultT, CallbackT> zzesVar, TaskCompletionSource<ResultT> taskCompletionSource) {
        this.zza = zzesVar;
        this.zzb = taskCompletionSource;
    }

    @Override // com.google.firebase.auth.api.internal.zzeq
    public final void zza(ResultT resultt, Status status) {
        Preconditions.checkNotNull(this.zzb, "completion source cannot be null");
        if (status != null) {
            if (this.zza.zzt != null) {
                this.zzb.setException(zzdw.zza(FirebaseAuth.getInstance(this.zza.zzd), this.zza.zzt, ("reauthenticateWithCredential".equals(this.zza.zza()) || "reauthenticateWithCredentialWithData".equals(this.zza.zza())) ? this.zza.zze : null));
                return;
            } else if (this.zza.zzq != null) {
                this.zzb.setException(zzdw.zza(status, this.zza.zzq, this.zza.zzr, this.zza.zzs));
                return;
            } else {
                this.zzb.setException(zzdw.zza(status));
                return;
            }
        }
        this.zzb.setResult(resultt);
    }
}
