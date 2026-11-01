package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcli extends zzva {
    private zzur zzblm;
    private final zzbds zzfyn;
    private final Context zzgbf;

    @VisibleForTesting
    private final zzcvm zzgbg = new zzcvm();

    @VisibleForTesting
    private final zzbuk zzgbh = new zzbuk();

    public zzcli(zzbds zzbdsVar, Context context, String str) {
        this.zzfyn = zzbdsVar;
        this.zzgbg.zzgf(str);
        this.zzgbf = context;
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final zzuw zzon() {
        zzbui zzbuiVarZzaii = this.zzgbh.zzaii();
        this.zzgbg.zzb(zzbuiVarZzaii.zzaif());
        this.zzgbg.zzc(zzbuiVarZzaii.zzaig());
        zzcvm zzcvmVar = this.zzgbg;
        if (zzcvmVar.zzjo() == null) {
            zzcvmVar.zzd(zztw.zzg(this.zzgbf));
        }
        return new zzclh(this.zzgbf, this.zzfyn, this.zzgbg, zzbuiVarZzaii, this.zzblm);
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final void zzb(zzur zzurVar) {
        this.zzblm = zzurVar;
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final void zza(zzabs zzabsVar) {
        this.zzgbh.zzb(zzabsVar);
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final void zza(zzacf zzacfVar) {
        this.zzgbh.zzb(zzacfVar);
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final void zza(zzabt zzabtVar) {
        this.zzgbh.zzb(zzabtVar);
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final void zza(String str, zzabz zzabzVar, zzaby zzabyVar) {
        this.zzgbh.zzb(str, zzabzVar, zzabyVar);
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final void zza(zzaai zzaaiVar) {
        this.zzgbg.zzb(zzaaiVar);
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final void zza(zzafj zzafjVar) {
        this.zzgbg.zzb(zzafjVar);
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final void zza(zzafp zzafpVar) {
        this.zzgbh.zzb(zzafpVar);
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final void zzb(zzvs zzvsVar) {
        this.zzgbg.zzc(zzvsVar);
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final void zza(zzace zzaceVar, zztw zztwVar) {
        this.zzgbh.zza(zzaceVar);
        this.zzgbg.zzd(zztwVar);
    }

    @Override // com.google.android.gms.internal.ads.zzux
    public final void zza(PublisherAdViewOptions publisherAdViewOptions) {
        this.zzgbg.zzb(publisherAdViewOptions);
    }
}
