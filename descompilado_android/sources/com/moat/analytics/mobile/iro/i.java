package com.moat.analytics.mobile.iro;

import android.os.Build;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class i {

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private static char f587 = 5;

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private static char[] f586 = {'s', 'a', '2', 'b', 'c', '3', '4', '1', '8', '9', 'f', '0', '6', 'd', 'e', '5', 'o', 'n', 'i', 'p', 'l', 't', 'u', 'v', 'w'};

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private static int f589 = 0;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private static int f588 = 1;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private boolean f590 = false;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private boolean f593 = false;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private boolean f594 = false;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private int f592 = 200;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private int f591 = 10;

    i(String str) {
        m531(str);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0075 A[Catch: Exception -> 0x0153, TRY_ENTER, TryCatch #0 {Exception -> 0x0153, blocks: (B:6:0x0008, B:41:0x00a9, B:46:0x00be, B:60:0x00f4, B:61:0x00f6, B:67:0x0114, B:68:0x0122, B:80:0x0141, B:82:0x014a, B:24:0x0075, B:29:0x0080, B:34:0x008f, B:40:0x00a7), top: B:87:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:26:0x007b  */
    /* JADX WARN: Code duplicated, block: B:27:0x007d  */
    /* JADX WARN: Code duplicated, block: B:29:0x0080 A[Catch: Exception -> 0x0153, TryCatch #0 {Exception -> 0x0153, blocks: (B:6:0x0008, B:41:0x00a9, B:46:0x00be, B:60:0x00f4, B:61:0x00f6, B:67:0x0114, B:68:0x0122, B:80:0x0141, B:82:0x014a, B:24:0x0075, B:29:0x0080, B:34:0x008f, B:40:0x00a7), top: B:87:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x0088  */
    /* JADX WARN: Code duplicated, block: B:32:0x008b  */
    /* JADX WARN: Code duplicated, block: B:34:0x008f A[Catch: Exception -> 0x0153, TRY_LEAVE, TryCatch #0 {Exception -> 0x0153, blocks: (B:6:0x0008, B:41:0x00a9, B:46:0x00be, B:60:0x00f4, B:61:0x00f6, B:67:0x0114, B:68:0x0122, B:80:0x0141, B:82:0x014a, B:24:0x0075, B:29:0x0080, B:34:0x008f, B:40:0x00a7), top: B:87:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x0099  */
    /* JADX WARN: Code duplicated, block: B:37:0x009b  */
    /* JADX WARN: Code duplicated, block: B:39:0x009e  */
    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private void m531(String str) {
        boolean z;
        char c;
        boolean z2;
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(m533(2, "\u0001\u0002", (byte) 59).intern());
            boolean zEquals = string.equals(m533(40, "\u0003\u0004\u0000\t\u0007\b\r\b\u0005\u0006\u000b\f\f\u0011\u0007\r\u000e\u0000\b\u0012\t\f\u0004\r\u0004\u0000\u0001\u000e\u0007\u000b\u0005\u0007\n\u0011\b\t\u0007\f\u0004\u0003", (byte) 66).intern());
            boolean zEquals2 = string.equals(m533(40, "\u0005\r\b\f\r\u0006\u0002\u0003\u000e\r\u0007\u0005\u0011\f\u0005\b\u0000\u0010\r\u0004\u0004\u000b\u0005\r\n\u0010\u0006\u000e\u000f\u0014\b\u0006\f\u0004\u0011\f\u0005\u0007\t\u0005", (byte) 43).intern());
            if (string.equals(m533(2, "\u0011\u0012", (byte) 121).intern())) {
                if (m532(jSONObject)) {
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    if (m535(jSONObject)) {
                        c = 'J';
                    } else {
                        c = '0';
                    }
                    if (c != 'J') {
                        this.f590 = true;
                        this.f593 = zEquals;
                        this.f594 = zEquals2;
                        if (this.f594) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        if (!z2) {
                            int i = f588 + 51;
                            f589 = i % 128;
                            int i2 = i % 2;
                            this.f593 = true;
                        }
                    }
                }
            } else {
                int i3 = f589 + 91;
                f588 = i3 % 128;
                int i4 = i3 % 2;
                if (!zEquals) {
                    if ((zEquals2 ? '\"' : (char) 20) != 20) {
                        if (m532(jSONObject)) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            if (m535(jSONObject)) {
                                c = '0';
                            } else {
                                c = 'J';
                            }
                            if (c != 'J') {
                                this.f590 = true;
                                this.f593 = zEquals;
                                this.f594 = zEquals2;
                                if (this.f594) {
                                    z2 = false;
                                } else {
                                    z2 = true;
                                }
                                if (!z2) {
                                    int i5 = f588 + 51;
                                    f589 = i5 % 128;
                                    int i6 = i5 % 2;
                                    this.f593 = true;
                                }
                            }
                        }
                    }
                } else {
                    if (m532(jSONObject)) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        if (m535(jSONObject)) {
                            c = '0';
                        } else {
                            c = 'J';
                        }
                        if (c != 'J') {
                            this.f590 = true;
                            this.f593 = zEquals;
                            this.f594 = zEquals2;
                            if (this.f594) {
                                z2 = false;
                            } else {
                                z2 = true;
                            }
                            if (!z2) {
                                int i7 = f588 + 51;
                                f589 = i7 % 128;
                                int i8 = i7 % 2;
                                this.f593 = true;
                            }
                        }
                    }
                }
            }
            if (jSONObject.has(m533(2, "\u0013\u0012", (byte) 25).intern())) {
                int i9 = jSONObject.getInt(m533(2, "\u0013\u0012", (byte) 25).intern());
                if ((i9 >= 100 ? '8' : 'B') == '8') {
                    int i10 = f589 + 117;
                    f588 = i10 % 128;
                    int i11 = i10 % 2;
                    if ((i9 <= 1000 ? 'K' : '4') == 'K') {
                        int i12 = f588 + 59;
                        f589 = i12 % 128;
                        int i13 = i12 % 2;
                        this.f592 = i9;
                    }
                }
            }
            if (!(!jSONObject.has(m533(2, "\n\u0004", (byte) 24).intern()))) {
                int i14 = f589 + 55;
                f588 = i14 % 128;
                int i15 = i14 % 2;
                this.f591 = jSONObject.getInt(m533(2, "\n\u0004", (byte) 24).intern());
            }
            if ((m534(jSONObject) ? 'G' : (char) 1) != 'G') {
                return;
            }
            int i16 = f589 + 69;
            f588 = i16 % 128;
            if (!(i16 % 2 == 0)) {
                ((j) MoatAnalytics.getInstance()).f600 = true;
            } else {
                ((j) MoatAnalytics.getInstance()).f600 = true;
            }
        } catch (Exception e) {
            this.f590 = false;
            this.f593 = false;
            this.f592 = 200;
            o.m569(e);
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static boolean m532(JSONObject jSONObject) {
        try {
            if (!(16 > Build.VERSION.SDK_INT)) {
                if ((jSONObject.has(m533(2, "\u0012\u0001", (byte) 62).intern()) ? 'M' : 'V') != 'V') {
                    JSONArray jSONArray = jSONObject.getJSONArray(m533(2, "\u0012\u0001", (byte) 62).intern());
                    int length = jSONArray.length();
                    int i = 0;
                    while (true) {
                        if ((i < length ? '<' : 'G') == '<') {
                            if ((jSONArray.getInt(i) == Build.VERSION.SDK_INT ? (char) 14 : (char) 26) == 14) {
                                return true;
                            }
                            i++;
                        }
                    }
                }
                return false;
            }
            int i2 = f589 + 69;
            f588 = i2 % 128;
            if ((i2 % 2 == 0 ? '?' : '\'') != '?') {
            }
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static boolean m535(JSONObject jSONObject) {
        try {
            if (jSONObject.has(m533(2, "\u0004\u0010", (byte) 47).intern())) {
                String strM583 = p.m576().m583();
                JSONArray jSONArray = jSONObject.getJSONArray(m533(2, "\u0004\u0010", (byte) 47).intern());
                int length = jSONArray.length();
                int i = 0;
                while (true) {
                    if ((i < length ? '\"' : '8') == '\"') {
                        int i2 = f588 + 115;
                        f589 = i2 % 128;
                        int i3 = i2 % 2;
                        if (jSONArray.getString(i).contentEquals(strM583)) {
                            int i4 = f588 + 47;
                            f589 = i4 % 128;
                            int i5 = i4 % 2;
                            return true;
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            o.m569(e);
        }
        return false;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static boolean m534(JSONObject jSONObject) {
        try {
            if ((jSONObject.has(m533(2, "\u0000\u0015", (byte) 3).intern()) ? (char) 23 : '7') == 23) {
                int i = f589 + 73;
                f588 = i % 128;
                int i2 = i % 2;
                String strM583 = p.m576().m583();
                JSONArray jSONArray = jSONObject.getJSONArray(m533(2, "\u0000\u0015", (byte) 3).intern());
                int length = jSONArray.length();
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    if (jSONArray.getString(i3).contentEquals(strM583)) {
                        return true;
                    }
                    i3++;
                }
            }
        } catch (Exception e) {
            o.m569(e);
        }
        return false;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final boolean m536() {
        return this.f593;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final boolean m538() {
        return this.f594;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final int m537() {
        return this.f592;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final int m540() {
        return this.f591;
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final int m539() {
        return this.f590 ? t.c.f677 : t.c.f676;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static String m533(int i, String str, byte b) {
        int i2 = i;
        char[] charArray = str.toCharArray();
        char[] cArr = f586;
        char c = f587;
        char[] cArr2 = new char[i2];
        if (!(i2 % 2 == 0)) {
            int i3 = f588 + 99;
            f589 = i3 % 128;
            int i4 = i3 % 2;
            i2--;
            cArr2[i2] = (char) (charArray[i2] - b);
        }
        if (!(i2 <= 1)) {
            int i5 = f588 + 125;
            f589 = i5 % 128;
            if (i5 % 2 != 0) {
            }
            int i6 = 0;
            while (true) {
                if ((i6 < i2 ? 'c' : 'U') == 'U') {
                    break;
                }
                char c2 = charArray[i6];
                int i7 = i6 + 1;
                char c3 = charArray[i7];
                if ((c2 == c3 ? (char) 24 : '#') != '#') {
                    cArr2[i6] = (char) (c2 - b);
                    cArr2[i7] = (char) (c3 - b);
                } else {
                    int iM220 = com.c.c.b.m220(c2, c);
                    int iM221 = com.c.c.b.m221(c2, c);
                    int iM222 = com.c.c.b.m220(c3, c);
                    int iM223 = com.c.c.b.m221(c3, c);
                    if (!(iM221 != iM223)) {
                        int iM219 = com.c.c.b.m219(iM220, c);
                        int iM2110 = com.c.c.b.m219(iM222, c);
                        int iM224 = com.c.c.b.m222(iM219, iM221, c);
                        int iM225 = com.c.c.b.m222(iM2110, iM223, c);
                        cArr2[i6] = cArr[iM224];
                        cArr2[i7] = cArr[iM225];
                    } else {
                        if ((iM220 == iM222 ? '.' : '1') != '1') {
                            int iM2111 = com.c.c.b.m219(iM221, c);
                            int iM2112 = com.c.c.b.m219(iM223, c);
                            int iM226 = com.c.c.b.m222(iM220, iM2111, c);
                            int iM227 = com.c.c.b.m222(iM222, iM2112, c);
                            cArr2[i6] = cArr[iM226];
                            cArr2[i7] = cArr[iM227];
                        } else {
                            int iM228 = com.c.c.b.m222(iM220, iM223, c);
                            int iM229 = com.c.c.b.m222(iM222, iM221, c);
                            cArr2[i6] = cArr[iM228];
                            cArr2[i7] = cArr[iM229];
                        }
                    }
                }
                i6 += 2;
            }
        }
        return new String(cArr2);
    }
}
