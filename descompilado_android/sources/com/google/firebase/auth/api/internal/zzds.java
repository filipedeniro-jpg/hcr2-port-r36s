package com.google.firebase.auth.api.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.firebase.FirebaseExceptionMapper;
import java.util.Collections;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzds implements Callable<zzal<zzej>> {
    private final zzej zza;
    private final Context zzb;

    public zzds(zzej zzejVar, Context context) {
        this.zza = zzejVar;
        this.zzb = context;
    }

    @NonNull
    private final GoogleApi<zzej> zza(boolean z, Context context) {
        zzej zzejVar = (zzej) this.zza.clone();
        zzejVar.zza = z;
        return new zzaq(context, zzeh.zza, zzejVar, new FirebaseExceptionMapper());
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ zzal<zzej> call() throws Exception {
        int localVersion = DynamiteModule.getLocalVersion(this.zzb, "com.google.firebase.auth");
        int remoteVersion = 1;
        GoogleApi<zzej> googleApiZza = localVersion != 0 ? zza(true, this.zzb) : null;
        if (localVersion != 0) {
            int iIsGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.zzb, 12451000);
            remoteVersion = (iIsGooglePlayServicesAvailable == 0 || iIsGooglePlayServicesAvailable == 2) ? DynamiteModule.getRemoteVersion(this.zzb, "com.google.android.gms.firebase_auth") : 0;
        }
        return new zzal<>(remoteVersion != 0 ? zza(false, this.zzb) : null, googleApiZza, new zzan(remoteVersion, localVersion, Collections.emptyMap()));
    }
}
