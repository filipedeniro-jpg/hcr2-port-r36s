package com.integralads.avid.library.adcolony.session.internal;

/* JADX INFO: loaded from: classes2.dex */
public enum SessionType {
    DISPLAY("display"),
    VIDEO("video"),
    MANAGED_DISPLAY("managedDisplay"),
    MANAGED_VIDEO("managedVideo");

    private final String a;

    SessionType(String str) {
        this.a = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.a;
    }
}
