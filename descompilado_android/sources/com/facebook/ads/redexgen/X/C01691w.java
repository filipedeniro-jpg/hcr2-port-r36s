package com.facebook.ads.redexgen.X;

import java.util.Arrays;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1w, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01691w {
    private int B = G & 1048575;
    private byte[] C;
    private int D;
    private static final char[] F = {'U', 19, '0', 'M', 11, '(', 'E', 3, ' ', '=', 'Z', 24, '5', 'R', 16, '-', 'J', '\b', '%', 'B', 0, 29, ':', 'W', 21, '2', 'O', '\r', '*', 'G', 5, '\"', '?', '\\', 26, '7', 'T', 18, '/', 'L', '\n', '\'', 'D', 2, 31, '<', 'Y', 23, '4', 'Q', 15, ',', 'I', 7, '$', 'A', '^', 28, '9', 'V', 20, '1', 'N', '\f', ')', 'F', 4, '!', '>', '[', 25, '6', 'S', 17, '.', 'K', '\t', '&', 'C', 1, 30, ';', 'X', 22, '3', 'P', 14, '+', 'H', 6, '#', '@', ']', 27, '8'};
    private static final char[] H = {0, 1, 2, 3, 4, 5, 6, 7, '\b', '\t', '\n', 11, '\f', '\r', 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, ' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', 0, 1, 2, 3, 4, 5, 6, 7, '\b', '\t', '\n', 11, '\f', '\r', 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, ' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', 0, 1, 2, 3, 4, 5, 6, 7, '\b', '\t', '\n', 11, '\f', '\r', 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, ' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', 0, 1, 2, 3, 4, 5, 6, 7, '\b', '\t', '\n', 11, '\f', '\r', 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
    private static final byte[] E = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126};
    private static final int G = C01691w.class.hashCode();

    public C01691w(String keyStr) {
        int length = keyStr.length();
        this.D = length + 4;
        this.C = new byte[this.D];
        for (int i = 0; i < length; i++) {
            this.C[i + 4] = (byte) (keyStr.charAt(i) & 127);
        }
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [com.facebook.ads.redexgen.X.24] */
    public final String A(String str) {
        int i = this.B;
        int n = this.B;
        this.B = (n + 489913) & 1048575;
        for (int i2 = 0; i2 < 4; i2++) {
            this.C[i2] = (byte) ((i & 31) + 48);
            i >>= 5;
        }
        ?? r4 = new Object() { // from class: com.facebook.ads.redexgen.X.24
            private char C;
            private char[] D = Arrays.copyOf(C01691w.F, C01691w.F.length);
            private char E;
            private char F;

            {
                this.C = '_';
                this.E = '_';
                do {
                    char[] cArr = C01691w.H;
                    char c = this.E;
                    char[] cArr2 = this.D;
                    char c2 = (char) (this.C - 1);
                    this.C = c2;
                    this.E = cArr[c + cArr2[c2] + this.B.C[this.C % this.B.D]];
                    this.F = this.D[this.C];
                    this.D[this.C] = this.D[this.E];
                    this.D[this.E] = this.F;
                    char[] cArr3 = C01691w.H;
                    char c3 = this.E;
                    char[] cArr4 = this.D;
                    char c4 = (char) (this.C - 1);
                    this.C = c4;
                    this.E = cArr3[c3 + cArr4[c4] + this.B.C[this.C % this.B.D]];
                    this.F = this.D[this.C];
                    this.D[this.C] = this.D[this.E];
                    this.D[this.E] = this.F;
                    char[] cArr5 = C01691w.H;
                    char c5 = this.E;
                    char[] cArr6 = this.D;
                    char c6 = (char) (this.C - 1);
                    this.C = c6;
                    this.E = cArr5[c5 + cArr6[c6] + this.B.C[this.C % this.B.D]];
                    this.F = this.D[this.C];
                    this.D[this.C] = this.D[this.E];
                    this.D[this.E] = this.F;
                    char[] cArr7 = C01691w.H;
                    char c7 = this.E;
                    char[] cArr8 = this.D;
                    char c8 = (char) (this.C - 1);
                    this.C = c8;
                    this.E = cArr7[c7 + cArr8[c8] + this.B.C[this.C % this.B.D]];
                    this.F = this.D[this.C];
                    this.D[this.C] = this.D[this.E];
                    this.D[this.E] = this.F;
                    char[] cArr9 = C01691w.H;
                    char c9 = this.E;
                    char[] cArr10 = this.D;
                    char c10 = (char) (this.C - 1);
                    this.C = c10;
                    this.E = cArr9[c9 + cArr10[c10] + this.B.C[this.C % this.B.D]];
                    this.F = this.D[this.C];
                    this.D[this.C] = this.D[this.E];
                    this.D[this.E] = this.F;
                } while (this.C > 0);
                this.E = (char) 0;
            }

            public final byte A(byte c) {
                try {
                    this.C = C01691w.H[this.C + 1];
                    this.E = C01691w.H[this.E + this.D[this.C]];
                    this.F = this.D[this.C];
                    this.D[this.C] = this.D[this.E];
                    this.D[this.E] = this.F;
                    c = C01691w.E[(char) (this.D[C01691w.H[this.D[this.C] + this.D[this.E]]] + c)];
                    return c;
                } catch (ArrayIndexOutOfBoundsException e) {
                    return c;
                }
            }
        };
        int iPos = str.length();
        byte[] bArr = new byte[(iPos * 4) + 4];
        int i3 = 0;
        int oPos = 0;
        while (i3 < 4) {
            bArr[i3] = this.C[i3];
            i3++;
        }
        while (oPos < iPos) {
            oPos++;
            int iPos2 = str.charAt(oPos);
            if (iPos2 < 32 || iPos2 > 125) {
                int i4 = i3 + 1;
                bArr[i3] = r4.A((byte) 126);
                if (iPos2 > 2047) {
                    int oPos2 = i4 + 1;
                    int oPos3 = iPos2 >> 11;
                    bArr[i4] = r4.A((byte) (oPos3 + 80));
                    i4 = oPos2;
                }
                int oPos4 = i4 + 1;
                int oPos5 = iPos2 >> 6;
                bArr[i4] = r4.A((byte) ((oPos5 & 31) + 48));
                i3 = oPos4 + 1;
                int oPos6 = iPos2 & 63;
                bArr[oPos4] = r4.A((byte) (oPos6 + 48));
            } else {
                int oPos7 = i3 + 1;
                bArr[i3] = r4.A((byte) iPos2);
                i3 = oPos7;
            }
        }
        return new String(bArr, 0, i3);
    }
}
