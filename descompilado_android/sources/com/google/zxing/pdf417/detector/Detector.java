package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class Detector {
    private static final int BARCODE_MIN_HEIGHT = 10;
    private static final float MAX_AVG_VARIANCE = 0.42f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.8f;
    private static final int MAX_PATTERN_DRIFT = 5;
    private static final int MAX_PIXEL_DRIFT = 3;
    private static final int ROW_STEP = 5;
    private static final int SKIPPED_ROW_COUNT_MAX = 25;
    private static final int[] INDEXES_START_PATTERN = {0, 4, 1, 5};
    private static final int[] INDEXES_STOP_PATTERN = {6, 2, 7, 3};
    private static final int[] START_PATTERN = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] STOP_PATTERN = {7, 1, 1, 3, 1, 1, 1, 2, 1};

    private Detector() {
    }

    public static PDF417DetectorResult detect(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, boolean z) throws NotFoundException {
        BitMatrix blackMatrix = binaryBitmap.getBlackMatrix();
        List<ResultPoint[]> listDetect = detect(z, blackMatrix);
        if (listDetect.isEmpty()) {
            blackMatrix = blackMatrix.m266clone();
            blackMatrix.rotate180();
            listDetect = detect(z, blackMatrix);
        }
        return new PDF417DetectorResult(blackMatrix, listDetect);
    }

    private static List<ResultPoint[]> detect(boolean z, BitMatrix bitMatrix) {
        int x;
        float y;
        ArrayList<ResultPoint[]> arrayList = new ArrayList();
        int iMax = 0;
        loop0: while (true) {
            int i = 0;
            boolean z2 = false;
            while (iMax < bitMatrix.getHeight()) {
                ResultPoint[] resultPointArrFindVertices = findVertices(bitMatrix, iMax, i);
                if (resultPointArrFindVertices[0] == null && resultPointArrFindVertices[3] == null) {
                    if (!z2) {
                        break;
                    }
                    for (ResultPoint[] resultPointArr : arrayList) {
                        if (resultPointArr[1] != null) {
                            iMax = (int) Math.max(iMax, resultPointArr[1].getY());
                        }
                        if (resultPointArr[3] != null) {
                            iMax = Math.max(iMax, (int) resultPointArr[3].getY());
                        }
                    }
                    iMax += 5;
                } else {
                    arrayList.add(resultPointArrFindVertices);
                    if (!z) {
                        break loop0;
                    }
                    if (resultPointArrFindVertices[2] != null) {
                        x = (int) resultPointArrFindVertices[2].getX();
                        y = resultPointArrFindVertices[2].getY();
                    } else {
                        x = (int) resultPointArrFindVertices[4].getX();
                        y = resultPointArrFindVertices[4].getY();
                    }
                    iMax = (int) y;
                    i = x;
                    z2 = true;
                }
            }
            break loop0;
        }
        return arrayList;
    }

    private static ResultPoint[] findVertices(BitMatrix bitMatrix, int i, int i2) {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        ResultPoint[] resultPointArr = new ResultPoint[8];
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i, i2, START_PATTERN), INDEXES_START_PATTERN);
        if (resultPointArr[4] != null) {
            i2 = (int) resultPointArr[4].getX();
            i = (int) resultPointArr[4].getY();
        }
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i, i2, STOP_PATTERN), INDEXES_STOP_PATTERN);
        return resultPointArr;
    }

    private static void copyToResult(ResultPoint[] resultPointArr, ResultPoint[] resultPointArr2, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            resultPointArr[iArr[i]] = resultPointArr2[i];
        }
    }

    private static ResultPoint[] findRowsWithPattern(BitMatrix bitMatrix, int i, int i2, int i3, int i4, int[] iArr) {
        int i5;
        boolean z;
        int i6;
        int i7;
        int[] iArr2;
        ResultPoint[] resultPointArr = new ResultPoint[4];
        int[] iArr3 = new int[iArr.length];
        int i8 = i3;
        while (true) {
            if (i8 >= i) {
                z = false;
                break;
            }
            int[] iArrFindGuardPattern = findGuardPattern(bitMatrix, i4, i8, i2, false, iArr, iArr3);
            if (iArrFindGuardPattern != null) {
                while (true) {
                    iArr2 = iArrFindGuardPattern;
                    if (i8 <= 0) {
                        break;
                    }
                    i8--;
                    iArrFindGuardPattern = findGuardPattern(bitMatrix, i4, i8, i2, false, iArr, iArr3);
                    if (iArrFindGuardPattern == null) {
                        i8++;
                        break;
                    }
                }
                float f = i8;
                resultPointArr[0] = new ResultPoint(iArr2[0], f);
                resultPointArr[1] = new ResultPoint(iArr2[1], f);
                z = true;
                break;
            }
            i8 += 5;
        }
        int i9 = i8 + 1;
        if (z) {
            int[] iArr4 = {(int) resultPointArr[0].getX(), (int) resultPointArr[1].getX()};
            int i10 = i9;
            int i11 = 0;
            while (true) {
                if (i10 >= i) {
                    i6 = i11;
                    i7 = i10;
                    break;
                }
                i6 = i11;
                i7 = i10;
                int[] iArrFindGuardPattern2 = findGuardPattern(bitMatrix, iArr4[0], i10, i2, false, iArr, iArr3);
                if (iArrFindGuardPattern2 != null && Math.abs(iArr4[0] - iArrFindGuardPattern2[0]) < 5 && Math.abs(iArr4[1] - iArrFindGuardPattern2[1]) < 5) {
                    iArr4 = iArrFindGuardPattern2;
                    i11 = 0;
                } else {
                    if (i6 > 25) {
                        break;
                    }
                    i11 = i6 + 1;
                }
                i10 = i7 + 1;
            }
            i9 = i7 - (i6 + 1);
            float f2 = i9;
            resultPointArr[2] = new ResultPoint(iArr4[0], f2);
            resultPointArr[3] = new ResultPoint(iArr4[1], f2);
        }
        if (i9 - i8 < 10) {
            for (i5 = 0; i5 < 4; i5++) {
                resultPointArr[i5] = null;
            }
        }
        return resultPointArr;
    }

    private static int[] findGuardPattern(BitMatrix bitMatrix, int i, int i2, int i3, boolean z, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int i4 = 0;
        while (bitMatrix.get(i, i2) && i > 0) {
            int i5 = i4 + 1;
            if (i4 >= 3) {
                break;
            }
            i--;
            i4 = i5;
        }
        int length = iArr.length;
        int i6 = i;
        boolean z2 = z;
        int i7 = 0;
        while (true) {
            if (i >= i3) {
                if (i7 != length - 1 || patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) >= MAX_AVG_VARIANCE) {
                    return null;
                }
                return new int[]{i6, i - 1};
            }
            if (bitMatrix.get(i, i2) ^ z2) {
                iArr2[i7] = iArr2[i7] + 1;
            } else {
                int i8 = length - 1;
                if (i7 != i8) {
                    i7++;
                } else {
                    if (patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                        return new int[]{i6, i};
                    }
                    i6 += iArr2[0] + iArr2[1];
                    int i9 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i9);
                    iArr2[i9] = 0;
                    iArr2[i8] = 0;
                    i7--;
                }
                iArr2[i7] = 1;
                z2 = z2 ? false : true;
            }
            i++;
        }
    }

    private static float patternMatchVariance(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = i;
        float f3 = f2 / i2;
        float f4 = f * f3;
        float f5 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = iArr[i4];
            float f6 = iArr2[i4] * f3;
            float f7 = i5;
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }
}
