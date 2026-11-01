package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import java.util.concurrent.Callable;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzbbl {
    public static zzdcp<zzbbc> zza(final Context context, final zzawv zzawvVar, final String str, final zzdf zzdfVar, final com.google.android.gms.ads.internal.zzb zzbVar) {
        return zzdcf.zzb(zzdcf.zzah(null), new zzdbq(context, zzdfVar, zzawvVar, zzbVar, str) { // from class: com.google.android.gms.internal.ads.zzbbk
            private final zzdf zzczj;
            private final Context zzdpm;
            private final zzawv zzeet;
            private final com.google.android.gms.ads.internal.zzb zzeeu;
            private final String zzeev;

            {
                this.zzdpm = context;
                this.zzczj = zzdfVar;
                this.zzeet = zzawvVar;
                this.zzeeu = zzbVar;
                this.zzeev = str;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) throws zzbbp {
                Context context2 = this.zzdpm;
                zzdf zzdfVar2 = this.zzczj;
                zzawv zzawvVar2 = this.zzeet;
                com.google.android.gms.ads.internal.zzb zzbVar2 = this.zzeeu;
                String str2 = this.zzeev;
                com.google.android.gms.ads.internal.zzp.zzjz();
                zzbbc zzbbcVarZza = zzbbl.zza(context2, zzbct.zzaan(), "", false, false, zzdfVar2, zzawvVar2, null, null, zzbVar2, zzrz.zzmk(), null, false);
                final zzaxc zzaxcVarZzl = zzaxc.zzl(zzbbcVarZza);
                zzbbcVarZza.zzyy().zza(new zzbcp(zzaxcVarZzl) { // from class: com.google.android.gms.internal.ads.zzbbm
                    private final zzaxc zzeew;

                    {
                        this.zzeew = zzaxcVarZzl;
                    }

                    @Override // com.google.android.gms.internal.ads.zzbcp
                    public final void zzab(boolean z) {
                        this.zzeew.zzwl();
                    }
                });
                zzbbcVarZza.loadUrl(str2);
                return zzaxcVarZzl;
            }
        }, zzawx.zzdwa);
    }

    public static zzbbc zza(final Context context, final zzbct zzbctVar, final String str, final boolean z, final boolean z2, @Nullable final zzdf zzdfVar, final zzawv zzawvVar, zzzh zzzhVar, final com.google.android.gms.ads.internal.zzk zzkVar, final com.google.android.gms.ads.internal.zzb zzbVar, final zzrz zzrzVar, final zzrb zzrbVar, final boolean z3) throws zzbbp {
        zzyt.initialize(context);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjr)).booleanValue()) {
            return zzbcz.zza(context, zzbctVar, str, z, z2, zzdfVar, zzawvVar, null, zzkVar, zzbVar, zzrzVar, zzrbVar, z3);
        }
        try {
            final zzzh zzzhVar2 = null;
            return (zzbbc) zzavw.zza(new Callable(context, zzbctVar, str, z, z2, zzdfVar, zzawvVar, zzzhVar2, zzkVar, zzbVar, zzrzVar, zzrbVar, z3) { // from class: com.google.android.gms.internal.ads.zzbbn
                private final String zzdbj;
                private final Context zzdpm;
                private final zzbct zzeex;
                private final boolean zzeey;
                private final boolean zzeez;
                private final zzdf zzefa;
                private final zzawv zzefb;
                private final zzzh zzefc = null;
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
                    zzbbo zzbboVar = new zzbbo(zzbbq.zzb(context2, zzbctVar2, str2, z4, z5, zzdfVar2, zzawvVar2, zzzhVar3, zzkVar2, zzbVar2, zzrzVar2, this.zzefg, this.zzefh));
                    zzbboVar.setWebViewClient(com.google.android.gms.ads.internal.zzp.zzka().zza(zzbboVar, zzrzVar2, z5));
                    zzbboVar.setWebChromeClient(new zzbau(zzbboVar));
                    return zzbboVar;
                }
            });
        } catch (Throwable th) {
            throw new zzbbp("Webview initialization failed.", th);
        }
    }
}
