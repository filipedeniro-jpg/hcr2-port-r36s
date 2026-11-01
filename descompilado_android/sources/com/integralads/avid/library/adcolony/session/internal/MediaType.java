package com.integralads.avid.library.adcolony.session.internal;

/* JADX INFO: loaded from: classes2.dex */
public enum MediaType {
    DISPLAY("display"),
    VIDEO("video");

    private final String a;

    MediaType(String str) {
        this.a = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.a;
    }
}
