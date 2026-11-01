package com.google.android.gms.internal.firebase_remote_config;

import com.facebook.internal.ServerProtocol;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfj implements Closeable {
    private static final char[] zznf = ")]}'\n".toCharArray();
    private final Reader in;
    private long zznl;
    private int zznm;
    private String zznn;
    private int zznp;
    private String[] zznq;
    private int[] zznr;
    private boolean zzng = false;
    private final char[] zznh = new char[1024];
    private int pos = 0;
    private int limit = 0;
    private int zzni = 0;
    private int zznj = 0;
    private int zznk = 0;
    private int[] zzno = new int[32];

    public zzfj(Reader reader) {
        this.zznp = 0;
        int[] iArr = this.zzno;
        int i = this.zznp;
        this.zznp = i + 1;
        iArr[i] = 6;
        this.zznq = new String[32];
        this.zznr = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.in = reader;
    }

    public final void setLenient(boolean z) {
        this.zzng = true;
    }

    public final void beginArray() throws IOException {
        int iZzdz = this.zznk;
        if (iZzdz == 0) {
            iZzdz = zzdz();
        }
        if (iZzdz == 3) {
            zzo(1);
            this.zznr[this.zznp - 1] = 0;
            this.zznk = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + zzdy() + zzed());
        }
    }

    public final void endArray() throws IOException {
        int iZzdz = this.zznk;
        if (iZzdz == 0) {
            iZzdz = zzdz();
        }
        if (iZzdz == 4) {
            this.zznp--;
            int[] iArr = this.zznr;
            int i = this.zznp - 1;
            iArr[i] = iArr[i] + 1;
            this.zznk = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + zzdy() + zzed());
    }

    public final void beginObject() throws IOException {
        int iZzdz = this.zznk;
        if (iZzdz == 0) {
            iZzdz = zzdz();
        }
        if (iZzdz == 1) {
            zzo(3);
            this.zznk = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + zzdy() + zzed());
        }
    }

    public final void endObject() throws IOException {
        int iZzdz = this.zznk;
        if (iZzdz == 0) {
            iZzdz = zzdz();
        }
        if (iZzdz == 2) {
            this.zznp--;
            String[] strArr = this.zznq;
            int i = this.zznp;
            strArr[i] = null;
            int[] iArr = this.zznr;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
            this.zznk = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + zzdy() + zzed());
    }

    public final zzfl zzdy() throws IOException {
        int iZzdz = this.zznk;
        if (iZzdz == 0) {
            iZzdz = zzdz();
        }
        switch (iZzdz) {
            case 1:
                return zzfl.BEGIN_OBJECT;
            case 2:
                return zzfl.END_OBJECT;
            case 3:
                return zzfl.BEGIN_ARRAY;
            case 4:
                return zzfl.END_ARRAY;
            case 5:
            case 6:
                return zzfl.BOOLEAN;
            case 7:
                return zzfl.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return zzfl.STRING;
            case 12:
            case 13:
            case 14:
                return zzfl.NAME;
            case 15:
            case 16:
                return zzfl.NUMBER;
            case 17:
                return zzfl.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARN: Code duplicated, block: B:105:0x017a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:106:0x017b  */
    /* JADX WARN: Code duplicated, block: B:109:0x018e  */
    /* JADX WARN: Code duplicated, block: B:111:0x0191  */
    /* JADX WARN: Code duplicated, block: B:113:0x0199  */
    /* JADX WARN: Code duplicated, block: B:114:0x019d A[PHI: r2 r7
  0x019d: PHI (r2v33 int) = (r2v32 int), (r2v38 int) binds: [B:108:0x018c, B:113:0x0199] A[DONT_GENERATE, DONT_INLINE]
  0x019d: PHI (r7v9 int) = (r7v8 int), (r7v10 int) binds: [B:108:0x018c, B:113:0x0199] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:116:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:118:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:160:0x021d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:171:0x0241 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:172:0x0243  */
    /* JADX WARN: Code duplicated, block: B:188:0x0266  */
    /* JADX WARN: Code duplicated, block: B:190:0x026a  */
    /* JADX WARN: Code duplicated, block: B:193:0x026f  */
    /* JADX WARN: Code duplicated, block: B:198:0x027b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:199:0x027c  */
    /* JADX WARN: Code duplicated, block: B:201:0x0288  */
    /* JADX WARN: Code duplicated, block: B:203:0x0290  */
    /* JADX WARN: Code duplicated, block: B:265:0x024a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:266:0x024a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:267:0x021b A[SYNTHETIC] */
    private final int zzdz() throws IOException {
        int iZzb;
        int i;
        String str;
        String str2;
        int i2;
        char c;
        char[] cArr;
        int i3;
        int i4;
        int i5;
        char c2;
        char c3;
        int i6;
        int[] iArr = this.zzno;
        int i7 = this.zznp;
        int i8 = iArr[i7 - 1];
        boolean z = true;
        if (i8 == 1) {
            iArr[i7 - 1] = 2;
        } else if (i8 == 2) {
            int iZzb2 = zzb(true);
            if (iZzb2 != 44) {
                if (iZzb2 != 59) {
                    if (iZzb2 == 93) {
                        this.zznk = 4;
                        return 4;
                    }
                    throw zzbe("Unterminated array");
                }
                zzeb();
            }
        } else {
            if (i8 == 3 || i8 == 5) {
                this.zzno[this.zznp - 1] = 4;
                if (i8 == 5 && (iZzb = zzb(true)) != 44) {
                    if (iZzb != 59) {
                        if (iZzb == 125) {
                            this.zznk = 2;
                            return 2;
                        }
                        throw zzbe("Unterminated object");
                    }
                    zzeb();
                }
                int iZzb3 = zzb(true);
                if (iZzb3 == 34) {
                    this.zznk = 13;
                    return 13;
                }
                if (iZzb3 == 39) {
                    zzeb();
                    this.zznk = 12;
                    return 12;
                }
                if (iZzb3 == 125) {
                    if (i8 != 5) {
                        this.zznk = 2;
                        return 2;
                    }
                    throw zzbe("Expected name");
                }
                zzeb();
                this.pos--;
                if (zze((char) iZzb3)) {
                    this.zznk = 14;
                    return 14;
                }
                throw zzbe("Expected name");
            }
            if (i8 == 4) {
                iArr[i7 - 1] = 5;
                int iZzb4 = zzb(true);
                if (iZzb4 != 58) {
                    if (iZzb4 == 61) {
                        zzeb();
                        if (this.pos < this.limit || zzp(1)) {
                            char[] cArr2 = this.zznh;
                            int i9 = this.pos;
                            if (cArr2[i9] == '>') {
                                this.pos = i9 + 1;
                            }
                        }
                    } else {
                        throw zzbe("Expected ':'");
                    }
                }
            } else if (i8 == 6) {
                if (this.zzng) {
                    zzb(true);
                    this.pos--;
                    int i10 = this.pos;
                    char[] cArr3 = zznf;
                    if (i10 + cArr3.length <= this.limit || zzp(cArr3.length)) {
                        int i11 = 0;
                        while (true) {
                            char[] cArr4 = zznf;
                            if (i11 < cArr4.length) {
                                if (this.zznh[this.pos + i11] != cArr4[i11]) {
                                    break;
                                }
                                i11++;
                            } else {
                                this.pos += cArr4.length;
                                break;
                            }
                        }
                    }
                }
                this.zzno[this.zznp - 1] = 7;
            } else if (i8 == 7) {
                if (zzb(false) == -1) {
                    this.zznk = 17;
                    return 17;
                }
                zzeb();
                this.pos--;
            } else if (i8 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iZzb5 = zzb(true);
        if (iZzb5 == 34) {
            this.zznk = 9;
            return 9;
        }
        if (iZzb5 == 39) {
            zzeb();
            this.zznk = 8;
            return 8;
        }
        if (iZzb5 == 44 || iZzb5 == 59) {
            i = 1;
        } else {
            if (iZzb5 == 91) {
                this.zznk = 3;
                return 3;
            }
            if (iZzb5 != 93) {
                if (iZzb5 == 123) {
                    this.zznk = 1;
                    return 1;
                }
                this.pos--;
                char c4 = this.zznh[this.pos];
                if (c4 == 't' || c4 == 'T') {
                    str = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
                    str2 = "TRUE";
                    i2 = 5;
                } else {
                    if (c4 != 'f' && c4 != 'F') {
                        if (c4 != 'n' && c4 != 'N') {
                            i2 = 0;
                            break;
                        }
                        str = "null";
                        str2 = "NULL";
                        i2 = 7;
                        if (i2 != 0) {
                            return i2;
                        }
                        cArr = this.zznh;
                        i3 = this.pos;
                        long j = 0;
                        i4 = this.limit;
                        long j2 = 0;
                        i5 = 0;
                        c2 = 0;
                        boolean z2 = true;
                        boolean z3 = false;
                        while (true) {
                            if (i3 + i5 != i4) {
                                if (i5 == cArr.length) {
                                    if (zzp(i5 + 1)) {
                                        i3 = this.pos;
                                        i4 = this.limit;
                                        c3 = cArr[i3 + i5];
                                        if (c3 != '+') {
                                            if (c3 != 'E') {
                                                if (c2 != 2) {
                                                }
                                                c2 = 5;
                                                i5++;
                                                z = true;
                                            } else {
                                                if (c2 != 2) {
                                                }
                                                c2 = 5;
                                                i5++;
                                                z = true;
                                            }
                                            if (i6 != 0) {
                                                return i6;
                                            }
                                            if (zze(this.zznh[this.pos])) {
                                                throw zzbe("Expected value");
                                            }
                                            zzeb();
                                            this.zznk = 10;
                                            return 10;
                                        }
                                        if (c2 != 5) {
                                        }
                                        c2 = 6;
                                        i5++;
                                        z = true;
                                    }
                                    if (c2 != 2) {
                                        if (c2 != 2) {
                                        }
                                        this.zznm = i5;
                                        this.zznk = 16;
                                        i6 = 16;
                                    } else {
                                        if (c2 != 2) {
                                        }
                                        this.zznm = i5;
                                        this.zznk = 16;
                                        i6 = 16;
                                    }
                                    if (i6 != 0) {
                                        return i6;
                                    }
                                    if (zze(this.zznh[this.pos])) {
                                        throw zzbe("Expected value");
                                    }
                                    zzeb();
                                    this.zznk = 10;
                                    return 10;
                                }
                            } else {
                                c3 = cArr[i3 + i5];
                                if (c3 != '+') {
                                    if (c3 != 'E' || c3 == 'e') {
                                        if (c2 != 2 || c2 == 4) {
                                            c2 = 5;
                                            i5++;
                                            z = true;
                                        }
                                    } else if (c3 == '-') {
                                        if (c2 == 0) {
                                            c2 = 1;
                                            z3 = true;
                                        } else {
                                            if (c2 != 5) {
                                            }
                                            c2 = 6;
                                        }
                                        i5++;
                                        z = true;
                                    } else if (c3 != '.') {
                                        if (c3 >= '0' && c3 <= '9') {
                                            if (c2 == z || c2 == 0) {
                                                j2 = -(c3 - '0');
                                                j = 0;
                                                c2 = 2;
                                            } else {
                                                if (c2 == 2) {
                                                    if (j2 != j) {
                                                        long j3 = (10 * j2) - ((long) (c3 - '0'));
                                                        z2 = (j2 > -922337203685477580L || (j2 == -922337203685477580L && j3 < j2)) & z2;
                                                        j2 = j3;
                                                    }
                                                } else if (c2 == 3) {
                                                    j = 0;
                                                    c2 = 4;
                                                } else if (c2 == 5 || c2 == 6) {
                                                    j = 0;
                                                    c2 = 7;
                                                }
                                                j = 0;
                                            }
                                            i5++;
                                            z = true;
                                        } else if (!zze(c3)) {
                                            if (c2 != 2 && z2 && ((j2 != Long.MIN_VALUE || z3) && (j2 != 0 || !z3))) {
                                                if (!z3) {
                                                    j2 = -j2;
                                                }
                                                this.zznl = j2;
                                                this.pos += i5;
                                                this.zznk = 15;
                                                i6 = 15;
                                            } else if (c2 != 2 || c2 == 4 || c2 == 7) {
                                                this.zznm = i5;
                                                this.zznk = 16;
                                                i6 = 16;
                                            }
                                        }
                                    } else if (c2 == 2) {
                                        c2 = 3;
                                        i5++;
                                        z = true;
                                    }
                                    if (i6 != 0) {
                                        return i6;
                                    }
                                    if (zze(this.zznh[this.pos])) {
                                        throw zzbe("Expected value");
                                    }
                                    zzeb();
                                    this.zznk = 10;
                                    return 10;
                                }
                                if (c2 != 5) {
                                }
                                c2 = 6;
                                i5++;
                                z = true;
                            }
                            i6 = 0;
                            if (i6 != 0) {
                                return i6;
                            }
                            if (zze(this.zznh[this.pos])) {
                                throw zzbe("Expected value");
                            }
                            zzeb();
                            this.zznk = 10;
                            return 10;
                        }
                    }
                    str = "false";
                    str2 = "FALSE";
                    i2 = 6;
                }
                int length = str.length();
                int i12 = 1;
                while (true) {
                    if (i12 < length) {
                        if ((this.pos + i12 < this.limit || zzp(i12 + 1)) && ((c = this.zznh[this.pos + i12]) == str.charAt(i12) || c == str2.charAt(i12))) {
                            i12++;
                        }
                    } else {
                        if ((this.pos + length >= this.limit && !zzp(length + 1)) || !zze(this.zznh[this.pos + length])) {
                            this.pos += length;
                            this.zznk = i2;
                            break;
                        }
                        break;
                    }
                    i2 = 0;
                    break;
                }
                if (i2 != 0) {
                    return i2;
                }
                cArr = this.zznh;
                i3 = this.pos;
                long j4 = 0;
                i4 = this.limit;
                long j5 = 0;
                i5 = 0;
                c2 = 0;
                boolean z4 = true;
                boolean z5 = false;
                while (true) {
                    if (i3 + i5 != i4) {
                        if (i5 == cArr.length) {
                            if (zzp(i5 + 1)) {
                                i3 = this.pos;
                                i4 = this.limit;
                                c3 = cArr[i3 + i5];
                                if (c3 != '+') {
                                    if (c3 != 'E') {
                                        if (c2 != 2) {
                                        }
                                        c2 = 5;
                                        i5++;
                                        z = true;
                                    } else {
                                        if (c2 != 2) {
                                        }
                                        c2 = 5;
                                        i5++;
                                        z = true;
                                    }
                                    if (i6 != 0) {
                                        return i6;
                                    }
                                    if (zze(this.zznh[this.pos])) {
                                        throw zzbe("Expected value");
                                    }
                                    zzeb();
                                    this.zznk = 10;
                                    return 10;
                                }
                                if (c2 != 5) {
                                }
                                c2 = 6;
                                i5++;
                                z = true;
                            }
                            if (c2 != 2) {
                                if (c2 != 2) {
                                }
                                this.zznm = i5;
                                this.zznk = 16;
                                i6 = 16;
                            } else {
                                if (c2 != 2) {
                                }
                                this.zznm = i5;
                                this.zznk = 16;
                                i6 = 16;
                            }
                            if (i6 != 0) {
                                return i6;
                            }
                            if (zze(this.zznh[this.pos])) {
                                throw zzbe("Expected value");
                            }
                            zzeb();
                            this.zznk = 10;
                            return 10;
                        }
                    } else {
                        c3 = cArr[i3 + i5];
                        if (c3 != '+') {
                            if (c3 != 'E') {
                                if (c2 != 2) {
                                }
                                c2 = 5;
                                i5++;
                                z = true;
                            } else {
                                if (c2 != 2) {
                                }
                                c2 = 5;
                                i5++;
                                z = true;
                            }
                            if (i6 != 0) {
                                return i6;
                            }
                            if (zze(this.zznh[this.pos])) {
                                throw zzbe("Expected value");
                            }
                            zzeb();
                            this.zznk = 10;
                            return 10;
                        }
                        if (c2 != 5) {
                        }
                        c2 = 6;
                        i5++;
                        z = true;
                    }
                    i6 = 0;
                    if (i6 != 0) {
                        return i6;
                    }
                    if (zze(this.zznh[this.pos])) {
                        throw zzbe("Expected value");
                    }
                    zzeb();
                    this.zznk = 10;
                    return 10;
                }
            }
            i = 1;
            if (i8 == 1) {
                this.zznk = 4;
                return 4;
            }
        }
        if (i8 == i || i8 == 2) {
            zzeb();
            this.pos -= i;
            this.zznk = 7;
            return 7;
        }
        throw zzbe("Unexpected value");
    }

    private final boolean zze(char c) throws IOException {
        if (c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' ') {
            return false;
        }
        if (c != '#') {
            if (c == ',') {
                return false;
            }
            if (c != '/' && c != '=') {
                if (c == '{' || c == '}' || c == ':') {
                    return false;
                }
                if (c != ';') {
                    switch (c) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        zzeb();
        return false;
    }

    public final String nextName() throws IOException {
        String strZzf;
        int iZzdz = this.zznk;
        if (iZzdz == 0) {
            iZzdz = zzdz();
        }
        if (iZzdz == 14) {
            strZzf = zzea();
        } else if (iZzdz == 12) {
            strZzf = zzf('\'');
        } else if (iZzdz == 13) {
            strZzf = zzf('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + zzdy() + zzed());
        }
        this.zznk = 0;
        this.zznq[this.zznp - 1] = strZzf;
        return strZzf;
    }

    public final String nextString() throws IOException {
        int iZzdz = this.zznk;
        if (iZzdz == 0) {
            iZzdz = zzdz();
        }
        String str = null;
        if (iZzdz == 10) {
            str = zzea();
        } else if (iZzdz == 8) {
            str = zzf('\'');
        } else if (iZzdz == 9) {
            str = zzf('\"');
        } else if (iZzdz == 11) {
            this.zznn = null;
        } else if (iZzdz == 15) {
            str = Long.toString(this.zznl);
        } else if (iZzdz == 16) {
            str = new String(this.zznh, this.pos, this.zznm);
            this.pos += this.zznm;
        } else {
            throw new IllegalStateException("Expected a string but was " + zzdy() + zzed());
        }
        this.zznk = 0;
        int[] iArr = this.zznr;
        int i = this.zznp - 1;
        iArr[i] = iArr[i] + 1;
        return str;
    }

    public final boolean nextBoolean() throws IOException {
        int iZzdz = this.zznk;
        if (iZzdz == 0) {
            iZzdz = zzdz();
        }
        if (iZzdz == 5) {
            this.zznk = 0;
            int[] iArr = this.zznr;
            int i = this.zznp - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iZzdz == 6) {
            this.zznk = 0;
            int[] iArr2 = this.zznr;
            int i2 = this.zznp - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + zzdy() + zzed());
    }

    public final void nextNull() throws IOException {
        int iZzdz = this.zznk;
        if (iZzdz == 0) {
            iZzdz = zzdz();
        }
        if (iZzdz == 7) {
            this.zznk = 0;
            int[] iArr = this.zznr;
            int i = this.zznp - 1;
            iArr[i] = iArr[i] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + zzdy() + zzed());
    }

    private final String zzf(char c) throws IOException {
        char[] cArr = this.zznh;
        StringBuilder sb = null;
        while (true) {
            int i = this.pos;
            int i2 = this.limit;
            int i3 = i;
            while (true) {
                if (i3 < i2) {
                    int i4 = i3 + 1;
                    char c2 = cArr[i3];
                    if (c2 == c) {
                        this.pos = i4;
                        int i5 = (i4 - i) - 1;
                        if (sb == null) {
                            return new String(cArr, i, i5);
                        }
                        sb.append(cArr, i, i5);
                        return sb.toString();
                    }
                    if (c2 == '\\') {
                        this.pos = i4;
                        int i6 = (i4 - i) - 1;
                        if (sb == null) {
                            sb = new StringBuilder(Math.max((i6 + 1) << 1, 16));
                        }
                        sb.append(cArr, i, i6);
                        sb.append(zzee());
                        break;
                    }
                    if (c2 == '\n') {
                        this.zzni++;
                        this.zznj = i4;
                    }
                    i3 = i4;
                } else {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max((i3 - i) << 1, 16));
                    }
                    sb.append(cArr, i, i3 - i);
                    this.pos = i3;
                    if (zzp(1)) {
                        break;
                    }
                    throw zzbe("Unterminated string");
                }
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0045. Please report as an issue. */
    private final String zzea() throws IOException {
        String string;
        int i = 0;
        StringBuilder sb = null;
        while (true) {
            int i2 = 0;
            while (true) {
                int i3 = this.pos;
                if (i3 + i2 < this.limit) {
                    char c = this.zznh[i3 + i2];
                    if (c != '\t' && c != '\n' && c != '\f' && c != '\r' && c != ' ') {
                        if (c != '#') {
                            if (c != ',') {
                                if (c != '/' && c != '=') {
                                    if (c != '{' && c != '}' && c != ':') {
                                        if (c != ';') {
                                            switch (c) {
                                                case '[':
                                                case ']':
                                                    break;
                                                case '\\':
                                                    break;
                                                default:
                                                    i2++;
                                                    break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        zzeb();
                    }
                    i = i2;
                } else if (i2 < this.zznh.length) {
                    if (!zzp(i2 + 1)) {
                        i = i2;
                    }
                } else {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max(i2, 16));
                    }
                    sb.append(this.zznh, this.pos, i2);
                    this.pos += i2;
                    if (!zzp(1)) {
                    }
                }
                if (sb == null) {
                    string = new String(this.zznh, this.pos, i);
                } else {
                    sb.append(this.zznh, this.pos, i);
                    string = sb.toString();
                }
                this.pos += i;
                return string;
            }
        }
    }

    private final void zzg(char c) throws IOException {
        char[] cArr = this.zznh;
        while (true) {
            int i = this.pos;
            int i2 = this.limit;
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.pos = i3;
                        return;
                    }
                    if (c2 == '\\') {
                        this.pos = i3;
                        zzee();
                        break;
                    } else {
                        if (c2 == '\n') {
                            this.zzni++;
                            this.zznj = i3;
                        }
                        i = i3;
                    }
                } else {
                    this.pos = i;
                    if (!zzp(1)) {
                        throw zzbe("Unterminated string");
                    }
                    break;
                }
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.zznk = 0;
        this.zzno[0] = 8;
        this.zznp = 1;
        this.in.close();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:61:0x009d. Please report as an issue. */
    public final void skipValue() throws IOException {
        int i = 0;
        do {
            int iZzdz = this.zznk;
            if (iZzdz == 0) {
                iZzdz = zzdz();
            }
            if (iZzdz == 3) {
                zzo(1);
            } else {
                if (iZzdz == 1) {
                    zzo(3);
                } else if (iZzdz == 4 || iZzdz == 2) {
                    this.zznp--;
                    i--;
                } else if (iZzdz == 14 || iZzdz == 10) {
                    while (true) {
                        int i2 = 0;
                        while (true) {
                            int i3 = this.pos;
                            if (i3 + i2 < this.limit) {
                                char c = this.zznh[i3 + i2];
                                if (c != '\t' && c != '\n' && c != '\f' && c != '\r' && c != ' ') {
                                    if (c != '#') {
                                        if (c != ',') {
                                            if (c != '/' && c != '=') {
                                                if (c != '{' && c != '}' && c != ':') {
                                                    if (c != ';') {
                                                        switch (c) {
                                                            case '[':
                                                            case ']':
                                                                break;
                                                            case '\\':
                                                                break;
                                                            default:
                                                                i2++;
                                                                break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    zzeb();
                                }
                                this.pos += i2;
                            } else {
                                this.pos = i3 + i2;
                                if (!zzp(1)) {
                                }
                            }
                        }
                    }
                } else if (iZzdz == 8 || iZzdz == 12) {
                    zzg('\'');
                } else if (iZzdz == 9 || iZzdz == 13) {
                    zzg('\"');
                } else if (iZzdz == 16) {
                    this.pos += this.zznm;
                }
                this.zznk = 0;
            }
            i++;
            this.zznk = 0;
        } while (i != 0);
        int[] iArr = this.zznr;
        int i4 = this.zznp;
        int i5 = i4 - 1;
        iArr[i5] = iArr[i5] + 1;
        this.zznq[i4 - 1] = "null";
    }

    private final void zzo(int i) {
        int i2 = this.zznp;
        int[] iArr = this.zzno;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 << 1];
            int[] iArr3 = new int[i2 << 1];
            String[] strArr = new String[i2 << 1];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.zznr, 0, iArr3, 0, this.zznp);
            System.arraycopy(this.zznq, 0, strArr, 0, this.zznp);
            this.zzno = iArr2;
            this.zznr = iArr3;
            this.zznq = strArr;
        }
        int[] iArr4 = this.zzno;
        int i3 = this.zznp;
        this.zznp = i3 + 1;
        iArr4[i3] = i;
    }

    private final boolean zzp(int i) throws IOException {
        int i2;
        char[] cArr = this.zznh;
        int i3 = this.zznj;
        int i4 = this.pos;
        this.zznj = i3 - i4;
        int i5 = this.limit;
        if (i5 != i4) {
            this.limit = i5 - i4;
            System.arraycopy(cArr, i4, cArr, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.in;
            int i6 = this.limit;
            int i7 = reader.read(cArr, i6, cArr.length - i6);
            if (i7 == -1) {
                return false;
            }
            this.limit += i7;
            if (this.zzni == 0 && (i2 = this.zznj) == 0 && this.limit > 0 && cArr[0] == 65279) {
                this.pos++;
                this.zznj = i2 + 1;
                i++;
            }
        } while (this.limit < i);
        return true;
    }

    private final int zzb(boolean z) throws IOException {
        char[] cArr = this.zznh;
        int i = this.pos;
        int i2 = this.limit;
        while (true) {
            boolean z2 = true;
            if (i == i2) {
                this.pos = i;
                if (!zzp(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input" + zzed());
                }
                i = this.pos;
                i2 = this.limit;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.zzni++;
                this.zznj = i3;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.pos = i3;
                    if (i3 == i2) {
                        this.pos--;
                        boolean zZzp = zzp(2);
                        this.pos++;
                        if (!zZzp) {
                            return c;
                        }
                    }
                    zzeb();
                    int i4 = this.pos;
                    char c2 = cArr[i4];
                    if (c2 == '*') {
                        this.pos = i4 + 1;
                        while (true) {
                            int i5 = 0;
                            if (this.pos + 2 > this.limit && !zzp(2)) {
                                z2 = false;
                                break;
                            }
                            char[] cArr2 = this.zznh;
                            int i6 = this.pos;
                            if (cArr2[i6] != '\n') {
                                while (true) {
                                    if (i5 >= 2) {
                                        break;
                                    }
                                    if (this.zznh[this.pos + i5] != "*/".charAt(i5)) {
                                        break;
                                    }
                                    i5++;
                                }
                            } else {
                                this.zzni++;
                                this.zznj = i6 + 1;
                            }
                            this.pos++;
                        }
                        if (!z2) {
                            throw zzbe("Unterminated comment");
                        }
                        i = this.pos + 2;
                        i2 = this.limit;
                    } else {
                        if (c2 != '/') {
                            return c;
                        }
                        this.pos = i4 + 1;
                        zzec();
                        i = this.pos;
                        i2 = this.limit;
                    }
                } else if (c == '#') {
                    this.pos = i3;
                    zzeb();
                    zzec();
                    i = this.pos;
                    i2 = this.limit;
                } else {
                    this.pos = i3;
                    return c;
                }
            }
            i = i3;
        }
    }

    private final void zzeb() throws IOException {
        if (!this.zzng) {
            throw zzbe("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private final void zzec() throws IOException {
        char c;
        do {
            if (this.pos >= this.limit && !zzp(1)) {
                return;
            }
            char[] cArr = this.zznh;
            int i = this.pos;
            this.pos = i + 1;
            c = cArr[i];
            if (c == '\n') {
                this.zzni++;
                this.zznj = this.pos;
                return;
            }
        } while (c != '\r');
    }

    public final String toString() {
        return getClass().getSimpleName() + zzed();
    }

    private final String zzed() {
        int i = this.zzni + 1;
        int i2 = (this.pos - this.zznj) + 1;
        StringBuilder sb = new StringBuilder(" at line ");
        sb.append(i);
        sb.append(" column ");
        sb.append(i2);
        sb.append(" path ");
        StringBuilder sb2 = new StringBuilder("$");
        int i3 = this.zznp;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = this.zzno[i4];
            if (i5 == 1 || i5 == 2) {
                sb2.append('[');
                sb2.append(this.zznr[i4]);
                sb2.append(']');
            } else if (i5 == 3 || i5 == 4 || i5 == 5) {
                sb2.append('.');
                String[] strArr = this.zznq;
                if (strArr[i4] != null) {
                    sb2.append(strArr[i4]);
                }
            }
        }
        sb.append(sb2.toString());
        return sb.toString();
    }

    private final char zzee() throws IOException {
        int i;
        int i2;
        if (this.pos == this.limit && !zzp(1)) {
            throw zzbe("Unterminated escape sequence");
        }
        char[] cArr = this.zznh;
        int i3 = this.pos;
        this.pos = i3 + 1;
        char c = cArr[i3];
        if (c == '\n') {
            this.zzni++;
            this.zznj = this.pos;
        } else if (c != '\"' && c != '\'' && c != '/' && c != '\\') {
            if (c == 'b') {
                return '\b';
            }
            if (c == 'f') {
                return '\f';
            }
            if (c == 'n') {
                return '\n';
            }
            if (c == 'r') {
                return '\r';
            }
            if (c == 't') {
                return '\t';
            }
            if (c == 'u') {
                if (this.pos + 4 > this.limit && !zzp(4)) {
                    throw zzbe("Unterminated escape sequence");
                }
                char c2 = 0;
                int i4 = this.pos;
                int i5 = i4 + 4;
                while (i4 < i5) {
                    char c3 = this.zznh[i4];
                    char c4 = (char) (c2 << 4);
                    if (c3 < '0' || c3 > '9') {
                        if (c3 >= 'a' && c3 <= 'f') {
                            i = c3 - 'a';
                        } else {
                            if (c3 < 'A' || c3 > 'F') {
                                throw new NumberFormatException("\\u" + new String(this.zznh, this.pos, 4));
                            }
                            i = c3 - 'A';
                        }
                        i2 = i + 10;
                    } else {
                        i2 = c3 - '0';
                    }
                    c2 = (char) (c4 + i2);
                    i4++;
                }
                this.pos += 4;
                return c2;
            }
            throw zzbe("Invalid escape sequence");
        }
        return c;
    }

    private final IOException zzbe(String str) throws IOException {
        throw new zzfn(str + zzed());
    }

    static {
        zzfk.zzns = new zzfm();
    }
}
