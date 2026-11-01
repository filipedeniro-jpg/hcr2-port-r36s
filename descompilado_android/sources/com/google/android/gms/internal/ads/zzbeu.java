package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbeu {
    private zzbdr zzejo;
    private zzbfg zzewz;
    private zzcxu zzexa;
    private zzbfo zzexb;
    private zzcvw zzexc;

    private zzbeu() {
    }

    public final zzbeu zzc(zzbdr zzbdrVar) {
        this.zzejo = (zzbdr) zzdvo.checkNotNull(zzbdrVar);
        return this;
    }

    public final zzbeu zza(zzbfg zzbfgVar) {
        this.zzewz = (zzbfg) zzdvo.checkNotNull(zzbfgVar);
        return this;
    }

    public final zzbds zzada() {
        zzdvo.zza(this.zzejo, (Class<zzbdr>) zzbdr.class);
        zzdvo.zza(this.zzewz, (Class<zzbfg>) zzbfg.class);
        if (this.zzexa == null) {
            this.zzexa = new zzcxu();
        }
        if (this.zzexb == null) {
            this.zzexb = new zzbfo();
        }
        if (this.zzexc == null) {
            this.zzexc = new zzcvw();
        }
        return new zzbeg(this.zzejo, this.zzewz, this.zzexa, this.zzexb, this.zzexc);
    }
}
