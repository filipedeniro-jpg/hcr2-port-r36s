package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzqe {
    private final float bottom;
    private final float left;
    private final float right;
    private final float top;
    private final int zzbpz;

    @VisibleForTesting
    public zzqe(float f, float f2, float f3, float f4, int i) {
        this.left = f;
        this.top = f2;
        this.right = f + f3;
        this.bottom = f2 + f4;
        this.zzbpz = i;
    }

    final float zzlw() {
        return this.left;
    }

    final float zzlx() {
        return this.top;
    }

    final float zzly() {
        return this.right;
    }

    final float zzlz() {
        return this.bottom;
    }

    final int zzma() {
        return this.zzbpz;
    }
}
