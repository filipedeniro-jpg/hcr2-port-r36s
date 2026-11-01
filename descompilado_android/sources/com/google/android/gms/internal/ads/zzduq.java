package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzduq extends zzdus implements zzbb {
    private String type;
    private long zzaum;
    private zzbe zzhvy;
    private boolean zzhvz;

    public zzduq(String str) {
        this.type = str;
    }

    @Override // com.google.android.gms.internal.ads.zzbb
    public final void zza(zzbe zzbeVar) {
        this.zzhvy = zzbeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbb
    public final String getType() {
        return this.type;
    }

    @Override // com.google.android.gms.internal.ads.zzbb
    public final void zza(zzduu zzduuVar, ByteBuffer byteBuffer, long j, zzba zzbaVar) throws IOException {
        this.zzaum = zzduuVar.position() - ((long) byteBuffer.remaining());
        this.zzhvz = byteBuffer.remaining() == 16;
        zza(zzduuVar, j, zzbaVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdus
    public final void zza(zzduu zzduuVar, long j, zzba zzbaVar) throws IOException {
        this.zzhwf = zzduuVar;
        this.zzhwk = zzduuVar.position();
        this.zzbcr = this.zzhwk - ((long) ((this.zzhvz || 8 + j >= 4294967296L) ? 16 : 8));
        zzduuVar.zzew(zzduuVar.position() + j);
        this.zzaui = zzduuVar.position();
        this.zzhwi = zzbaVar;
    }
}
