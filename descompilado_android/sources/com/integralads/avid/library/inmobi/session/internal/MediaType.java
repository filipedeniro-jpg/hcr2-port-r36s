package com.integralads.avid.library.inmobi.session.internal;

/* JADX INFO: loaded from: classes2.dex */
public enum MediaType {
    DISPLAY("display"),
    VIDEO("video");

    private final String value;

    MediaType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.value;
    }
}
