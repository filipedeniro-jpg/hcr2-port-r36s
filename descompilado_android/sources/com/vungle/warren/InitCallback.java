package com.vungle.warren;

/* JADX INFO: loaded from: classes2.dex */
public interface InitCallback {
    void onAutoCacheAdAvailable(String str);

    void onError(Throwable th);

    void onSuccess();
}
