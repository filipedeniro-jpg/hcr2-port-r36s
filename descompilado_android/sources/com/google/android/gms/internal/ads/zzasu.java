package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzasu implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzaxf zzdpo;

    zzasu(zzasv zzasvVar, Context context, zzaxf zzaxfVar) {
        this.val$context = context;
        this.zzdpo = zzaxfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzdpo.set(AdvertisingIdClient.getAdvertisingIdInfo(this.val$context));
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            this.zzdpo.setException(e);
            zzawo.zzc("Exception while getting advertising Id info", e);
        }
    }
}
