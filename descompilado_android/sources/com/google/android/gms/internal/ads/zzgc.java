package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.util.UUID;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzgc {
    public static final int CHANNEL_OUT_7POINT1_SURROUND;
    public static final UUID zzacf;
    private static final UUID zzacg;
    private static final UUID zzach;
    private static final UUID zzaci;

    public static long zzdi(long j) {
        return j == C.TIME_UNSET ? C.TIME_UNSET : j / 1000;
    }

    public static long zzdj(long j) {
        return j == C.TIME_UNSET ? C.TIME_UNSET : j * 1000;
    }

    static {
        CHANNEL_OUT_7POINT1_SURROUND = zzoh.SDK_INT < 23 ? 1020 : 6396;
        zzacf = new UUID(0L, 0L);
        zzacg = new UUID(1186680826959645954L, -5988876978535335093L);
        zzach = new UUID(-1301668207276963122L, -6645017420763422227L);
        zzaci = new UUID(-7348484286925749626L, -6083546864340672619L);
    }
}
