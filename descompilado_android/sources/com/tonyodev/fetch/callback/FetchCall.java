package com.tonyodev.fetch.callback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tonyodev.fetch.request.Request;

/* JADX INFO: loaded from: classes2.dex */
public interface FetchCall<T> {
    void onError(int i, @NonNull Request request);

    void onSuccess(@Nullable T t, @NonNull Request request);
}
