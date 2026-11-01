package com.tonyodev.fetch.request;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class Header {
    private final String header;
    private final String value;

    public Header(@NonNull String str, @Nullable String str2) {
        if (str == null) {
            throw new NullPointerException("header cannot be null");
        }
        if (str.contains(":")) {
            throw new IllegalArgumentException("header may not contain ':'");
        }
        str2 = str2 == null ? "" : str2;
        this.header = str;
        this.value = str2;
    }

    @NonNull
    public String getHeader() {
        return this.header;
    }

    @NonNull
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return this.header + ":" + this.value;
    }
}
