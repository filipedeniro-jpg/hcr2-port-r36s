package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzahb implements zzadx<zzahr> {
    private final /* synthetic */ zzdf zzczp;
    private final /* synthetic */ zzagk zzczq;
    private final /* synthetic */ zzavx zzczr;
    private final /* synthetic */ zzagt zzczs;

    zzahb(zzagt zzagtVar, zzdf zzdfVar, zzagk zzagkVar, zzavx zzavxVar) {
        this.zzczs = zzagtVar;
        this.zzczp = zzdfVar;
        this.zzczq = zzagkVar;
        this.zzczr = zzavxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzahr zzahrVar, Map map) {
        synchronized (this.zzczs.lock) {
            zzatm.zzet("JS Engine is requesting an update");
            if (this.zzczs.status == 0) {
                zzatm.zzet("Starting reload.");
                this.zzczs.status = 2;
                this.zzczs.zza(this.zzczp);
            }
            this.zzczq.zzb("/requestReload", (zzadx<? super zzahr>) this.zzczr.get());
        }
    }
}
