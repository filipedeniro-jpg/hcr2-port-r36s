package com.inmobi.ads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeCtaAsset.java */
/* JADX INFO: loaded from: classes2.dex */
public final class an extends az {

    /* JADX INFO: compiled from: NativeCtaAsset.java */
    static class a extends az.a {
        public a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, String str2, @NonNull String str3, @NonNull String str4, int i9, String str5, String[] strArr, @Nullable ba baVar) {
            super(i, i2, i3, i4, i5, i6, i7, i8, str, str2, str3, str4, baVar);
            this.l = i9;
            this.n = Integer.MAX_VALUE;
            this.m = str5.length() == 0 ? "#ff000000" : str5;
            int iMin = Math.min(strArr.length, 1);
            this.o = new String[iMin];
            System.arraycopy(strArr, 0, this.o, 0, iMin);
        }
    }

    public an(String str, String str2, al alVar, String str3, int i, JSONObject jSONObject) {
        this(str, str2, alVar, str3, new LinkedList(), i, jSONObject);
    }

    public an(String str, String str2, al alVar, String str3, List<NativeTracker> list, int i, JSONObject jSONObject) {
        super(str, str2, "CTA", alVar, str3);
        a(list);
        if (jSONObject != null) {
            this.i = i;
            this.f = jSONObject;
        }
    }
}
