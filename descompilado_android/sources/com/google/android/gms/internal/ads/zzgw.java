package com.google.android.gms.internal.ads;

import com.ironsource.mediationsdk.logger.IronSourceError;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzgw {
    public static final zzgw zzafx = new zzgw(1.0f, 1.0f);
    public final float zzafy;
    public final float zzafz;
    private final int zzaga;

    public zzgw(float f, float f2) {
        this.zzafy = f;
        this.zzafz = f2;
        this.zzaga = Math.round(f * 1000.0f);
    }

    public final long zzdo(long j) {
        return j * ((long) this.zzaga);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzgw zzgwVar = (zzgw) obj;
            if (this.zzafy == zzgwVar.zzafy && this.zzafz == zzgwVar.zzafz) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((Float.floatToRawIntBits(this.zzafy) + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31) + Float.floatToRawIntBits(this.zzafz);
    }
}
