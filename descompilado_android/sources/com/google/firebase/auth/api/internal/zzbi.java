package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.GetTokenResult;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
final class zzbi extends zzes<GetTokenResult, com.google.firebase.auth.internal.zza> {
    private final com.google.android.gms.internal.firebase_auth.zzch zza;

    public zzbi(String str) {
        super(1);
        Preconditions.checkNotEmpty(str, "refresh token cannot be null");
        this.zza = new com.google.android.gms.internal.firebase_auth.zzch(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "getAccessToken";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzdu, GetTokenResult> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{com.google.android.gms.internal.firebase_auth.zze.zza}).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzbh
            private final zzbi zza;

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
        if (TextUtils.isEmpty(this.zzk.zzc())) {
            this.zzk.zza(this.zza.zza());
        }
        ((com.google.firebase.auth.internal.zza) this.zzf).zza(this.zzk, this.zze);
        zzb(com.google.firebase.auth.internal.zzap.zza(this.zzk.zzd()));
    }

    final /* synthetic */ void zza(zzdu zzduVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzh = new zzez(this, taskCompletionSource);
        if (this.zzu) {
            zzduVar.zza().zza(this.zza.zza(), this.zzc);
        } else {
            zzduVar.zza().zza(this.zza, this.zzc);
        }
    }
}
