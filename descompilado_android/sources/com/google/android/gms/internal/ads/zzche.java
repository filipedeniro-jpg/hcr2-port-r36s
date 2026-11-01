package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzche implements zzbse {
    private final zzawv zzbkw;
    private final zzcvb zzfli;
    private final zzdcp<zzbre> zzfyd;
    private final zzbbc zzfye;
    private final Context zzlk;

    private zzche(Context context, zzawv zzawvVar, zzdcp<zzbre> zzdcpVar, zzcvb zzcvbVar, zzbbc zzbbcVar) {
        this.zzlk = context;
        this.zzbkw = zzawvVar;
        this.zzfyd = zzdcpVar;
        this.zzfli = zzcvbVar;
        this.zzfye = zzbbcVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbse
    public final void zza(boolean z, Context context) {
        zzbre zzbreVar = (zzbre) zzdcf.zzc(this.zzfyd);
        this.zzfye.zzaq(true);
        com.google.android.gms.ads.internal.zzp.zzjy();
        com.google.android.gms.ads.internal.zzi zziVar = new com.google.android.gms.ads.internal.zzi(false, zzatv.zzba(this.zzlk), false, 0.0f, -1, z, this.zzfli.zzgjd, false);
        com.google.android.gms.ads.internal.zzp.zzjx();
        com.google.android.gms.ads.internal.overlay.zzl.zza(context, new AdOverlayInfoParcel((zzth) null, zzbreVar.zzadg(), (com.google.android.gms.ads.internal.overlay.zzv) null, this.zzfye, this.zzfli.zzgje, this.zzbkw, this.zzfli.zzdkj, zziVar, this.zzfli.zzgiw.zzdhn, this.zzfli.zzgiw.zzdhp), true);
    }
}
