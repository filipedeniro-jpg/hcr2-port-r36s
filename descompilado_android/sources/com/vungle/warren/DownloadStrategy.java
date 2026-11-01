package com.vungle.warren;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadStrategy {

    public interface VerificationCallback {
        void onResult(boolean z);
    }

    void isApplicationAvailable(String str, VerificationCallback verificationCallback);
}
