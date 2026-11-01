package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqv implements zzcra<zzcqs> {
    private final zzawv zzblh;
    private final zzdcs zzfnq;
    private final Context zzlk;

    public zzcqv(zzdcs zzdcsVar, Context context, zzawv zzawvVar) {
        this.zzfnq = zzdcsVar;
        this.zzlk = context;
        this.zzblh = zzawvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcqs> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcqu
            private final zzcqv zzgfr;

            {
                this.zzgfr = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzgfr.zzamd();
            }
        });
    }

    final /* synthetic */ zzcqs zzamd() throws Exception {
        boolean zIsCallerInstantApp = Wrappers.packageManager(this.zzlk).isCallerInstantApp();
        com.google.android.gms.ads.internal.zzp.zzjy();
        boolean zZzax = zzatv.zzax(this.zzlk);
        String str = this.zzblh.zzbnh;
        com.google.android.gms.ads.internal.zzp.zzka();
        boolean zZzvo = zzaub.zzvo();
        com.google.android.gms.ads.internal.zzp.zzjy();
        return new zzcqs(zIsCallerInstantApp, zZzax, str, zZzvo, zzatv.zzau(this.zzlk), DynamiteModule.getRemoteVersion(this.zzlk, ModuleDescriptor.MODULE_ID), DynamiteModule.getLocalVersion(this.zzlk, ModuleDescriptor.MODULE_ID));
    }
}
