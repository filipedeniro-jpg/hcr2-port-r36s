package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzif {
    private int flags;

    public void clear() {
        this.flags = 0;
    }

    public final boolean zzfo() {
        return zzw(Integer.MIN_VALUE);
    }

    public final boolean zzfp() {
        return zzw(4);
    }

    public final boolean zzfq() {
        return zzw(1);
    }

    public final void setFlags(int i) {
        this.flags = i;
    }

    public final void zzv(int i) {
        this.flags |= Integer.MIN_VALUE;
    }

    protected final boolean zzw(int i) {
        return (this.flags & i) == i;
    }
}
