package com.google.android.gms.internal.ads;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjo {
    private final zzoa zzanr = new zzoa(8);
    private int zzapk;

    public final boolean zza(zzix zzixVar) throws InterruptedException, IOException {
        long length = zzixVar.getLength();
        long j = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        if (length != -1 && length <= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            j = length;
        }
        int i = (int) j;
        zzixVar.zza(this.zzanr.data, 0, 4);
        long jZzig = this.zzanr.zzig();
        this.zzapk = 4;
        while (jZzig != 440786851) {
            int i2 = this.zzapk + 1;
            this.zzapk = i2;
            if (i2 == i) {
                return false;
            }
            zzixVar.zza(this.zzanr.data, 0, 1);
            jZzig = ((jZzig << 8) & (-256)) | ((long) (this.zzanr.data[0] & 255));
        }
        long jZzc = zzc(zzixVar);
        long j2 = this.zzapk;
        if (jZzc != Long.MIN_VALUE && (length == -1 || j2 + jZzc < length)) {
            while (true) {
                int i3 = this.zzapk;
                long j3 = j2 + jZzc;
                if (i3 < j3) {
                    if (zzc(zzixVar) == Long.MIN_VALUE) {
                        return false;
                    }
                    long jZzc2 = zzc(zzixVar);
                    if (jZzc2 < 0 || jZzc2 > 2147483647L) {
                        return false;
                    }
                    if (jZzc2 != 0) {
                        zzixVar.zzac((int) jZzc2);
                        this.zzapk = (int) (((long) this.zzapk) + jZzc2);
                    }
                } else if (i3 == j3) {
                    return true;
                }
            }
        }
        return false;
    }

    private final long zzc(zzix zzixVar) throws InterruptedException, IOException {
        int i = 0;
        zzixVar.zza(this.zzanr.data, 0, 1);
        int i2 = this.zzanr.data[0] & 255;
        if (i2 == 0) {
            return Long.MIN_VALUE;
        }
        int i3 = 128;
        int i4 = 0;
        while ((i2 & i3) == 0) {
            i3 >>= 1;
            i4++;
        }
        int i5 = i2 & (i3 ^ (-1));
        zzixVar.zza(this.zzanr.data, 1, i4);
        while (i < i4) {
            i++;
            i5 = (this.zzanr.data[i] & 255) + (i5 << 8);
        }
        this.zzapk += i4 + 1;
        return i5;
    }
}
