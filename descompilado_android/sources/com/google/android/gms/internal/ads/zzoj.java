package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzoj {
    private final int height;
    private final int width;
    public final List<byte[]> zzafd;
    public final int zzaqq;
    public final float zzbgc;

    public static zzoj zzf(zzoa zzoaVar) throws zzgt {
        int i;
        int i2;
        float f;
        try {
            zzoaVar.zzbe(4);
            int unsignedByte = (zzoaVar.readUnsignedByte() & 3) + 1;
            if (unsignedByte == 3) {
                throw new IllegalStateException();
            }
            ArrayList arrayList = new ArrayList();
            int unsignedByte2 = zzoaVar.readUnsignedByte() & 31;
            for (int i3 = 0; i3 < unsignedByte2; i3++) {
                arrayList.add(zzg(zzoaVar));
            }
            int unsignedByte3 = zzoaVar.readUnsignedByte();
            for (int i4 = 0; i4 < unsignedByte3; i4++) {
                arrayList.add(zzg(zzoaVar));
            }
            if (unsignedByte2 > 0) {
                zzny zznyVarZzd = zznz.zzd((byte[]) arrayList.get(0), unsignedByte, ((byte[]) arrayList.get(0)).length);
                int i5 = zznyVarZzd.width;
                int i6 = zznyVarZzd.height;
                f = zznyVarZzd.zzbgc;
                i = i5;
                i2 = i6;
            } else {
                i = -1;
                i2 = -1;
                f = 1.0f;
            }
            return new zzoj(arrayList, unsignedByte, i, i2, f);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new zzgt("Error parsing AVC config", e);
        }
    }

    private zzoj(List<byte[]> list, int i, int i2, int i3, float f) {
        this.zzafd = list;
        this.zzaqq = i;
        this.width = i2;
        this.height = i3;
        this.zzbgc = f;
    }

    private static byte[] zzg(zzoa zzoaVar) {
        int unsignedShort = zzoaVar.readUnsignedShort();
        int position = zzoaVar.getPosition();
        zzoaVar.zzbe(unsignedShort);
        return zzns.zzc(zzoaVar.data, position, unsignedShort);
    }
}
