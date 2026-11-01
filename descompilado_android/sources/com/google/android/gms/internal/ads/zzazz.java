package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzazz {
    private long zzcu;

    public final long zzl(ByteBuffer byteBuffer) {
        zzbg zzbgVar;
        zzbd zzbdVar;
        long j = this.zzcu;
        if (j > 0) {
            return j;
        }
        try {
            ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
            byteBufferDuplicate.flip();
            Iterator<zzbb> it = new zzaz(new zzazw(byteBufferDuplicate), zzbab.zzecp).zzbcx().iterator();
            while (true) {
                zzbgVar = null;
                if (!it.hasNext()) {
                    zzbdVar = null;
                    break;
                }
                zzbb next = it.next();
                if (next instanceof zzbd) {
                    zzbdVar = (zzbd) next;
                    break;
                }
            }
            for (zzbb zzbbVar : zzbdVar.zzbcx()) {
                if (zzbbVar instanceof zzbg) {
                    zzbgVar = (zzbg) zzbbVar;
                    break;
                }
            }
            this.zzcu = (zzbgVar.getDuration() * 1000) / zzbgVar.zzq();
            return this.zzcu;
        } catch (IOException | RuntimeException unused) {
            return 0L;
        }
    }
}
