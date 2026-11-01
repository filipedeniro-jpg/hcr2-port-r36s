package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zztd extends zzdts<zztd> {
    private Integer zzcac = null;
    public String zzcad = null;
    private Integer zzcae = null;
    private zzso zzcaf = null;
    private zztg zzcag = null;
    public long[] zzcah = zzdub.zzhqy;
    public zztb zzcai = null;
    private zzte zzcaj = null;
    private zzsh.zzh zzcak = null;
    public zzta zzcal = null;
    public zzsh.zzj zzcam = null;
    public zzsh.zzw zzcan = null;
    private zzsh.zza zzcao = null;

    public zztd() {
        this.zzhqk = null;
        this.zzhqt = -1;
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    public final void zza(zzdtq zzdtqVar) throws IOException {
        String str = this.zzcad;
        if (str != null) {
            zzdtqVar.zzg(10, str);
        }
        long[] jArr = this.zzcah;
        if (jArr != null && jArr.length > 0) {
            int i = 0;
            while (true) {
                long[] jArr2 = this.zzcah;
                if (i >= jArr2.length) {
                    break;
                }
                long j = jArr2[i];
                zzdtqVar.zzz(14, 0);
                zzdtqVar.zzfm(j);
                i++;
            }
        }
        zztb zztbVar = this.zzcai;
        if (zztbVar != null) {
            zzdtqVar.zza(15, zztbVar);
        }
        zzta zztaVar = this.zzcal;
        if (zztaVar != null) {
            zzdtqVar.zza(18, zztaVar);
        }
        zzsh.zzj zzjVar = this.zzcam;
        if (zzjVar != null) {
            zzdtqVar.zze(19, zzjVar);
        }
        zzsh.zzw zzwVar = this.zzcan;
        if (zzwVar != null) {
            zzdtqVar.zze(20, zzwVar);
        }
        super.zza(zzdtqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdts, com.google.android.gms.internal.ads.zzdtz
    protected final int zznv() {
        long[] jArr;
        int i;
        int iZznv = super.zznv();
        String str = this.zzcad;
        if (str != null) {
            iZznv += zzdtq.zzh(10, str);
        }
        long[] jArr2 = this.zzcah;
        if (jArr2 != null && jArr2.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                jArr = this.zzcah;
                if (i2 >= jArr.length) {
                    break;
                }
                long j = jArr[i2];
                if (((-128) & j) == 0) {
                    i = 1;
                } else if (((-16384) & j) == 0) {
                    i = 2;
                } else if (((-2097152) & j) == 0) {
                    i = 3;
                } else if (((-268435456) & j) == 0) {
                    i = 4;
                } else if (((-34359738368L) & j) == 0) {
                    i = 5;
                } else if (((-4398046511104L) & j) == 0) {
                    i = 6;
                } else if (((-562949953421312L) & j) == 0) {
                    i = 7;
                } else if (((-72057594037927936L) & j) == 0) {
                    i = 8;
                } else {
                    i = (Long.MIN_VALUE & j) == 0 ? 9 : 10;
                }
                i3 += i;
                i2++;
            }
            iZznv = iZznv + i3 + (jArr.length * 1);
        }
        zztb zztbVar = this.zzcai;
        if (zztbVar != null) {
            iZznv += zzdtq.zzb(15, zztbVar);
        }
        zzta zztaVar = this.zzcal;
        if (zztaVar != null) {
            iZznv += zzdtq.zzb(18, zztaVar);
        }
        zzsh.zzj zzjVar = this.zzcam;
        if (zzjVar != null) {
            iZznv += zzdpm.zzc(19, zzjVar);
        }
        zzsh.zzw zzwVar = this.zzcan;
        return zzwVar != null ? iZznv + zzdpm.zzc(20, zzwVar) : iZznv;
    }
}
