package com.google.android.gms.internal.ads;

import android.content.Context;
import android.webkit.CookieManager;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzccs implements zzdvi<zzdcp<String>> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzcxn> zzfdw;

    private zzccs(zzdvv<zzcxn> zzdvvVar, zzdvv<Context> zzdvvVar2) {
        this.zzfdw = zzdvvVar;
        this.zzejn = zzdvvVar2;
    }

    public static zzccs zzac(zzdvv<zzcxn> zzdvvVar, zzdvv<Context> zzdvvVar2) {
        return new zzccs(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        zzcxn zzcxnVar = this.zzfdw.get();
        final Context context = this.zzejn.get();
        return (zzdcp) zzdvo.zza(zzcxnVar.zzu(zzcxk.WEBVIEW_COOKIE).zzc(new Callable(context) { // from class: com.google.android.gms.internal.ads.zzccj
            private final Context zzdpm;

            {
                this.zzdpm = context;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                CookieManager cookieManagerZzbc = com.google.android.gms.ads.internal.zzp.zzka().zzbc(this.zzdpm);
                return cookieManagerZzbc != null ? cookieManagerZzbc.getCookie("googleads.g.doubleclick.net") : "";
            }
        }).zza(1L, TimeUnit.SECONDS).zza(Exception.class, zzccm.zzfti).zzano(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
