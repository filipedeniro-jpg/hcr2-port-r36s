package com.google.firebase.auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzey;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzo implements com.google.firebase.auth.internal.zza, com.google.firebase.auth.internal.zzae {
    private final /* synthetic */ FirebaseAuth zza;

    zzo(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    @Override // com.google.firebase.auth.internal.zza
    public final void zza(@NonNull zzey zzeyVar, @NonNull FirebaseUser firebaseUser) {
        this.zza.zza(firebaseUser, zzeyVar, true);
    }

    @Override // com.google.firebase.auth.internal.zzae
    public final void zza(Status status) {
        int statusCode = status.getStatusCode();
        if (statusCode == 17011 || statusCode == 17021 || statusCode == 17005) {
            this.zza.signOut();
        }
    }
}
