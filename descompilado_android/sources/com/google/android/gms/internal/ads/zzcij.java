package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcij extends zzcig<zzbyf> {
    private final zzbox zzelb;
    private final zzbds zzfyn;
    private final zzblu.zza zzfyo;

    public zzcij(zzbds zzbdsVar, zzblu.zza zzaVar, zzbox zzboxVar) {
        this.zzfyn = zzbdsVar;
        this.zzfyo = zzaVar;
        this.zzelb = zzboxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcig
    protected final zzdcp<zzbyf> zza(zzcvk zzcvkVar, Bundle bundle) {
        return this.zzfyn.zzabm().zze(this.zzfyo.zza(zzcvkVar).zze(bundle).zzafu()).zze(this.zzelb).zzadh().zzaci().zzafo();
    }
}
