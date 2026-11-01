package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: OvvStat.java */
/* JADX INFO: loaded from: classes2.dex */
public class ao extends as {
    private boolean dr;

    @NonNull
    public static ao v(@NonNull String str) {
        return new ao(aq.a.dK, str);
    }

    private ao(@NonNull String str, @NonNull String str2) {
        super(str, str2);
    }

    public boolean X() {
        return this.dr;
    }

    public void f(boolean z) {
        this.dr = z;
    }
}
