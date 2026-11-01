package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zztc extends zzdts<zztc> {
    public String zzbzx = null;
    private zzsh.zzb[] zzbzy = new zzsh.zzb[0];
    private zzso zzbzz = null;
    private zzso zzcaa = null;
    private zzso zzcab = null;

    public zztc() {
        this.zzhqk = null;
        this.zzhqt = -1;
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    public final void zza(zzdtq zzdtqVar) throws IOException {
        String str = this.zzbzx;
        if (str != null) {
            zzdtqVar.zzg(1, str);
        }
        zzsh.zzb[] zzbVarArr = this.zzbzy;
        if (zzbVarArr != null && zzbVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzsh.zzb[] zzbVarArr2 = this.zzbzy;
                if (i >= zzbVarArr2.length) {
                    break;
                }
                zzsh.zzb zzbVar = zzbVarArr2[i];
                if (zzbVar != null) {
                    zzdtqVar.zze(2, zzbVar);
                }
                i++;
            }
        }
        super.zza(zzdtqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    protected final int zznv() {
        int iZznv = super.zznv();
        String str = this.zzbzx;
        if (str != null) {
            iZznv += zzdtq.zzh(1, str);
        }
        zzsh.zzb[] zzbVarArr = this.zzbzy;
        if (zzbVarArr != null && zzbVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzsh.zzb[] zzbVarArr2 = this.zzbzy;
                if (i >= zzbVarArr2.length) {
                    break;
                }
                zzsh.zzb zzbVar = zzbVarArr2[i];
                if (zzbVar != null) {
                    iZznv += zzdpm.zzc(2, zzbVar);
                }
                i++;
            }
        }
        return iZznv;
    }
}
