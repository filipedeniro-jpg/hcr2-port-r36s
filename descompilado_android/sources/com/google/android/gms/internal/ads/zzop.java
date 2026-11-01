package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzop {
    public final List<byte[]> zzafd;
    public final int zzaqq;

    public static zzop zzh(zzoa zzoaVar) throws zzgt {
        try {
            zzoaVar.zzbe(21);
            int unsignedByte = zzoaVar.readUnsignedByte() & 3;
            int unsignedByte2 = zzoaVar.readUnsignedByte();
            int position = zzoaVar.getPosition();
            int i = 0;
            int i2 = 0;
            while (i < unsignedByte2) {
                zzoaVar.zzbe(1);
                int unsignedShort = zzoaVar.readUnsignedShort();
                int i3 = i2;
                for (int i4 = 0; i4 < unsignedShort; i4++) {
                    int unsignedShort2 = zzoaVar.readUnsignedShort();
                    i3 += unsignedShort2 + 4;
                    zzoaVar.zzbe(unsignedShort2);
                }
                i++;
                i2 = i3;
            }
            zzoaVar.zzbd(position);
            byte[] bArr = new byte[i2];
            int i5 = 0;
            int i6 = 0;
            while (i5 < unsignedByte2) {
                zzoaVar.zzbe(1);
                int unsignedShort3 = zzoaVar.readUnsignedShort();
                int i7 = i6;
                for (int i8 = 0; i8 < unsignedShort3; i8++) {
                    int unsignedShort4 = zzoaVar.readUnsignedShort();
                    System.arraycopy(zznz.zzbfx, 0, bArr, i7, zznz.zzbfx.length);
                    int length = i7 + zznz.zzbfx.length;
                    System.arraycopy(zzoaVar.data, zzoaVar.getPosition(), bArr, length, unsignedShort4);
                    i7 = length + unsignedShort4;
                    zzoaVar.zzbe(unsignedShort4);
                }
                i5++;
                i6 = i7;
            }
            return new zzop(i2 == 0 ? null : Collections.singletonList(bArr), unsignedByte + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new zzgt("Error parsing HEVC config", e);
        }
    }

    private zzop(List<byte[]> list, int i) {
        this.zzafd = list;
        this.zzaqq = i;
    }
}
