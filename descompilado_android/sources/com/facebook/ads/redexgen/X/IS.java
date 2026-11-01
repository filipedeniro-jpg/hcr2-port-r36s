package com.facebook.ads.redexgen.X;

import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import com.facebook.ads.internal.util.image.JavaBlurProcess;
import java.util.ArrayList;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class IS implements J3 {
    private static final short[] B = {512, 512, 456, 512, 328, 456, 335, 512, 405, 328, 271, 456, 388, 335, 292, 512, 454, 405, 364, 328, 298, 271, 496, 456, 420, 388, 360, 335, 312, 292, 273, 512, 482, 454, 428, 405, 383, 364, 345, 328, 312, 298, 284, 271, 259, 496, 475, 456, 437, 420, 404, 388, 374, 360, 347, 335, 323, 312, 302, 292, 282, 273, 265, 512, 497, 482, 468, 454, 441, 428, 417, 405, 394, 383, 373, 364, 354, 345, 337, 328, 320, 312, 305, 298, 291, 284, 278, 271, 265, 259, 507, 496, 485, 475, 465, 456, 446, 437, 428, 420, 412, 404, 396, 388, 381, 374, 367, 360, 354, 347, 341, 335, 329, 323, 318, 312, 307, 302, 297, 292, 287, 282, 278, 273, 269, 265, 261, 512, 505, 497, 489, 482, 475, 468, 461, 454, 447, 441, 435, 428, 422, 417, 411, 405, 399, 394, 389, 383, 378, 373, 368, 364, 359, 354, 350, 345, 341, 337, 332, 328, 324, 320, 316, 312, 309, 305, 301, 298, 294, 291, 287, 284, 281, 278, 274, 271, 268, 265, 262, 259, 257, 507, 501, 496, 491, 485, 480, 475, 470, 465, 460, 456, 451, 446, 442, 437, 433, 428, 424, 420, 416, 412, 408, 404, 400, 396, 392, 388, 385, 381, 377, 374, 370, 367, 363, 360, 357, 354, 350, 347, 344, 341, 338, 335, 332, 329, 326, 323, 320, 318, 315, 312, 310, 307, 304, 302, 299, 297, 294, 292, 289, 287, 285, 282, 280, 278, 275, 273, 271, 269, 267, 265, 263, 261, 259};
    private static final byte[] C = {9, 11, 12, 13, 13, 14, 14, 15, 15, 15, 15, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24};

    public final Bitmap A(Bitmap original, float radius) {
        int width = original.getWidth();
        int w = original.getHeight();
        try {
            int[] iArr = new int[width * w];
            original.getPixels(iArr, 0, width, 0, 0, width, w);
            int i = IG.C;
            ArrayList<JavaBlurProcess.BlurTask> horizontal = new ArrayList<>(i);
            ArrayList<JavaBlurProcess.BlurTask> vertical = new ArrayList<>(i);
            for (int i2 = 0; i2 < i; i2++) {
                int w2 = w;
                horizontal.add(new IU(iArr, width, w2, (int) radius, i, i2, 1));
                w = w2;
                vertical.add(new IU(iArr, width, w, (int) radius, i, i2, 2));
            }
            try {
                IG.B.invokeAll(horizontal);
                try {
                    IG.B.invokeAll(vertical);
                    try {
                        return Bitmap.createBitmap(iArr, width, w, Bitmap.Config.ARGB_8888);
                    } catch (OutOfMemoryError e) {
                        return null;
                    }
                } catch (InterruptedException e2) {
                    return null;
                }
            } catch (InterruptedException e3) {
                return null;
            }
        } catch (OutOfMemoryError e4) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void C(int[] src, int w, int h, int radius, int cores, int core, int step) {
        int maxY = w - 1;
        int minX = h - 1;
        int mul_sum = (radius * 2) + 1;
        short s = B[radius];
        byte b = C[radius];
        int[] iArr = new int[mul_sum];
        if (step == 1) {
            int minY = ((core + 1) * h) / cores;
            for (int wm = (core * h) / cores; wm < minY; wm++) {
                long sum_in_r = 0;
                long sum_out_b = 0;
                long sum_out_g = 0;
                long sum_out_r = 0;
                long sum_r = 0;
                long j = 0;
                long j2 = 0;
                long j3 = 0;
                long j4 = 0;
                int i = w * wm;
                for (int i2 = 0; i2 <= radius; i2++) {
                    int src_i = src[i];
                    iArr[i2] = src_i;
                    int src_i2 = src[i];
                    int wm2 = i2 + 1;
                    j4 += (long) (((src_i2 >>> 16) & 255) * wm2);
                    int src_i3 = src[i];
                    int wm3 = i2 + 1;
                    j3 += (long) (((src_i3 >>> 8) & 255) * wm3);
                    int src_i4 = src[i];
                    int wm4 = i2 + 1;
                    j2 += (long) ((src_i4 & 255) * wm4);
                    int src_i5 = src[i];
                    sum_out_g += (long) ((src_i5 >>> 16) & 255);
                    int src_i6 = src[i];
                    sum_out_b += (long) ((src_i6 >>> 8) & 255);
                    int src_i7 = src[i];
                    sum_in_r += (long) (src_i7 & 255);
                }
                for (int i3 = 1; i3 <= radius; i3++) {
                    if (i3 <= maxY) {
                        i++;
                    }
                    int wm5 = i3 + radius;
                    int src_i8 = src[i];
                    iArr[wm5] = src_i8;
                    int src_i9 = src[i];
                    int wm6 = radius + 1;
                    j4 += (long) (((src_i9 >>> 16) & 255) * (wm6 - i3));
                    int src_i10 = src[i];
                    int wm7 = radius + 1;
                    j3 += (long) (((src_i10 >>> 8) & 255) * (wm7 - i3));
                    int src_i11 = src[i];
                    int wm8 = radius + 1;
                    j2 += (long) ((src_i11 & 255) * (wm8 - i3));
                    int src_i12 = src[i];
                    j += (long) ((src_i12 >>> 16) & 255);
                    int src_i13 = src[i];
                    sum_r += (long) ((src_i13 >>> 8) & 255);
                    int src_i14 = src[i];
                    sum_out_r += (long) (src_i14 & 255);
                }
                int i4 = radius;
                int wm9 = radius;
                if (wm9 > maxY) {
                    wm9 = maxY;
                }
                int src_i15 = wm * w;
                int i5 = wm9 + src_i15;
                int i6 = wm * w;
                int i7 = 0;
                while (wm < w) {
                    int src_i16 = src[i6];
                    src[i6] = (int) (((long) (src_i16 & ViewCompat.MEASURED_STATE_MASK)) | ((((((long) s) * j4) >>> b) & 255) << 16) | ((((((long) s) * j3) >>> b) & 255) << 8) | (((((long) s) * j2) >>> b) & 255));
                    i6++;
                    long j5 = j4 - sum_out_g;
                    long j6 = j3 - sum_out_b;
                    long j7 = j2 - sum_in_r;
                    int i8 = (i4 + mul_sum) - radius;
                    if (i8 >= mul_sum) {
                        i8 -= mul_sum;
                    }
                    int stack_start = iArr[i8];
                    long sum_out_g2 = sum_out_g - ((long) ((stack_start >>> 16) & 255));
                    int stack_start2 = iArr[i8];
                    long sum_out_b2 = sum_out_b - ((long) ((stack_start2 >>> 8) & 255));
                    int stack_start3 = iArr[i8];
                    long sum_in_r2 = sum_in_r - ((long) (stack_start3 & 255));
                    if (wm9 < maxY) {
                        i5++;
                        wm9++;
                    }
                    int stack_start4 = src[i5];
                    iArr[i8] = stack_start4;
                    int stack_start5 = src[i5];
                    long j8 = j + ((long) ((stack_start5 >>> 16) & 255));
                    int stack_start6 = src[i5];
                    long sum_r2 = sum_r + ((long) ((stack_start6 >>> 8) & 255));
                    int stack_start7 = src[i5];
                    long sum_out_r2 = sum_out_r + ((long) (stack_start7 & 255));
                    j4 = j5 + j8;
                    j3 = j6 + sum_r2;
                    j2 = j7 + sum_out_r2;
                    i4++;
                    if (i4 >= mul_sum) {
                        i4 = 0;
                    }
                    int stack_start8 = iArr[i4];
                    sum_out_g = sum_out_g2 + ((long) ((stack_start8 >>> 16) & 255));
                    int stack_start9 = iArr[i4];
                    sum_out_b = sum_out_b2 + ((long) ((stack_start9 >>> 8) & 255));
                    int stack_start10 = iArr[i4];
                    sum_in_r = sum_in_r2 + ((long) (stack_start10 & 255));
                    int stack_start11 = iArr[i4];
                    j = j8 - ((long) ((stack_start11 >>> 16) & 255));
                    int stack_start12 = iArr[i4];
                    sum_r = sum_r2 - ((long) ((stack_start12 >>> 8) & 255));
                    int stack_start13 = iArr[i4];
                    sum_out_r = sum_out_r2 - ((long) (stack_start13 & 255));
                    i7++;
                }
            }
            return;
        }
        if (step == 2) {
            int i9 = ((core + 1) * w) / cores;
            for (int maxX = (core * w) / cores; maxX < i9; maxX++) {
                long sum_in_r3 = 0;
                long sum_out_b3 = 0;
                long sum_out_g3 = 0;
                long sum_out_r3 = 0;
                long sum_r3 = 0;
                long j9 = 0;
                long j10 = 0;
                long j11 = 0;
                long j12 = 0;
                int i10 = maxX;
                for (int i11 = 0; i11 <= radius; i11++) {
                    int stack_start14 = src[i10];
                    iArr[i11] = stack_start14;
                    int stack_start15 = src[i10];
                    int maxX2 = i11 + 1;
                    j12 += (long) (((stack_start15 >>> 16) & 255) * maxX2);
                    int stack_start16 = src[i10];
                    int maxX3 = i11 + 1;
                    j11 += (long) (((stack_start16 >>> 8) & 255) * maxX3);
                    int stack_start17 = src[i10];
                    int maxX4 = i11 + 1;
                    j10 += (long) ((stack_start17 & 255) * maxX4);
                    int stack_start18 = src[i10];
                    sum_out_g3 += (long) ((stack_start18 >>> 16) & 255);
                    int stack_start19 = src[i10];
                    sum_out_b3 += (long) ((stack_start19 >>> 8) & 255);
                    int stack_start20 = src[i10];
                    sum_in_r3 += (long) (stack_start20 & 255);
                }
                for (int i12 = 1; i12 <= radius; i12++) {
                    if (i12 <= minX) {
                        i10 += w;
                    }
                    int maxX5 = i12 + radius;
                    int stack_start21 = src[i10];
                    iArr[maxX5] = stack_start21;
                    int stack_start22 = src[i10];
                    int maxX6 = radius + 1;
                    j12 += (long) (((stack_start22 >>> 16) & 255) * (maxX6 - i12));
                    int stack_start23 = src[i10];
                    int maxX7 = radius + 1;
                    j11 += (long) (((stack_start23 >>> 8) & 255) * (maxX7 - i12));
                    int stack_start24 = src[i10];
                    int maxX8 = radius + 1;
                    j10 += (long) ((stack_start24 & 255) * (maxX8 - i12));
                    int stack_start25 = src[i10];
                    j9 += (long) ((stack_start25 >>> 16) & 255);
                    int stack_start26 = src[i10];
                    sum_r3 += (long) ((stack_start26 >>> 8) & 255);
                    int stack_start27 = src[i10];
                    sum_out_r3 += (long) (stack_start27 & 255);
                }
                int i13 = radius;
                int maxX9 = radius;
                if (maxX9 > minX) {
                    maxX9 = minX;
                }
                int stack_start28 = maxX9 * w;
                int i14 = maxX + stack_start28;
                int i15 = maxX;
                int i16 = 0;
                while (maxX < h) {
                    int stack_start29 = src[i15];
                    src[i15] = (int) (((long) (stack_start29 & ViewCompat.MEASURED_STATE_MASK)) | ((((((long) s) * j12) >>> b) & 255) << 16) | ((((((long) s) * j11) >>> b) & 255) << 8) | (((((long) s) * j10) >>> b) & 255));
                    i15 += w;
                    long j13 = j12 - sum_out_g3;
                    long j14 = j11 - sum_out_b3;
                    long j15 = j10 - sum_in_r3;
                    int i17 = (i13 + mul_sum) - radius;
                    if (i17 >= mul_sum) {
                        i17 -= mul_sum;
                    }
                    int stack_start30 = iArr[i17];
                    long sum_out_g4 = sum_out_g3 - ((long) ((stack_start30 >>> 16) & 255));
                    int stack_start31 = iArr[i17];
                    long sum_out_b4 = sum_out_b3 - ((long) ((stack_start31 >>> 8) & 255));
                    int stack_start32 = iArr[i17];
                    long sum_in_r4 = sum_in_r3 - ((long) (stack_start32 & 255));
                    if (maxX9 < minX) {
                        i14 += w;
                        maxX9++;
                    }
                    int stack_start33 = src[i14];
                    iArr[i17] = stack_start33;
                    int stack_start34 = src[i14];
                    long j16 = j9 + ((long) ((stack_start34 >>> 16) & 255));
                    int stack_start35 = src[i14];
                    long sum_r4 = sum_r3 + ((long) ((stack_start35 >>> 8) & 255));
                    int stack_start36 = src[i14];
                    long sum_out_r4 = sum_out_r3 + ((long) (stack_start36 & 255));
                    j12 = j13 + j16;
                    j11 = j14 + sum_r4;
                    j10 = j15 + sum_out_r4;
                    i13++;
                    if (i13 >= mul_sum) {
                        i13 = 0;
                    }
                    int stack_start37 = iArr[i13];
                    sum_out_g3 = sum_out_g4 + ((long) ((stack_start37 >>> 16) & 255));
                    int stack_start38 = iArr[i13];
                    sum_out_b3 = sum_out_b4 + ((long) ((stack_start38 >>> 8) & 255));
                    int stack_start39 = iArr[i13];
                    sum_in_r3 = sum_in_r4 + ((long) (stack_start39 & 255));
                    int stack_start40 = iArr[i13];
                    j9 = j16 - ((long) ((stack_start40 >>> 16) & 255));
                    int stack_start41 = iArr[i13];
                    sum_r3 = sum_r4 - ((long) ((stack_start41 >>> 8) & 255));
                    int stack_start42 = iArr[i13];
                    sum_out_r3 = sum_out_r4 - ((long) (stack_start42 & 255));
                    i16++;
                }
            }
        }
    }
}
