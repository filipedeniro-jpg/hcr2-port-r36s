package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzta extends zzdts<zzta> {
    public Integer zzbze = null;
    private zzso zzbzf = null;
    private zzsh.zzc zzbzg = null;
    public zztc zzbzh = null;
    private zzsh.zzb[] zzbzi = new zzsh.zzb[0];
    private zzsh.zzd zzbzj = null;
    private zzsh.zzk zzbzk = null;
    private zzsh.zzi zzbzl = null;
    private zzsh.zzf zzbzm = null;
    private zzsh.zzg zzbzn = null;
    private zztf[] zzbzo = zztf.zznw();

    public zzta() {
        this.zzhqk = null;
        this.zzhqt = -1;
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    public final void zza(zzdtq zzdtqVar) throws IOException {
        Integer num = this.zzbze;
        if (num != null) {
            zzdtqVar.zzaa(7, num.intValue());
        }
        zztc zztcVar = this.zzbzh;
        if (zztcVar != null) {
            zzdtqVar.zza(10, zztcVar);
        }
        zzsh.zzb[] zzbVarArr = this.zzbzi;
        int i = 0;
        if (zzbVarArr != null && zzbVarArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzsh.zzb[] zzbVarArr2 = this.zzbzi;
                if (i2 >= zzbVarArr2.length) {
                    break;
                }
                zzsh.zzb zzbVar = zzbVarArr2[i2];
                if (zzbVar != null) {
                    zzdtqVar.zze(11, zzbVar);
                }
                i2++;
            }
        }
        zztf[] zztfVarArr = this.zzbzo;
        if (zztfVarArr != null && zztfVarArr.length > 0) {
            while (true) {
                zztf[] zztfVarArr2 = this.zzbzo;
                if (i >= zztfVarArr2.length) {
                    break;
                }
                zztf zztfVar = zztfVarArr2[i];
                if (zztfVar != null) {
                    zzdtqVar.zza(17, zztfVar);
                }
                i++;
            }
        }
        super.zza(zzdtqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    protected final int zznv() {
        int iZznv = super.zznv();
        Integer num = this.zzbze;
        if (num != null) {
            iZznv += zzdtq.zzae(7, num.intValue());
        }
        zztc zztcVar = this.zzbzh;
        if (zztcVar != null) {
            iZznv += zzdtq.zzb(10, zztcVar);
        }
        zzsh.zzb[] zzbVarArr = this.zzbzi;
        int i = 0;
        if (zzbVarArr != null && zzbVarArr.length > 0) {
            int iZzc = iZznv;
            int i2 = 0;
            while (true) {
                zzsh.zzb[] zzbVarArr2 = this.zzbzi;
                if (i2 >= zzbVarArr2.length) {
                    break;
                }
                zzsh.zzb zzbVar = zzbVarArr2[i2];
                if (zzbVar != null) {
                    iZzc += zzdpm.zzc(11, zzbVar);
                }
                i2++;
            }
            iZznv = iZzc;
        }
        zztf[] zztfVarArr = this.zzbzo;
        if (zztfVarArr != null && zztfVarArr.length > 0) {
            while (true) {
                zztf[] zztfVarArr2 = this.zzbzo;
                if (i >= zztfVarArr2.length) {
                    break;
                }
                zztf zztfVar = zztfVarArr2[i];
                if (zztfVar != null) {
                    iZznv += zzdtq.zzb(17, zztfVar);
                }
                i++;
            }
        }
        return iZznv;
    }
}
