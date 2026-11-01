package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdun extends zzdts<zzdun> {
    private zzdua.zzb.zzd.C0049zzb zzhvi = null;
    public zzdua.zzb.zzc[] zzhvj = new zzdua.zzb.zzc[0];
    private byte[] zzhvk = null;
    private byte[] zzhvl = null;
    private Integer zzhvm = null;

    public zzdun() {
        this.zzhqk = null;
        this.zzhqt = -1;
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    public final void zza(zzdtq zzdtqVar) throws IOException {
        zzdua.zzb.zzc[] zzcVarArr = this.zzhvj;
        if (zzcVarArr != null && zzcVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzdua.zzb.zzc[] zzcVarArr2 = this.zzhvj;
                if (i >= zzcVarArr2.length) {
                    break;
                }
                zzdua.zzb.zzc zzcVar = zzcVarArr2[i];
                if (zzcVar != null) {
                    zzdtqVar.zze(2, zzcVar);
                }
                i++;
            }
        }
        super.zza(zzdtqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    protected final int zznv() {
        int iZznv = super.zznv();
        zzdua.zzb.zzc[] zzcVarArr = this.zzhvj;
        if (zzcVarArr != null && zzcVarArr.length > 0) {
            int i = 0;
            while (true) {
                zzdua.zzb.zzc[] zzcVarArr2 = this.zzhvj;
                if (i >= zzcVarArr2.length) {
                    break;
                }
                zzdua.zzb.zzc zzcVar = zzcVarArr2[i];
                if (zzcVar != null) {
                    iZznv += zzdpm.zzc(2, zzcVar);
                }
                i++;
            }
        }
        return iZznv;
    }
}
