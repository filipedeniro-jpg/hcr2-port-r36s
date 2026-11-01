package com.moat.analytics.mobile.cha;

import android.os.Build;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class g {

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private static int f322 = 565428102;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private static int f323 = -1138247343;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private static int f324 = 117;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private static byte[] f326 = {-73, 79, -77, 127, -123, -125, -126, 73, 125, -81, 81, -77, 81, 127, -83, -121, 73, -78, 127, 85, -82, -128, 77, -79, 77, -80, -126, 123, -124, 77, -125, -87, -123, 117, -123, 120, -122, 82, -83, -128, -69, -66, 65, -78, 65, -66, 119, -120, -72, 70, 117, -116, 77, -66, -65, 112, -108, 105, -66, 69, 71, -118, 110, -118, 66, -78, 65, 71, -65, 68, 104, -120, 107, -109, -78, 118, -119, 113, -108, -91, -81, -5, 80, -25, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private static int f321 = 0;

    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    private static int f325 = 1;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private boolean f330 = false;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private boolean f329 = false;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private boolean f327 = false;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private int f331 = 200;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private int f328 = 10;

    g(String str) {
        m316(str);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0082 A[Catch: Exception -> 0x01b1, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x01b1, blocks: (B:5:0x0004, B:58:0x00df, B:69:0x010e, B:86:0x0146, B:75:0x0124, B:87:0x0148, B:97:0x0173, B:98:0x017f, B:99:0x0182, B:100:0x018f, B:108:0x01a8, B:23:0x0082, B:35:0x00a4, B:48:0x00c0, B:57:0x00dd, B:42:0x00b4), top: B:113:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:26:0x008a  */
    /* JADX WARN: Code duplicated, block: B:27:0x008d  */
    /* JADX WARN: Code duplicated, block: B:30:0x0092  */
    /* JADX WARN: Code duplicated, block: B:32:0x009e  */
    /* JADX WARN: Code duplicated, block: B:33:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:35:0x00a4 A[Catch: Exception -> 0x01b1, TRY_ENTER, TryCatch #0 {Exception -> 0x01b1, blocks: (B:5:0x0004, B:58:0x00df, B:69:0x010e, B:86:0x0146, B:75:0x0124, B:87:0x0148, B:97:0x0173, B:98:0x017f, B:99:0x0182, B:100:0x018f, B:108:0x01a8, B:23:0x0082, B:35:0x00a4, B:48:0x00c0, B:57:0x00dd, B:42:0x00b4), top: B:113:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:38:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:41:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:42:0x00b4 A[Catch: Exception -> 0x01b1, TryCatch #0 {Exception -> 0x01b1, blocks: (B:5:0x0004, B:58:0x00df, B:69:0x010e, B:86:0x0146, B:75:0x0124, B:87:0x0148, B:97:0x0173, B:98:0x017f, B:99:0x0182, B:100:0x018f, B:108:0x01a8, B:23:0x0082, B:35:0x00a4, B:48:0x00c0, B:57:0x00dd, B:42:0x00b4), top: B:113:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:45:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:47:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:48:0x00c0 A[Catch: Exception -> 0x01b1, TRY_LEAVE, TryCatch #0 {Exception -> 0x01b1, blocks: (B:5:0x0004, B:58:0x00df, B:69:0x010e, B:86:0x0146, B:75:0x0124, B:87:0x0148, B:97:0x0173, B:98:0x017f, B:99:0x0182, B:100:0x018f, B:108:0x01a8, B:23:0x0082, B:35:0x00a4, B:48:0x00c0, B:57:0x00dd, B:42:0x00b4), top: B:113:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:51:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:53:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:55:0x00da  */
    /* JADX WARN: Code duplicated, block: B:56:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:83:0x0141  */
    /* JADX WARN: Code duplicated, block: B:84:0x0143  */
    /* JADX WARN: Code duplicated, block: B:86:0x0146 A[Catch: Exception -> 0x01b1, TryCatch #0 {Exception -> 0x01b1, blocks: (B:5:0x0004, B:58:0x00df, B:69:0x010e, B:86:0x0146, B:75:0x0124, B:87:0x0148, B:97:0x0173, B:98:0x017f, B:99:0x0182, B:100:0x018f, B:108:0x01a8, B:23:0x0082, B:35:0x00a4, B:48:0x00c0, B:57:0x00dd, B:42:0x00b4), top: B:113:0x0004 }] */
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private void m316(String str) {
        char c;
        int i;
        char c2;
        boolean z;
        boolean z2;
        int i2;
        char c3;
        int i3;
        boolean z3;
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(m313(-115, 1138247458, (byte) 89, -565428102).intern());
            boolean zEquals = string.equals(m313(-77, 1138247394, (byte) -126, -565428101).intern());
            boolean zEquals2 = string.equals(m313(-77, 1138247399, (byte) -70, -565428062).intern());
            if ((!string.equals(m313(-115, 1138247454, (byte) 90, -565428023).intern()) ? 'B' : (char) 2) == 'B') {
                if (zEquals) {
                    if (m315(jSONObject)) {
                        c = '8';
                    } else {
                        c = '+';
                    }
                    if (c == '+') {
                        i = f321 + 95;
                        f325 = i % 128;
                        if (i % 2 == 0) {
                            c2 = 'P';
                        } else {
                            c2 = 6;
                        }
                        if (c2 != 6) {
                            if (m314(jSONObject)) {
                                c3 = 'E';
                            } else {
                                c3 = '<';
                            }
                            if (c3 != 'E') {
                                this.f330 = true;
                                this.f329 = zEquals;
                                this.f327 = zEquals2;
                                if (this.f327) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                if (z2) {
                                    i2 = f325 + 81;
                                    f321 = i2 % 128;
                                    if (i2 % 2 != 0) {
                                    }
                                    this.f329 = true;
                                }
                            }
                        } else {
                            if (m314(jSONObject)) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (!z) {
                                this.f330 = true;
                                this.f329 = zEquals;
                                this.f327 = zEquals2;
                                if (this.f327) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                if (z2) {
                                    i2 = f325 + 81;
                                    f321 = i2 % 128;
                                    if (i2 % 2 != 0) {
                                    }
                                    this.f329 = true;
                                }
                            }
                        }
                    }
                } else {
                    int i4 = f325 + 57;
                    f321 = i4 % 128;
                    int i5 = i4 % 2;
                    if (!(!zEquals2)) {
                        if (m315(jSONObject)) {
                            c = '+';
                        } else {
                            c = '8';
                        }
                        if (c == '+') {
                            i = f321 + 95;
                            f325 = i % 128;
                            if (i % 2 == 0) {
                                c2 = 'P';
                            } else {
                                c2 = 6;
                            }
                            if (c2 != 6) {
                                if (m314(jSONObject)) {
                                    c3 = '<';
                                } else {
                                    c3 = 'E';
                                }
                                if (c3 != 'E') {
                                    this.f330 = true;
                                    this.f329 = zEquals;
                                    this.f327 = zEquals2;
                                    if (this.f327) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                    if (z2) {
                                        i2 = f325 + 81;
                                        f321 = i2 % 128;
                                        if (i2 % 2 != 0) {
                                        }
                                        this.f329 = true;
                                    }
                                }
                            } else {
                                if (m314(jSONObject)) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                if (!z) {
                                    this.f330 = true;
                                    this.f329 = zEquals;
                                    this.f327 = zEquals2;
                                    if (this.f327) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                    if (z2) {
                                        i2 = f325 + 81;
                                        f321 = i2 % 128;
                                        if (i2 % 2 != 0) {
                                        }
                                        this.f329 = true;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (m315(jSONObject)) {
                    c = '+';
                } else {
                    c = '8';
                }
                if (c == '+') {
                    i = f321 + 95;
                    f325 = i % 128;
                    if (i % 2 == 0) {
                        c2 = 'P';
                    } else {
                        c2 = 6;
                    }
                    if (c2 != 6) {
                        if (m314(jSONObject)) {
                            c3 = '<';
                        } else {
                            c3 = 'E';
                        }
                        if (c3 != 'E') {
                            this.f330 = true;
                            this.f329 = zEquals;
                            this.f327 = zEquals2;
                            if (this.f327) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                i2 = f325 + 81;
                                f321 = i2 % 128;
                                if (i2 % 2 != 0) {
                                }
                                this.f329 = true;
                            }
                        }
                    } else {
                        if (m314(jSONObject)) {
                            z = false;
                        } else {
                            z = true;
                        }
                        if (!z) {
                            this.f330 = true;
                            this.f329 = zEquals;
                            this.f327 = zEquals2;
                            if (this.f327) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                i2 = f325 + 81;
                                f321 = i2 % 128;
                                if (i2 % 2 != 0) {
                                }
                                this.f329 = true;
                            }
                        }
                    }
                }
            }
            if ((jSONObject.has(m313(-115, 1138247448, (byte) -86, -565428022).intern()) ? (char) 27 : 'K') != 'K') {
                int i6 = f321 + 77;
                f325 = i6 % 128;
                if (!(i6 % 2 == 0)) {
                    i3 = jSONObject.getInt(m313(-115, 1138247448, (byte) -86, -565428022).intern());
                    if (i3 >= 100) {
                        if (i3 <= 1000) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (z3) {
                            this.f331 = i3;
                        }
                    }
                } else {
                    i3 = jSONObject.getInt(m313(-115, 1138247448, (byte) -86, -565428022).intern());
                    if ((i3 >= 100 ? 'M' : (char) 19) != 19) {
                        if (i3 <= 1000) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (z3) {
                            this.f331 = i3;
                        }
                    }
                }
            }
            if (!(!jSONObject.has(m313(-115, 1138247444, (byte) -11, -565428021).intern()))) {
                int i7 = f321 + 53;
                f325 = i7 % 128;
                this.f328 = i7 % 2 != 0 ? jSONObject.getInt(m313(-115, 1138247444, (byte) -11, -565428021).intern()) : jSONObject.getInt(m313(-115, 1138247444, (byte) -11, -565428021).intern());
            }
            if ((m317(jSONObject) ? (char) 28 : '`') != 28) {
                return;
            }
            int i8 = f321 + 23;
            f325 = i8 % 128;
            int i9 = i8 % 2;
            ((f) MoatAnalytics.getInstance()).f317 = true;
        } catch (Exception e) {
            this.f330 = false;
            this.f329 = false;
            this.f331 = 200;
            o.m389(e);
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static boolean m315(JSONObject jSONObject) {
        try {
            if ((16 > Build.VERSION.SDK_INT ? ':' : '$') == '$') {
                if (!(!jSONObject.has(m313(-115, 1138247454, (byte) -93, -565428020).intern()))) {
                    int i = f321 + 71;
                    f325 = i % 128;
                    JSONArray jSONArray = (i % 2 == 0 ? '`' : '8') != '`' ? jSONObject.getJSONArray(m313(-115, 1138247454, (byte) -93, -565428020).intern()) : jSONObject.getJSONArray(m313(-115, 1138247454, (byte) -93, -565428020).intern());
                    int length = jSONArray.length();
                    JSONArray jSONArray2 = jSONArray;
                    int i2 = 0;
                    while (true) {
                        if ((i2 < length ? ')' : (char) 24) != 24) {
                            if (!(jSONArray2.getInt(i2) != Build.VERSION.SDK_INT)) {
                                int i3 = f321 + 65;
                                f325 = i3 % 128;
                                int i4 = i3 % 2;
                                return true;
                            }
                            i2++;
                        }
                    }
                }
                return false;
            }
            int i5 = f325 + 43;
            f321 = i5 % 128;
            int i6 = i5 % 2;
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static boolean m314(JSONObject jSONObject) {
        String strM413;
        JSONArray jSONArray;
        try {
            if ((jSONObject.has(m313(-115, 1138247440, (byte) -24, -565428019).intern()) ? (char) 14 : (char) 18) != 18) {
                int i = f321 + 113;
                f325 = i % 128;
                if (i % 2 == 0) {
                    strM413 = r.m403().m413();
                    jSONArray = jSONObject.getJSONArray(m313(-115, 1138247440, (byte) -24, -565428019).intern());
                } else {
                    strM413 = r.m403().m413();
                    jSONArray = jSONObject.getJSONArray(m313(-115, 1138247440, (byte) -24, -565428019).intern());
                }
                int length = jSONArray.length();
                JSONArray jSONArray2 = jSONArray;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    if ((jSONArray2.getString(i2).contentEquals(strM413) ? (char) 23 : 'X') == 23) {
                        int i3 = f325 + 71;
                        f321 = i3 % 128;
                        if ((i3 % 2 != 0 ? (char) 4 : 'O') != 'O') {
                        }
                        return true;
                    }
                    i2++;
                }
            }
        } catch (Exception e) {
            o.m389(e);
        }
        return false;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static boolean m317(JSONObject jSONObject) {
        try {
            if ((jSONObject.has(m313(-115, 1138247440, (byte) 1, -565428018).intern()) ? '8' : (char) 19) == '8') {
                int i = f321 + 99;
                f325 = i % 128;
                int i2 = i % 2;
                String strM413 = r.m403().m413();
                JSONArray jSONArray = jSONObject.getJSONArray(m313(-115, 1138247440, (byte) 1, -565428018).intern());
                int length = jSONArray.length();
                int i3 = 0;
                while (true) {
                    if (!(i3 >= length)) {
                        int i4 = f321 + 49;
                        f325 = i4 % 128;
                        if ((i4 % 2 == 0 ? (char) 0 : 'X') == 0) {
                            if ((jSONArray.getString(i3).contentEquals(strM413) ? '>' : 'I') == '>') {
                                break;
                            }
                            i3++;
                        } else {
                            if ((jSONArray.getString(i3).contentEquals(strM413) ? (char) 11 : 'K') != 'K') {
                                break;
                            }
                            i3++;
                        }
                    }
                }
                return true;
            }
        } catch (Exception e) {
            o.m389(e);
        }
        return false;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final boolean m318() {
        return this.f329;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final boolean m322() {
        return this.f327;
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final int m321() {
        return this.f331;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final int m320() {
        return this.f328;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final int m319() {
        return this.f330 ? t.a.f452 : t.a.f453;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static String m313(int i, int i2, byte b, int i3) {
        StringBuilder sb = new StringBuilder();
        int i4 = i + f324;
        boolean z = !(i4 != -1);
        short[] sArr = null;
        if (z) {
            if (f326 == null) {
                i4 = (short) (sArr[f322 + i3] + f324);
            } else {
                int i5 = f325 + 87;
                f321 = i5 % 128;
                i4 = (byte) ((!(i5 % 2 == 0) ? f326[f322 + i3] : f326[f322 + i3]) + f324);
            }
        }
        if ((i4 > 0 ? '=' : (char) 24) != 24) {
            int i6 = ((i3 + i4) - 2) + f322 + ((z ? 'Q' : 'V') == 'Q' ? 1 : 0);
            char c = (char) (i2 + f323);
            sb.append(c);
            char c2 = c;
            int i7 = 1;
            while (true) {
                if ((i7 < i4 ? 'U' : ',') == ',') {
                    break;
                }
                int i8 = f321 + 1;
                f325 = i8 % 128;
                int i9 = i8 % 2;
                if ((f326 != null ? 'B' : 'E') == 'B') {
                    c2 = (char) (c2 + (f326[i6] ^ b));
                    i6--;
                } else {
                    c2 = (char) (c2 + (sArr[i6] ^ b));
                    i6--;
                }
                sb.append(c2);
                i7++;
            }
        }
        return sb.toString();
    }
}
