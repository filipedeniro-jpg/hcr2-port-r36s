package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: classes2.dex */
final class zzjk {
    private final byte[] buffer;
    private final zzjr zznx;

    private zzjk(int i) {
        this.buffer = new byte[i];
        this.zznx = zzjr.zzb(this.buffer);
    }

    public final zzjc zzbx() {
        this.zznx.zzcb();
        return new zzjm(this.buffer);
    }

    public final zzjr zzby() {
        return this.zznx;
    }

    /* synthetic */ zzjk(int i, zzjd zzjdVar) {
        this(i);
    }
}
