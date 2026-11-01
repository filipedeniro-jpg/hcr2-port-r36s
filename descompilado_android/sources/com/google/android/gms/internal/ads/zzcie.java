package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcie extends zzcig<zzbka> {
    private final zzbox zzelb;
    private final zzbsn zzeld;
    private final zzbds zzfyn;
    private final zzblu.zza zzfyo;

    public zzcie(zzbds zzbdsVar, zzbsn zzbsnVar, zzblu.zza zzaVar, zzbox zzboxVar) {
        this.zzfyn = zzbdsVar;
        this.zzeld = zzbsnVar;
        this.zzfyo = zzaVar;
        this.zzelb = zzboxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcig
    protected final zzdcp<zzbka> zza(zzcvk zzcvkVar, Bundle bundle) {
        return this.zzfyn.zzabl().zza(this.zzfyo.zza(zzcvkVar).zze(bundle).zzafu()).zza(this.zzelb).zza(this.zzeld).zzabv().zzaci().zzafo();
    }
}
