package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzblj implements zzboi {
    private final zzawv zzbkw;
    private final zzato zzdqz;
    private final zzcbp zzezz;
    private final zzcvk zzffn;
    private final Context zzzc;

    public zzblj(Context context, zzcvk zzcvkVar, zzawv zzawvVar, zzato zzatoVar, zzcbp zzcbpVar) {
        this.zzzc = context;
        this.zzffn = zzcvkVar;
        this.zzbkw = zzawvVar;
        this.zzdqz = zzatoVar;
        this.zzezz = zzcbpVar;
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zza(zzcvj zzcvjVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zzb(zzaok zzaokVar) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcoo)).booleanValue()) {
            com.google.android.gms.ads.internal.zzp.zzkg().zza(this.zzzc, this.zzbkw, this.zzffn.zzgjt, this.zzdqz.zzuy());
        }
        this.zzezz.zzajw();
    }
}
