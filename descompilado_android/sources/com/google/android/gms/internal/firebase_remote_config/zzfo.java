package com.google.android.gms.internal.firebase_remote_config;

import com.facebook.internal.ServerProtocol;
import com.ironsource.sdk.constants.Constants;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfo implements Closeable, Flushable {
    private static final String[] zzoe = new String[128];
    private static final String[] zzof;
    private final Writer out;
    private String separator;
    private boolean zzng;
    private int[] zzno = new int[32];
    private int zznp = 0;
    private String zzog;
    private String zzoh;
    private boolean zzoi;

    public zzfo(Writer writer) {
        zzo(6);
        this.separator = ":";
        this.zzoi = true;
        this.out = writer;
    }

    public final void setIndent(String str) {
        if (str.length() == 0) {
            this.zzog = null;
            this.separator = ":";
        } else {
            this.zzog = str;
            this.separator = ": ";
        }
    }

    public final void setLenient(boolean z) {
        this.zzng = true;
    }

    public final zzfo zzef() throws IOException {
        zzek();
        return zza(1, Constants.RequestParameters.LEFT_BRACKETS);
    }

    public final zzfo zzeg() throws IOException {
        return zzb(1, 2, Constants.RequestParameters.RIGHT_BRACKETS);
    }

    public final zzfo zzeh() throws IOException {
        zzek();
        return zza(3, "{");
    }

    public final zzfo zzei() throws IOException {
        return zzb(3, 5, "}");
    }

    private final zzfo zza(int i, String str) throws IOException {
        zzen();
        zzo(i);
        this.out.write(str);
        return this;
    }

    private final zzfo zzb(int i, int i2, String str) throws IOException {
        int iZzej = zzej();
        if (iZzej != i2 && iZzej != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.zzoh != null) {
            throw new IllegalStateException("Dangling name: " + this.zzoh);
        }
        this.zznp--;
        if (iZzej == i2) {
            zzem();
        }
        this.out.write(str);
        return this;
    }

    private final void zzo(int i) {
        int i2 = this.zznp;
        int[] iArr = this.zzno;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 << 1];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.zzno = iArr2;
        }
        int[] iArr3 = this.zzno;
        int i3 = this.zznp;
        this.zznp = i3 + 1;
        iArr3[i3] = i;
    }

    private final int zzej() {
        int i = this.zznp;
        if (i == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        return this.zzno[i - 1];
    }

    private final void zzq(int i) {
        this.zzno[this.zznp - 1] = i;
    }

    public final zzfo zzbf(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.zzoh != null) {
            throw new IllegalStateException();
        }
        if (this.zznp == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.zzoh = str;
        return this;
    }

    private final void zzek() throws IOException {
        if (this.zzoh != null) {
            int iZzej = zzej();
            if (iZzej == 5) {
                this.out.write(44);
            } else if (iZzej != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            zzem();
            zzq(4);
            zzbh(this.zzoh);
            this.zzoh = null;
        }
    }

    public final zzfo zzbg(String str) throws IOException {
        if (str == null) {
            return zzel();
        }
        zzek();
        zzen();
        zzbh(str);
        return this;
    }

    public final zzfo zzel() throws IOException {
        if (this.zzoh != null) {
            if (this.zzoi) {
                zzek();
            } else {
                this.zzoh = null;
                return this;
            }
        }
        zzen();
        this.out.write("null");
        return this;
    }

    public final zzfo zzc(boolean z) throws IOException {
        zzek();
        zzen();
        this.out.write(z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        return this;
    }

    public final zzfo zzb(double d) throws IOException {
        zzek();
        if (!this.zzng && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        }
        zzen();
        this.out.append((CharSequence) Double.toString(d));
        return this;
    }

    public final zzfo zzd(long j) throws IOException {
        zzek();
        zzen();
        this.out.write(Long.toString(j));
        return this;
    }

    public final zzfo zza(Number number) throws IOException {
        if (number == null) {
            return zzel();
        }
        zzek();
        String string = number.toString();
        if (!this.zzng && (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        }
        zzen();
        this.out.append((CharSequence) string);
        return this;
    }

    @Override // java.io.Flushable
    public final void flush() throws IOException {
        if (this.zznp == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.out.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.out.close();
        int i = this.zznp;
        if (i > 1 || (i == 1 && this.zzno[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.zznp = 0;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x002d  */
    private final void zzbh(String str) throws IOException {
        String str2;
        String[] strArr = zzoe;
        this.out.write("\"");
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < 128) {
                str2 = strArr[cCharAt];
                if (str2 != null) {
                    if (i < i2) {
                        this.out.write(str, i, i2 - i);
                    }
                    this.out.write(str2);
                    i = i2 + 1;
                }
            } else {
                if (cCharAt == 8232) {
                    str2 = "\\u2028";
                } else if (cCharAt == 8233) {
                    str2 = "\\u2029";
                }
                if (i < i2) {
                    this.out.write(str, i, i2 - i);
                }
                this.out.write(str2);
                i = i2 + 1;
            }
        }
        if (i < length) {
            this.out.write(str, i, length - i);
        }
        this.out.write("\"");
    }

    private final void zzem() throws IOException {
        if (this.zzog == null) {
            return;
        }
        this.out.write("\n");
        int i = this.zznp;
        for (int i2 = 1; i2 < i; i2++) {
            this.out.write(this.zzog);
        }
    }

    private final void zzen() throws IOException {
        int iZzej = zzej();
        if (iZzej == 1) {
            zzq(2);
            zzem();
            return;
        }
        if (iZzej == 2) {
            this.out.append(',');
            zzem();
        } else {
            if (iZzej != 4) {
                if (iZzej != 6) {
                    if (iZzej == 7) {
                        if (!this.zzng) {
                            throw new IllegalStateException("JSON must have only one top-level value.");
                        }
                    } else {
                        throw new IllegalStateException("Nesting problem.");
                    }
                }
                zzq(7);
                return;
            }
            this.out.append((CharSequence) this.separator);
            zzq(5);
        }
    }

    static {
        for (int i = 0; i <= 31; i++) {
            zzoe[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = zzoe;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        zzof = strArr2;
        strArr2[60] = "\\u003c";
        String[] strArr3 = zzof;
        strArr3[62] = "\\u003e";
        strArr3[38] = "\\u0026";
        strArr3[61] = "\\u003d";
        strArr3[39] = "\\u0027";
    }
}
