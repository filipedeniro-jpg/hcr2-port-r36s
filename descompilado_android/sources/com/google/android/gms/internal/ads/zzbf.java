package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbf extends zzdur {
    private ByteBuffer zzcq;

    public zzbf(String str) {
        super(str);
    }

    @Override // com.google.android.gms.internal.ads.zzdur
    public final void zzg(ByteBuffer byteBuffer) {
        this.zzcq = byteBuffer;
        byteBuffer.position(byteBuffer.position() + byteBuffer.remaining());
    }
}
