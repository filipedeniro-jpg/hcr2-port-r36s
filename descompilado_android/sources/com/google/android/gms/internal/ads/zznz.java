package com.google.android.gms.internal.ads;

import android.util.Log;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznz {
    public static final byte[] zzbfx = {0, 0, 0, 1};
    private static final float[] zzbgj = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object zzbgk = new Object();
    private static int[] zzbgl = new int[10];

    public static void zzk(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i + 1;
            if (i3 >= iPosition) {
                byteBuffer.clear();
                return;
            }
            int i4 = byteBuffer.get(i) & 255;
            if (i2 == 3) {
                if (i4 == 1 && (byteBuffer.get(i3) & 31) == 7) {
                    ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
                    byteBufferDuplicate.position(i - 3);
                    byteBufferDuplicate.limit(iPosition);
                    byteBuffer.position(0);
                    byteBuffer.put(byteBufferDuplicate);
                    return;
                }
            } else if (i4 == 0) {
                i2++;
            }
            if (i4 != 0) {
                i2 = 0;
            }
            i = i3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:64:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:67:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:69:0x010f  */
    /* JADX WARN: Code duplicated, block: B:70:0x0112  */
    /* JADX WARN: Code duplicated, block: B:72:0x0115  */
    /* JADX WARN: Code duplicated, block: B:73:0x0117  */
    /* JADX WARN: Code duplicated, block: B:75:0x011a  */
    /* JADX WARN: Code duplicated, block: B:93:0x0176  */
    public static zzny zzd(byte[] bArr, int i, int i2) {
        int iZzim;
        boolean z;
        int iZzim2;
        boolean z2;
        boolean zZzil;
        int i3;
        int i4;
        float f;
        int i5;
        int i6;
        zzod zzodVar = new zzod(bArr, i, i2);
        zzodVar.zzbh(8);
        int iZzbg = zzodVar.zzbg(8);
        zzodVar.zzbh(16);
        int iZzim3 = zzodVar.zzim();
        if (iZzbg == 100 || iZzbg == 110 || iZzbg == 122 || iZzbg == 244 || iZzbg == 44 || iZzbg == 83 || iZzbg == 86 || iZzbg == 118 || iZzbg == 128 || iZzbg == 138) {
            iZzim = zzodVar.zzim();
            boolean zZzil2 = iZzim == 3 ? zzodVar.zzil() : false;
            zzodVar.zzim();
            zzodVar.zzim();
            zzodVar.zzbh(1);
            if (zzodVar.zzil()) {
                int i7 = iZzim != 3 ? 8 : 12;
                int i8 = 0;
                while (i8 < i7) {
                    if (zzodVar.zzil()) {
                        int i9 = i8 < 6 ? 16 : 64;
                        int iZzin = 8;
                        int i10 = 8;
                        for (int i11 = 0; i11 < i9; i11++) {
                            if (iZzin != 0) {
                                iZzin = ((zzodVar.zzin() + i10) + 256) % 256;
                            }
                            if (iZzin != 0) {
                                i10 = iZzin;
                            }
                        }
                    }
                    i8++;
                }
            }
            z = zZzil2;
        } else {
            iZzim = 1;
            z = false;
        }
        int iZzim4 = zzodVar.zzim() + 4;
        int iZzim5 = zzodVar.zzim();
        if (iZzim5 == 0) {
            iZzim2 = zzodVar.zzim() + 4;
        } else {
            if (iZzim5 == 1) {
                boolean zZzil3 = zzodVar.zzil();
                zzodVar.zzin();
                zzodVar.zzin();
                long jZzim = zzodVar.zzim();
                for (int i12 = 0; i12 < jZzim; i12++) {
                    zzodVar.zzim();
                }
                z2 = zZzil3;
                iZzim2 = 0;
            } else {
                iZzim2 = 0;
            }
            zzodVar.zzim();
            zzodVar.zzbh(1);
            int iZzim6 = zzodVar.zzim() + 1;
            int iZzim7 = zzodVar.zzim() + 1;
            zZzil = zzodVar.zzil();
            int i13 = (2 - (zZzil ? 1 : 0)) * iZzim7;
            if (!zZzil) {
                zzodVar.zzbh(1);
            }
            zzodVar.zzbh(1);
            i3 = iZzim6 << 4;
            i4 = i13 << 4;
            if (zzodVar.zzil()) {
                int iZzim8 = zzodVar.zzim();
                int iZzim9 = zzodVar.zzim();
                int iZzim10 = zzodVar.zzim();
                int iZzim11 = zzodVar.zzim();
                if (iZzim == 0) {
                    i6 = 2 - (zZzil ? 1 : 0);
                    i5 = 1;
                } else {
                    if (iZzim == 3) {
                        i5 = 1;
                    } else {
                        i5 = 2;
                    }
                    i6 = (2 - (zZzil ? 1 : 0)) * (iZzim == 1 ? 2 : 1);
                }
                i3 -= (iZzim8 + iZzim9) * i5;
                i4 -= (iZzim10 + iZzim11) * i6;
            }
            int i14 = i3;
            int i15 = i4;
            float f2 = 1.0f;
            if (zzodVar.zzil() || !zzodVar.zzil()) {
                f = 1.0f;
            } else {
                int iZzbg2 = zzodVar.zzbg(8);
                if (iZzbg2 == 255) {
                    int iZzbg3 = zzodVar.zzbg(16);
                    int iZzbg4 = zzodVar.zzbg(16);
                    if (iZzbg3 != 0 && iZzbg4 != 0) {
                        f2 = iZzbg3 / iZzbg4;
                    }
                    f = f2;
                } else {
                    float[] fArr = zzbgj;
                    if (iZzbg2 < fArr.length) {
                        f = fArr[iZzbg2];
                    } else {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append("Unexpected aspect_ratio_idc value: ");
                        sb.append(iZzbg2);
                        Log.w("NalUnitUtil", sb.toString());
                        f = 1.0f;
                    }
                }
            }
            return new zzny(iZzim3, i14, i15, f, z, zZzil, iZzim4, iZzim5, iZzim2, z2);
        }
        z2 = false;
        zzodVar.zzim();
        zzodVar.zzbh(1);
        int iZzim12 = zzodVar.zzim() + 1;
        int iZzim13 = zzodVar.zzim() + 1;
        zZzil = zzodVar.zzil();
        int i16 = (2 - (zZzil ? 1 : 0)) * iZzim13;
        if (!zZzil) {
            zzodVar.zzbh(1);
        }
        zzodVar.zzbh(1);
        i3 = iZzim12 << 4;
        i4 = i16 << 4;
        if (zzodVar.zzil()) {
            int iZzim14 = zzodVar.zzim();
            int iZzim15 = zzodVar.zzim();
            int iZzim16 = zzodVar.zzim();
            int iZzim17 = zzodVar.zzim();
            if (iZzim == 0) {
                i6 = 2 - (zZzil ? 1 : 0);
                i5 = 1;
            } else {
                if (iZzim == 3) {
                    i5 = 1;
                } else {
                    i5 = 2;
                }
                i6 = (2 - (zZzil ? 1 : 0)) * (iZzim == 1 ? 2 : 1);
            }
            i3 -= (iZzim14 + iZzim15) * i5;
            i4 -= (iZzim16 + iZzim17) * i6;
        }
        int i17 = i3;
        int i18 = i4;
        float f3 = 1.0f;
        if (zzodVar.zzil()) {
            f = 1.0f;
        } else {
            f = 1.0f;
        }
        return new zzny(iZzim3, i17, i18, f, z, zZzil, iZzim4, iZzim5, iZzim2, z2);
    }
}
