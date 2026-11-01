package com.google.android.gms.internal.ads;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzmx {
    public final int length;
    private int zzafu;
    private final zzmv[] zzbeb;

    public zzmx(zzmv... zzmvVarArr) {
        this.zzbeb = zzmvVarArr;
        this.length = zzmvVarArr.length;
    }

    public final zzmv zzax(int i) {
        return this.zzbeb[i];
    }

    public final zzmv[] zzhv() {
        return (zzmv[]) this.zzbeb.clone();
    }

    public final int hashCode() {
        if (this.zzafu == 0) {
            this.zzafu = Arrays.hashCode(this.zzbeb) + IronSourceError.ERROR_NON_EXISTENT_INSTANCE;
        }
        return this.zzafu;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.zzbeb, ((zzmx) obj).zzbeb);
    }
}
