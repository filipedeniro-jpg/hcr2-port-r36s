package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbxx implements Callable<zzbxn> {
    private final zzbbl zzblw;
    private final zzawv zzdid;
    private final zzdf zzefm;
    private final Executor zzfbm;
    private final com.google.android.gms.ads.internal.zzb zzfoe;
    private final Context zzlk;

    public zzbxx(Context context, Executor executor, zzdf zzdfVar, zzawv zzawvVar, com.google.android.gms.ads.internal.zzb zzbVar, zzbbl zzbblVar) {
        this.zzlk = context;
        this.zzfbm = executor;
        this.zzefm = zzdfVar;
        this.zzdid = zzawvVar;
        this.zzfoe = zzbVar;
        this.zzblw = zzbblVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ zzbxn call() throws Exception {
        zzbxn zzbxnVar = new zzbxn(this);
        zzbxnVar.zzajb();
        return zzbxnVar;
    }
}
