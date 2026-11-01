package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthProvider;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzey implements zzfa {
    private final /* synthetic */ Status zza;

    zzey(zzeu zzeuVar, Status status) {
        this.zza = status;
    }

    @Override // com.google.firebase.auth.api.internal.zzfa
    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationFailed(zzdw.zza(this.zza));
    }
}
