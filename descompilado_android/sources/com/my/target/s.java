package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: JsAdClickEvent.java */
/* JADX INFO: loaded from: classes2.dex */
public class s extends q {

    @NonNull
    private final String ax;

    @NonNull
    private final String format;

    @NonNull
    private final String url;

    public s(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        super(v.aM);
        this.ax = str;
        this.format = str2;
        this.url = str3;
    }

    @NonNull
    public String h() {
        return this.ax;
    }

    @NonNull
    public String getFormat() {
        return this.format;
    }

    @NonNull
    public String getUrl() {
        return this.url;
    }
}
