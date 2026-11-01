package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdul extends zzdts<zzdul> {
    public zzdua.zzb.zzg zzhur = null;
    private zzdua.zza.zzc zzhus = null;
    public String url = null;
    public String zzhut = null;
    private String zzhuu = null;
    public zzdua.zzb.C0048zzb zzhuv = null;
    public zzduo[] zzhuw = zzduo.zzbcu();
    public String zzhux = null;
    public zzdup zzhuy = null;
    private Boolean zzhuz = null;
    private String[] zzhva = zzdub.zzhrc;
    private String zzhvb = null;
    private Boolean zzhvc = null;
    private Boolean zzhvd = null;
    private byte[] zzhve = null;
    public zzdua.zzb.zzi zzhvf = null;
    public String[] zzhvg = zzdub.zzhrc;
    public String[] zzhvh = zzdub.zzhrc;

    public zzdul() {
        this.zzhqk = null;
        this.zzhqt = -1;
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    public final void zza(zzdtq zzdtqVar) throws IOException {
        String str = this.url;
        if (str != null) {
            zzdtqVar.zzg(1, str);
        }
        String str2 = this.zzhut;
        if (str2 != null) {
            zzdtqVar.zzg(2, str2);
        }
        zzduo[] zzduoVarArr = this.zzhuw;
        int i = 0;
        if (zzduoVarArr != null && zzduoVarArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzduo[] zzduoVarArr2 = this.zzhuw;
                if (i2 >= zzduoVarArr2.length) {
                    break;
                }
                zzduo zzduoVar = zzduoVarArr2[i2];
                if (zzduoVar != null) {
                    zzdtqVar.zza(4, zzduoVar);
                }
                i2++;
            }
        }
        String[] strArr = this.zzhva;
        if (strArr != null && strArr.length > 0) {
            int i3 = 0;
            while (true) {
                String[] strArr2 = this.zzhva;
                if (i3 >= strArr2.length) {
                    break;
                }
                String str3 = strArr2[i3];
                if (str3 != null) {
                    zzdtqVar.zzg(6, str3);
                }
                i3++;
            }
        }
        zzdua.zzb.zzg zzgVar = this.zzhur;
        if (zzgVar != null && zzgVar != null) {
            zzdtqVar.zzaa(10, zzgVar.zzab());
        }
        zzdua.zzb.C0048zzb c0048zzb = this.zzhuv;
        if (c0048zzb != null) {
            zzdtqVar.zze(12, c0048zzb);
        }
        String str4 = this.zzhux;
        if (str4 != null) {
            zzdtqVar.zzg(13, str4);
        }
        zzdup zzdupVar = this.zzhuy;
        if (zzdupVar != null) {
            zzdtqVar.zza(14, zzdupVar);
        }
        zzdua.zzb.zzi zziVar = this.zzhvf;
        if (zziVar != null) {
            zzdtqVar.zze(17, zziVar);
        }
        String[] strArr3 = this.zzhvg;
        if (strArr3 != null && strArr3.length > 0) {
            int i4 = 0;
            while (true) {
                String[] strArr4 = this.zzhvg;
                if (i4 >= strArr4.length) {
                    break;
                }
                String str5 = strArr4[i4];
                if (str5 != null) {
                    zzdtqVar.zzg(20, str5);
                }
                i4++;
            }
        }
        String[] strArr5 = this.zzhvh;
        if (strArr5 != null && strArr5.length > 0) {
            while (true) {
                String[] strArr6 = this.zzhvh;
                if (i >= strArr6.length) {
                    break;
                }
                String str6 = strArr6[i];
                if (str6 != null) {
                    zzdtqVar.zzg(21, str6);
                }
                i++;
            }
        }
        super.zza(zzdtqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    protected final int zznv() {
        int iZznv = super.zznv();
        String str = this.url;
        if (str != null) {
            iZznv += zzdtq.zzh(1, str);
        }
        String str2 = this.zzhut;
        if (str2 != null) {
            iZznv += zzdtq.zzh(2, str2);
        }
        zzduo[] zzduoVarArr = this.zzhuw;
        int i = 0;
        if (zzduoVarArr != null && zzduoVarArr.length > 0) {
            int iZzb = iZznv;
            int i2 = 0;
            while (true) {
                zzduo[] zzduoVarArr2 = this.zzhuw;
                if (i2 >= zzduoVarArr2.length) {
                    break;
                }
                zzduo zzduoVar = zzduoVarArr2[i2];
                if (zzduoVar != null) {
                    iZzb += zzdtq.zzb(4, zzduoVar);
                }
                i2++;
            }
            iZznv = iZzb;
        }
        String[] strArr = this.zzhva;
        if (strArr != null && strArr.length > 0) {
            int i3 = 0;
            int iZzhj = 0;
            int i4 = 0;
            while (true) {
                String[] strArr2 = this.zzhva;
                if (i3 >= strArr2.length) {
                    break;
                }
                String str3 = strArr2[i3];
                if (str3 != null) {
                    i4++;
                    iZzhj += zzdtq.zzhj(str3);
                }
                i3++;
            }
            iZznv = iZznv + iZzhj + (i4 * 1);
        }
        zzdua.zzb.zzg zzgVar = this.zzhur;
        if (zzgVar != null && zzgVar != null) {
            iZznv += zzdtq.zzae(10, zzgVar.zzab());
        }
        zzdua.zzb.C0048zzb c0048zzb = this.zzhuv;
        if (c0048zzb != null) {
            iZznv += zzdpm.zzc(12, c0048zzb);
        }
        String str4 = this.zzhux;
        if (str4 != null) {
            iZznv += zzdtq.zzh(13, str4);
        }
        zzdup zzdupVar = this.zzhuy;
        if (zzdupVar != null) {
            iZznv += zzdtq.zzb(14, zzdupVar);
        }
        zzdua.zzb.zzi zziVar = this.zzhvf;
        if (zziVar != null) {
            iZznv += zzdpm.zzc(17, zziVar);
        }
        String[] strArr3 = this.zzhvg;
        if (strArr3 != null && strArr3.length > 0) {
            int i5 = 0;
            int iZzhj2 = 0;
            int i6 = 0;
            while (true) {
                String[] strArr4 = this.zzhvg;
                if (i5 >= strArr4.length) {
                    break;
                }
                String str5 = strArr4[i5];
                if (str5 != null) {
                    i6++;
                    iZzhj2 += zzdtq.zzhj(str5);
                }
                i5++;
            }
            iZznv = iZznv + iZzhj2 + (i6 * 2);
        }
        String[] strArr5 = this.zzhvh;
        if (strArr5 == null || strArr5.length <= 0) {
            return iZznv;
        }
        int iZzhj3 = 0;
        int i7 = 0;
        while (true) {
            String[] strArr6 = this.zzhvh;
            if (i >= strArr6.length) {
                return iZznv + iZzhj3 + (i7 * 2);
            }
            String str6 = strArr6[i];
            if (str6 != null) {
                i7++;
                iZzhj3 += zzdtq.zzhj(str6);
            }
            i++;
        }
    }
}
