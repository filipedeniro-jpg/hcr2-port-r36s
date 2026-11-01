package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznd implements zznc {
    private final byte[] data;
    private Uri uri;
    private int zzbeg;
    private int zzbeh;

    public zznd(byte[] bArr) {
        zznt.checkNotNull(bArr);
        zznt.checkArgument(bArr.length > 0);
        this.data = bArr;
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final long zza(zznh zznhVar) throws IOException {
        this.uri = zznhVar.uri;
        this.zzbeg = (int) zznhVar.zzams;
        this.zzbeh = (int) (zznhVar.zzcb == -1 ? ((long) this.data.length) - zznhVar.zzams : zznhVar.zzcb);
        int i = this.zzbeh;
        if (i > 0 && this.zzbeg + i <= this.data.length) {
            return i;
        }
        int i2 = this.zzbeg;
        long j = zznhVar.zzcb;
        int length = this.data.length;
        StringBuilder sb = new StringBuilder(77);
        sb.append("Unsatisfiable range: [");
        sb.append(i2);
        sb.append(", ");
        sb.append(j);
        sb.append("], length: ");
        sb.append(length);
        throw new IOException(sb.toString());
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.zzbeh;
        if (i3 == 0) {
            return -1;
        }
        int iMin = Math.min(i2, i3);
        System.arraycopy(this.data, this.zzbeg, bArr, i, iMin);
        this.zzbeg += iMin;
        this.zzbeh -= iMin;
        return iMin;
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final Uri getUri() {
        return this.uri;
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final void close() throws IOException {
        this.uri = null;
    }
}
