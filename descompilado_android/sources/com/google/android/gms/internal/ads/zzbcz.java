package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import java.util.concurrent.Callable;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzbcz {
    public static zzbbc zza(final Context context, final zzbct zzbctVar, final String str, final boolean z, final boolean z2, @Nullable final zzdf zzdfVar, final zzawv zzawvVar, zzzh zzzhVar, final com.google.android.gms.ads.internal.zzk zzkVar, final com.google.android.gms.ads.internal.zzb zzbVar, final zzrz zzrzVar, final zzrb zzrbVar, final boolean z3) throws zzbbp {
        try {
            final zzzh zzzhVar2 = null;
            return (zzbbc) zzavw.zza(new Callable(context, zzbctVar, str, z, z2, zzdfVar, zzawvVar, zzzhVar2, zzkVar, zzbVar, zzrzVar, zzrbVar, z3) { // from class: com.google.android.gms.internal.ads.zzbcy
                private final String zzdbj;
                private final Context zzdpm;
                private final zzbct zzeex;
                private final boolean zzeey;
                private final boolean zzeez;
                private final zzdf zzefa;
                private final zzawv zzefb;
                private final zzzh zzefc;
                private final com.google.android.gms.ads.internal.zzk zzefd;
                private final com.google.android.gms.ads.internal.zzb zzefe;
                private final zzrz zzeff;
                private final zzrb zzefg;
                private final boolean zzefh;

                {
                    this.zzdpm = context;
                    this.zzeex = zzbctVar;
                    this.zzdbj = str;
                    this.zzeey = z;
                    this.zzeez = z2;
                    this.zzefa = zzdfVar;
                    this.zzefb = zzawvVar;
                    this.zzefc = zzzhVar2;
                    this.zzefd = zzkVar;
                    this.zzefe = zzbVar;
                    this.zzeff = zzrzVar;
                    this.zzefg = zzrbVar;
                    this.zzefh = z3;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Context context2 = this.zzdpm;
                    zzbct zzbctVar2 = this.zzeex;
                    String str2 = this.zzdbj;
                    boolean z4 = this.zzeey;
                    boolean z5 = this.zzeez;
                    zzdf zzdfVar2 = this.zzefa;
                    zzawv zzawvVar2 = this.zzefb;
                    zzzh zzzhVar3 = this.zzefc;
                    com.google.android.gms.ads.internal.zzk zzkVar2 = this.zzefd;
                    com.google.android.gms.ads.internal.zzb zzbVar2 = this.zzefe;
                    zzrz zzrzVar2 = this.zzeff;
                    zzrb zzrbVar2 = this.zzefg;
                    boolean z6 = this.zzefh;
                    zzbcs zzbcsVar = new zzbcs();
                    zzbdb zzbdbVar = new zzbdb(new zzbcq(context2), zzbcsVar, zzbctVar2, str2, z4, z5, zzdfVar2, zzawvVar2, zzzhVar3, zzkVar2, zzbVar2, zzrzVar2, zzrbVar2, z6);
                    zzbbo zzbboVar = new zzbbo(zzbdbVar);
                    zzbdbVar.setWebChromeClient(new zzbau(zzbboVar));
                    zzbcsVar.zza(zzbboVar, z5);
                    return zzbboVar;
                }
            });
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "AdWebViewFactory.newAdWebView2");
            throw new zzbbp("Webview initialization failed.", th);
        }
    }
}
