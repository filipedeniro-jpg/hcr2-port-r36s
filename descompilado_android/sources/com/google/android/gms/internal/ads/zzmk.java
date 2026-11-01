package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzmk implements zzmv {
    private final int length;
    private int zzafu;
    private final zzgq[] zzbbu;
    private final zzmj zzbdc;
    private final int[] zzbdd;
    private final long[] zzbde;

    public zzmk(zzmj zzmjVar, int... iArr) {
        int i = 0;
        zznt.checkState(iArr.length > 0);
        this.zzbdc = (zzmj) zznt.checkNotNull(zzmjVar);
        this.length = iArr.length;
        this.zzbbu = new zzgq[this.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.zzbbu[i2] = zzmjVar.zzav(iArr[i2]);
        }
        Arrays.sort(this.zzbbu, new zzmm());
        this.zzbdd = new int[this.length];
        while (true) {
            int i3 = this.length;
            if (i >= i3) {
                this.zzbde = new long[i3];
                return;
            } else {
                this.zzbdd[i] = zzmjVar.zzh(this.zzbbu[i]);
                i++;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzmv
    public final zzmj zzhu() {
        return this.zzbdc;
    }

    @Override // com.google.android.gms.internal.ads.zzmv
    public final int length() {
        return this.zzbdd.length;
    }

    @Override // com.google.android.gms.internal.ads.zzmv
    public final zzgq zzav(int i) {
        return this.zzbbu[i];
    }

    @Override // com.google.android.gms.internal.ads.zzmv
    public final int zzaw(int i) {
        return this.zzbdd[0];
    }

    public int hashCode() {
        if (this.zzafu == 0) {
            this.zzafu = (System.identityHashCode(this.zzbdc) * 31) + Arrays.hashCode(this.zzbdd);
        }
        return this.zzafu;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzmk zzmkVar = (zzmk) obj;
            if (this.zzbdc == zzmkVar.zzbdc && Arrays.equals(this.zzbdd, zzmkVar.zzbdd)) {
                return true;
            }
        }
        return false;
    }
}
