package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdur implements zzbb {
    private static zzdva zzcp = zzdva.zzn(zzdur.class);
    private String type;
    private long zzaum;
    private zzbe zzhvy;
    private ByteBuffer zzhwc;
    private long zzhwd;
    private zzduu zzhwf;
    private long zzhwe = -1;
    private ByteBuffer zzhwg = null;
    private boolean zzhwb = true;
    boolean zzhwa = true;

    private final synchronized void zzbcv() {
        if (!this.zzhwb) {
            try {
                zzdva zzdvaVar = zzcp;
                String strValueOf = String.valueOf(this.type);
                zzdvaVar.zzhr(strValueOf.length() != 0 ? "mem mapping ".concat(strValueOf) : new String("mem mapping "));
                this.zzhwc = this.zzhwf.zzh(this.zzhwd, this.zzhwe);
                this.zzhwb = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected abstract void zzg(ByteBuffer byteBuffer);

    protected zzdur(String str) {
        this.type = str;
    }

    @Override // com.google.android.gms.internal.ads.zzbb
    public final void zza(zzduu zzduuVar, ByteBuffer byteBuffer, long j, zzba zzbaVar) throws IOException {
        this.zzhwd = zzduuVar.position();
        this.zzaum = this.zzhwd - ((long) byteBuffer.remaining());
        this.zzhwe = j;
        this.zzhwf = zzduuVar;
        zzduuVar.zzew(zzduuVar.position() + j);
        this.zzhwb = false;
        this.zzhwa = false;
        zzbcw();
    }

    public final synchronized void zzbcw() {
        zzbcv();
        zzdva zzdvaVar = zzcp;
        String strValueOf = String.valueOf(this.type);
        zzdvaVar.zzhr(strValueOf.length() != 0 ? "parsing details of ".concat(strValueOf) : new String("parsing details of "));
        if (this.zzhwc != null) {
            ByteBuffer byteBuffer = this.zzhwc;
            this.zzhwa = true;
            byteBuffer.rewind();
            zzg(byteBuffer);
            if (byteBuffer.remaining() > 0) {
                this.zzhwg = byteBuffer.slice();
            }
            this.zzhwc = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbb
    public final String getType() {
        return this.type;
    }

    @Override // com.google.android.gms.internal.ads.zzbb
    public final void zza(zzbe zzbeVar) {
        this.zzhvy = zzbeVar;
    }
}
