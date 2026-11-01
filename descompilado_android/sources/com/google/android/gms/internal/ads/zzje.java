package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzje {
    public final int zzamy = 1;
    public final byte[] zzamz;

    public zzje(int i, byte[] bArr) {
        this.zzamz = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzje zzjeVar = (zzje) obj;
            if (this.zzamy == zzjeVar.zzamy && Arrays.equals(this.zzamz, zzjeVar.zzamz)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.zzamy * 31) + Arrays.hashCode(this.zzamz);
    }
}
