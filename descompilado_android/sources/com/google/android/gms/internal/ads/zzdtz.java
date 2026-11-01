package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzdtz {
    protected volatile int zzhqt = -1;

    public void zza(zzdtq zzdtqVar) throws IOException {
    }

    protected int zznv() {
        return 0;
    }

    public final int zzazp() {
        int iZznv = zznv();
        this.zzhqt = iZznv;
        return iZznv;
    }

    public static final byte[] zzb(zzdtz zzdtzVar) {
        byte[] bArr = new byte[zzdtzVar.zzazp()];
        try {
            zzdtq zzdtqVarZzp = zzdtq.zzp(bArr, 0, bArr.length);
            zzdtzVar.zza(zzdtqVarZzp);
            zzdtqVarZzp.zzayq();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public String toString() {
        return zzdty.zza(this);
    }

    @Override // 
    /* JADX INFO: renamed from: zzbcd, reason: merged with bridge method [inline-methods] */
    public zzdtz clone() throws CloneNotSupportedException {
        return (zzdtz) super.clone();
    }
}
