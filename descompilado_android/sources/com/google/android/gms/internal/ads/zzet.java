package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzet extends zzfl {
    public zzet(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2) {
        super(zzdxVar, str, str2, zzbVar, i, 24);
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    /* JADX INFO: renamed from: zzcv */
    public final Void call() throws Exception {
        if (this.zzvo.isInitialized()) {
            return super.call();
        }
        if (!this.zzvo.zzch()) {
            return null;
        }
        zzcw();
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        if (this.zzvo.zzch()) {
            zzcw();
            return;
        }
        synchronized (this.zzaaa) {
            this.zzaaa.zzah((String) this.zzaal.invoke(null, this.zzvo.getContext()));
        }
    }

    private final void zzcw() {
        AdvertisingIdClient advertisingIdClientZzcp = this.zzvo.zzcp();
        if (advertisingIdClientZzcp == null) {
            return;
        }
        try {
            AdvertisingIdClient.Info info = advertisingIdClientZzcp.getInfo();
            String strZzas = zzee.zzas(info.getId());
            if (strZzas != null) {
                synchronized (this.zzaaa) {
                    try {
                        this.zzaaa.zzah(strZzas);
                        this.zzaaa.zzb(info.isLimitAdTrackingEnabled());
                        this.zzaaa.zzb(zzbo.zza.zzc.DEVICE_IDENTIFIER_ANDROID_AD_ID);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        } catch (IOException unused) {
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfl, java.util.concurrent.Callable
    public final /* synthetic */ Object call() throws Exception {
        return call();
    }
}
