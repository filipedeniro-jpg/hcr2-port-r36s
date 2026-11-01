package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdut extends zzdur implements zzbb {
    private int flags;
    private int version;

    protected zzdut(String str) {
        super(str);
    }

    public final int getVersion() {
        if (!this.zzhwa) {
            zzbcw();
        }
        return this.version;
    }

    protected final long zzo(ByteBuffer byteBuffer) {
        this.version = zzbc.zza(byteBuffer.get());
        this.flags = (zzbc.zzb(byteBuffer) << 8) + 0 + zzbc.zza(byteBuffer.get());
        return 4L;
    }
}
