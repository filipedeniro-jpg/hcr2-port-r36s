package com.google.firebase.auth.internal;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzan implements Runnable {
    private final /* synthetic */ FederatedSignInActivity zza;

    zzan(FederatedSignInActivity federatedSignInActivity) {
        this.zza = federatedSignInActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza();
        FederatedSignInActivity.zza((Runnable) null);
    }
}
