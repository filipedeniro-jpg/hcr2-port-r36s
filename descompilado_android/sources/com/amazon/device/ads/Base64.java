package com.amazon.device.ads;

import com.ironsource.sdk.constants.Constants;

/* JADX INFO: loaded from: classes.dex */
class Base64 {
    private static final String ENCODE_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    Base64() {
    }

    public static byte[] decode(String str) {
        int i;
        int iIndexOf;
        int i2;
        if (StringUtils.isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("Encoded String must not be null or white space");
        }
        int decodedLength = getDecodedLength(str);
        if (decodedLength <= 0) {
            throw new IllegalArgumentException("Encoded String decodes to zero bytes");
        }
        byte[] bArr = new byte[decodedLength];
        int i3 = 0;
        for (int i4 = 0; i4 < str.length() && i3 < decodedLength && (((i = i4 % 4) != 0 || str.length() >= i4 + 4) && (iIndexOf = ENCODE_CHARSET.indexOf(str.charAt(i4))) != -1); i4++) {
            if (i != 0) {
                if (i == 1) {
                    i2 = i3 + 1;
                    bArr[i3] = (byte) (((byte) (3 & (iIndexOf >> 4))) | bArr[i3]);
                    if (i2 < decodedLength) {
                        bArr[i2] = (byte) (iIndexOf << 4);
                    }
                } else if (i == 2) {
                    i2 = i3 + 1;
                    bArr[i3] = (byte) (bArr[i3] | ((byte) ((iIndexOf >> 2) & 15)));
                    if (i2 < decodedLength) {
                        bArr[i2] = (byte) (iIndexOf << 6);
                    }
                } else if (i == 3) {
                    i2 = i3 + 1;
                    bArr[i3] = (byte) (((byte) (iIndexOf & 63)) | bArr[i3]);
                }
                i3 = i2;
            } else {
                bArr[i3] = (byte) (iIndexOf << 2);
            }
        }
        return bArr;
    }

    private static int getDecodedLength(String str) {
        int iIndexOf = str.indexOf(Constants.RequestParameters.EQUAL);
        return (((str.length() + 3) / 4) * 3) - (iIndexOf > -1 ? str.length() - iIndexOf : 0);
    }
}
