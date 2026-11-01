package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdeb<P> {
    private final P zzgsd;
    private final byte[] zzgse;
    private final zzdiy zzgsf;
    private final zzdjq zzgsg;

    public zzdeb(P p, byte[] bArr, zzdiy zzdiyVar, zzdjq zzdjqVar) {
        this.zzgsd = p;
        this.zzgse = Arrays.copyOf(bArr, bArr.length);
        this.zzgsf = zzdiyVar;
        this.zzgsg = zzdjqVar;
    }

    public final P zzapm() {
        return this.zzgsd;
    }

    public final zzdiy zzapn() {
        return this.zzgsf;
    }

    public final zzdjq zzapo() {
        return this.zzgsg;
    }

    public final byte[] zzapp() {
        byte[] bArr = this.zzgse;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }
}
