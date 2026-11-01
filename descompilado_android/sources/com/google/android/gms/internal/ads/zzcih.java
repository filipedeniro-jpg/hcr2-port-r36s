package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcih extends zzcig<zzbrc> {
    private final zzbox zzelb;
    private final zzcko zzeuw;
    private final zzbds zzfyn;
    private final zzblu.zza zzfyo;

    public zzcih(zzbds zzbdsVar, zzblu.zza zzaVar, zzcko zzckoVar, zzbox zzboxVar) {
        this.zzfyn = zzbdsVar;
        this.zzfyo = zzaVar;
        this.zzeuw = zzckoVar;
        this.zzelb = zzboxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcig
    protected final zzdcp<zzbrc> zza(zzcvk zzcvkVar, Bundle bundle) {
        return this.zzfyn.zzabk().zzd(this.zzfyo.zza(zzcvkVar).zze(bundle).zzafu()).zzd(this.zzelb).zzb(this.zzeuw).zzadc().zzaci().zzafo();
    }
}
