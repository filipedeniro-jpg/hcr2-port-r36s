package com.inmobi.ads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Locale;

/* JADX INFO: compiled from: NativeTextAsset.java */
/* JADX INFO: loaded from: classes2.dex */
public class az extends ak {

    /* JADX INFO: compiled from: NativeTextAsset.java */
    static class a extends al {
        protected int l;
        protected String m;
        protected int n;
        protected String[] o;
        int p;

        public a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, String str2, @NonNull String str3, @NonNull String str4, @Nullable ba baVar) {
            this(i, i2, i3, i4, i5, i6, i7, i8, str, str2, str3, str4, 12, 0, Integer.MAX_VALUE, "#ff000000", new String[]{"none"}, baVar);
        }

        public a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, String str2, @NonNull String str3, @NonNull String str4, int i9, int i10, int i11, @NonNull String str5, String[] strArr, @Nullable ba baVar) {
            super(i, i2, i3, i4, i5, i6, i7, i8, str, str2, str3, str4, baVar);
            this.l = i9;
            this.m = str5.length() == 0 ? "#ff000000" : str5;
            this.n = i11;
            int iMin = Math.min(strArr.length, 4);
            this.o = new String[iMin];
            this.p = i10;
            System.arraycopy(strArr, 0, this.o, 0, iMin);
        }

        public final int h() {
            return this.l;
        }

        public final String i() {
            return this.m.toLowerCase(Locale.US);
        }

        public final String[] j() {
            return this.o;
        }

        @Override // com.inmobi.ads.al
        public final String e() {
            return this.j.toLowerCase(Locale.US);
        }
    }

    public az(String str, String str2, al alVar, String str3) {
        super(str, str2, "TEXT", alVar);
        this.e = str3;
    }

    public az(String str, String str2, String str3, al alVar, String str4) {
        super(str, str2, str3, alVar);
        this.e = str4;
    }
}
