package com.google.firebase.auth;

import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.internal.InternalTokenResult;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzm implements Runnable {
    private final /* synthetic */ InternalTokenResult zza;
    private final /* synthetic */ FirebaseAuth zzb;

    zzm(FirebaseAuth firebaseAuth, InternalTokenResult internalTokenResult) {
        this.zzb = firebaseAuth;
        this.zza = internalTokenResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Iterator it = this.zzb.zzc.iterator();
        while (it.hasNext()) {
            ((IdTokenListener) it.next()).onIdTokenChanged(this.zza);
        }
        Iterator it2 = this.zzb.zzb.iterator();
        while (it2.hasNext()) {
            ((FirebaseAuth.IdTokenListener) it2.next()).onIdTokenChanged(this.zzb);
        }
    }
}
