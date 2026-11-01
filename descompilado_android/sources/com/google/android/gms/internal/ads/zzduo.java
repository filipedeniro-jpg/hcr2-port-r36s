package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzduo extends zzdts<zzduo> {
    private static volatile zzduo[] zzhvn;
    public Integer zzhvo = null;
    public String url = null;
    public zzdun zzhvp = null;
    private zzdum zzhvq = null;
    private Integer zzhvr = null;
    private int[] zzhvs = zzdub.zzhml;
    private String zzhvt = null;
    public zzdua.zzb.zzh.zza zzhvu = null;
    public String[] zzhvv = zzdub.zzhrc;

    public static zzduo[] zzbcu() {
        if (zzhvn == null) {
            synchronized (zzdtw.zzhqq) {
                if (zzhvn == null) {
                    zzhvn = new zzduo[0];
                }
            }
        }
        return zzhvn;
    }

    public zzduo() {
        this.zzhqk = null;
        this.zzhqt = -1;
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    public final void zza(zzdtq zzdtqVar) throws IOException {
        zzdtqVar.zzaa(1, this.zzhvo.intValue());
        String str = this.url;
        if (str != null) {
            zzdtqVar.zzg(2, str);
        }
        zzdun zzdunVar = this.zzhvp;
        if (zzdunVar != null) {
            zzdtqVar.zza(3, zzdunVar);
        }
        int[] iArr = this.zzhvs;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = this.zzhvs;
                if (i2 >= iArr2.length) {
                    break;
                }
                zzdtqVar.zzaa(6, iArr2[i2]);
                i2++;
            }
        }
        zzdua.zzb.zzh.zza zzaVar = this.zzhvu;
        if (zzaVar != null && zzaVar != null) {
            zzdtqVar.zzaa(8, zzaVar.zzab());
        }
        String[] strArr = this.zzhvv;
        if (strArr != null && strArr.length > 0) {
            while (true) {
                String[] strArr2 = this.zzhvv;
                if (i >= strArr2.length) {
                    break;
                }
                String str2 = strArr2[i];
                if (str2 != null) {
                    zzdtqVar.zzg(9, str2);
                }
                i++;
            }
        }
        super.zza(zzdtqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    protected final int zznv() {
        int[] iArr;
        int iZznv = super.zznv() + zzdtq.zzae(1, this.zzhvo.intValue());
        String str = this.url;
        if (str != null) {
            iZznv += zzdtq.zzh(2, str);
        }
        zzdun zzdunVar = this.zzhvp;
        if (zzdunVar != null) {
            iZznv += zzdtq.zzb(3, zzdunVar);
        }
        int[] iArr2 = this.zzhvs;
        int i = 0;
        if (iArr2 != null && iArr2.length > 0) {
            int i2 = 0;
            int iZzgf = 0;
            while (true) {
                iArr = this.zzhvs;
                if (i2 >= iArr.length) {
                    break;
                }
                iZzgf += zzdtq.zzgf(iArr[i2]);
                i2++;
            }
            iZznv = iZznv + iZzgf + (iArr.length * 1);
        }
        zzdua.zzb.zzh.zza zzaVar = this.zzhvu;
        if (zzaVar != null && zzaVar != null) {
            iZznv += zzdtq.zzae(8, zzaVar.zzab());
        }
        String[] strArr = this.zzhvv;
        if (strArr == null || strArr.length <= 0) {
            return iZznv;
        }
        int iZzhj = 0;
        int i3 = 0;
        while (true) {
            String[] strArr2 = this.zzhvv;
            if (i >= strArr2.length) {
                return iZznv + iZzhj + (i3 * 1);
            }
            String str2 = strArr2[i];
            if (str2 != null) {
                i3++;
                iZzhj += zzdtq.zzhj(str2);
            }
            i++;
        }
    }
}
