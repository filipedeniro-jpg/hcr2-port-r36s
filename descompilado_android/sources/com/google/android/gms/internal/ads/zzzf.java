package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzzf {
    private final long time;

    @Nullable
    private final String zzcua;

    @Nullable
    private final zzzf zzcub;

    public zzzf(long j, @Nullable String str, @Nullable zzzf zzzfVar) {
        this.time = j;
        this.zzcua = str;
        this.zzcub = zzzfVar;
    }

    public final long getTime() {
        return this.time;
    }

    public final String zzpu() {
        return this.zzcua;
    }

    @Nullable
    public final zzzf zzpv() {
        return this.zzcub;
    }
}
