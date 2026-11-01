package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger bigIntegerValueOf = BigInteger.valueOf(900L);
        EXP900[1] = bigIntegerValueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(bigIntegerValueOf);
            i++;
        }
    }

    private DecodedBitStreamParser() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x004e  */
    static DecoderResult decode(int[] iArr, String str) throws FormatException {
        int iTextCompaction;
        StringBuilder sb = new StringBuilder(iArr.length << 1);
        Charset charsetForName = DEFAULT_ENCODING;
        int i = iArr[1];
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        int i2 = 2;
        while (i2 < iArr[0]) {
            if (i != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                switch (i) {
                    case 900:
                        iTextCompaction = textCompaction(iArr, i2, sb);
                        break;
                    case 901:
                        iTextCompaction = byteCompaction(i, iArr, charsetForName, i2, sb);
                        break;
                    case 902:
                        iTextCompaction = numericCompaction(iArr, i2, sb);
                        break;
                    default:
                        switch (i) {
                            case MACRO_PDF417_TERMINATOR /* 922 */:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                                throw FormatException.getFormatInstance();
                            case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                                iTextCompaction = byteCompaction(i, iArr, charsetForName, i2, sb);
                                break;
                            case ECI_USER_DEFINED /* 925 */:
                                iTextCompaction = i2 + 1;
                                break;
                            case ECI_GENERAL_PURPOSE /* 926 */:
                                iTextCompaction = i2 + 2;
                                break;
                            case ECI_CHARSET /* 927 */:
                                iTextCompaction = i2 + 1;
                                charsetForName = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(iArr[i2]).name());
                                break;
                            case 928:
                                iTextCompaction = decodeMacroBlock(iArr, i2, pDF417ResultMetadata);
                                break;
                            default:
                                iTextCompaction = textCompaction(iArr, i2 - 1, sb);
                                break;
                        }
                        break;
                }
            } else {
                iTextCompaction = i2 + 1;
                sb.append((char) iArr[i2]);
            }
            if (iTextCompaction < iArr.length) {
                i2 = iTextCompaction + 1;
                i = iArr[iTextCompaction];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (sb.length() == 0) {
            throw FormatException.getFormatInstance();
        }
        DecoderResult decoderResult = new DecoderResult(null, sb.toString(), null, str);
        decoderResult.setOther(pDF417ResultMetadata);
        return decoderResult;
    }

    private static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i + 2 > iArr[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] iArr2 = new int[2];
        int i2 = i;
        int i3 = 0;
        while (i3 < 2) {
            iArr2[i3] = iArr[i2];
            i3++;
            i2++;
        }
        pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
        StringBuilder sb = new StringBuilder();
        int iTextCompaction = textCompaction(iArr, i2, sb);
        pDF417ResultMetadata.setFileId(sb.toString());
        if (iArr[iTextCompaction] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
            int i4 = iTextCompaction + 1;
            int[] iArr3 = new int[iArr[0] - i4];
            boolean z = false;
            int i5 = 0;
            while (i4 < iArr[0] && !z) {
                int i6 = i4 + 1;
                int i7 = iArr[i4];
                if (i7 < 900) {
                    iArr3[i5] = i7;
                    i4 = i6;
                    i5++;
                } else if (i7 == MACRO_PDF417_TERMINATOR) {
                    pDF417ResultMetadata.setLastSegment(true);
                    i4 = i6 + 1;
                    z = true;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            pDF417ResultMetadata.setOptionalData(Arrays.copyOf(iArr3, i5));
            return i4;
        }
        if (iArr[iTextCompaction] != MACRO_PDF417_TERMINATOR) {
            return iTextCompaction;
        }
        pDF417ResultMetadata.setLastSegment(true);
        return iTextCompaction + 1;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:14:0x0034. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x0037. Please report as an issue. */
    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[(iArr[0] - i) << 1];
        int[] iArr3 = new int[(iArr[0] - i) << 1];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < 900) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
            } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i4 != 928) {
                    switch (i4) {
                        case 900:
                            iArr2[i2] = 900;
                            i2++;
                            break;
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i4) {
                                case MACRO_PDF417_TERMINATOR /* 922 */:
                                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                                case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                }
                i = i3 - 1;
                z = true;
            } else {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
            i = i3;
        }
        decodeTextCompaction(iArr2, iArr3, i2, sb);
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:67:0x00e1 A[PHI: r4
  0x00e1: PHI (r4v15 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode) = 
  (r4v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
  (r4v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
  (r4v1 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
  (r4v17 com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode)
 binds: [B:66:0x00df, B:50:0x00b5, B:36:0x008c, B:19:0x0052] A[DONT_GENERATE, DONT_INLINE]] */
    private static void decodeTextCompaction(int[] iArr, int[] iArr2, int i, StringBuilder sb) {
        char c;
        Mode mode;
        int i2;
        char c2;
        Mode mode2 = Mode.ALPHA;
        Mode mode3 = Mode.ALPHA;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            switch (mode2) {
                case ALPHA:
                    if (i4 < 26) {
                        i2 = i4 + 65;
                        c = (char) i2;
                    } else if (i4 == 26) {
                        c = ' ';
                    } else {
                        if (i4 == 27) {
                            mode2 = Mode.LOWER;
                        } else if (i4 == 28) {
                            mode2 = Mode.MIXED;
                        } else if (i4 == 29) {
                            mode = Mode.PUNCT_SHIFT;
                            c = 0;
                            Mode mode4 = mode;
                            mode3 = mode2;
                            mode2 = mode4;
                            break;
                        } else if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            sb.append((char) iArr2[i3]);
                        } else if (i4 == 900) {
                            mode2 = Mode.ALPHA;
                        }
                        c = 0;
                    }
                    break;
                case LOWER:
                    if (i4 < 26) {
                        i2 = i4 + 97;
                        c = (char) i2;
                    } else if (i4 == 26) {
                        c = ' ';
                    } else {
                        if (i4 != 27) {
                            if (i4 == 28) {
                                mode2 = Mode.MIXED;
                            } else if (i4 == 29) {
                                mode = Mode.PUNCT_SHIFT;
                            } else if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                sb.append((char) iArr2[i3]);
                            } else if (i4 == 900) {
                                mode2 = Mode.ALPHA;
                            }
                            c = 0;
                        } else {
                            mode = Mode.ALPHA_SHIFT;
                        }
                        c = 0;
                        Mode mode5 = mode;
                        mode3 = mode2;
                        mode2 = mode5;
                    }
                    break;
                case MIXED:
                    if (i4 < 25) {
                        c = MIXED_CHARS[i4];
                    } else {
                        if (i4 == 25) {
                            mode2 = Mode.PUNCT;
                        } else if (i4 == 26) {
                            c = ' ';
                            break;
                        } else if (i4 == 27) {
                            mode2 = Mode.LOWER;
                        } else if (i4 == 28) {
                            mode2 = Mode.ALPHA;
                        } else if (i4 == 29) {
                            mode = Mode.PUNCT_SHIFT;
                            c = 0;
                            Mode mode6 = mode;
                            mode3 = mode2;
                            mode2 = mode6;
                            break;
                        } else if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            sb.append((char) iArr2[i3]);
                        } else if (i4 == 900) {
                            mode2 = Mode.ALPHA;
                        }
                        c = 0;
                    }
                    break;
                case PUNCT:
                    if (i4 < 29) {
                        c = PUNCT_CHARS[i4];
                    } else {
                        if (i4 == 29) {
                            mode2 = Mode.ALPHA;
                        } else if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            sb.append((char) iArr2[i3]);
                        } else if (i4 == 900) {
                            mode2 = Mode.ALPHA;
                        }
                        c = 0;
                    }
                    break;
                case ALPHA_SHIFT:
                    if (i4 < 26) {
                        c2 = (char) (i4 + 65);
                        c = c2;
                        mode2 = mode3;
                    } else if (i4 == 26) {
                        mode2 = mode3;
                        c = ' ';
                    } else {
                        mode2 = i4 == 900 ? Mode.ALPHA : mode3;
                        c = 0;
                    }
                    break;
                case PUNCT_SHIFT:
                    if (i4 < 29) {
                        c2 = PUNCT_CHARS[i4];
                        c = c2;
                        mode2 = mode3;
                    } else {
                        if (i4 == 29) {
                            mode2 = Mode.ALPHA;
                        } else if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            sb.append((char) iArr2[i3]);
                        } else if (i4 == 900) {
                            mode2 = Mode.ALPHA;
                        }
                        c = 0;
                    }
                    break;
                default:
                    c = 0;
                    break;
            }
            if (c != 0) {
                sb.append(c);
            }
        }
    }

    private static int byteCompaction(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        int i3;
        int i4;
        int i5;
        int i6;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i7 = MACRO_PDF417_TERMINATOR;
        int i8 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
        int i9 = 928;
        long j = 900;
        if (i == 901) {
            int[] iArr2 = new int[6];
            i3 = i2 + 1;
            int i10 = iArr[i2];
            boolean z = false;
            loop0: while (true) {
                i4 = 0;
                long j2 = 0;
                while (true) {
                    if (i3 >= iArr[0] || z) {
                        break loop0;
                    }
                    i4++;
                    iArr2[i4] = i10;
                    j2 = (j2 * j) + ((long) i10);
                    i6 = i3 + 1;
                    i10 = iArr[i3];
                    if (i10 == 900 || i10 == 901 || i10 == 902 || i10 == BYTE_COMPACTION_MODE_LATCH_6 || i10 == 928 || i10 == i8 || i10 == i7) {
                        i3 = i6 - 1;
                        i7 = MACRO_PDF417_TERMINATOR;
                        i8 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                        j = 900;
                        z = true;
                    } else if (i4 % 5 != 0 || i4 <= 0) {
                        i3 = i6;
                        i7 = MACRO_PDF417_TERMINATOR;
                        i8 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                        j = 900;
                    }
                }
                int i11 = 0;
                while (i11 < 6) {
                    byteArrayOutputStream.write((byte) (j2 >> ((5 - i11) * 8)));
                    i11++;
                    i7 = MACRO_PDF417_TERMINATOR;
                    i8 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                }
                i3 = i6;
                j = 900;
            }
            if (i3 != iArr[0] || i10 >= 900) {
                i5 = i4;
            } else {
                i5 = i4 + 1;
                iArr2[i4] = i10;
            }
            for (int i12 = 0; i12 < i5; i12++) {
                byteArrayOutputStream.write((byte) iArr2[i12]);
            }
        } else if (i == BYTE_COMPACTION_MODE_LATCH_6) {
            int i13 = i2;
            int i14 = 0;
            long j3 = 0;
            boolean z2 = false;
            while (i13 < iArr[0] && !z2) {
                int i15 = i13 + 1;
                int i16 = iArr[i13];
                if (i16 < 900) {
                    i14++;
                    j3 = (j3 * 900) + ((long) i16);
                    i13 = i15;
                } else {
                    if (i16 != 900 && i16 != 901 && i16 != 902 && i16 != BYTE_COMPACTION_MODE_LATCH_6 && i16 != i9) {
                        if (i16 != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                            if (i16 != MACRO_PDF417_TERMINATOR) {
                                i13 = i15;
                            }
                        }
                        i13 = i15 - 1;
                        z2 = true;
                    }
                    i13 = i15 - 1;
                    z2 = true;
                }
                if (i14 % 5 == 0 && i14 > 0) {
                    int i17 = 0;
                    for (int i18 = 6; i17 < i18; i18 = 6) {
                        byteArrayOutputStream.write((byte) (j3 >> ((5 - i17) * 8)));
                        i17++;
                    }
                    i14 = 0;
                    j3 = 0;
                }
                i9 = 928;
            }
            i3 = i13;
        } else {
            i3 = i2;
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i3;
    }

    private static int numericCompaction(int[] iArr, int i, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < 900) {
                iArr2[i2] = i4;
                i2++;
            } else if (i4 == 900 || i4 == 901 || i4 == BYTE_COMPACTION_MODE_LATCH_6 || i4 == 928 || i4 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i4 == MACRO_PDF417_TERMINATOR) {
                i3--;
                z = true;
            }
            if ((i2 % 15 == 0 || i4 == 902 || z) && i2 > 0) {
                sb.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigIntegerAdd = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigIntegerAdd = bigIntegerAdd.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String string = bigIntegerAdd.toString();
        if (string.charAt(0) != '1') {
            throw FormatException.getFormatInstance();
        }
        return string.substring(1);
    }
}
