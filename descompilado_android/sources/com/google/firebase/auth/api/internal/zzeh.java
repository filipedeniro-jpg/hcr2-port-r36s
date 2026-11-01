package com.google.firebase.auth.api.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzeh {
    private static final Api.ClientKey<zzdu> zzb = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zzdu, zzej> zzc = new zzeg();
    public static final Api<zzej> zza = new Api<>("InternalFirebaseAuth.FIREBASE_AUTH_API", zzc, zzb);

    public static zzau zza(Context context, zzej zzejVar) {
        return new zzau(context, zzejVar);
    }
}
