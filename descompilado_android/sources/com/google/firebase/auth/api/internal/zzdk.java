package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthCredential;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
final class zzdk extends zzes<Void, com.google.firebase.auth.internal.zza> {
    private final PhoneAuthCredential zza;

    public zzdk(PhoneAuthCredential phoneAuthCredential) {
        super(2);
        this.zza = (PhoneAuthCredential) Preconditions.checkNotNull(phoneAuthCredential);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "updatePhoneNumber";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzdu, Void> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{com.google.android.gms.internal.firebase_auth.zze.zza}).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzdj
            private final zzdk zza;

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
        ((com.google.firebase.auth.internal.zza) this.zzf).zza(this.zzk, zzau.zza(this.zzd, this.zzl));
        zzb((Object) null);
    }

    final /* synthetic */ void zza(zzdu zzduVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzh = new zzez(this, taskCompletionSource);
        if (this.zzu) {
            zzduVar.zza().zza(this.zze.zzf(), this.zza, this.zzc);
        } else {
            zzduVar.zza().zza(new com.google.android.gms.internal.firebase_auth.zzcp(this.zze.zzf(), this.zza), this.zzc);
        }
    }
}
