package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: JsAdStartEvent.java */
/* JADX INFO: loaded from: classes2.dex */
public class t extends q {

    @NonNull
    private final String[] ay;

    @NonNull
    private final String format;

    public t(@NonNull String[] strArr, @NonNull String str) {
        super(v.aH);
        this.format = str;
        this.ay = strArr;
    }

    @NonNull
    public String getFormat() {
        return this.format;
    }

    @NonNull
    public String[] i() {
        return this.ay;
    }
}
