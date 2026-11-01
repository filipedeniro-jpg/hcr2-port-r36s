package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzmi {
    public static final zzmi zzbda = new zzmi(new zzmj[0]);
    public final int length;
    private int zzafu;
    private final zzmj[] zzbdb;

    public zzmi(zzmj... zzmjVarArr) {
        this.zzbdb = zzmjVarArr;
        this.length = zzmjVarArr.length;
    }

    public final zzmj zzau(int i) {
        return this.zzbdb[i];
    }

    public final int zza(zzmj zzmjVar) {
        for (int i = 0; i < this.length; i++) {
            if (this.zzbdb[i] == zzmjVar) {
                return i;
            }
        }
        return -1;
    }

    public final int hashCode() {
        if (this.zzafu == 0) {
            this.zzafu = Arrays.hashCode(this.zzbdb);
        }
        return this.zzafu;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzmi zzmiVar = (zzmi) obj;
            if (this.length == zzmiVar.length && Arrays.equals(this.zzbdb, zzmiVar.zzbdb)) {
                return true;
            }
        }
        return false;
    }
}
