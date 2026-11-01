package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcoe implements zzcra<zzcof> {
    private final zzdcs zzfnq;
    private final Context zzlk;

    public zzcoe(zzdcs zzdcsVar, Context context) {
        this.zzfnq = zzdcsVar;
        this.zzlk = context;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcof> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcoh
            private final zzcoe zzgei;

            {
                this.zzgei = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzgei.zzalu();
            }
        });
    }

    final /* synthetic */ zzcof zzalu() throws Exception {
        AudioManager audioManager = (AudioManager) this.zzlk.getSystemService("audio");
        return new zzcof(audioManager.getMode(), audioManager.isMusicActive(), audioManager.isSpeakerphoneOn(), audioManager.getStreamVolume(3), audioManager.getRingerMode(), audioManager.getStreamVolume(2), com.google.android.gms.ads.internal.zzp.zzkd().zzoo(), com.google.android.gms.ads.internal.zzp.zzkd().zzop());
    }
}
