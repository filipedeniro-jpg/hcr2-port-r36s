package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: ArrayUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class cb {
    @NonNull
    public static String a(@Nullable String[] strArr) {
        String str = null;
        if (strArr != null) {
            for (String str2 : strArr) {
                str = str == null ? str2 : str + "," + str2;
            }
        }
        return str == null ? "" : str;
    }

    @NonNull
    public static String toString(@Nullable int[] iArr) {
        String str = null;
        if (iArr != null) {
            for (int i : iArr) {
                str = str == null ? "" + i : str + "," + i;
            }
        }
        return str == null ? "" : str;
    }

    private cb() {
    }
}
