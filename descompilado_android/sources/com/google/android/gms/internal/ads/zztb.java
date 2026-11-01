package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zztb extends zzdts<zztb> {
    public String zzbzp = null;
    private zzsh.zzo zzbzq = null;
    private Integer zzbzr = null;
    public zztg zzbzs = null;
    private Integer zzbzt = null;
    private zzso zzbzu = null;
    private zzso zzbzv = null;
    private zzso zzbzw = null;

    public zztb() {
        this.zzhqk = null;
        this.zzhqt = -1;
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    public final void zza(zzdtq zzdtqVar) throws IOException {
        String str = this.zzbzp;
        if (str != null) {
            zzdtqVar.zzg(1, str);
        }
        zztg zztgVar = this.zzbzs;
        if (zztgVar != null) {
            zzdtqVar.zza(4, zztgVar);
        }
        super.zza(zzdtqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    protected final int zznv() {
        int iZznv = super.zznv();
        String str = this.zzbzp;
        if (str != null) {
            iZznv += zzdtq.zzh(1, str);
        }
        zztg zztgVar = this.zzbzs;
        return zztgVar != null ? iZznv + zzdtq.zzb(4, zztgVar) : iZznv;
    }
}
