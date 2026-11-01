package com.google.android.gms.internal.ads;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzmj {
    public final int length;
    private int zzafu;
    private final zzgq[] zzbbu;

    public zzmj(zzgq... zzgqVarArr) {
        zznt.checkState(zzgqVarArr.length > 0);
        this.zzbbu = zzgqVarArr;
        this.length = zzgqVarArr.length;
    }

    public final zzgq zzav(int i) {
        return this.zzbbu[i];
    }

    public final int zzh(zzgq zzgqVar) {
        int i = 0;
        while (true) {
            zzgq[] zzgqVarArr = this.zzbbu;
            if (i >= zzgqVarArr.length) {
                return -1;
            }
            if (zzgqVar == zzgqVarArr[i]) {
                return i;
            }
            i++;
        }
    }

    public final int hashCode() {
        if (this.zzafu == 0) {
            this.zzafu = Arrays.hashCode(this.zzbbu) + IronSourceError.ERROR_NON_EXISTENT_INSTANCE;
        }
        return this.zzafu;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzmj zzmjVar = (zzmj) obj;
            if (this.length == zzmjVar.length && Arrays.equals(this.zzbbu, zzmjVar.zzbbu)) {
                return true;
            }
        }
        return false;
    }
}
