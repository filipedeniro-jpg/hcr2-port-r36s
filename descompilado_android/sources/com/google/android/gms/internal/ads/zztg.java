package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zztg extends zzdts<zztg> {
    public Integer zzcbg = null;
    public Integer zzcbh = null;
    public Integer zzcbi = null;

    public zztg() {
        this.zzhqk = null;
        this.zzhqt = -1;
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    public final void zza(zzdtq zzdtqVar) throws IOException {
        Integer num = this.zzcbg;
        if (num != null) {
            zzdtqVar.zzaa(1, num.intValue());
        }
        Integer num2 = this.zzcbh;
        if (num2 != null) {
            zzdtqVar.zzaa(2, num2.intValue());
        }
        Integer num3 = this.zzcbi;
        if (num3 != null) {
            zzdtqVar.zzaa(3, num3.intValue());
        }
        super.zza(zzdtqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    protected final int zznv() {
        int iZznv = super.zznv();
        Integer num = this.zzcbg;
        if (num != null) {
            iZznv += zzdtq.zzae(1, num.intValue());
        }
        Integer num2 = this.zzcbh;
        if (num2 != null) {
            iZznv += zzdtq.zzae(2, num2.intValue());
        }
        Integer num3 = this.zzcbi;
        return num3 != null ? iZznv + zzdtq.zzae(3, num3.intValue()) : iZznv;
    }
}
