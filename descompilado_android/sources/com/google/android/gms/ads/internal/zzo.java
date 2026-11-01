package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzdf;
import com.google.android.gms.internal.ads.zzdg;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzo implements Callable<zzdf> {
    private final /* synthetic */ zzj zzblp;

    zzo(zzj zzjVar) {
        this.zzblp = zzjVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ zzdf call() throws Exception {
        return new zzdf(zzdg.zza(this.zzblp.zzblh.zzbnh, this.zzblp.zzlk, false));
    }
}
