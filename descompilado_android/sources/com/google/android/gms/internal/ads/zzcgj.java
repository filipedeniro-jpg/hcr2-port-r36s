package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcgj implements zzcfk<zzbrc> {
    private final zzcuz zzfao;
    private final Executor zzfbm;
    private final zzbry zzfxh;
    private final Context zzlk;

    public zzcgj(Context context, Executor executor, zzbry zzbryVar, zzcuz zzcuzVar) {
        this.zzlk = context;
        this.zzfxh = zzbryVar;
        this.zzfbm = executor;
        this.zzfao = zzcuzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return (this.zzlk instanceof Activity) && PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzzr.zzj(this.zzlk) && !TextUtils.isEmpty(zzc(zzcvbVar));
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<zzbrc> zzb(final zzcvj zzcvjVar, final zzcvb zzcvbVar) {
        String strZzc = zzc(zzcvbVar);
        final Uri uri = strZzc != null ? Uri.parse(strZzc) : null;
        return zzdcf.zzb(zzdcf.zzah(null), new zzdbq(this, uri, zzcvjVar, zzcvbVar) { // from class: com.google.android.gms.internal.ads.zzcgm
            private final zzcgj zzfxi;
            private final Uri zzfxj;
            private final zzcvj zzfxk;
            private final zzcvb zzfxl;

            {
                this.zzfxi = this;
                this.zzfxj = uri;
                this.zzfxk = zzcvjVar;
                this.zzfxl = zzcvbVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfxi.zza(this.zzfxj, this.zzfxk, this.zzfxl, obj);
            }
        }, this.zzfbm);
    }

    private static String zzc(zzcvb zzcvbVar) {
        try {
            return zzcvbVar.zzgiz.getString("tab_url");
        } catch (Exception unused) {
            return null;
        }
    }

    final /* synthetic */ zzdcp zza(Uri uri, zzcvj zzcvjVar, zzcvb zzcvbVar, Object obj) throws Exception {
        try {
            CustomTabsIntent customTabsIntentBuild = new CustomTabsIntent.Builder().build();
            customTabsIntentBuild.intent.setData(uri);
            com.google.android.gms.ads.internal.overlay.zzb zzbVar = new com.google.android.gms.ads.internal.overlay.zzb(customTabsIntentBuild.intent);
            final zzaxf zzaxfVar = new zzaxf();
            zzbre zzbreVarZza = this.zzfxh.zza(new zzbkk(zzcvjVar, zzcvbVar, null), new zzbrd(new zzbse(zzaxfVar) { // from class: com.google.android.gms.internal.ads.zzcgl
                private final zzaxf zzbrp;

                {
                    this.zzbrp = zzaxfVar;
                }

                @Override // com.google.android.gms.internal.ads.zzbse
                public final void zza(boolean z, Context context) {
                    zzaxf zzaxfVar2 = this.zzbrp;
                    try {
                        com.google.android.gms.ads.internal.zzp.zzjx();
                        com.google.android.gms.ads.internal.overlay.zzl.zza(context, (AdOverlayInfoParcel) zzaxfVar2.get(), true);
                    } catch (Exception unused) {
                    }
                }
            }));
            zzaxfVar.set(new AdOverlayInfoParcel(zzbVar, null, zzbreVarZza.zzadf(), null, new zzawv(0, 0, false)));
            this.zzfao.zzuf();
            return zzdcf.zzah(zzbreVarZza.zzade());
        } catch (Throwable th) {
            zzatm.zzc("Error in CustomTabsAdRenderer", th);
            throw th;
        }
    }
}
