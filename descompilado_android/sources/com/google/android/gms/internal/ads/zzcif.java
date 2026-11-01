package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcif extends zzcig<zzbhy> {
    private final zzbox zzelb;
    private final zzbsn zzeld;
    private final zzcko zzeuw;
    private final ViewGroup zzfcw;
    private final zzboa zzfen;
    private final zzbds zzfyn;
    private final zzblu.zza zzfyo;

    public zzcif(zzbds zzbdsVar, zzblu.zza zzaVar, zzcko zzckoVar, zzbox zzboxVar, zzbsn zzbsnVar, zzboa zzboaVar, @Nullable ViewGroup viewGroup) {
        this.zzfyn = zzbdsVar;
        this.zzfyo = zzaVar;
        this.zzeuw = zzckoVar;
        this.zzelb = zzboxVar;
        this.zzeld = zzbsnVar;
        this.zzfen = zzboaVar;
        this.zzfcw = viewGroup;
    }

    @Override // com.google.android.gms.internal.ads.zzcig
    protected final zzdcp<zzbhy> zza(zzcvk zzcvkVar, Bundle bundle) {
        return this.zzfyn.zzabi().zzc(this.zzfyo.zza(zzcvkVar).zze(bundle).zzafu()).zzc(this.zzelb).zza(this.zzeuw).zzb(this.zzeld).zza(new zzbjn(this.zzfen)).zzb(new zzbht(this.zzfcw)).zzacr().zzaci().zzafo();
    }
}
