package com.integralads.avid.library.inmobi.session.internal;

/* JADX INFO: loaded from: classes2.dex */
public enum SessionType {
    DISPLAY("display"),
    VIDEO("video"),
    MANAGED_DISPLAY("managedDisplay"),
    MANAGED_VIDEO("managedVideo");

    private final String value;

    SessionType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.value;
    }
}
