package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzazx implements zznc {
    private Uri uri;
    private final zznc zzeck;
    private final long zzecl;
    private final zznc zzecm;
    private long zzecn;

    zzazx(zznc zzncVar, int i, zznc zzncVar2) {
        this.zzeck = zzncVar;
        this.zzecl = i;
        this.zzecm = zzncVar2;
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final long zza(zznh zznhVar) throws IOException {
        long jMin;
        zznh zznhVar2;
        zznh zznhVar3;
        this.uri = zznhVar.uri;
        if (zznhVar.zzams >= this.zzecl) {
            zznhVar2 = null;
        } else {
            long j = zznhVar.zzams;
            if (zznhVar.zzcb != -1) {
                jMin = Math.min(zznhVar.zzcb, this.zzecl - j);
            } else {
                jMin = this.zzecl - j;
            }
            zznhVar2 = new zznh(zznhVar.uri, j, jMin, null);
        }
        if (zznhVar.zzcb == -1 || zznhVar.zzams + zznhVar.zzcb > this.zzecl) {
            zznhVar3 = new zznh(zznhVar.uri, Math.max(this.zzecl, zznhVar.zzams), zznhVar.zzcb != -1 ? Math.min(zznhVar.zzcb, (zznhVar.zzams + zznhVar.zzcb) - this.zzecl) : -1L, null);
        } else {
            zznhVar3 = null;
        }
        long jZza = zznhVar2 != null ? this.zzeck.zza(zznhVar2) : 0L;
        long jZza2 = zznhVar3 != null ? this.zzecm.zza(zznhVar3) : 0L;
        this.zzecn = zznhVar.zzams;
        if (jZza == -1 || jZza2 == -1) {
            return -1L;
        }
        return jZza + jZza2;
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        long j = this.zzecn;
        long j2 = this.zzecl;
        if (j < j2) {
            i3 = this.zzeck.read(bArr, i, (int) Math.min(i2, j2 - j));
            this.zzecn += (long) i3;
        } else {
            i3 = 0;
        }
        if (this.zzecn < this.zzecl) {
            return i3;
        }
        int i4 = this.zzecm.read(bArr, i + i3, i2 - i3);
        int i5 = i3 + i4;
        this.zzecn += (long) i4;
        return i5;
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final Uri getUri() {
        return this.uri;
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final void close() throws IOException {
        this.zzeck.close();
        this.zzecm.close();
    }
}
