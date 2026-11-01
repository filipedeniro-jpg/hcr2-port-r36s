package com.vungle.warren;

/* JADX INFO: loaded from: classes2.dex */
public interface PlayAdCallback {
    void onAdEnd(String str, boolean z, boolean z2);

    void onAdStart(String str);

    void onError(String str, Throwable th);
}
