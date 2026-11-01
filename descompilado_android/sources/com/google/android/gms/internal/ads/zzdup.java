package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdup extends zzdts<zzdup> {
    public zzdua.zzb.zzf.EnumC0051zzb zzhvw = null;
    public String mimeType = null;
    public byte[] zzhvx = null;

    public zzdup() {
        this.zzhqk = null;
        this.zzhqt = -1;
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    public final void zza(zzdtq zzdtqVar) throws IOException {
        zzdua.zzb.zzf.EnumC0051zzb enumC0051zzb = this.zzhvw;
        if (enumC0051zzb != null && enumC0051zzb != null) {
            zzdtqVar.zzaa(1, enumC0051zzb.zzab());
        }
        String str = this.mimeType;
        if (str != null) {
            zzdtqVar.zzg(2, str);
        }
        byte[] bArr = this.zzhvx;
        if (bArr != null) {
            zzdtqVar.zza(3, bArr);
        }
        super.zza(zzdtqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    protected final int zznv() {
        int iZznv = super.zznv();
        zzdua.zzb.zzf.EnumC0051zzb enumC0051zzb = this.zzhvw;
        if (enumC0051zzb != null && enumC0051zzb != null) {
            iZznv += zzdtq.zzae(1, enumC0051zzb.zzab());
        }
        String str = this.mimeType;
        if (str != null) {
            iZznv += zzdtq.zzh(2, str);
        }
        byte[] bArr = this.zzhvx;
        return bArr != null ? iZznv + zzdtq.zzge(3) + zzdtq.zzgm(bArr.length) + bArr.length : iZznv;
    }
}
