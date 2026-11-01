package com.tonyodev.fetch.exception;

/* JADX INFO: loaded from: classes2.dex */
public final class InvalidStatusException extends RuntimeException {
    private final int errorCode;

    public InvalidStatusException(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
