package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.InitializationCompleteCallback;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzakb implements InitializationCompleteCallback {
    private final /* synthetic */ zzaez zzddl;

    zzakb(zzajz zzajzVar, zzaez zzaezVar) {
        this.zzddl = zzaezVar;
    }

    @Override // com.google.android.gms.ads.mediation.InitializationCompleteCallback
    public final void onInitializationSucceeded() {
        try {
            this.zzddl.onInitializationSucceeded();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.InitializationCompleteCallback
    public final void onInitializationFailed(String str) {
        try {
            this.zzddl.onInitializationFailed(str);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
