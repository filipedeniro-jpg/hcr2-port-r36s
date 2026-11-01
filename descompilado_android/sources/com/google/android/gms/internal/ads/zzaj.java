package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaj {
    private static final Comparator<byte[]> zzbt = new zzam();
    private final List<byte[]> zzbp = new ArrayList();
    private final List<byte[]> zzbq = new ArrayList(64);
    private int zzbr = 0;
    private final int zzbs = 4096;

    public zzaj(int i) {
    }

    public final synchronized byte[] zzc(int i) {
        for (int i2 = 0; i2 < this.zzbq.size(); i2++) {
            byte[] bArr = this.zzbq.get(i2);
            if (bArr.length >= i) {
                this.zzbr -= bArr.length;
                this.zzbq.remove(i2);
                this.zzbp.remove(bArr);
                return bArr;
            }
        }
        return new byte[i];
    }

    public final synchronized void zza(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.zzbs) {
                this.zzbp.add(bArr);
                int iBinarySearch = Collections.binarySearch(this.zzbq, bArr, zzbt);
                if (iBinarySearch < 0) {
                    iBinarySearch = (-iBinarySearch) - 1;
                }
                this.zzbq.add(iBinarySearch, bArr);
                this.zzbr += bArr.length;
                zzm();
            }
        }
    }

    private final synchronized void zzm() {
        while (this.zzbr > this.zzbs) {
            byte[] bArrRemove = this.zzbp.remove(0);
            this.zzbq.remove(bArrRemove);
            this.zzbr -= bArrRemove.length;
        }
    }
}
