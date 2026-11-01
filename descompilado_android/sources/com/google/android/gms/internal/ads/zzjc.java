package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzjc implements zzjd {
    private final long zzagm;

    public zzjc(long j) {
        this.zzagm = j;
    }

    @Override // com.google.android.gms.internal.ads.zzjd
    public final long zzdt(long j) {
        return 0L;
    }

    @Override // com.google.android.gms.internal.ads.zzjd
    public final boolean zzfy() {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzjd
    public final long getDurationUs() {
        return this.zzagm;
    }
}
