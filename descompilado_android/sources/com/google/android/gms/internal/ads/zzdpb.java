package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdpb {
    private final byte[] buffer;
    private final zzdpm zzhft;

    private zzdpb(int i) {
        this.buffer = new byte[i];
        this.zzhft = zzdpm.zzaa(this.buffer);
    }

    public final zzdot zzaxn() {
        this.zzhft.zzayq();
        return new zzdpd(this.buffer);
    }

    public final zzdpm zzaxo() {
        return this.zzhft;
    }

    /* synthetic */ zzdpb(int i, zzdow zzdowVar) {
        this(i);
    }
}
